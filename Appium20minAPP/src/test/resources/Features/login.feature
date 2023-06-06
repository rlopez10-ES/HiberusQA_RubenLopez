Feature: loginTestSuite

  Background:Navigate to the login page
    Given the user is on the login page

    Scenario Outline: the user login OK
      When the user click on login option
      And put a valid "<mail>" and "<password>"
      And the user clicks the login button
      Then the user is on the home page

      Examples:
        | mail              | password |
        | rlopezr@hiberus.com | 12345678   |

    Scenario Outline: the user login KO
      When the user click on login option
      And the user put a invalid "<mail>" and "<password>"
      And the user clicks the login button
      Then an error pop up will appear

      Examples:
        | mail              | password |
        | rlopezr@gmail.com | 12345678   |