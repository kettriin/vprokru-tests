package web.tests;

import web.data.Categories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import web.pages.MainPage;

public class MainPageTests extends TestBase{

    MainPage mainPage = new MainPage();

    @Test
    @DisplayName("Логотип отображается и ведет на главную страницу при клике")
    @Tag("WEB")
    void logoIsVisibleAndClickable() {
        mainPage.waitForLoad();
        mainPage.verifyLogoIsVisibleAndClickable();
        mainPage.clickLogo();
        mainPage.verifyCurrentUrl("https://www.vprok.ru/");
    }

    @ValueSource(strings = {
            "Зоотовары", "Детские товары", "Красота и здоровье", "О доставке"
    })
    @ParameterizedTest(name = "В шапке отображается информационный раздел {0}")
    @Tag("WEB")
    void infoBlocksAreVisibleInTheHeader(String infoBlockHeader) {
        mainPage.verifyInfoBlockIsVisible(infoBlockHeader);
    }

    @CsvSource({
            "хлеб , Хлеб",
            "молоко 3 процента , Молоко ",
            "ВОДА , Вода"
    })
    @ParameterizedTest(name = "В поисковом запросе {0} отображается карточка товара {1}")
    @Tag("WEB")
    void searchQueryContainsProductCard(String searchQuery, String searchedProductName) {
        mainPage.searchForProduct(searchQuery);
        mainPage.verifyProductIsFound(searchedProductName);
    }

    @ParameterizedTest(name = "В каталоге присутствует категория {0}")
    @EnumSource(value = Categories.class)
    @Tags({
            @Tag("WEB"),
            @Tag("SMOKE")
    })
    void mainCategoriesInCatalogAreDisplayed(Categories categories) {
        mainPage.waitForLoad();
        mainPage.openCatalog();
        mainPage.verifyCategoryInCatalog(categories);
    }

    @ParameterizedTest
    @DisplayName("Можно изменить город через селектор")
    @CsvSource({
            "Санкт-Петербург и область, Санкт-Петербург и область",
            "Тверская обл., Тверская обл."
    })
    @Tag("WEB")
    void canChangeCity(String cityToSelect, String expectedCity) {
        mainPage.waitForLoad();
        mainPage.openCitySelector();
        mainPage.selectCity(cityToSelect);
        mainPage.verifySelectedCity(expectedCity);
    }

    @Test
    @DisplayName("Кнопка входа/регистрации отображается и кликабельна")
    @Tag("WEB")
    void loginButtonIsFunctional() {
        mainPage.waitForLoad();
        mainPage.verifyLoginButtonIsVisible();
        mainPage.clickLoginButton();
        mainPage.authModalIsVisible();
    }
}
