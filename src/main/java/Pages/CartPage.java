package Pages;

import Utils.AndriodActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.internal.BaseTestMethod;

import java.util.List;

public class CartPage extends AndriodActions {
    AndroidDriver driver;

    public CartPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    private List<WebElement> productPrice;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement totalAmount;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
    private WebElement termButton;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement acceptButton;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
    private WebElement proceed;

    @AndroidFindBy(className = "android.widget.CheckBox")
    private WebElement checkBox;

    public List<WebElement> getProductPrice() {
        return productPrice;
    }

    public double getProductSum() {
        int count = productPrice.size();
        double totalsum = 0;
        for (int i = 0; i < count; i++) {
            String amountString = productPrice.get(i).getText();
            // Call the correctly defined getFormattedAmount method here
            Double price = getFormattedAmount(amountString);
            totalsum = totalsum + price;
        }
        return totalsum;
    }

    public Double getTotalAmountDisplayed() {
        return getFormattedAmount(totalAmount.getText());
    }

    public void setAcceptButton() {
        longPressGesture(termButton);
        acceptButton.click();
    }


    public void submitOrder() {
        checkBox.click();
        proceed.click();
    }
}
