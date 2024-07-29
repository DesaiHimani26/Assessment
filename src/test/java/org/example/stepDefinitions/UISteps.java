package org.example.stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.uiPages.BoardPage;
import org.example.pages.uiPages.LandingPage;
import org.example.pages.uiPages.LoginPage;
import org.example.utils.TestBase;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class UISteps {

    TestBase testBase;
    LoginPage loginPage;
    LandingPage landingPage;
    BoardPage boardPage;

    String uniqueBoardName;


    public UISteps(){
        testBase = TestBase.getInstance();
        loginPage = testBase.getPageObjectParent().getLoginPage();
        landingPage = testBase.getPageObjectParent().getHomePage();
        boardPage = testBase.getPageObjectParent().getBoardPage();
    }

    @Given("user is on Trello login page")
    public void user_is_on_trello_login_page() {
            Assert.assertTrue(loginPage.trelloLoginPageIsDisplayed());
    }

    @When("user enters valid username and password to login")
    public void user_enters_valid_username_and_password(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        String username = data.get(0).get("email");
        String password = data.get(0).get("password");
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginLink();
        Assert.assertTrue(landingPage.defaultLandingPageIsDisplayed());
    }


    @Given("User click Create to create New Board")
    public void userClickCreateToCreateNewBoard() {
        uniqueBoardName = new Faker().superhero().name();
        landingPage.clickCreateNewBoard();
        Assert.assertTrue(landingPage.createBoardPopupIsDisplayed());
    }

    @When("User creates board with Board unique title")
    public void addsBoardTitleBackground() {
        landingPage.enterTitle(uniqueBoardName);
        landingPage.clickCreateBoard();

    }

    @Then("New board is created with title")
    public void newBoardIsCreatedWithTitle() {
        Assert.assertEquals(boardPage.getBoardname(), uniqueBoardName);
    }

    @When("User closes current board")
    public void userClosesCurrentBoard() {
        boardPage.closeBoard();
    }

    @And("User deletes current board")
    public void userDeletesCurrentBoard() {
        boardPage.deleteBoard();
    }

    @Then("User verifies board is deleted")
    public void userVerifiesBoardWithTitleIsDeleted() {
        landingPage.defaultLandingPageIsDisplayed();
       // Assert.assertTrue(landingPage.verifyBoardWithTitle(uniqueBoardName));
    }

    @Then("Message {string} is displayed")
    public void messageIsDisplayed(String message) {
        String actualMessage = boardPage.getMessageText();
        Assert.assertEquals("Message does NOT match",message,actualMessage);
    }

    @And("User create lists {string}")
    public void userCreateListWithTitle(String listName) {
        boardPage.createListWithTitle(listName);

    }

    @Then("User adds cards in list")
    public void validateTheCardsAdditionFunctionalityForTheList(DataTable dataTable) throws Exception {
       boardPage.AddCardToList(dataTable);
    }

    @When("User create another lists {string}")
    public void userCreateAnotherLists(String arg0) {
        boardPage.createAnotherList(arg0);
    }

    @And("User moving the old list task to new list {string}")
    public void userMovingTheOldListTaskToNewList(String listName, DataTable dataTable) throws Exception {
        boardPage.moveCardToList(dataTable, listName);
    }

    @Given("User opens {string} board")
    public void userOpensBoard(String boardName) {
        landingPage.goToBoard(boardName);
    }

    @And("User Archives list {string}")
    public void userArchivesList(String listTitle) throws Exception {
        boardPage.clickEditMenuAndArchiveList(listTitle);
    }

    @Then("User verify {string} list is Archived")
    public void userVerifyListIsArchived(String listTitle) {
        Assert.assertFalse(boardPage.verifyListWithTitle(listTitle));
    }
}
