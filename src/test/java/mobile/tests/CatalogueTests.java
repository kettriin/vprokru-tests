package mobile.tests;

import mobile.screens.CatalogScreen;
import mobile.screens.MainScreen;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import mobile.data.CategoriesForAppTests;

public class CatalogueTests extends TestBase {
    MainScreen mainScreen = new MainScreen();
    CatalogScreen catalogScreen = new CatalogScreen();

    @ParameterizedTest(name = "В каталоге присутствует категория {0}")
    @EnumSource(value = CategoriesForAppTests.class)
    @Tag("APP")
    void mainCategoriesInCatalogAreDisplayed(CategoriesForAppTests categoryName) {
        mainScreen.catalogOpen();
        catalogScreen.mainCategoriesAreDisplayd(categoryName);
    }

    @CsvSource({
            "Наше производство , Все товары категории",
            "Готовая еда , Все товары категории ",
            "Красота и здоровье , Все товары категории"
    })
    @ParameterizedTest(name = "Категория {0} содержит подкатегорию \"Все товары категории\" ")
    @Tag("APP")
    void categoriesHaveAllProductsSubcatgory(String categoryName, String subCategoryName) {
        mainScreen.catalogOpen();
        catalogScreen.goToCategory(categoryName);
        catalogScreen.innerCategoryTitleHasText(subCategoryName);
    }

}
