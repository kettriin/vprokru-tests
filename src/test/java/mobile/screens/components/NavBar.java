package mobile.screens.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class NavBar {

    private final SelenideElement cartIcon = $(id("ru.x5.perekrestok.darkstore:id/tab_cart"));

    @Step("Переход на экран корзины по тапу в навигационном баре")
    public void goToCart() {
        cartIcon.click();
    }
}
