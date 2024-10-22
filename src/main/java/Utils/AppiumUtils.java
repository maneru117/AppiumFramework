package Utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class AppiumUtils {
    AppiumDriverLocalService service;


    public Double getFormattedAmount(String amount) {
        // Assuming the amount starts with a currency symbol and the price is numeric after that
        Double price = Double.parseDouble(amount.replaceAll("[^\\d.]", ""));  // Remove non-numeric characters
        return price;
    }

    public List<HashMap<String, String>> getJsonData(String jasonFilePath) throws IOException {
        //System.getProperty("user.dir")+ "\\src\\main\\java\\testData\\eCommerce\\eCommers.json"
        String jsonContent = FileUtils.readFileToString(new File(jasonFilePath), StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {
                });
        return data;
    }

    public AppiumDriverLocalService startAppiumService(String ipAddress, int port) {
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:\\Users\\Arya\\.appium\\node_modules\\appium-uiautomator2-driver\\build\\index.js"))
                .withIPAddress(ipAddress)
                .usingPort(port)
                .build();
        //return service;

    try {
        // Manually handle the timeout with custom logic
        service.start();
        long startTime = System.currentTimeMillis();
        long timeout = 60000; // 60 seconds timeout

        // Poll until the server is running or timeout is reached
        while (!service.isRunning()) {
            if (System.currentTimeMillis() - startTime > timeout) {
                throw new RuntimeException("Appium server did not start within the timeout period.");
            }
            Thread.sleep(1000); // Wait 1 second before checking again
        }

    } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("Failed to start Appium server: " + e.getMessage());
    }

    return service;
}

    public void waitForElementToAppear(WebElement ele, AndroidDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains(ele, "text", "Cart"));
    }

    public String getScreenShotPath(String testCaseNAme, AppiumDriver driver) throws IOException {
         File Source= driver.getScreenshotAs(OutputType.FILE);
         String destinationFile= System.getProperty("user.dir")+"\\reports"+testCaseNAme+".png";
         FileUtils.copyFile(Source, new File(destinationFile));
         return destinationFile;
    }
}
