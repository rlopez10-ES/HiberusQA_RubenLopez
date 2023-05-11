@intentorySuite
  Feature: Inventory test suite
    Background: Navigate to the home page
      Given the user is on the home page
      And the user provide the username "username" and password "password"
      And the user clicks the login button
      And is into the inventory page

    @validateSixProducts
    Scenario Outline: Validate there are six products on the inventory page
      When the user is looking the inventory page
      Then there should be six products

      Examples:
        | username      | password     |
        | standard_user | secret_sauce |

    @productExists
    Scenario Outline: Validate a product exists on the inventory page
      When the user is looking the inventory page
      Then there should be a product named "product" "Sauce Labs Bolt T-Shirt"

      Examples:
        | username      | password     | product                 |
        | standard_user | secret_sauce | Sauce Labs Bolt T-Shirt |

    @incresingShoppingCart
    Scenario Outline: add a product to the shopping cart


