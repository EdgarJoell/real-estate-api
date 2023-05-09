Feature: Rest API functionalities

  Scenario: Agent able to create, update and remove property
    Given A list of properties are available
    When I search for one property by id
    Then property is displayed
