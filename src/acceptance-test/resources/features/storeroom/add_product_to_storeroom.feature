Feature: Add a given product to Storeroom throught a REST API
  In order to fill my storeroom with given products
  As user
  I want a REST API to add a given product to my storeroom

  Background:
    * configure logPrettyRequest = true
    * configure logPrettyResponse = true
    * url baseUrl

  Scenario: Add product with all the required information to given storeroom
    Given path '/storerooms/'
    And request {id: '41facbbc-caf0-4d4e-a31a-d6e1d4166dd9', name: 'My Storeroom A'}
    When method post
    Then status 201

    Given path '/storerooms/41facbbc-caf0-4d4e-a31a-d6e1d4166dd9/products'
    And request {id: 'feca488a-2fd6-4c91-b56d-7700e04adad2', name: 'Product A'}
    When method post
    Then status 201
    And match response contains {name: 'Product A'}
    And header location = '/storerooms/41facbbc-caf0-4d4e-a31a-d6e1d4166dd9/products/feca488a-2fd6-4c91-b56d-7700e04adad2'

  Scenario: New products have stock zero
    Given path '/storerooms/'
    And request {id: 'cd6e7088-4eb8-4094-a3a0-f6d08faf9715', name: 'My Storeroom A1'}
    When method post
    Then status 201

    Given path '/storerooms/cd6e7088-4eb8-4094-a3a0-f6d08faf9715/products'
    And request {id: '5933f4a2-23a9-4ce4-ab73-e65b451e1d2d', name: 'Product A1'}
    When method post
    Then status 201
    And match response contains {stock: 0}

