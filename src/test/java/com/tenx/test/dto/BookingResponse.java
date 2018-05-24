package com.tenx.test.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by hemanth.shivashankrappa on May, 2018
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "bookingid",
        "booking"
})

public class BookingResponse {

    @JsonProperty("bookingid")
    private Integer bookingid;
    @JsonProperty("booking")
    private Booking booking;

    @JsonProperty("bookingid")
    public Integer getBookingid() {
        return bookingid;
    }

    @JsonProperty("booking")
    public Booking getBooking() {
        return booking;
    }

    @JsonProperty("booking")
    public void setBooking(Booking booking) {
        this.booking = booking;
    }

}
