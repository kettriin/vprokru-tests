package mobile.driver;

import com.codeborne.selenide.WebDriverProvider;
import mobile.config.RemoteConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class RemoteDriver implements WebDriverProvider {
    private final RemoteConfig config = ConfigFactory.create(RemoteConfig.class, System.getProperties());

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();

        caps.setCapability("platformName", "android");
        caps.setCapability("appium:automationName", "UiAutomator2");

        MutableCapabilities bstackOptions = new MutableCapabilities();
        bstackOptions.setCapability("userName", config.browserstackUser());
        bstackOptions.setCapability("accessKey", config.browserstackKey());
        bstackOptions.setCapability("projectName", "Vprok.ru Tests");
        bstackOptions.setCapability("buildName", "Automated Tests");
        bstackOptions.setCapability("sessionName", "Mobile App Test");

        caps.setCapability("bstack:options", bstackOptions);

        caps.setCapability("appium:app", config.appName());
        caps.setCapability("appium:deviceName", config.deviceName());
        caps.setCapability("appium:platformVersion", config.androidVersion());
        caps.setCapability("appium:autoGrantPermissions", true);
        caps.setCapability("appium:noReset", false);

        try {
            return new RemoteWebDriver(new URL(config.hubUrl()), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}