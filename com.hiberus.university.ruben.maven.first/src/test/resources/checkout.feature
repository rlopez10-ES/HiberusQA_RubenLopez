@checkOutSuite
Feature: Chekout test suite
  Background: Navigate to the home page
    Given the user is on the home page
    And the user provides the username "standard_user" and password "secret_sauce"
    And the user clicks the login button
    And is into the inventory page


  @validateTheSumOfTheSelectedProducts
  Scenario Outline: validate (item total) is the sum of the selected products
    When the user adds 3 products
    And clicks the shopping cart icon
    And clicks the button Checkout
    And the user provides the Firsts Name "<firstName>", Last Name "<lastName>" and Postal Code "<zipCode>"
    And clicks the button Continue
    Then the item total should be the sum of the selected products

    Examples:
      | firstName | lastName | zipCode |
      | Ruben     | Lopez    | 132456  |



  @makeAnOrder
  Scenario Outline: validate that the order is made and a message is displayed
    When the user adds 1 product
    And clicks the shopping cart icon
    And clicks the button Checkout
    And the user provides the Firsts Name "<firstName>", Last Name "<lastName>" and Postal Code "<zipCode>"
    And clicks the button Continue
    And clicks the button Finish
    Then the order should be made and a message should be displayed

    Examples:
      | firstName | lastName | zipCode |
      | Ruben     | Lopez    | 132456  |