package Pages;

import Utils.AndriodActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AndriodActions {
    AndroidDriver driver;


    @AndroidFindBy(xpath = "//android.widget.TextView[@text= 'ADD TO CART']")
    public List<WebElement> addToCart;

    /* @AndroidFindBy(xpath = "com.androidsample.generalstore:id/productAddCart")
     private WebElement product1;

     @AndroidFindBy(xpath = "com.androidsample.generalstore:id/productAddCart")
     public WebElement product2;
 */
    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    public WebElement cartButton;

    public ProductCatalogue(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void addItemToCartByIndex(int index) {
        addToCart.get(index).click();
    }


    public CartPage addToCartButton() {
        cartButton.click();
        return new CartPage(driver);
    }

}
