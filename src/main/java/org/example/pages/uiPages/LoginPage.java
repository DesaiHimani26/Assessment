package org.example.pages.uiPages;

import org.example.utils.CustomCommands;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage {
    private final WebDriver webDriver;

    @FindBy(id = "ProductHeading")
    private WebElement trelloLogo;

    @FindBy(id = "login-submit")
    private WebElement loginLink;

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;


    Long timeOut = 5L;
    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(new AjaxElementLocatorFactory(webDriver, 8), this);
    }

    public boolean trelloLoginPageIsDisplayed() {
        usernameField.isDisplayed();
        loginLink.isDisplayed();
        return true;
    }

    public void clickLoginLink() {
        CustomCommands.click(webDriver, loginLink, timeOut);
    }

    public void enterUsername(String username) {
        CustomCommands.sendKeys(webDriver, usernameField, timeOut, username);
        loginNow();
    }

    public void enterPassword(String password) {
        CustomCommands.sendKeys(webDriver, passwordField, timeOut, password);
        loginNow();

    }

    public void loginNow() {
        CustomCommands.click(webDriver, loginLink, timeOut);
    }
}
