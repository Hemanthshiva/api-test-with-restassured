package com.tenx.test.apisupport;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by hemanth.shivashankrappa on May, 2018
 */
public class RestApiCalls {


    public static Response getRequest(String endPoint) {

        return given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                when().
                get(endPoint);
    }


    public static Response postRequest(String endPoint, String jsonBody) {

        return given().
                contentType(ContentType.JSON).
                with()
                .body(jsonBody).
                        when().
                        post(endPoint).
                        thenReturn();
    }

    public static Response deleteRequest(String endPoint, String cookie) {
        return given()
                .cookie("token", cookie)
                .contentType(ContentType.JSON)
                .when()
                .delete(endPoint)
                .thenReturn();
    }

    public static Response authenticate(String endPoint, String jsonBody) {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .body(jsonBody)
                .post(endPoint)
                .thenReturn();
    }
}
