Feature: Logging In

  Scenario: Logging In
    Given I am on the "Home" page
    When I click on the login link
    And I fill in the username "username"
    And I fill in the password "password"
    And I click on the login button
    Then I should be logged in

  Scenario: Logging In Fail
    Given I am on the "Home" page
    When I click on the login link
    And I fill in the username "username"
    And I fill in the password "thisisnotthepassword"
    And I click on the login button
    Then I should not be logged in
    And I should be on the "Login" page
