Feature: (e2e) Validate store

  Scenario Outline: (e2e) Validate create, post order
    Given the following post request that add orders
    And the response code is <statusCode> for the post in store
    And the body contains key petId
    Then the body response contains the <petId> of the order created

    Examples:
      | statusCode | petId   |
      | 200        | 123321 |

  Scenario Outline: (e2e) Validate get, get order
    Given the following get request that brings us the orders by id <orderId>
    And the response is <statusCode> for the id <orderId> in order
    Then the total id contains <orderId> in order

    Examples:
      | statusCode | orderId   |
      | 200        | 123455432 |

  Scenario Outline: (e2e) Validate getByStatus, get order by status
    Given the following get request that brings us the orders by status
    And the response code is <statusCode> for the get
    Then the body response contains "sold" , "available", "pending"

    Examples:
      | statusCode |
      | 200        |

  Scenario Outline: (e2e) Validate delete, delete order
    Given the following post request that add orders
    And the following delete request that delete order
    And the response code is <statusCode> for the delete in store
    Then the body order response key message is "123455432"

    Examples:
      | statusCode |
      | 200        |


