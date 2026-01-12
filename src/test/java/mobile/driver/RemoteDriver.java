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

        caps.setCapability("browserstack.user", config.browserstackUser());
        caps.setCapability("browserstack.key", config.browserstackKey());

        caps.setCapability("app", config.appName());

        caps.setCapability("deviceName", config.deviceName());
        caps.setCapability("platformVersion", config.androidVersion());

        try {
            return new RemoteWebDriver(
                    new URL(config.hubUrl()), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}