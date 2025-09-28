Feature: Pokemon SOAP WebService - Get Name

  Scenario: Get Pokemon Name by request
    Given a Pokemon SOAP Name request with name "pikachu"
    When I call the SOAP getName operation
    Then I receive the Pokemon Name "pikachu"
