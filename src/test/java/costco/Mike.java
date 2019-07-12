package costco;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Mike {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void getLocationsByZip() throws InterruptedException, IOException {
        driver.get("https://www.costco.com/");
        WebElement locationsLink = driver.findElement(By.linkText("Locations"));
        locationsLink.click();
        driver.navigate().refresh();

        boolean reachedSuccessful = false;
        WebElement inputZipCodeBox = driver.findElement(By.cssSelector("#search-warehouse"));
        inputZipCodeBox.sendKeys("60630");
        Thread.sleep(2000);
        inputZipCodeBox.sendKeys(Keys.ENTER);
        //driver.findElement(By.xpath("(//button[@class='btn btn-primary'])[1]")).click();

        Thread.sleep(20000);
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String pathName = "/Users/mykhailoselivanov/Desktop/CigramSynergyCostco.png";
        FileUtils.copyFile(scrFile, new File(pathName));
        reachedSuccessful = true;
        Assert.assertTrue(reachedSuccessful, "Code didn't reach this point, scrnshot is not saved!");
    }

    @Test (priority = 1)
    public void verifyClosestCostcoLocation() throws InterruptedException {
        driver.get("https://www.costco.com/");
        WebElement locationsLink = driver.findElement(By.linkText("Locations"));
        locationsLink.click();
        driver.navigate().refresh();
        Thread.sleep(3000);
        WebElement inputZipCodeBox = driver.findElement(By.cssSelector("#search-warehouse"));
        inputZipCodeBox.clear();
        inputZipCodeBox.sendKeys("60630");
        Thread.sleep(2000);
        inputZipCodeBox.sendKeys(Keys.ENTER);

        Thread.sleep(20000);
        String firstStoreAddress = driver.findElement(By.xpath
                ("//div[@class='block col-sm-12 col-md-7 col-lg-8']//span")).getText();

        Thread.sleep(3000);
        driver.get("https://www.google.com/");
        WebElement googleSearchBox = driver.findElement(By.name("q"));
        googleSearchBox.sendKeys(firstStoreAddress + " " + "60630" + Keys.ENTER);

        WebElement firstLinkFromSearch = driver.findElement(By.xpath("//body//h3"));
        firstLinkFromSearch.click();

        Assert.assertTrue(driver.getTitle().contains("Costco"),
                "Title does not contain Costco, location is invalid");
    }

    @Test (priority = 2)
    public void navigateToClosestCostcoGasPrices () throws InterruptedException {
        driver.get("https://www.costco.com/");
        WebElement locationsLink = driver.findElement(By.linkText("Locations"));
        locationsLink.click();
        driver.navigate().refresh();
        Thread.sleep(3000);
        WebElement inputZipCodeBox = driver.findElement(By.cssSelector("#search-warehouse"));
        inputZipCodeBox.clear();
        inputZipCodeBox.sendKeys("60630");
        Thread.sleep(2000);
        inputZipCodeBox.sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        boolean reachedSuccessful = false;

        Thread.sleep(20000);
        WebElement getStoreDetails = driver.findElement(By.linkText("Store Details"));
        getStoreDetails.click();

        Thread.sleep(2000);
        WebElement gasStationDetails = driver.findElement(By.linkText("Gas Station"));
        gasStationDetails.click();
        reachedSuccessful = true;

        Assert.assertTrue(reachedSuccessful,
                "The code did not reach this point,  " +
                        "navigating to gas station gas prices was unsuccesfull");
    }


    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }

}