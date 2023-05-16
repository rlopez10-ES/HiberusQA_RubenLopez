@inventorySuite
Feature: Inventory test suite

  Background: Navigate to the home page
    Given the user is on the home page
    And the user provide the username "standard_user" and password "secret_sauce"
    And the user clicks the login button
    And is into the inventory page

  @validateSixProducts
  Scenario Outline: Validate there are six products on the inventory page
    When the user counts the products
    Then there should be 6 products

    Examples:
      | totalItem | password     |
      | 6         | secret_sauce |

  @productExists
  Scenario Outline: Validate a product exists on the inventory page
    When the user search the product "<product>"
    Then there should be a product named product "Sauce Labs Bolt T-Shirt"

    Examples:
      | product                 |
      | Sauce Labs Bolt T-Shirt |

  @addingProduct
  Scenario Outline: add a product to the shopping cart
    When the user clicks the button "<button>" of the product "<product>"
    Then the cart icon will have "1" product added

    Examples:
      | product                 | button      |
      | Sauce Labs Bolt T-Shirt | add-to-cart |


  @removeProduct
  Scenario Outline: remove a product after adding it and verify the shopping cart is empty
    When the user clicks the button "<button>" of the product "<product>"
    And the cart icon will have "1" product added
    And the user clicks the button "remove" of the product "<product>"
    Then the shopping cart should be empty

    Examples:
      | product                 | button      |
      | Sauce Labs Bolt T-Shirt | add-to-cart |

  @addThreeProducts
  Scenario Outline: validate the shopping cart has 3 products after adding three
    When the user clicks the button "<button>" of 3 products
    Then the cart icon will have "3" product added

    Examples:
      | button      |
      | add-to-cart |


  @sortZtoA
  Scenario Outline: validate that the products are ordered Z to A after applying the filter "Name (Z to A)"
    When the users selects the filter "<filter>"
    Then the products should be ordered form Z to A

    Examples:
      | filter |
      | za     |


  @sortByPriceLowToHigh
  Scenario Outline: validate that the products are ordered from price low to high after applying the filter "Price (Low to High)"
    When the users selects the filter "<filter>"
    Then the products should be ordered from price low to high

    Examples:
      | filter |
      | lohi   |


  @sortByPriceLowToHigh
  Scenario Outline: validate that the products are ordered from price high to low after applying the filter "Price (High to Low)"
    When the users selects the filter "<filter>"
    Then the products should be ordered from price high to low

    Examples:
      | filter |
      | hilo   |


