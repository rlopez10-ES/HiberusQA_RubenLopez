Feature: (e2e) Validate users

  Scenario Outline: (e2e) Validate create, post user
    Given the following post request that add users
    And the response code is <statusCode> for the post in user
    And the body user contains key message
    Then the body response contains the "<id>" of the user created

    Examples:
      | statusCode | id   |
      | 200        | 13042001 |

  Scenario Outline: (e2e) Validate create, post user list
    Given the following post request that add users with a list
    And the response code is <statusCode> for the post list in user
    Then the body response contains the message "ok"

    Examples:
      | statusCode |
      | 200        |

  Scenario Outline: (e2e) Validate update, put user
    Given the following put request that update users by "<username>"
    And the response code is <statusCode> for the put in user
    Then the body response contains the "<usernameUpdated>" of the user updated

    Examples:
      | statusCode | username | usernameUpdated |
      | 200        | ruben   | 13042001     |

  Scenario Outline: (e2e) Validate delete, delete user
    Given the following post request that add users
    And the following delete request that delete user by "<username>"
    And the response code is <statusCode> for the delete in user
    Then the body user response key message is "<username>"

    Examples:
      | statusCode |username |
      | 200        |string   |

  Scenario Outline: (e2e) Validate get, get users
    Given the following get request that brings us the users by "<username>"
    And the response is <statusCode> for the user "<username>" in user
    Then the body user of the response contains the "<username>"

    Examples:
      | statusCode | username |
      | 200        | ruben |

  Scenario Outline: (e2e) Validate login, get user
    Given the following get request that login the user
    And the response code is <statusCode> for the get in user
    Then the body user response contains a message <message>

    Examples:
      | statusCode | message |
      | 200        | 200  |


  Scenario Outline: (e2e) Validate logout, get user
    Given the following get request that login the user
    And the user logout
    And the response code is <statusCode> for the get in user
    Then the body user response logout contains a message "<message>"

    Examples:
      | statusCode | message |
      | 200        | ok      |
