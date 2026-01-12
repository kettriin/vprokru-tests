package mobile.helpers;

import static io.restassured.RestAssured.given;


public class Browserstack {

    public static String videoUrl(String sessionId) {
        String url = String.format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);
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
