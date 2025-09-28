Feature: Pokemon SOAP WebService - Get Base Experience

  Scenario: Get Base Experience by name
    When I request Pokemon base experience for "pikachu"
    Then I receive its base experience
