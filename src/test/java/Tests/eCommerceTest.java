package Tests;

import Pages.CartPage;
import Pages.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testUtils.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class eCommerceTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void preSetup() {
        formPage.setActivity();
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "\\src\\main\\java\\testData\\eCommerce\\eCommers.json");
        //return new Object[][]{{"dheeraj","Female", "Argentina"},{"maneru","Mmle", "Argentina"}};
        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }

    @Test(dataProvider = "getData", groups = {"smoke"})
    public void fillForm(HashMap<String, String> input) throws InterruptedException {
        formPage.setNameField(input.get("name"));
        formPage.setGender(input.get("gender"));
        formPage.setSelectCountry(input.get("country"));
        ProductCatalogue productCatalogue = formPage.clickShopButton();
        //  productCatalogue.setSelectProduct1("Jordan 6 Rings");
        productCatalogue.addItemToCartByIndex(0);
        productCatalogue.addItemToCartByIndex(0);
        CartPage cartPage = productCatalogue.addToCartButton();
        double totalSum = cartPage.getProductSum();
        double totalAmountDisplayed = cartPage.getTotalAmountDisplayed();
        Assert.assertEquals(totalSum, totalAmountDisplayed);
        cartPage.setAcceptButton();
        cartPage.submitOrder();
       // driver.quit();

    }


}
