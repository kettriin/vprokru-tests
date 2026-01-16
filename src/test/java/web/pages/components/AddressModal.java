package web.pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class AddressModal {

    private final SelenideElement streetInput = $(byName("address"));
    private final SelenideElement flatNumber = $(byName("flat"));
    private final SelenideElement submitButton = $(byText("Сохранить адрес"));
    private final SelenideElement addressList = $(".Options_list__CoBea");

    final String noExpressAddress = "Москва, Матвеевская улица, 10к4";
    final String flatNumberValue = "12";

    @Step("Ввод минимального адреса")
    public void minAddressInput() {
        streetInput.setValue(noExpressAddress);
        addressList.click();
        flatNumber.setValue(flatNumberValue);
    }

    @Step("Подтверждение введённого адреса")
    public void approveAddress() {
        submitButton.click();
    }
}
