package web.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import web.config.WebConfig;
import web.config.WebDriverProvider;
import web.helpers.Attach;

import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    @BeforeAll
    static void setupSelenideConfig() {
        WebConfig config = ConfigFactory.create(WebConfig.class, System.getProperties());
        WebDriverProvider webConfig = new WebDriverProvider(config);
        webConfig.setUp();

        Configuration.pageLoadStrategy = "eager";
        Configuration.pageLoadTimeout = 30000;
    }

    @BeforeEach
    void addListenerAndOpen() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open("/");
    }

    @AfterEach
    void addAttachmentsAndClose() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

        Selenide.closeWebDriver();
    }
}