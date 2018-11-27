Feature: Add a given product to Storeroom throught a REST API
  In order to fill my storeroom with given products
  As user
  I want a REST API to add a given product to my storeroom

  Background:
    * url baseUrl
    * def storeroomId = callonce read('classpath:uuidGenerator.js')
    * def productId = call read('classpath:uuidGenerator.js')
    * def productName = call read('classpath:stringGenerator.js') 25
    * callonce read('storeroom-create.feature') {storeroomId : '#(storeroomId)'}


  Scenario: Add new product with all the required information to given storeroom
    Given path '/storerooms', storeroomId, 'products'
    And request {id: '#(productId)', name: '#(productName)'}
    When method post
    Then status 201
    And match response contains {name: '#(productName)'}
    And header location = '/storerooms/#(storeroomId)/products/#(productId)'

#  Scenario: Add new product with stock 0
#    Given path '/storerooms', storeroomId, 'products'
#    And request {id: '#(productId)', name: '#(productName)'}
#    When method post
#    Then status 201
#    And match response contains {stock: 0}

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
