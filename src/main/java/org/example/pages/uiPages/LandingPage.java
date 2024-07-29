package org.example.pages.uiPages;

import org.example.utils.CustomCommands;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LandingPage {

    private final WebDriver webDriver;

    @FindBy(className = "home-sticky-container")
    private WebElement homeContainer;

    @FindBy(xpath = "//li[@data-testid='create-board-tile']")
    private WebElement createNewBoardButton;

    @FindBy(xpath = "//input[@data-testid='create-board-title-input']")
    private WebElement createBoardTitle;

    @FindBy(xpath = "//button[@data-testid='create-board-submit-button']")
    private WebElement createBoardButton;

    @FindBy(xpath = "//button[@data-testid='popover-close']")
    private WebElement closePopUpButton;

    private WebElement getBoardTitle(String boardName) {
        return this.webDriver.findElement(By.xpath("//div[@title='"+boardName+"']"));
    }

    Long timeOut = 5L;

    public LandingPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(new AjaxElementLocatorFactory(webDriver, 5), this);
    }
    public boolean defaultLandingPageIsDisplayed() {
        CustomCommands.untilElementIsVisible(webDriver,homeContainer,timeOut);
        homeContainer.isDisplayed();
        return true;
    }

    public boolean createBoardPopupIsDisplayed(){
        CustomCommands.untilElementIsVisible(webDriver,closePopUpButton,timeOut);
        closePopUpButton.isDisplayed();
        return true;
    }

    public void clickCreateNewBoard(){
        CustomCommands.click(webDriver, createNewBoardButton, timeOut);
    }

    public void goToBoard(String title){
        CustomCommands.click(webDriver, webDriver.findElement(By.xpath("//div[@title='"+title+"']")), timeOut);
    }

    public void enterTitle(String title){
        CustomCommands.sendKeys(webDriver, createBoardTitle,timeOut,title);
    }

    public void clickCreateBoard(){
        CustomCommands.click(webDriver, createBoardButton, timeOut);
    }

    public boolean verifyBoardWithTitle(String Title){
        if(webDriver.findElements(By.xpath("//div[@class='board-tile-details-name'][@title='"+Title+"']")).isEmpty())
            return true;
        else
            return false;
    }

}
