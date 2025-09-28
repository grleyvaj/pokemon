Feature: Pokemon detail

  Scenario: Get details of a Pokemon
    Given a Pokemon named "pikachu"
    When I request its details
    Then I receive its id, name, abilities and held items
