package web.pages.components;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class PromoModal {

    private final SelenideElement promoModalCloseButton = $(".UiModalCloseButton_root__Yv8dT");
    private final SelenideElement promoModalOverlay = $(".UiModalContent_open__twBBW");

    public void closePromoModalIfPresent() {
        if (promoModalOverlay.exists() && promoModalOverlay.isDisplayed()) {
            try {
                promoModalCloseButton.shouldBe(visible).click();
                promoModalOverlay.should(disappear);
            } catch (Exception e) {
                actions().sendKeys(Keys.ESCAPE).perform();
                sleep(1000);
            }
        }
    }
}
