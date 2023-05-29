@homeSuite
Feature: Home test suite

  Background: Navigate to the home page
    Given the user is on the home page

  Scenario: Verify that the delete button is disable when there is an adult
    When the user selects 1 adult
    Then the delete button is disable

  Scenario: Verify that the add buttons of adult and child are disable when there are 9 people
    When the user adds 9 adults and 0 children
    Then the add buttons of adults and children are disable

  Scenario: Verify that the add button of child is disable when the are 6 children
    When the user adds 2 adults and 6 children
    Then the add button of children is disable

  Scenario: Verify that an age box appears after add a child
    When the user adds 1 child
    Then an age box will appear

  Scenario: Verify that a warning message appears after add a child
    When the user adds 1 child
    Then a warning message will appear that the user must specify the age of the child

  Scenario: Verify that the button of add rooms is disable after add 5 rooms and 9 people
    When the user adds 5 rooms
    And there are 9 people
    Then the button of add rooms is disable
