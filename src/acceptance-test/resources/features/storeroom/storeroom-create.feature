@ignore
Feature: re-usable feature to create a single storeroom

  Background:
    * def storeroomName = call read('classpath:stringGenerator.js') 25

  Scenario:

    Given url baseUrl
    And path '/storerooms'
    And request {id: '#(storeroomId)', name: '#(storeroomName)'}
    When method post
    Then status 201





