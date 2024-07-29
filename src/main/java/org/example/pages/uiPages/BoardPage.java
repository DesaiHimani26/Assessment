package org.example.pages.uiPages;

import io.cucumber.datatable.DataTable;
import org.example.utils.CustomCommands;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

public class BoardPage {

    private final WebDriver webDriver;

    @FindBy(xpath = "//h1[@data-testid='board-name-display']")
    private WebElement boardNameHeading;

//    @FindBy(xpath = "//h2[contains(@aria-label,'Your boards')]")
//    private WebElement boardSideBar;
//
//    @FindBy(className = "board-menu-content-frame")
//    private WebElement boardMenu;

    @FindBy(xpath = "//button[@aria-label='Show menu']")
    private WebElement boardActionMenu;

    @FindBy(xpath = "//a[contains(@aria-label,'(currently active)')]")
    private WebElement currentBoard;

    @FindBy(xpath = "//li/a[contains(@class,'js-close-board')]")
    private WebElement closeBoard;

    @FindBy(xpath = "//input[@data-testid='close-board-confirm-button']")
    private WebElement closeConfirm;

    @FindBy(xpath = "//button[@data-testid='close-board-delete-board-button']")
    private WebElement deleteBoard;

    @FindBy(xpath = "//button[@data-testid='close-board-delete-board-confirm-button']")
    private WebElement deleteConfirm;

    @FindBy(xpath = "//*[@id=\"content-wrapper\"]/div[1]//p")
    private WebElement messageBanner;

    @FindBy(xpath = "//textarea[starts-with(@placeholder, 'Enter list title')]")
    private WebElement listName;

    @FindBy(xpath = "//button[@data-testid='list-composer-add-list-button']")
    private WebElement addList;

    @FindBy(xpath = "//button[@data-testid='list-add-card-button']")
    private WebElement addCardListButton;

    @FindBy(xpath = "//button[@data-testid='list-card-composer-add-card-button']")
    private WebElement addCardComposeButton;

    @FindBy(xpath = "//textarea[@data-testid='list-card-composer-textarea']")
    private WebElement cardName;

//    @FindBy(xpath = "//div[contains(@class,'list-cards')])[1]//span[contains(@class,'list-card-title js-card-name')]")
//    private List<WebElement> todoList;
//
//    @FindBy(xpath = "//*[@data-testid='list-wrapper'][contains(.,'Doing')]")
//    private WebElement doingList;
//
//    @FindBy(xpath = "//button[contains(text(),'Add a card')])[2]")
//    private WebElement addCard_Doing;

    @FindBy(xpath = "//button[@data-testid='list-composer-button']")
    private WebElement listComposeButton;


    Long timeOut = 5L;
    static String parameter = null;

