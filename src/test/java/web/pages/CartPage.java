package web.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

public class CartPage {

    private final SelenideElement emptyCart = $(byText("В вашей корзине пока нет товаров"));
    private final SelenideElement productInCart = $(".CounterButton_input__ASHT1");
    private final SelenideElement incrementButton = $("button[data-counter-type='increment']");
    private final SelenideElement productBlock = $("[data-testid='product-item']");
    private final SelenideElement removeProductButton = $(".ProductItem_delete__brt3r");

    @Step("Корзина пуста")
    public void cartIsEmpty() {
        emptyCart.shouldBe(visible);
    }

    @Step("Для добавленного товара отображается его количество")
    public void productAmountInCartIsNotEmpty() {
        String value = productInCart.getValue();

        assertThat(value)
                .isNotEmpty()
                .isNotEqualTo("0")
                .isNotEqualTo("0 кг")
                .isNotEqualTo("0 шт");
    }

    @Step("Увеличесние количества товара по кнопке + в корзине")
    public CartPage incrementButtonClick() {
        incrementButton.click();
        return this;
    }

    @Step("Удаление товара по кнопке х в корзине")
    public CartPage removeProductButtonClick() {
        productBlock.shouldBe(visible).hover();
        removeProductButton.click();
        return this;
    }

    @Step("Получить только цифровое значение количества товара в корзине")
    public double parseCounterValue() {
        String counterValue = productInCart.getValue();
        String numberPart = counterValue.split(" ")[0].replace(",", ".");

        return Double.parseDouble(numberPart);
    }
}
