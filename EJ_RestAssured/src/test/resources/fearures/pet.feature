Feature: (e2e) Validate pets

  @addPet
  Scenario Outline: (e2e) Validate create, post pet
    Given the following post request that add pets
    And the response code is <statusCode> for the post
    And the body contains key name
    Then the body response contains the "<petName>" of the pet created

    Examples:
      | statusCode | petName |
      | 200        | RUBEN   |

  @pets
  Scenario Outline: (e2e) Validate that the response of the pet request is 200
    Given the following get request that brings us the pet by id <userId>
    Then the response is <statusCode> for the id <userId>

    Examples:
      | statusCode | userId |
      | 200        | 123321 |

  Scenario Outline: (e2e) Validate update, put pet
    Given the following put request that update pets
    And the response code is <statusCode> for the put
    Then the body response contains the "<petNameUpdated>" of the pet updated

    Examples:
      | statusCode | petNameUpdated |
      | 200        | RUBENLOPEZ        |

  Scenario Outline: (e2e) Validate get, get pet
    Given the following get request that brings us the pet by id <userId>
    And the response is <statusCode> for the id <userId>
    Then the total id contains <userId>

    Examples:
      | statusCode | userId |
      | 200        | 123321 |

  Scenario Outline: (e2e) Validate delete, delete pet
    Given the following post request that add pets
    And the following delete request that delete pet
    And the response code is <statusCode> for the delete
    Then the body response key message is "123321"

    Examples:
      | statusCode |
      | 200        |

  Scenario Outline: (e2e) Validate getByStatus, get pet by status
    Given the following get request that brings us the pets by "<petStatus>"
    And the response code is <statusCode> for the get and "<petStatus>"
    Then the total status contains "<petStatus>"

    Examples:
      | statusCode | petStatus |
      | 200        | sold      |
      | 200        | pending   |
      | 200        | available |





