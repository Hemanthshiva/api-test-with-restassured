Feature: Create and Delete bookings using the booking api

 Scenario: User calls a postBooking web services to create a booking
    Given api endPoint is "https://restful-booker.herokuapp.com/booking"
    When i make a api call to post the booking data
    Then the status code should be 200
#    Store the booking id into a variable and use it in the next scenario


  Scenario: User calls a deleteBooking web services to delete a booking
    Given api endPoint is "https://restful-booker.herokuapp.com/auth"
    And i make a api call to get the token
    And api endPoint is "https://restful-booker.herokuapp.com/booking"
#    Delete endpoint takes booking id as a parameter to delete the booking
#    Token received from /auth endpoint should be passed to delete endpoint
    When i make a api call to delete the booking data
    Then print booking id is that is being deleted
    And the status code should be 201
