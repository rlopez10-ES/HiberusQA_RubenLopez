@logOutSuite
Feature: Logout test suite
  Background: Navigate to the home page
    Given the user is on the home page
    And the user provide the username "username" and password "password"
    And the user clicks the login button
    And is into the inventory page

  @validateLogout
  Scenario Outline: Validate logout after doing it
    When the user goes to the side menu
    And clicks the option "Logout"
    Then the user should be redirected to the home page

    Examples:
      | username      | password     | option   |
      | standard_user | secret_sauce | Logout   |