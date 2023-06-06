Feature: perfilTestSuite

  Background:Navigate to the login page
    Given the user is on the login page
    And the user click on login option
    And the user put a valid "rlopezr@hiberus.com" and "12345678"
    And the user clicks the login button
    And the user is on the home page
    And the user clicks on perfil section

  Scenario: Validate logout
    When the user clicks the button “Cerrar sesion”
    Then the user is on the welcome page

  Scenario: Validate the change of the font size
    When the user clicks the button “Gestionar tamaño texto”
    And the user selects big “Grande” font
    Then the font size is big “Grande”