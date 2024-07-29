package org.example.stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.example.pages.apiPage.BoardRequests;
import org.example.utils.APIBase;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class APISteps {

    APIBase apiBase;
    BoardRequests boardRequests;
    String boardName, boardId ;
    private Response response;


    private String uniqueBoardName, updatedBoardName;


    public APISteps(APIBase base) {
        apiBase = base;
        boardRequests = apiBase.getboardRequests();
        uniqueBoardName = new Faker().superhero().name();
        updatedBoardName = new Faker().superhero().name();

    }

    @Given("POST Request is sent to Trello to create Board with Unique Name")
    public void postRequestIsSentToTrelloToCreateBoardWithName() throws URISyntaxException {
        response = boardRequests.postCreateBoard(uniqueBoardName);
    }

    @Then("Verify Request is successful with response code {int}")
    public void verifyRequestIsSuccessfulWithResponseCode(int expectedStatusCode) {
        int actualStatuscode = response.getStatusCode();
        assertEquals("Invalid status code: " + actualStatuscode, actualStatuscode, expectedStatusCode);
    }

    @And("Response body should have correct board name")
    public void responseBodyShouldHaveName() {
        boardName = response.getBody().jsonPath().getString("name");
        assertEquals("Actual name should match the expected name", boardName, uniqueBoardName);
    }

    @And("Response Body contains ID")
    public void responseBodyContainsID() {
        boardId = response.getBody().jsonPath().getString("id");
        assert boardId != null && !boardId.isEmpty();
    }

    @When("GET Request is sent to get Board Details")
    public void getRequestIsSentToGetBoardDetails() throws URISyntaxException {
        response = boardRequests.getBoardDetails(boardId);
    }


    @Given("DELETE Request is sent to delete Board")
    public void deleteRequestIsSentToForBoard() throws URISyntaxException {
        response = boardRequests.deleteBoard(boardId);
    }

    @Given("PUT Request is sent to update Board Name")
    public void putRequestIsSentToUpdateBoard() throws URISyntaxException {
        response = boardRequests.putBoard(boardId, "name", updatedBoardName );
    }

    @And("Verify Status line should be {string}")
    public void verifyStatusLineCodeShouldBe(String code) {
        assertEquals("Actual name should match the expected name", response.statusLine(), code);
    }


    @And("Verify response header has following details")
    public void verifyResponseHeaderHasFollowingDetails(DataTable dataTable) {
        List<Map<String, String>> headers = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> header : headers) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                response.then().header(entry.getKey(), equalTo(entry.getValue()));
            }
        }
    }

    @And("Verify response time is less than {int} ms")
    public void verifyResponseTimeIsLessThanMs(int responseTime) {
        assertTrue(response.time() < responseTime);
    }


    @And("Verify response schema for {string}")
    public void verifyTheResponseSchemaString(String schemaName) {
        response.then().assertThat().body(matchesJsonSchemaInClasspath(schemaName + "Schema.json"));
    }
}
