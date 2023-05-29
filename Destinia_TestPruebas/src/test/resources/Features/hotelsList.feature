@hotelsListSuite
Feature: Hotels List Test Suite
  Background: Navigate to the hotels list
    Given the user is on the home page
    And the user searchs a hotel
    And the user is on the hotel list page

  Scenario: Verify that the user can change their search preferences
    When the user clicks on change
    Then the preferencences will be enable to edit

  Scenario: Verify that the hotel list page is sort by sales
    When the user clicks on sort by sales
    Then the hotels will be sort by sales

  Scenario: Verify that the hotel list page is sort by assessment
    When the user clicks on sort by assessment
    Then the hotels will be sort by assessment

  Scenario: Verify that the hotel list page is sort by more economic
    When the user clicks on sort by more economic
    Then the hotels will be sort by more economic

