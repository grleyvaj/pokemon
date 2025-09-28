Feature: Pokemon SOAP WebService - Get Held Items

  Scenario: Get Held Items list (not empty) for vileplume
    When I request Pokemon held items for "vileplume"
    Then I receive held items list not empty

  Scenario: Verify held item "absorb-bulb" exists for vileplume
    When I request Pokemon held items for "vileplume"
    Then I receive held item "absorb-bulb" present

  Scenario: Verify version details for "absorb-bulb" include "omega-ruby" with rarity 5
    When I request Pokemon held items for "vileplume"
    Then the held item "absorb-bulb" has version "omega-ruby" with rarity 5
