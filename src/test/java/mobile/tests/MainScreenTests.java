package mobile.tests;

import mobile.screens.MainScreen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.qameta.allure.Allure.step;

@DisplayName("Тесты на блоки и контент на главной странице")
@Tag("MAIN_APP")
public class MainScreenTests extends TestBase {

    MainScreen mainScreen = new MainScreen();

    @ValueSource(strings = {
            "Каталог", "Адрес", "Войти"
    })
    @ParameterizedTest(name = "На главном экране отображаются кнопки {0}")
    void darkstoreButtonsAreDisplayedTest(String darkstoreButtonName) {
        step("На главной отображаются основные кнопки даркстора", () ->
                mainScreen.darkstoreItemButtonDisplayed(darkstoreButtonName));
    }
}
