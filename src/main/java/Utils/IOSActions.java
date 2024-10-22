package Utils;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.HashMap;

public class IOSActions {
    IOSDriver driver;

    public IOSActions() {
        this.driver = driver;
    }

    public void performLongPress(WebElement element) {
        TouchAction touchAction = new TouchAction(driver);

        touchAction.longPress(LongPressOptions.longPressOptions()
                        .withElement(ElementOption.element(element))
                        .withDuration(Duration.ofSeconds(2)))
                .release()
                .perform();
    }
}
