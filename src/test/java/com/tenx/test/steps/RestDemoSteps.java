package com.tenx.test.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.response.Response;
import com.tenx.test.dto.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static com.tenx.test.apisupport.RestApiCalls.*;
import static com.tenx.test.util.DateUtil.getCurrentDateAsString;
import static com.tenx.test.util.DateUtil.getCurrentDatePlusMonthAsString;

/**
 * Created by hemanth.shivashankrappa on May, 2018
 */
public class RestDemoSteps {
    private static Properties props = new Properties();
    private Response responseBody;
    private List<Integer> bookings;
    private String jsonAsString;
    private ObjectMapper mapper = new ObjectMapper();
    private String apiEndPoint;

    private Booking booking = new Booking();
    private BookingDates bookingDates = new BookingDates();
    private BookingResponse bookingResponse = new BookingResponse();

    @Given("^api is endPoint is \"([^\"]*)\"$")
    public void apiIsEndPointIs(String apiEndPoint) {
        this.apiEndPoint = apiEndPoint;
    }


    @Then("^the status code should be (\\d+)$")
    public void theStatusCodeShouldBe(int statusCode) {
        Assert.assertEquals("Status code is not as expected", statusCode, responseBody.statusCode());
    }

    @When("^i make a api call to get the booking data$")
    public void iMakeAApiCallToGetTheBookingData() {
        responseBody = getRequest(apiEndPoint);
        bookings = responseBody.as(List.class);
        for (int i = 0; i < bookings.size(); i++) {
            System.out.println(bookings.get(i));
        }

    }

    @When("^i make a api call to post the booking data$")
    public void iMakeAApiCallToPostTheBookingData() throws IOException {

        booking.setFirstname("Sally");
        booking.setLastname("Rogers");
        booking.setDepositpaid(true);
        booking.setTotalprice(500);
        bookingDates.setCheckin(getCurrentDateAsString());
        bookingDates.setCheckout(getCurrentDatePlusMonthAsString(1));
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("Breakfast");

        jsonAsString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        responseBody = postRequest(apiEndPoint, jsonAsString);
        bookingResponse = responseBody.as(BookingResponse.class);

        props.setProperty("bookingId", bookingResponse.getBookingid().toString());

    }

    @When("^i make a api call to delete the booking data$")
    public void iMakeAApiCallToDeleteTheBookingData() throws JsonProcessingException {

        Credentials credentials = new Credentials();
        jsonAsString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(credentials);

        responseBody = authenticate(apiEndPoint + "/auth", jsonAsString);
        Token cookie = responseBody.as(Token.class);

        responseBody = deleteRequest(apiEndPoint + "/booking/" + props.getProperty("bookingId"), cookie.getToken());
    }

    @And("^print booking id is that is being deleted$")
    public void printBookingIdIsThatIsBeingDeleted() {
        System.out.println("Booking id that is deleted is: " + props.getProperty("bookingId"));
    }

}
