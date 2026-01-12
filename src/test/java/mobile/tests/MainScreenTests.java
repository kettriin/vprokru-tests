package mobile.tests;

import mobile.screens.MainScreen;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MainScreenTests extends TestBase {

    MainScreen mainScreen = new MainScreen();

    @ValueSource(strings = {
            "Каталог", "Адрес", "Войти"
    })
    @ParameterizedTest(name = "На главном экране отображаются кнопки {0}")
    @Tag("APP")
    void darkstoreButtonsAreDisplayedTest(String darkstoreButtonName) {
        mainScreen.darkstoreItemButtonDisplayed(darkstoreButtonName);
    }
}
