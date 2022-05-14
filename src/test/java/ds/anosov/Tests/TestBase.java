package ds.anosov.Tests;

import Attachments.Attachtent;
import ConfigTest.CredentialsConfig;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static java.lang.String.format;

public class TestBase {
    @BeforeAll
    static void settingsTest() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        //изучаем библиотеку Owner, передаем эти параметры в Jenkins
       // Configuration.baseUrl = "https://demoqa.com";
       // Configuration.browserSize = "1920x1080";
       //  Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub"; // не позволяет запускать локально в браузере, а выполняет запуск в Selenide

        CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class); //создаем экземпляр интерфейса CredentialsConfig

        String propertyBaseUrl = System.getProperty("propertyBaseUrl", "https://demoqa.com");
        String propertyBrowserSize = System.getProperty("propertyBrowserSize", "1920x1080");
        String https = "https://";
        String login = config.login();
        String password = config.password();
        String tailOfUrl = config.testUrl();
        String fullSelenoidUrl = format("%s%s%s%s", https, login, password, tailOfUrl);

        Configuration.baseUrl = propertyBaseUrl;
        Configuration.browserSize = propertyBrowserSize;
        Configuration.remote = fullSelenoidUrl;

        //блок с добавлением видео с прохождением теста в аттачменты
        DesiredCapabilities capabilities = new DesiredCapabilities(); // набор ключей и значений
        capabilities.setCapability("enableVNC", true); // трнаслировать видео = тру
        capabilities.setCapability("enableVideo", true); // делать видео = тру
        Configuration.browserCapabilities = capabilities;
    }
    @AfterEach
    void addAttachments() {
        Attachtent.screenshotAs("Last screenshot");
        Attachtent.pageSource();
        Attachtent.browserConsoleLogs();
        Attachtent.addVideo();
    }
}
