package stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import io.restassured.config.SSLConfig;
import io.restassured.config.HttpClientConfig;
import org.junit.Assert;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

/**
 * Step definitions for Booking API feature.
 * Implements all steps for the refactored feature file.
 */
public class BookingApiStepDefinitions {

    private String baseUrl;
    private Response response;
    private String authToken;
    private Integer bookingId;

    @Given("the booking API base URL is {string}")
    public void the_booking_api_endpoint_is(String endpoint) {
        this.baseUrl = endpoint;

        // Configure RestAssured with SSL and timeout settings
        RestAssured.config = RestAssured.config()
            .sslConfig(new SSLConfig().relaxedHTTPSValidation())
            .httpClient(HttpClientConfig.httpClientConfig()
                .setParam("http.connection.timeout", 60000)
                .setParam("http.socket.timeout", 60000));
    }

    @When("I send a POST request to {string} with booking details")
    public void i_create_a_new_booking_via_the_api(String endpoint) throws IOException {
        String bookingPayload = new String(Files.readAllBytes(
            Paths.get("src/test/resources/json/Booking.json")));

        response = RestAssured.given()
            .baseUri(baseUrl)
            .contentType(ContentType.JSON)
            .body(bookingPayload)
            .post(endpoint);
    }

    @Then("the response status code should be {int}")
    public void the_status_code_should_be(Integer expectedStatusCode) {
        Assert.assertEquals(expectedStatusCode.intValue(), response.getStatusCode());
    }

    @And("I store the booking ID for later use")
    public void i_store_the_booking_id_for_later_use() {
        Integer apiReturnedId = response.jsonPath().getInt("bookingid");
        Assert.assertNotNull("Booking ID should be present in POST response!", apiReturnedId);
        BookingIdContext.bookingId = apiReturnedId;
        bookingId = apiReturnedId;
    }

    @Then("the response should contain a booking ID")
    public void verifyResponseContainsBookingId() {
        Assert.assertNotNull(response.jsonPath().getInt("bookingid"));
    }

    @Given("I have a stored booking ID")
    public void iHaveAStoredBookingId() {
        Assert.assertNotNull("No booking ID has been stored", BookingIdContext.bookingId);
        this.bookingId = BookingIdContext.bookingId;
    }

    @When("I send a POST request to {string} to get an authentication token")
    public void iSendPOSTRequestForAuth(String endpoint) {
        response = RestAssured.given()
            .baseUri(baseUrl)
            .contentType(ContentType.JSON)
            .body("{\"username\":\"admin\",\"password\":\"password123\"}")
            .post(endpoint);
        
        this.authToken = response.jsonPath().getString("token");
    }

    @When("I send a DELETE request to {string} with the authentication token")
    public void iSendDELETERequestWithToken(String endpoint) {
        String finalEndpoint = endpoint.replace("{id}", bookingId.toString());
        response = RestAssured.given()
            .baseUri(baseUrl)
            .header("Cookie", "token=" + authToken)
            .delete(finalEndpoint);
    }

    @And("I log the deleted booking ID")
    public void iLogTheDeletedBookingId() {
        System.out.println("Deleted booking ID: " + bookingId);
    }
}
