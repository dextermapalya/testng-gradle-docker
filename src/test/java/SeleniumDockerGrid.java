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

public class SeleniumDockerGrid {

    private static WebDriver driver;
    //private static Logger Log = Logger.getLogger(Log.class.getName());//
    private static Logger logger = LoggerFactory.getLogger(SeleniumDockerGrid.class);

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
    }

    @AfterClass
    public static void closeBrowser(){
        driver.quit();
    }

    @Test()
    public void browserInitTest() {
        driver.get("http://www.google.com/");
        Assert.assertEquals(driver.getTitle(),"Google");
        logger.info("user is opening main page");


    }


    @Test
    public void openGoogle() {
        WebDriver webDriver = driver; //getDriver();

        webDriver.navigate().to("http://www.google.com");
        Assert.assertEquals("Google", webDriver.getTitle());
    }

    @Test
    public void enterGoogleSearchAndViewResults() {
        WebDriver webDriver = driver; //getDriver();

        By searchLocator = By.cssSelector("input[value='Google Search']");
        webDriver.navigate().to("http://www.google.com");
        WebElement searchText = driver.findElement(By.name("q"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        //WebElement searchText = webDriver.findElement(By.cssSelector("input[title=Search]"));
        searchText.sendKeys("sunnxt portal");
        searchText.submit();

        //Wait until the google page shows the result
        WebElement myDynamicElement = (new WebDriverWait(driver, 20)).until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));

        List<WebElement> findElements = driver.findElements(By.xpath("//*[@id='rso']//h3/a"));
        
       System.out.printf( "-----");
       System.out.println( findElements.size() );
       System.out.println( "+++++");
       for(int i=0;i<findElements.size();i++) {
           findElements= driver.findElements(By.xpath("//*[@id='rso']//h3/a"));    
            System.out.println(findElements.get(i)); 
       }



        //String third_link = findElements.get(2).getAttribute("href");
        //System.out.println(third_link);
        //driver.navigate().to(third_link);


        //WebElement searchButton = webDriver.findElement(searchLocator);
        //searchButton.click();
        //Assert.assertEquals("hi - Google Search", webDriver.getTitle());
    }

    
}
