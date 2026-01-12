package mobile.screens;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.id;

import io.qameta.allure.Step;
import mobile.data.CategoriesForAppTests;

public class CatalogScreen {

    private final ElementsCollection categoryTitles = $$(id("ru.x5.perekrestok.darkstore:id/tvName"));
    private final SelenideElement innerCategoryTitle = $(id("ru.x5.perekrestok.darkstore:id/tvChildCatalog"));

    @Step("Проверить, что категория '{categoryName}' отображается в каталоге")
    public void checkCategoryIsDisplayed(CategoriesForAppTests category) {
        categoryTitles
                .findBy(text(category.categoryName))
                .shouldBe(visible);
    }

    @Step("Перейти в категорию '{categoryName}'")
    public void goToCategory(String categoryName) {
        categoryTitles
                .findBy(text(categoryName))
                .shouldBe(visible)
                .click();
    }

    @Step("Проверить заголовок подкатегории: {expectedText}")
    public CatalogScreen innerCategoryTitleHasText(String expectedText) {
        innerCategoryTitle.shouldHave(text(expectedText));
        return this;
    }
}
