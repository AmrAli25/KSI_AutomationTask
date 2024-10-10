package utils;

import com.microsoft.playwright.*;
import io.qameta.allure.Step;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PlaywrightFactory {

    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    Properties properties;

    @Step("Initialize the browser instance")
    public Page initBrowser(Properties properties) {
        playwright = Playwright.create();
        String browserName = properties.getProperty("browser");
        String url = properties.getProperty("baseURL");

        switch (browserName.toLowerCase()) {
            case "chromium":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "webkit":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "chrome":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
                break;
            default:

                break;
        }
        browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(1280, 720));
        page = browserContext.newPage();
        page.navigate(url);
        return page;
    }

    @Step("Initialize the system properties ")
    public Properties init_properties() {
        try {
            FileInputStream inputStream = new FileInputStream("./src/main/resources/config/config.properties");
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;

    }
}
