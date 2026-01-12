package mobile.screens;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

import mobile.data.CategoriesForAppTests;

public class CatalogScreen {

    private final SelenideElement categoryTileTitle = $(id("ru.x5.perekrestok.darkstore:id/tvName"));
    private final SelenideElement innerCategoryTitle = $(id("ru.x5.perekrestok.darkstore:id/tvChildCatalog"));

    public void mainCategoriesAreDisplayd(CategoriesForAppTests categoryName) {
        categoryTileTitle.shouldBe(visible);
        categoryTileTitle.shouldHave(text(String.valueOf(categoryName)));
    }

    public void goToCategory(String categoryName) {
        categoryTileTitle.shouldHave(text(categoryName)).click();
    }

    public CatalogScreen innerCategoryTitleHasText(String innerTitleText) {
        innerCategoryTitle.shouldHave(text(innerTitleText));
        return this;
    }
}
