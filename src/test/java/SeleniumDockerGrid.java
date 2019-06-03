import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class SeleniumDockerGrid {

    private static WebDriver driver;

    @BeforeClass
    public static void openBrowser() throws MalformedURLException {
        DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();

        if(System.getProperty("webdriver.chrome.driver") != null)
            //driver = new ChromeDriver();
            driver = new RemoteWebDriver(new URL("http://172.22.0.2:4444/wd/hub"), chromeCapabilities);

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

    }
}
