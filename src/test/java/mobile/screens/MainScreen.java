package mobile.screens;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class MainScreen {

    private final SelenideElement darkstoreItemButton = $(id("ru.x5.perekrestok.darkstore:id/tvItemButtonText"));
    private final SelenideElement catalogueButton = $(byText("Каталог"));

    @Step("Кнопка {0} отображается при запуске")
    public MainScreen darkstoreItemButtonDisplayed(String darkstoreItem) {
        darkstoreItemButton.shouldBe(visible).shouldHave(text(darkstoreItem));
        return this;
    }

    @Step("Клик по кнопке Каталог")
    public void catalogOpen() {
        catalogueButton.click();
    }
}
