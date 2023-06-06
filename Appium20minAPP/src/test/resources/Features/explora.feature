Feature: exploraTestSuite

  Background:Navigate to the login page
    Given the user is on the login page
    And the user click on login option
    And the user put a valid "rlopezr@hiberus.com" and "12345678"
    And the user clicks the login button
    And the user is on the home page
    And the user clicks on explora section
    And the user is on explora section

  Scenario: Check that by selecting a theme show us news about the selected theme
    When the user clicks on a theme
    Then the news are about the chosen theme

  Scenario: Check that by clicking on Ver todos show us all themes
    When the user clicks on “Ver todos” in theme section
    Then the user can see all themes