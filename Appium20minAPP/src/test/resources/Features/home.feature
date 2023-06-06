Feature: homeTestSuite

  Background:Navigate to the login page
    Given the user is on the login page
    And the user click on login option
    And the user put a valid "rlopezr@hiberus.com" and "12345678"
    And the user clicks the login button
    And the user is on the home page

  Scenario: Check the piece of new title on the home page is the on the page of new page
    When the user clicks on a piece of new
    Then the title of the chosen piece of new is the same on the chosen piece of new page