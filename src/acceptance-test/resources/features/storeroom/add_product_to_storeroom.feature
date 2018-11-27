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
    And header location = '/storerooms/#(storeroomId)/products/#(productId)'