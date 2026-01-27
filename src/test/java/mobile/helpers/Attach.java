package mobile.helpers;

import io.qameta.allure.Allure;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Attach {

    public static void pageSource() {
        Allure.attachment("Page source", getWebDriver().getPageSource());
    }

    public static void addVideo(String sessionId) {
        String videoUrl = Browserstack.videoUrl(sessionId);

        if (videoUrl != null && !videoUrl.isEmpty()) {
            Allure.addAttachment("Video",
                    "text/uri-list",
                    videoUrl,
                    ".mp4");
        }
    }
}