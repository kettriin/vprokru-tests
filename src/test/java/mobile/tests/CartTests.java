package mobile.tests;

import mobile.screens.CartScreen;
import mobile.screens.components.NavBar;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@DisplayName("Тесты на взаимодействие с корзиной")
@Tag("CART_APP")
public class CartTests extends TestBase {

    NavBar navBar = new NavBar();
    CartScreen cartScreen = new CartScreen();

    @Test
    @DisplayName("Корзина пустая при первом запуске")
    @Tag("APP")
    void firstStartEmptyCartTest() {
        step("Перейти на вкладку Корзинав нижнем навбаре", () -> navBar.goToCart());
        step("В корзине присутствует заголовок 'Корзина пуста'", () -> cartScreen.emptyCartHeader());
    }
}
