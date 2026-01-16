package web.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import web.pages.CartPage;
import web.pages.MainPage;
import web.pages.components.AddressModal;
import web.pages.components.PromoModal;

import static com.codeborne.selenide.Selenide.sleep;
import static io.qameta.allure.Allure.step;
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
    void cartIsEmptyByFirstVisitTest() {
        step("Иконка корзины отображается на главной", () -> mainPage.verifyCartIsVisible());
        step("Перейти на вкладку коризны", () -> mainPage.openCart());
        step("Корзина пуста", () -> cartPage.cartIsEmpty());
    }

    @DisplayName("Добавленный товар отображается в ненулевом количестве")
    @Test
    void cartHasAddedItemsTest() {
        step("Добавить товар в корзину", () -> addProductToCart());
        step("Для добавленного товара в корзине отображается его количество",
                () -> cartPage.productAmountInCartIsNotEmpty());
    }

    @DisplayName("Увеличение количества товаров в корзине с помощью каунтера")
    @Test
    void itemQuantityCounterTest() {
        step("Добавить товар в корзину", () -> addProductToCart());
        double initialValue = cartPage.parseCounterValue();
        step("Увеличить количество товара в корзине в 2 раза",
                () -> cartPage.incrementButtonClick());
        double finalValue = cartPage.parseCounterValue();
        step("Количество добавленного товара в корзине стало больше в 2 раза",
                () -> assertThat(finalValue).isGreaterThanOrEqualTo(initialValue * 2));
    }

    @DisplayName("Удаление товара из корзины")
    @Test
    void itemRemoveFromCartTest() {
        step("Добавить товар в корзину", () -> addProductToCart());
        step("Удалить товар из корзины по крестику", () -> cartPage.removeProductButtonClick());
        step("Корзина пуста", () -> cartPage.cartIsEmpty());
    }

    //helper предусловий добавления товара в корзину и перехода к ней (в перспективе - через api)
    private void addProductToCart() {
        mainPage.verifyCartIsVisible();
        mainPage.openCitySelector();
        mainPage.selectCity("Тверская обл.");
        mainPage.addProduct();
        address.minAddressInput();
        address.approveAddress();
        mainPage.addProduct();
        mainPage.openCart();
        sleep(5000);
        promo.closePromoModalIfPresent();
    }
}
