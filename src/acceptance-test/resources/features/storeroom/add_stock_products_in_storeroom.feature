Feature: Add stock to products in storeroom throught a REST API
  As user
  I want a REST API to add stock a given product in my storeroom
  In order to add new amount of a product

  Background:
    * url baseUrl
    * def storeroomId = callonce read('classpath:uuidGenerator.js')
    * def productId = call read('classpath:uuidGenerator.js')
    * def nonExistingProductId = callonce read('classpath:uuidGenerator.js')
    * callonce read('storeroom-create.feature') {storeroomId : '#(storeroomId)'}
    * def productName = call read('classpath:stringGenerator.js') 25

  Scenario: Add more stock to product in storeroom
    Given path '/storerooms', storeroomId, '/products'
    And request {id: '#(productId)', name: '#(productName)'}
    When method post
    Then status 201

    Given path '/storerooms', storeroomId, '/products', productId, '/refill'
    And request {quantity: 2}
    When method post
    Then status 200

  Scenario: Add more stock to non existent product in storeroom
    Given path '/storerooms', storeroomId, '/products', nonExistingProductId, '/refill'
    And request {quantity: 2}
    When method post
    Then status 404

