Feature: guardadoTestSuite

  Background:Navigate to the login page
    Given the user is on the login page
    And the user click on login option
    And the user put a valid "rlopezr@hiberus.com" and "12345678"
    And the user clicks the login button
    And the user is on the home page

  Scenario: Check that the saved news are the selected ones
    When the user clicks on a piece of new
    And save piece of new
    And the user click on guardado section
    Then the piece of new is saved on “Guardado” section



