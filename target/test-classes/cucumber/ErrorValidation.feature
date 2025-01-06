
@tag
Feature: Error Validation
  I want to use this template for my feature file


  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I Landed on Ecommerce Page
    When Login with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | name                | password  |
      | chaitanya@gmail.com | Sairam12 |
