Feature: Create Account

  Scenario: Create new user account
    Given that the customer navigates to the registration page
    When he inputs valid data and clicks the submit button
    Then he should be registered for the website