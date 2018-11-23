Feature: Add stock to products in storeroom throught a REST API
  As user
  I want a REST API to add stock a given product in my storeroom
  In order to add new amount of a product

  Background:
    * configure logPrettyRequest = true
    * configure logPrettyResponse = true
    * url baseUrl

  Scenario: Add more stock to product in storeroom
    Given path '/storerooms/'
    And request {id: '724e5192-da0e-489c-83c7-2925a99c9bcc', name: 'My Storeroom B1'}
    When method post
    Then status 201

    Given path '/storerooms/54150b8e-0b94-4992-8044-efd0f8cb3826/products'
    And request {id: '724e5192-da0e-489c-83c7-2925a99c9bcc', name: 'Product B1'}
    When method post
    Then status 201

    Given path '/storerooms/54150b8e-0b94-4992-8044-efd0f8cb3826/products/724e5192-da0e-489c-83c7-2925a99c9bcc/refill'
    And request {quantity: 2}
    When method post
    Then status 201
    And match response == {name: 'Product B1', stock: 2}

    Given path '/storerooms/54150b8e-0b94-4992-8044-efd0f8cb3826/products/724e5192-da0e-489c-83c7-2925a99c9bcc/refill'
    And request {quantity: 1}
    When method post
    Then status 201
    And match response == {name: 'Product B1', stock: 3}

  Scenario: Add more stock to non existent product in storeroom
    Given path '/storerooms/'
    And request {id: '01c499ae-ed12-4ab4-bfd2-040f2c3432a3', name: 'My Storeroom B2'}
    When method post
    Then status 201

    Given path '/storerooms/54150b8e-0b94-4992-8044-efd0f8cb3826/products/ac125a48-a4f5-4718-ac42-07791d4c6a43/refill'
    And request {quantity: 2}
    When method post
    Then status 404

