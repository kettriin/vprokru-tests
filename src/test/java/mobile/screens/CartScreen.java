package mobile.screens;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class CartScreen {

    private final SelenideElement emptyCartHeader = $(id("ru.x5.perekrestok.darkstore:id/tvError"));

    @Step("Отображение заголовка \"Корзина пуста\" ")
    public CartScreen emptyCartHeader() {
        emptyCartHeader.shouldBe(visible);
        emptyCartHeader.shouldHave(text("Корзина пуста"));
        return this;
    }
}
