Feature: Add a Storeroom throught a REST API
  In order to manage my storeroom
  As user
  I want a REST API to create a storeroom with a given name

  Background:
    * url baseUrl
    * def storeroomId = call read('classpath:uuidGenerator.js')
    * def storeroomName = call read('classpath:stringGenerator.js') 25

  Scenario: Create storeroom with all the required information
    Given path '/storerooms'
    And request {id: '#(storeroomId)', name: '#(storeroomName)'}
    When method post
    Then status 201
    And header location = '/storerooms/#(storeroomId)'
