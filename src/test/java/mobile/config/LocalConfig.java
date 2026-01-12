package mobile.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"classpath:local.properties"})
public interface LocalConfig extends Config {

    @Key("platform.name")
    String platformName();

    @Key("device.name")
    String deviceName();

    @Key("device.avd")
    String deviceAvd();

    @Key("hub.url")
    String hubUrl();
}