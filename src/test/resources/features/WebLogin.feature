Feature: Login to a portal and search for the an item

  Scenario: Login as a valid user search for an item and get the price
    Given i am on "http://automationpractice.com/index.php"
    And i login using the below credentials
      | username                   | password    |
      | automationguru92@gmail.com | password123 |
    And i search for "T-shirts"
    When i click add to cart
    Then the item should be added cart
    And delete the item from the cart