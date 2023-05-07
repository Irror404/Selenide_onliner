package common;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import constants.Constant;
import org.openqa.selenium.chrome.ChromeOptions;

public class BasicAction {
    public static void createDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = Constant.TimeoutVariable.EXPLICIT_WAIT * 1000;
        Configuration.baseUrl = constants.Constant.Url.ONLINER_START_PAGE;
        Configuration.reportsFolder = "target/surefire-reports";

        Selenide.open("/");
    }
}
