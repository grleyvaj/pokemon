Feature: Pokemon SOAP WebService - Get Local Area Encounters

  Scenario: Get Local Area Encounters by location name (list not empty)
    Given a Pokemon SOAP LocalAreaEncounters request with name "bulbasaur"
    When I call the SOAP getLocalAreaEncounter operation
    Then I receive LocalAreaEncounters and the list is not empty
