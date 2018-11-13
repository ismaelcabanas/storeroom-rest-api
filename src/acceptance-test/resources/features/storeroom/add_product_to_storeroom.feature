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

#  Scenario: Add product into given storeroom with existent product identifier
#    Given path '/storerooms/'
#    And request {id: '7c1f97fc-ed10-4134-b92b-81e94ef54207', name: 'My Storeroom A1'}
#    When method post
#    Then status 201

#    Given path '/storerooms/7c1f97fc-ed10-4134-b92b-81e94ef54207/products'
#    And request {id: 'a62c87ab-68b2-4cad-9224-b29e85a1ac59', name: 'Product A1'}
#    When method post
#    Then status 201

#    Given path '/storerooms/7c1f97fc-ed10-4134-b92b-81e94ef54207/products'
#    And request {id: 'a62c87ab-68b2-4cad-9224-b29e85a1ac59', name: 'Product A2'}
#    When method post
#    Then status 409
