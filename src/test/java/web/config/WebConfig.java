package web.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:${env}.properties"
})
public interface WebConfig extends Config{
    @Key("baseUrl")
    @DefaultValue("https://vprok.ru")
    String getBaseUrl();

    @Key("browser")
    @DefaultValue("CHROME")
    String getBrowser();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String getBrowserSize();

    @Key("browserVersion")
    @DefaultValue("128.0")
    String getBrowserVersion();

    @Key("isRemote")
    @DefaultValue("false")
    Boolean isRemote();
}