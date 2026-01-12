package mobile.screens;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class GettingStartedScreen {

    private final SelenideElement laterButton = $(id("ru.x5.perekrestok.darkstore:id/btnLater"));

    @Step("Отказ от включения уведомлений")
    public GettingStartedScreen dismissNotifications() {
        laterButton.click();
        return this;
    }
}
