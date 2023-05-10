Feature: Rest API functionalities

  Scenario: Agent able to create, update and remove property
    Given A list of properties are available
    When I search for one property by id
    Then property is displayed
    When I add a property to my property list
    Then The property is added
    When I update a property from my property list
    Then The property is updated
    When I delete a property from property list
    Then the property is deleted

  Scenario: Agent is able to create sale
    Given A list of sales are available
    When I search for one sale by id
    Then The sale is displayed
    When I add a sale to my sales list
    Then The sale is added

  Scenario: Agent is able to register and login
    Given That an agent is able to register
    # When I login to an account
    # Then JWT key is returned
