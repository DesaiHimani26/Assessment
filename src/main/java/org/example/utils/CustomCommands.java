package org.example.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class CustomCommands {

    public static void click(WebDriver webDriver,WebElement webElement,Long timeOutInSeconds){
        untilElementIsVisible(webDriver, webElement, timeOutInSeconds);
        untilElementIsClickable(webDriver, webElement, timeOutInSeconds);
        webElement.click();
    }

    public static void sendKeys(WebDriver webDriver,WebElement webElement,Long timeOutInSeconds,CharSequence keysToSend ){
        untilElementIsVisible(webDriver, webElement, timeOutInSeconds);
        untilElementIsClickable(webDriver, webElement, timeOutInSeconds);
        webElement.sendKeys(keysToSend);
    }

    public static String getText(WebDriver webDriver, WebElement webElement, Long timeOutInSeconds){
        try {
            untilElementIsVisible(webDriver, webElement, timeOutInSeconds);
            untilElementIsClickable(webDriver, webElement, timeOutInSeconds);
            return webElement.getText();
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void untilElementIsVisible(WebDriver webDriver, WebElement webElement, Long timeOutInSeconds) {
        new WebDriverWait(webDriver, Duration.ofSeconds(timeOutInSeconds))
                .until(ExpectedConditions.visibilityOf(webElement));
    }


    public static void untilElementIsClickable(WebDriver webDriver, WebElement webElement, Long timeOutInSeconds) {
        new WebDriverWait(webDriver, Duration.ofSeconds(timeOutInSeconds))
                .until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static void DragAndDrop(WebElement from, WebElement To, WebDriver driver) {
        Actions act = new Actions(driver);
        act.dragAndDrop(from, To).build().perform();
    }


    public static WebElement getElementWithXpath(WebDriver webDriver, String xpathString){
        return webDriver.findElement(By.xpath(xpathString));
    }

    public static void sleep(Long timeOutInSeconds) {
        try {
            Thread.sleep(timeOutInSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
