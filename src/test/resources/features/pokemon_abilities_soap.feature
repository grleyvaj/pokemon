Feature: Pokemon SOAP WebService - Get Abilities

  Scenario: Get Abilities list (not empty) for bulbasaur
    When I request Pokemon abilities for "bulbasaur"
    Then I receive abilities list not empty

  Scenario: Verify ability "overgrow" for bulbasaur
    When I request Pokemon abilities for "bulbasaur"
    Then I receive ability "overgrow" with hidden "false" and slot 1

  Scenario: Verify ability "chlorophyll" for bulbasaur
    When I request Pokemon abilities for "bulbasaur"
    Then I receive ability "chlorophyll" with hidden "true" and slot 3
