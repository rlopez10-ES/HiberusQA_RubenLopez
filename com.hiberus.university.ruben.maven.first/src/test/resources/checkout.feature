@checkOutSuite
Feature: Chekout test suite
  Background: Navigate to the home page
    Given the user is on the home page
    And the user provides the username "username" and password "password"
    And the user clicks the login button
    And is into the inventory page

    And add two products
    And clicks the shopping cart icon
    And is into the shopping cart


  @validateRemoveProductFromShoppingCart
  Scenario Outline: validate (item total) is the sum of the selected products
    When the user clicks the button "Add to cart" of three products
    And clicks the shopping cart icon
    And clicks the button "Checkout"
    And the user prodives the Firts Name, Last Name and Postal Code
    And clicks the button "Continue"
    Then the item total should be the sum of the selected products

    Examples:
      | username      | password     | product                 | button       |
      | standard_user | secret_sauce | Sauce Labs Bolt T-Shirt | Remove       |

  @makeAnOrder
  Scenario Outline: validate that the order is made and a message is displayed
    When the user clicks the button "Add to cart" of one product
    And clicks the shopping cart icon
    And clicks the button "Checkout"
    And the user prodives the Firts Name, Last Name and Postal Code
    And clicks the button "Continue"
    And clicks the button "Finish"
    Then the order should be made and a message should be displayed

    Examples:
      | username      | password     | product                 | button       |
      | standard_user | secret_sauce | Sauce Labs Bolt T-Shirt | Remove       |
      | standard_user | secret_sauce | Sauce Labs Bolt T-Shirt | Checkout       |
      | standard_user | secret_sauce | Sauce Labs Bolt T-Shirt | Finish       |
      | standard_user | secret_sauce | Sauce Labs Bolt T-Shirt | Add to cart       |