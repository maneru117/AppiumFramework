package testUtils;

import Pages.FormPage;
import Utils.AndriodActions;
import Utils.AppiumUtils;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class BaseTest extends AppiumUtils {
    public AndroidDriver driver;
    AppiumDriverLocalService service;
    public FormPage formPage;

    @BeforeClass(alwaysRun = true)
    public void ConfigureAppium() throws IOException {

        Properties prop= new Properties();
        FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\data\\data.properties");
        prop.load(fis);

        String ipAddress= prop.getProperty("ipAddress");
                String port = prop.getProperty("port");
        service = startAppiumService(ipAddress, Integer.parseInt(port));

        service.start();
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("dheerajmaneru");
        options.setChromedriverExecutable("C:\\\\WORK\\\\chrome-win64\\\\chrome.exe");
        // options.setApp("C:\\Users\\Arya\\Desktop\\Appium\\src\\main\\resources\\ApiDemos-debug.apk");
        options.setApp("C:\\Users\\Arya\\Desktop\\Appium\\src\\main\\resources\\General-Store.apk");
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
        // AppiumDriverLocalServices service = new AppiumDriverLocalServices()
        // driver = new AndroidDriver(service.getUrl(), options);
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));
        formPage = new FormPage(driver);
    }


    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
        service.stop();

    }
}
