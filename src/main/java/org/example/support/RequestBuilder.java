package org.example.support;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.net.URISyntaxException;

import static io.restassured.RestAssured.given;


public class RequestBuilder {


    RequestSpecBuilder builder = new RequestSpecBuilder();
    public static RequestSpecification Request;
    static String baseURI = FileReaderManager.getInstance().getConfigFileReader().getAPIBaseUrl();
    static String apiKey = FileReaderManager.getInstance().getConfigFileReader().getAPIKey();
    static String apiToken = FileReaderManager.getInstance().getConfigFileReader().getAPIToken();


    public RequestBuilder() {

    }


    public static Response postReq(String url) throws URISyntaxException {

        Response response =  given()
                .queryParam("key", apiKey)
                .queryParam("token", apiToken)
                .contentType(ContentType.JSON)
                .post(baseURI);

        return response;

    }

//    public static Response putTReq(String url,Map<String,String> body) throws URISyntaxException {
//        return Request.put(new URI(baseURI+url)+);
//
//    }
//
//    public static ResponseOptions<Response> GetReq(String URL) throws URISyntaxException {
//        return Request.get(new URI(baseURI+URL));
//
//    }
//    public static Response DeleteReq(String pathParams) throws URISyntaxException{
//
//    }

}
