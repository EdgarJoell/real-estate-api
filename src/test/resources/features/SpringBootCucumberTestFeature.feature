Feature: Rest API functionalities

  Scenario: Agent able to create, update and remove property
    Given A list of properties are available
    When I search for one property by id
    Then property is displayed
    When I add a property to my property list
    Then The property is added
    When I update a property from my property list
    #Then The property is added