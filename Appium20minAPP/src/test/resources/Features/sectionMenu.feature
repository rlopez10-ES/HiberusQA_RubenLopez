Feature: sectionMenuTestSuite

  Background:Navigate to the login page
    Given the user is on the login page
    And the user click on login option
    And the user put a valid "rlopezr@hiberus.com" and "12345678"
    And the user clicks the login button
    And the user is on the home page

  Scenario Outline: Validate that by clicking a section in the section menu take us to that section
    When the user clicks "<section>" section
    Then the user is on the selected "<section>"

    Examples:
      | section  |
      | home     |
      | explora  |
      | guardado |
      | perfil   |