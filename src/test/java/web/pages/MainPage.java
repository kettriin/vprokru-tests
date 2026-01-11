package web.pages;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import web.data.Categories;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class MainPage {

    private final ElementsCollection naviLinks = $$(".UiSharedNavigationLinksAndMore_list__2keQZ a");
    private final SelenideElement searchInput = $(".UiSharedInputSearch_input__G0Un1");
    private final SelenideElement burgerMenuButton = $(".BurgerButton_burger__k87p1");
    private final SelenideElement searchResultsTitle = $(".SearchResultsInformer_title__FVcvT");
    private final SelenideElement productTile = $(".UiProductTileMain_root__Zk2eh.UiProductTileMain_listing__t356q");
    private final SelenideElement catalogMenu = $(".FeatureCatalogNavigationDesktop_parents__ts8uK");
    private final SelenideElement logo = $(".Logo_desktop__IL1Zv");
    private final SelenideElement cartButton = $(".CartIcon_cart__8xX__");
    private final SelenideElement buyButton = $(byText("Купить"));
    private final SelenideElement loginButton = $(".ProfileIcon_profile__LXIkC");
    private final SelenideElement authModal = $(".AuthModal_authModal__nouL7");
    private final SelenideElement citySelector = $(".Region_text__Wm7FO");
    private final SelenideElement citySearch = $(".UiRegionListBase_listWrapper__Iqbd5");
    private final SelenideElement productCard = $(".UiProductTileMain_root__Zk2eh");

    @Step("Логотип отображается и кликабелен")
    public void verifyLogoIsVisibleAndClickable() {
        logo.shouldBe(visible).shouldBe(enabled);
    }

    @Step("Информационный блок отображается в шапке")
    public void verifyInfoBlockIsVisible(String infoBlockHeader) {
        naviLinks.filterBy(com.codeborne.selenide.Condition.text(infoBlockHeader))
                .shouldHave(com.codeborne.selenide.CollectionCondition.sizeGreaterThan(0));
    }

    @Step("Выполняет поиск товара")
    public void searchForProduct(String searchQuery) {
        searchInput.setValue(searchQuery).pressEnter();
        searchResultsTitle.shouldBe(visible);
    }

    @Step("Найден товар с указанным названием")
    public void verifyProductIsFound(String productName) {
        productTile.shouldHave(com.codeborne.selenide.Condition.text(productName));
    }

    @Step("Открывает каталог")
    public void openCatalog() {
        burgerMenuButton.shouldBe(visible).click();
        catalogMenu.shouldBe(visible);
    }

    @Step("Проверяет наличие категории в каталоге")
    public void verifyCategoryInCatalog(Categories categories) {
        catalogMenu.shouldHave(text(categories.categoryName));
    }

    @Step("Ожидание загрузки страницы")
    public MainPage waitForLoad() {
        burgerMenuButton.shouldBe(visible);
        return this;
    }

    @Step("Клик по лого")
    public void clickLogo() {
        logo.click();
    }

    @Step("Открыта модалка авторизации")
    public void authModalIsVisible(){
        authModal.shouldBe(visible);
    }

    @Step("Проверить текущий URL")
    public void verifyCurrentUrl(String expectedUrl) {
        webdriver().shouldHave(url(expectedUrl));
    }

    @Step("Иконка корзины отображается на главной")
    public void verifyCartIsVisible() {
        cartButton.shouldBe(visible);
    }

    @Step("Открыть корзину")
    public void openCart() {
        cartButton.click();
    }

    @Step("Добавить товар в корзину")
    public void addProduct() {
        productCard.scrollTo();
        buyButton.shouldBe(visible);
        buyButton.click();
    }

    @Step("Открывает селектор города")
    public void openCitySelector() {
        citySelector.click();
        citySearch.shouldBe(visible);
    }

    @Step("Выбрать город: {cityName}")
    public void selectCity(String cityName) {
        $(byText(cityName))
                .shouldBe(visible)
                .click(ClickOptions.usingJavaScript());
    }

    @Step("Выбранный город отображается в хедере")
    public void verifySelectedCity(String expectedCity) {
        citySelector.shouldHave(text(expectedCity));
    }

    @Step("Проверяет, что кнопка входа/регистрации отображается")
    public void verifyLoginButtonIsVisible() {
        loginButton.shouldBe(visible);
    }

    @Step("Нажимает на кнопку входа")
    public void clickLoginButton() {
        loginButton.click();
    }
}
