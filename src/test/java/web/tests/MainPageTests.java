package web.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import web.data.CategoriesForWebTests;
import web.pages.MainPage;

import static io.qameta.allure.Allure.step;

@DisplayName("Тесты на блоки и содержание главной страницы")
@Tag("MAIN_WEB")
public class MainPageTests extends TestBase {

    MainPage mainPage = new MainPage();
    String mainPageUrl = "https://www.vprok.ru/";

    @Test
    @DisplayName("Логотип отображается и ведет на главную страницу при клике")
    void logoIsVisibleAndClickable() {
        step("Отображается главная страница", () -> mainPage.waitForLoad());
        step("Лого сайта отображается на главной", () -> mainPage.verifyLogoIsVisibleAndClickable());
        step("Клик по лого", () -> mainPage.clickLogo());
        step("Редирект на главную", () ->
                mainPage.verifyCurrentUrl(mainPageUrl));
    }

    @ValueSource(strings = {
            "Зоотовары", "Детские товары", "Красота и здоровье", "О доставке"
    })
    @ParameterizedTest(name = "В шапке отображается информационный раздел {0}")
    void infoBlocksAreVisibleInTheHeader(String infoBlockHeader) {
        step("На главной отображается инфоблок",
                () -> mainPage.verifyInfoBlockIsVisible(infoBlockHeader));
    }

    @CsvSource({
            "хлеб , Хлеб",
            "молоко 3 процента , Молоко ",
            "ВОДА , Вода"
    })
    @ParameterizedTest(name = "В поисковом запросе {0} отображается карточка товара {1}")
    void searchQueryContainsProductCard(String searchQuery, String searchedProductName) {
        step("Выполнить поисковый запрос", () -> mainPage.searchForProduct(searchQuery));
        step("Найден продукт, соответствующий поиск",
                () -> mainPage.verifyProductIsFound(searchedProductName));
    }

    @ParameterizedTest(name = "В каталоге присутствует категория {0}")
    @EnumSource(value = CategoriesForWebTests.class)
    void mainCategoriesInCatalogAreDisplayed(CategoriesForWebTests categories) {
        step("Отображается главная страница", () -> mainPage.waitForLoad());
        step("Открыть каталог", () -> mainPage.openCatalog());
        step("В каталоге отображается категория",
                () -> mainPage.verifyCategoryInCatalog(categories));
    }

    @ParameterizedTest
    @DisplayName("Можно изменить город через селектор")
    @CsvSource({
            "Санкт-Петербург и область, Санкт-Петербург и область",
            "Тверская обл., Тверская обл."
    })
    void canChangeCity(String cityToSelect, String expectedCity) {
        step("Отображается главная страница", () -> mainPage.waitForLoad());
        step("Открыть список доступных городов", () -> mainPage.openCitySelector());
        step("Выбрать город из списка", () -> mainPage.selectCity(cityToSelect));
        step("На главной отображается выбранный город",
                () -> mainPage.verifySelectedCity(expectedCity));
    }

    @Test
    @DisplayName("Кнопка входа/регистрации отображается и кликабельна")
    void loginButtonIsFunctional() {
        step("Отображается главная страница", () -> mainPage.waitForLoad());
        step("Кнопка входа отображается на главной", () -> mainPage.verifyLoginButtonIsVisible());
        step("Кликнуть кнопку входа", () -> mainPage.clickLoginButton());
        step("Отображается модалка авторизации", () -> mainPage.authModalIsVisible());
    }
}
