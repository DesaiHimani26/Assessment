package org.example.pages.apiPage;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.support.FileReaderManager;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BoardRequests {


    static String baseURL = FileReaderManager.getInstance().getConfigFileReader().getAPIBaseUrl() + "/boards/";
    static String apiKey = FileReaderManager.getInstance().getConfigFileReader().getAPIKey();
    static String apiToken = FileReaderManager.getInstance().getConfigFileReader().getAPIToken();

    public Response getBoardDetails(String boardID) throws URISyntaxException {

        Response response = given().header("Accept", "application/json").queryParam("key", apiKey).queryParam("token", apiToken)
                .contentType(ContentType.JSON).get(baseURL + boardID);

        response.then().log().all();
        return response;
    }



    public Response postCreateBoard(String boardName) throws URISyntaxException {

        Response response = given().queryParam("name", boardName).queryParam("key", apiKey).queryParam("token", apiToken)
                .contentType(ContentType.JSON).post(baseURL);
        response.then().log().all();
        return response;
    }

    public Response deleteBoard(String boardId) throws URISyntaxException {

        Response response = given().queryParam("key", apiKey).queryParam("token", apiToken)
                .contentType(ContentType.JSON).delete(baseURL + boardId);
        response.then().log().all();
        return response;
    }

    public Response putBoard(String boardId, Map<String, String> queryParams) throws URISyntaxException {
        RequestSpecification request = given().queryParam("key", apiKey).queryParam("token", apiToken)
                .contentType(ContentType.JSON);


        if (queryParams != null) {
            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                request = request.queryParam(entry.getKey(), entry.getValue());
            }
        }

        Response response = request.put(baseURL + boardId);
        response.then().log().all();
        return response;
    }


    public Response putBoard(String boardId, String... queryParams) throws URISyntaxException {
        Map<String, String> queryMap = new HashMap<>();
        if (queryParams.length % 2 != 0) {
            throw new IllegalArgumentException("Query parameters should be in key-value pairs.");
        }
        for (int i = 0; i < queryParams.length; i += 2) {
            queryMap.put(queryParams[i], queryParams[i + 1]);
        }
        return putBoard(boardId, queryMap);
    }


}
