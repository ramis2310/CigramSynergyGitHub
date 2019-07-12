package costco;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Ilkin {
    WebDriver driver;
    @BeforeMethod

    public  void setUp(){

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();


        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test(priority=1)


    public  void  costcoCustomerServicesnumbers() throws InterruptedException, IOException {
        driver.get("https://www.costco.com/");


        WebElement customerServiceLink = driver.findElement(By.id("customer-service-link"));
        customerServiceLink.click();

        Thread.sleep(5000);
        WebElement callUsLink = driver.findElement(By.linkText("Call Us"));
        callUsLink.click();
        Thread.sleep(5000);

        WebElement giveUsCall = driver.findElement(By.linkText("Give Us a Call"));
        giveUsCall.click();
        Thread.sleep(5000);


    }

    @Test(priority=2)
    public  void costcoCustomerServiceSearchField() throws InterruptedException{
        driver.get("https://www.costco.com/");

        WebElement customerServiceLink = driver.findElement(By.id("customer-service-link"));
        customerServiceLink.click();
        Thread.sleep(5000);
        WebElement searchField = driver.findElement(By.id("rn_SourceSearchField_9_SearchInput"));
        searchField.sendKeys("info" + Keys.ENTER);


    }

    @Test(priority=3)

    public  void hoursAndClosures()throws InterruptedException{
        driver.get("https://www.costco.com/");

        WebElement customerServiceLink = driver.findElement(By.id("customer-service-link"));
        customerServiceLink.click();
        Thread.sleep(5000);

        WebElement hoursLink =  driver.findElement(By.linkText("Hours and Closures"));
        hoursLink.click();

        Thread.sleep(5000);

        driver.close();


    }
}

