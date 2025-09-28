Feature: Pokemon api rest

  Scenario: Get Pokemon via REST API
    Given a Pokemon named "bulbasaur"
    When I request Pokemon details for "bulbasaur"
    Then I receive its id, name and base experience
