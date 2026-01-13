package mobile.tests;

import mobile.data.CategoriesForAppTests;
import mobile.screens.CatalogScreen;
import mobile.screens.MainScreen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import static io.qameta.allure.Allure.step;

@DisplayName("Тесты на работу каталога")
@Tag("CAT_APP")
public class CatalogueTests extends TestBase {
    MainScreen mainScreen = new MainScreen();
    CatalogScreen catalogScreen = new CatalogScreen();

    @ParameterizedTest(name = "В каталоге присутствует категория {0}")
    @EnumSource(value = CategoriesForAppTests.class)
    void mainCategoriesInCatalogAreDisplayedTest(CategoriesForAppTests categoryName) {
        step("Перейти в каталог", () -> mainScreen.catalogOpen());
        step("В каталоге отображается плитка категории", () ->
                catalogScreen.checkCategoryIsDisplayed(categoryName));
    }

    @CsvSource({
            "Наше производство , Все товары категории",
            "Готовая еда , Все товары категории ",
            "Красота и здоровье , Все товары категории"
    })
    @ParameterizedTest(name = "Категория {0} содержит подкатегорию \"Все товары категории\" ")
    void categoriesHaveAllProductsSubcatgoryTest(String categoryName, String subCategoryName) {
        step("Перейти в каталог", () -> mainScreen.catalogOpen());
        step("Перейти в категорию", () -> catalogScreen.goToCategory(categoryName));
        step("Отобрадается заголовок подкатегории Все товары категории", () ->
                catalogScreen.innerCategoryTitleHasText(subCategoryName));
    }

}
