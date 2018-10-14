Feature: Add a Storeroom throught a REST API
  In order to manage my storeroom
  As user
  I want a REST API to create a storeroom with a given name

  Background:
    * configure logPrettyRequest = true
    * configure logPrettyResponse = true
    * url baseUrl

  Scenario: Create storeroom with all the required information
    Given path '/storerooms'
    And request {id: 'dfd5056c-0f89-476b-a9eb-f1865d9e36e5', name: 'My Storeroom'}
    When method post
    Then status 201
    And match response == {name: 'My Storeroom'}
    And header location = '/storerooms/dfd5056c-0f89-476b-a9eb-f1865d9e36e5'
