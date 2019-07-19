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

public class Dashboard {

    private static WebDriver driver;
    //private static Logger Log = Logger.getLogger(Log.class.getName());//
    private static Logger logger = LoggerFactory.getLogger(SeleniumDockerGrid.class);

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
    public void verifyHTACCESSAuth() throws MalformedURLException {
        URL dashboardURL = this.getURL();
        System.out.println(dashboardURL);
        driver.get(dashboardURL.toString());
        driver.manage().window().maximize();

        System.out.println(dashboardURL.toString());

        //driver.navigate().to(dashboardURL.toString());

        WebElement body = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id("wrapper_body")));

    }

    @Test
    public void verifyInstallationChart() throws MalformedURLException, InterruptedException {
        URL dashboardURL = this.getURL();
        //driver.get(dashboardURL.toString());


        WebElement svg = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id("wrapper_body")));
        //Relative xpath: //*[@class='featured-box']//*[text()='Testing']
        String xpath = "//*[name()='svg']//*[name()='g' and contains(@class, 'highcharts-legend-item')]//*[contains(text(), 'Android')]";
        WebElement installChart  = (new WebDriverWait(driver, 90))
        .until(
            ExpectedConditions.presenceOfElementLocated( 
                By.xpath(xpath))
                );

        //JavascriptExecutor is an interface
	 	JavascriptExecutor jse = (JavascriptExecutor) driver;
	 	Object o = jse.executeScript("return document.getElementsByTagName('path')[0].getAttribute('d')");
	 	String s = (String) o;
	 	System.out.println(s);
	 	System.out.println("___");
	 	
	 	
	 	o = jse.executeScript("return document.title");
	    s=(String) o;
	    System.out.println(s);
        jse.executeScript("window.scrollBy(0,1000)", "");
        Thread.sleep(3000); //pause for 3 seconds

    }


    @Test
    public void verifyRegistrationChart() throws MalformedURLException, InterruptedException {
        URL dashboardURL = this.getURL();

        WebElement svg = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id("wrapper_body")));
        //Relative xpath: //*[@class='featured-box']//*[text()='Testing']
        String xpath = "//*[name()='svg']//*[name()='g' and contains(@class, 'highcharts-legend-item')]//*[contains(text(), 'Mobile')]";
        WebElement installChart  = (new WebDriverWait(driver, 90))
        .until(
            ExpectedConditions.presenceOfElementLocated( 
                By.xpath(xpath))
                );

        Thread.sleep(500); //pause for 3 seconds

    }

    @Test
    public void verifyRenewalChart() throws MalformedURLException, InterruptedException {
        URL dashboardURL = this.getURL();

        WebElement svg = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id("wrapper_body")));
        //Relative xpath: //*[@class='featured-box']//*[text()='Testing']
        String xpath = "//*[name()='svg']//*[name()='g' and contains(@class, 'highcharts-legend-item')]//*[contains(text(), 'Debit Card') or contains(text(), 'Credit Card')]";
        WebElement installChart  = (new WebDriverWait(driver, 90))
        .until(
            ExpectedConditions.presenceOfElementLocated( 
                By.xpath(xpath))
                );

        Thread.sleep(500); //pause for 3 seconds

    }

    @Test
    public void verifySubscriptionChart() throws MalformedURLException, InterruptedException {
        //URL dashboardURL = this.getURL();

        WebElement svg = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id("wrapper_body")));
        //Relative xpath: //*[@class='featured-box']//*[text()='Testing']
        String xpath = "//*[name()='svg']//*[name()='g' and contains(@class, 'highcharts-column-series')]//*[contains(text(), 'Paytm')]";
        WebElement installChart  = (new WebDriverWait(driver, 90))
        .until(
            ExpectedConditions.presenceOfElementLocated( 
                By.xpath(xpath))
                );

        Thread.sleep(500); //pause for 3 seconds

    }

   
}
