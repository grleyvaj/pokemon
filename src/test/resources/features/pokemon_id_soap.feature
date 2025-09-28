Feature: Pokemon SOAP WebService - Get ID

  Scenario: Get Pokemon ID by name via SOAP
    Given a Pokemon SOAP request with name "pikachu"
    When I call the SOAP getId operation
    Then I receive the Pokemon ID
