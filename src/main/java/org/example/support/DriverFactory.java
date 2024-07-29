package org.example.support;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.constants.DriverTypes;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class DriverFactory {
    private WebDriver webDriver;
    private static DriverTypes driverTypes;

    public DriverFactory() {
         driverTypes = FileReaderManager.getInstance().getConfigFileReader().getBrowser();
    }

    private WebDriver createLocalDriver() {
        switch (driverTypes) {
            default:
                WebDriverManager.chromedriver().setup();
                ChromeDriverService service =
                        new ChromeDriverService.Builder().withSilent(true).build();
                ChromeOptions chromeOptions = new ChromeOptions();
                //chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--remote-allow-origins=*");
                webDriver = new ChromeDriver(service,chromeOptions);
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--remote-allow-origins=*");
                webDriver = new FirefoxDriver(firefoxOptions);
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver();
                break;
            case SAFARI:
                webDriver = new SafariDriver();
                break;
        }
         long time = FileReaderManager.getInstance().getConfigFileReader().getTime();

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(time));
        webDriver.manage().timeouts().scriptTimeout(Duration.ofSeconds(time));
        return webDriver;
    }


    public WebDriver getDriver() {
        if (webDriver == null) {
            webDriver = createLocalDriver();
        }
        return webDriver;
    }

    public void closeDriver() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }

}
