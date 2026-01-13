package mobile.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverProvider;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import mobile.driver.LocalDriver;
import mobile.driver.RemoteDriver;
import mobile.helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    @BeforeAll
    static void setupSelenideConfig() {
        Configuration.browser = chooseMode().getClass().getName();
        Configuration.browserSize = null;
        Configuration.timeout = 30000;

        Configuration.remoteReadTimeout = 60000;
        Configuration.remoteConnectionTimeout = 60000;
    }

    @BeforeEach
    void addListenerAndOpen() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void addAttachments() {
        String sessionId = Selenide.sessionId().toString();
        System.out.println("Session ID: " + sessionId);

        Attach.pageSource();
        closeWebDriver();
        Attach.addVideo(sessionId);
    }

    static WebDriverProvider chooseMode() {
        String testDeviceDriver = System.getProperty("dd", "REMOTE");

        return switch (testDeviceDriver) {
            case "REMOTE" -> new RemoteDriver();
            case "LOCAL" -> new LocalDriver();
            default -> throw new IllegalArgumentException("Unknown device: ");
        };
    }
}