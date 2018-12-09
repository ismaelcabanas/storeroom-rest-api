Feature: Consume stock from products in storeroom throught a REST API
  As user
  I want a REST API to consume some quantity from a given product in my storeroom
  In order to feed me

  Background:
    * url baseUrl
    * def storeroomId = callonce read('classpath:uuidGenerator.js')
    * def productId = call read('classpath:uuidGenerator.js')
    * def nonExistingProductId = callonce read('classpath:uuidGenerator.js')
    * callonce read('storeroom-create.feature') {storeroomId : '#(storeroomId)'}
    * def productName = call read('classpath:stringGenerator.js') 25

  Scenario: Consume product in storeroom
    Given path '/storerooms', storeroomId, '/products'
    And request {id: '#(productId)', name: '#(productName)'}
    When method post
    Then status 201

    Given path '/storerooms', storeroomId, '/products', productId, '/refill'
    And request {quantity: 5}
    When method post
    Then status 200

    Given path '/storerooms', storeroomId, '/products', productId, '/consume'
    And request {quantity: 2}
    When method post
    Then status 200

  Scenario: Consume from non existent product in storeroom
    Given path '/storerooms', storeroomId, '/products', nonExistingProductId, '/consume'
    And request {quantity: 2}
    When method post
    Then status 404

