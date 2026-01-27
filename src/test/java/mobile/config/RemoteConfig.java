package mobile.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({
        "classpath:remoteapp.properties"
})

public interface RemoteConfig extends Config {

    @Key("browserstack.user")
    String browserstackUser();

    @Key("browserstack.key")
    String browserstackKey();

    @Key("app.name")
    String appName();

    @Key("device.name")
    String deviceName();

    @Key("android.version")
    String androidVersion();

    @Key("hub.url")
    String hubUrl();

    @Key("browserstack.api.url")
    @DefaultValue("https://api.browserstack.com/app-automate")
    String browserstackApiUrl();
}
