package org.example.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.example.support.FileReaderManager;
import org.example.utils.ExtentReportUtil;
import org.example.utils.TestBase;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;


public class Hooks {

     private TestBase testBase;
     private WebDriver webDriver;

    private boolean webDriverInitialized = false;



    @Before
    public void beforeScenario(Scenario scenario) {
        ExtentReportUtil.initialize();
        ExtentReportUtil.setTest(ExtentReportUtil.getExtent().createTest(scenario.getName()));
    }

    @After
    public void afterScenario(Scenario scenario) {
        ExtentReportUtil.flush();
        if (webDriverInitialized) {
            if (scenario.isFailed()) {
                try {
                    byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
                    scenario.attach(screenshot, "image/png", "screenshot");
                } catch (WebDriverException noSupportScreenshot) {
                    System.err.println(noSupportScreenshot.getMessage());
                }
            }
            if (webDriver != null) {
                webDriver.quit();
                webDriver = null;
            }
        }
        TestBase.getInstance().reset();
    }


    @Before("@UITest")
    public void setUp() {
        testBase = TestBase.getInstance();
        if (!webDriverInitialized) {
            webDriver = testBase.getDriverManager().getDriver();
            webDriver.get(FileReaderManager.getInstance().getConfigFileReader().getUrl());
            webDriver.manage().window().maximize();
            webDriverInitialized = true;
        }
    }

    @After("@UITest")
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) testBase.getDriverManager().getDriver())
                        .getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "screenshot");
            } catch (WebDriverException noSupportScreenshot) {
                System.err.println(noSupportScreenshot.getMessage());
            }
        }
        if (webDriver != null) {
            testBase.getDriverManager().closeDriver();
            webDriver = null;
        }
    }


}
