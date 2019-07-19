package test.java;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.util.List;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;  //import ChromeOptions

/* webdriver manager */
import io.github.bonigarcia.wdm.WebDriverManager;
import static java.lang.System.out;
//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* List of chromedrivers online 
https://chromedriver.storage.googleapis.com/
*/

public class DashboardDateSelector {

    private static WebDriver driver;
    //private static Logger Log = Logger.getLogger(Log.class.getName());//
    private static Logger logger = LoggerFactory.getLogger(DashboardDateSelector.class);

    /* return the protected url 
    * selenium does not accept encrypted passwords
    * the passwords must be converted to hex value before being invoked
    */
    public static URL getURL() throws MalformedURLException {
        //https://support.brightcove.com/special-characters-usernames-and-passwords
        //convert special charact to hex equivalent
        String URL = "dashboard.sunnxt.com:81";
        String authURL = "http://admin:a%25d%5En%237%2518%2F20" + "@" + URL;
        return new URL(authURL);
    }

    @BeforeClass
    public static void openBrowser() throws MalformedURLException {
        
        //WebDriverManager.chromedriver().targetPath("/tmp").setup();
        //Log.info("TESTING LOGS....");

        //WebDriverManager.chromedriver().setup();
        WebDriverManager.chromedriver().version("74.0.3729.6").targetPath("/tmp").setup();
        DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
        final ChromeOptions chromeOptions = new ChromeOptions();
        String driverPath = "/workspace/testng-gradle/browsers/chrome/76/chromedriver";
        //String driverPath = System.getProperty("user.dir") + "/browsers/chrome/chromedriver";
        //chromeOptions.setBinary("/usr/bin/chromium-browser");
        //chromeOptions.setBinary(driverPath);
        //chromeOptions.addArguments("--headless");
        chromeCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        chromeCapabilities.setBrowserName("chrome");



        //chromeCapabilities.setVersion("2.24");
        //String driverPath = System.getProperty("user.dir") + "/build/webdriver/chromedriver/chromedriver";
        //System.setProperty("webdriver.chrome.driver", driverPath);


        if(System.getProperty("webdriver.chrome.driver") != null)
            driver = new ChromeDriver();
            
            //driver = new RemoteWebDriver(new URL("http://172.26.0.2:4444/wd/hub"), chromeCapabilities);
            //driver = new RemoteWebDriver(new URL("http://selenium-hub:4444/wd/hub"), chromeCapabilities);
        else if(System.getProperty("phantomjs_binary_path") != null)
            driver = new PhantomJSDriver();
        else
            throw new RuntimeException("Unknown web driver specified.");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.get(myurl.toString());

    }

    @AfterClass
    public static void closeBrowser(){
        driver.quit();
    }


    @Test
    public void verifyPicker() throws MalformedURLException,  InterruptedException {
        URL dashboardURL = this.getURL();
        driver.navigate().to(dashboardURL.toString());

        //driver.get(dashboardURL.toString());

        //URL dashboardURL = this.getURL();
        WebElement svg = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id("wrapper_body")));

        //String s=driver.findElement(By.xpath("//input[@placeholder='Gender']")).getAttribute("placeholder"); 
        Thread.sleep(500);
        String xpath = "//input[@placeholder = 'Select a Date']";
        WebElement datePicker  = (new WebDriverWait(driver, 90))
        .until(
            ExpectedConditions.presenceOfElementLocated( 
                By.xpath(xpath))
                );
        datePicker.click();
        Thread.sleep(1000);

        //datePicker.sendKeys("2019-07-18");
        xpath = "//div[contains(@class, 'react-datepicker__month-container')]//div[contains(@class, 'react-datepicker__week')]//div[contains(@class, 'react-datepicker__day--017')]";
        datePicker  = (new WebDriverWait(driver, 90))
        .until(
            ExpectedConditions.presenceOfElementLocated( 
                By.xpath(xpath))
                );
        datePicker.click();
        Thread.sleep(5000);



    }

    @Test
    public void verifyDatePickerPreviousMonth() throws MalformedURLException, InterruptedException {
        URL dashboardURL = this.getURL();
        driver.navigate().to(dashboardURL.toString());
        WebElement svg = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id("wrapper_body")));
        String xpath = "//input[@placeholder = 'Select a Date']";
        WebElement datePicker = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        datePicker.click();
        Thread.sleep(500);

        xpath = "//button[contains(@class, 'react-datepicker__navigation--previous')]";
        //xpath = "//div[contains(@class, 'react-datepicker__month-container')]//div[contains(@class, 'react-datepicker__week')]//div[contains(@class, 'react-datepicker__day--017')]";
         datePicker  = (new WebDriverWait(driver, 90))
        .until(
            ExpectedConditions.presenceOfElementLocated( 
                By.xpath(xpath))
                );
        datePicker.click();
        Thread.sleep(1000); //pause before performing the next click
        xpath = "//div[contains(@class, 'react-datepicker__month-container')]//div[contains(@class, 'react-datepicker__week')]//div[contains(@class, 'react-datepicker__day--017')]";
        datePicker  = (new WebDriverWait(driver, 90))
        .until(
            ExpectedConditions.presenceOfElementLocated( 
                By.xpath(xpath))
                );
        datePicker.click();
        Thread.sleep(5000);


    }

}
