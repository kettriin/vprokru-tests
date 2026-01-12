package mobile.tests;

import mobile.screens.CartScreen;
import mobile.screens.components.NavBar;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class CartTests extends TestBase {

    NavBar navBar = new NavBar();
    CartScreen cartScreen = new CartScreen();

    @Test
    @DisplayName("Корзина пустая при первом запуске")
    @Tag("APP")
    void firstStartEmptyCartTest() {
        navBar.goToCart();
        cartScreen.emptyCartHeader();
    }
}
