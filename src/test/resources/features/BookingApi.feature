Feature: Booking API Management
  As a user of the Booking API
  I want to be able to create and delete bookings
  So that I can manage reservations programmatically

  Background:
    Given the booking API base URL is "https://restful-booker.herokuapp.com"

  @order1 @create
  Scenario: Create a new booking
    When I send a POST request to "/booking" with booking details
    Then the response status code should be 200
    And the response should contain a booking ID
    And I store the booking ID for later use

  @order2 @delete
  Scenario: Delete an existing booking
    Given I have a stored booking ID
    When I send a POST request to "/auth" to get an authentication token
    And I send a DELETE request to "/booking/{id}" with the authentication token
    Then the response status code should be 201
    And I log the deleted booking ID
