Feature: Add a product throught a REST API
  In order to choose a product from product catalog and put it into storeroom
  As owner of storeroom
  I want a REST API that add a product into product catalog

  Background:
    * configure logPrettyRequest = true
    * configure logPrettyResponse = true
    * url baseUrl

  Scenario: with all the required information
    Given path '/products'
    And request {name: 'Huevos'}
    When method post
    Then status 201
    And match $ == {name: 'Huevos'}
    And header location = '/products/#uuid'
