package web.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import web.pages.CartPage;
import web.pages.MainPage;
import web.pages.components.AddressModal;
import web.pages.components.PromoModal;

import static com.codeborne.selenide.Selenide.sleep;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тесты на взаимодействие с корзиной")
@Tag("CART_WEB")
public class CartTests extends TestBase{

    MainPage mainPage = new MainPage();
    CartPage cartPage = new CartPage();
    AddressModal address = new AddressModal();
    PromoModal promo = new PromoModal();

    @DisplayName("При первом переходе на сайт корзина - пустая")
    @Test
    @Tag("WEB")
    void cartIsEmptyByFirstVisitTest() {
        mainPage.verifyCartIsVisible();
        mainPage.openCart();
        cartPage.cartIsEmpty();
    }

    @DisplayName("Добавленный товар отображается в ненулевом количестве")
    @Test
    @Tag("WEB")
    void cartHasAddedItemsTest() {
        addProductToCart();
        cartPage.productAmountInCartIsNotEmpty();
    }

    @DisplayName("Увеличение количества товаров в корзине с помощью каунтера")
    @Test
    @Tag("WEB")
    void itemQuantityCounterTest() {
        addProductToCart();
        double initialValue = cartPage.parseCounterValue();
        cartPage.incrementButtonClick();
        double finalValue = cartPage.parseCounterValue();
        assertThat(finalValue).isGreaterThanOrEqualTo(initialValue * 2);
    }

    @DisplayName("Удаление товара из корзины")
    @Test
    @Tag("WEB")
    void itemRemoveFromCartTest() {
        addProductToCart();
        cartPage.removeProductButtonClick();
        cartPage.cartIsEmpty();
    }

    //helper предусловий добавления товара в корзину и перехода к ней (в перспективе - через api)
    private void addProductToCart() {
        mainPage.verifyCartIsVisible();
        mainPage.openCitySelector();
        mainPage.selectCity("Тверская обл.");
        mainPage.addProduct();
        address.minAddressInput();
        address.approveAddress();
        mainPage.openCart();
        sleep(5000);
        promo.closePromoModalIfPresent();
    }
}
