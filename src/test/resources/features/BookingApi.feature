Feature: Create and Delete bookings using the booking api

  Scenario: User calls a postBooking web services to create a booking
    Given api is endPoint is "https://restful-booker.herokuapp.com/booking"
    When i make a api call to post the booking data
    Then the status code should be 200
#    Store the booking id into a variable and use it in the next scenario



  Scenario: User calls a deleteBooking web services to delete a booking
    Given api is endPoint is "https://restful-booker.herokuapp.com"
    When i make a api call to delete the booking data
    And print booking id is that is being deleted
    Then the status code should be 201
