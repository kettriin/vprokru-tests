package web.tests;

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
import static com.codeborne.selenide.Selenide.sleep;

public class TestBase {

    @BeforeAll
    static void setupSelenideConfig() {

        WebConfig config = ConfigFactory.create(WebConfig.class, System.getProperties());
        WebDriverProvider webConfig = new WebDriverProvider(config);
        webConfig.setUp();
    }

    @BeforeEach
    void addListenerAndOpen() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open("/");
        sleep(8000);
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