    public BoardPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(new AjaxElementLocatorFactory(webDriver, 8), this);
    }

    public String getBoardname() {
        return CustomCommands.getText(webDriver, boardNameHeading, timeOut);
    }

    public void closeBoard() {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(currentBoard).perform();
        CustomCommands.click(webDriver, boardActionMenu, timeOut);
        CustomCommands.click(webDriver, closeBoard, timeOut);
        CustomCommands.click(webDriver, closeConfirm, timeOut);
    }

    public void deleteBoard() {
        CustomCommands.click(webDriver, deleteBoard, timeOut);
        CustomCommands.untilElementIsClickable(webDriver, deleteConfirm, timeOut);
        CustomCommands.click(webDriver, deleteConfirm, timeOut);
    }

    public String getMessageText() {
        return CustomCommands.getText(webDriver, messageBanner, timeOut);

    }

    public void createListWithTitle(String listTitle) {
        CustomCommands.sendKeys(webDriver, listName, timeOut, listTitle);
        CustomCommands.click(webDriver, addList, timeOut);
        CustomCommands.untilElementIsClickable(webDriver, addCardListButton, timeOut);
    }

    public void createAnotherList(String listTitle) {
        CustomCommands.click(webDriver, listComposeButton, timeOut);
        createListWithTitle(listTitle);
    }

    public void AddCardToList(DataTable dataTable) throws Exception {
        boolean isDisplayed;
        List<List<String>> rows = dataTable.asLists(String.class);
        int retryCount = 3;
        while (retryCount > 0) {
            try {

                isDisplayed = addCardListButton.isDisplayed();
                isDisplayed = webDriver.findElements(By.xpath("//button[@data-testid='list-add-card-button']")).size() > 1;

                if (!isDisplayed) {
                    CustomCommands.click(webDriver, addCardListButton, 3L);
                }


                for (List<String> cardList : rows) {
                    CustomCommands.sendKeys(webDriver, cardName, timeOut, cardList.get(0));
                    CustomCommands.click(webDriver, addCardComposeButton, timeOut);
                }
                break;
            } catch (StaleElementReferenceException e) {
                retryCount--;
                if (retryCount == 0) {
                    throw new Exception("Element is not attached to the DOM: " + e.getMessage());
                }
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }
    }

    public void moveCardToList(DataTable dataTable, String listName) throws Exception {
        int retryCount = 3;

        while (retryCount > 0) {
            try {
                List<List<String>> rows = dataTable.asLists(String.class);
                WebElement fromElement;
                WebElement toElement = null;

                for (List<String> moveCards : rows) {
                    String cardName = moveCards.get(0);
                    fromElement = webDriver.findElement(By.xpath("//a[contains(text(),'" + cardName + "')]"));
                    toElement = webDriver.findElement(By.xpath("(//*[@data-testid='list-wrapper'][contains(.,'" + listName + "')])[1]"));

                    CustomCommands.untilElementIsVisible(webDriver, fromElement, timeOut);
                    CustomCommands.untilElementIsVisible(webDriver, toElement, timeOut);
                    CustomCommands.DragAndDrop(fromElement, toElement, webDriver);
                }
                break;

            } catch (StaleElementReferenceException e) {
                retryCount--;
                if (retryCount == 0) {
                    throw new Exception("Stale element reference error while moving card to list: " + e.getMessage(), e);
                }
                Thread.sleep(500);
            } catch (Exception e) {
                throw new Exception("Error while moving card to list: " + e.getMessage(), e);
            }
        }
    }

    public void clickEditMenuAndArchiveList(String listTitle) throws Exception {
        int retryCount = 3; // Number of retries for handling stale elements

        while (retryCount > 0) {
            try {
                WebElement editMenuForList = CustomCommands.getElementWithXpath(webDriver, "//h2[@data-testid='list-name'][contains(text(),'" + listTitle + "')]/ancestor :: div[@data-testid='list-header']//button[@data-testid='list-edit-menu-button']");
                CustomCommands.untilElementIsClickable(webDriver, editMenuForList, timeOut);
                CustomCommands.click(webDriver, editMenuForList, timeOut);
                WebElement menuItelList = CustomCommands.getElementWithXpath(webDriver, "//section[@data-testid='list-actions-popover']");
                CustomCommands.untilElementIsVisible(webDriver, menuItelList, timeOut);
                // Locate the desired menu item by its text and click it
                WebElement menuItem = CustomCommands.getElementWithXpath(webDriver, "//section[@data-testid='list-actions-popover']//li//span[contains(text(),'Archive this list')]");
                menuItem.click();
                break;

            } catch (StaleElementReferenceException e) {
                retryCount--;
                if (retryCount == 0) {
                    throw new Exception("Stale element reference error while clicking Edit Menu: " + e.getMessage(), e);
                }
                Thread.sleep(500);
            } catch (Exception e) {
                throw new Exception("Error while moving card to list: " + e.getMessage(), e);
            }
        }
    }

    public boolean verifyListWithTitle(String listTitle) {
        if (webDriver.findElements(By.xpath("//h2[@data-testid='list-name'][contains(text(),'" + listTitle + "')]")).isEmpty())
            return true;
        else
            return false;
    }

}
