package Tests;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testUtils.BaseTest;

public class eCommerce1 extends BaseTest{

   // AndroidDriver driver;
    @BeforeMethod
    public void preSetup() {

        //Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
        //driver.startActivity(activity);
        ((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of("intent", "com.androidsample.generalstore/com.androidsample.generalstore.SpalshActivity"

        ));
    }

    @Test
    public void fillForm() {

        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4000));
        //driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Dheeraj");
        driver.hideKeyboard();
        driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"Aruba\"));"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='Aruba']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        String toastMsg = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
        Assert.assertEquals(toastMsg, "Please enter your name");
    }

    @Test
    public void fillForm_Positive() {
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4000));
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Dheeraj");
        driver.hideKeyboard();
        driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"Aruba\"));"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='Aruba']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        //Assert.assertTrue(driver.findElement(By.xpath("(//android.widget.Toast)[1]")).size()<1);


    }
}

