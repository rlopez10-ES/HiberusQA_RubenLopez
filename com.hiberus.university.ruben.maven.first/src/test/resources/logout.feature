@logOutSuite
Feature: Logout test suite

  Background: Navigate to the home page
    Given the user is on the home page

  @loginOK
  Scenario Outline: Login with a valid user
    And the user provide the username "<username>" and password "<password>"
    And the user clicks the login button
    And the user is logged successfully and is into the inventory page
    When the user logout
    Then the user should be on the home page

    Examples:
      | username      | password     |
      | standard_user | secret_sauce |