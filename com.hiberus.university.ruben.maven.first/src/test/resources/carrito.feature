@shoppingCartSuite
Feature: ShoppingCart test suite
  Background: Navigate to the home page
    Given the user is on the home page
    And the user provide the username "<username>" and password "<password>"
    And the user clicks the login button
    And is into the inventory page
    And add 2 products
    And clicks the shopping cart icon
    And is into the shopping cart


  @validateRemoveProductFromShoppingCart
  Scenario Outline: Remove a product and validate is removed after adding 2
    When the user clicks the button "<button>" of one product
    Then there should be 1 product on the shopping cart

    Examples:
      | username      | password     | product                 | button       |
      | standard_user | secret_sauce | Sauce Labs Bolt T-Shirt | Remove       |