Feature: SIMBAT Login Functionality
  As a user
  I want to be able to login to the SIMBAT system
  So that I can access the system's features

  @login @positive
  Scenario: Successful login with valid credentials
    Given I am on the login page
    When I enter valid email "admin@simbat.disyfa.site"
    And I enter valid password "admin"
    And I click the login button
    Then I should be logged in successfully

  @login @negative
  Scenario Outline: Failed login with invalid credentials
    Given I am on the login page
    When I enter email "<email>"
    And I enter password "<password>"
    And I click the login button
    Then I should remain on the login page
    And the login form should be empty

    Examples:
      | email                    | password           |
      | apalah123@gmail.com     | admin             |
      | admin@simbat.disyfa.site| linggangguliguli  |
      |                         | admin             |
      | admin@simbat.disyfa.site|                   |
      |                         |                   |
