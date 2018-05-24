package com.tenx.test.apisupport;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by hemanth.shivashankrappa on May, 2018
 */
public class RestApiCalls {

    public static Response authenticate(String endPoint, String jsonBody) {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .body(jsonBody)
                .post(endPoint)
                .thenReturn();
    }
}
