package mobile.helpers;

import mobile.config.RemoteConfig;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;


public class Browserstack {

    private static final RemoteConfig config = ConfigFactory.create(RemoteConfig.class);

    public static String videoUrl(String sessionId) {
        String apiBaseUrl = config.browserstackApiUrl();

        String url = String.format("%s/sessions/%s.json", apiBaseUrl, sessionId);

        String user = System.getProperty("browserstack.user");
        String key = System.getProperty("browserstack.key");
        if (user == null || key == null) {
            System.err.println("Error: JVM properties 'browserstack.user' and 'browserstack.key' are not set");
            return null;
        }

        return given()
                .auth().basic(user, key)
                .get(url)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().path("automation_session.video_url");
    }
}
