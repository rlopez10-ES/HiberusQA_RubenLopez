@intentorySuite
  Feature: Inventory test suite
    Background: Navigate to the home page
      Given the user is on the home page
      And the user provide the username "<username>" and password "<password>"
      And the user clicks the login button
      And is into the inventory page

    @validateSixProducts
    Scenario Outline: Validate there are six products on the inventory page
      When the user is counts the products
      Then there should be 6 products

      Examples:
        | totalItem | password     |
        | 6         | secret_sauce |

    @productExists
    Scenario Outline: Validate a product exists on the inventory page
      When the user shearch the product "<product>"
      Then there should be a product named product "Sauce Labs Bolt T-Shirt"

      Examples:
        | username      | password     | product                 |
        | standard_user | secret_sauce | Sauce Labs Bolt T-Shirt |

    @addingProduct
    Scenario Outline: add a product to the shopping cart
      When the user clicks the button "<button>" of the product "<product>"
      Then the cart icon will have 1 product added

      Examples:
        | username      | password     | product                 | button       |
        | standard_user | secret_sauce | Sauce Labs Bolt T-Shirt | Add to cart  |


    @removeProduct
    Scenario Outline: remove a product after adding it and verify the shopping cart is empty
      When the user clicks the button "<button>" of the product "<product>"
      And the cart icon will have 1 product added
      And the user clicks the button "<button>" of the product "<product>"
      Then the shopping cart should be empty

      Examples:
        | username      | password     | product                 | button      |
        | standard_user | secret_sauce | Sauce Labs Bolt T-Shirt | Add to cart |
        |               |              | Sauce Labs Bolt T-Shirt | Remove      |

    @addThreeProducts
    Scenario Outline: validate the shopping cart has 3 products after adding three
      When the user clicks the button "Add to cart" of three products
      Then the cart icon should have a 3, meaning three products

      Examples:
        | username      | password     | product                 | button       |
        | standard_user | secret_sauce | Sauce Labs Bolt T-Shirt | Add to cart  |
        |               |              |                         | Remove       |


    @sortZtoA
    Scenario Outline: validate that the products are ordered Z to A after applying the filter "Name (Z to A)"
      When the users selects the filter "Name (Z to A)"
      Then the products should be ordered form Z to A

      Examples:
        | username      | password     | product                 | button       | filter|
        | standard_user | secret_sauce | Sauce Labs Bolt T-Shirt | Add to cart  | Name (Z to A)|
        |               |              |                         | Remove       | |

    @sortByPriceLowToHigh
    Scenario Outline: validate that the products are ordered from price low to high after applying the filter "Price (Low to High)"
      When the users selects the filter "Price (Low to High)"
      Then the products should be ordered from price low to high

      Examples:
        | username      | password     | product                 | button       | filter|
        | standard_user | secret_sauce | Sauce Labs Bolt T-Shirt | Add to cart  | Name (Z to A)|
        |               |              |                         | Remove       | Price (Low to High)|

    @sortByPriceLowToHigh
    Scenario Outline: validate that the products are ordered from price high to low after applying the filter "Price (High to Low)"
      When the users selects the filter "Price (High to Low)"
      Then the products should be ordered from price high to low

      Examples:
        | username      | password     | product                 | button       | filter              |
        | standard_user | secret_sauce | Sauce Labs Bolt T-Shirt | Add to cart  | Name (Z to A)       |
        |               |              |                         | Remove       | Price (Low to High) |
        |               |              |                         | Remove       | Price (High to Low) |

