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

