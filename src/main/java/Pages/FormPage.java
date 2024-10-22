package Pages;

import Utils.AndriodActions;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import io.appium.java_client.android.Activity;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class FormPage extends AndriodActions {
    AndroidDriver  driver;
    public FormPage (AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
    private WebElement nameField;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
    private WebElement femaleOption;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
    private WebElement maleOption;

    @AndroidFindBy(id = "android:id/text1")
    private  WebElement selectCountry;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private  WebElement shopButton;

    public void setActivity() {

        Activity activity = new Activity("com.google.android.apps.nexuslauncher", "com.google.android.apps.nexuslauncher.NexusLauncherActivity");
        //driver.startActivity(activity);
        ((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of("intent","com.google.android.apps.nexuslauncher/com.google.android.apps.nexuslauncher.NexusLauncherActivity"
        ));
    }

    public  void setNameField (String name){
        nameField.sendKeys(name);
        driver.hideKeyboard();
    }
    public void setGender(String gender){
        if(gender.contains("female"))
        femaleOption.click();
        else{
            maleOption.click();
        }
    }
    public void setSelectCountry(String countryName){
        selectCountry.click();
        scrollToText(countryName);
        driver.findElement(By.xpath("//android.widget.TextView[@text='"+countryName+"']")).click();
    }

    public ProductCatalogue clickShopButton(){
        shopButton.click();
         return new ProductCatalogue(driver);
    }
}
