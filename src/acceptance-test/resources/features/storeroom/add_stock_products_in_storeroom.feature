Feature: Add stock to products in storeroom throught a REST API
  As user
  I want a REST API to add stock a given product in my storeroom
  In order to add new amount of a product

  Background:
    * configure logPrettyRequest = true
    * configure logPrettyResponse = true
    * url baseUrl

  Scenario: New product has zero stock
    Given path '/storerooms/'
    And request {id: '1eb88160-883c-4c15-9132-1b5c7c97f32e', name: 'My Storeroom B'}
    When method post
    Then status 201

    Given path '/storerooms/1eb88160-883c-4c15-9132-1b5c7c97f32e/products'
    And request {id: '2efde223-f269-4c27-bad0-0dd3c8042436', name: 'Product B'}
    When method post
    Then status 201
    And match response == {name: 'Product B', stock: 0}

  Scenario: Add more stock to product in storeroom
    Given path '/storerooms/'
    And request {id: '724e5192-da0e-489c-83c7-2925a99c9bcc', name: 'My Storeroom B1'}
    When method post
    Then status 201

    Given path '/storerooms/54150b8e-0b94-4992-8044-efd0f8cb3826/products'
    And request {id: '724e5192-da0e-489c-83c7-2925a99c9bcc', name: 'Product B1'}
    When method post
    Then status 201

    Given path '/storerooms/54150b8e-0b94-4992-8044-efd0f8cb3826/products/724e5192-da0e-489c-83c7-2925a99c9bcc/add'
    And request {quantity: 2}
    When method post
    Then status 201
    And match response == {name: 'Product B1', stock: 2}

  Scenario: Add more stock to non existent product in storeroom
    Given path '/storerooms/'
    And request {id: '01c499ae-ed12-4ab4-bfd2-040f2c3432a3', name: 'My Storeroom B2'}
    When method post
    Then status 201

    Given path '/storerooms/54150b8e-0b94-4992-8044-efd0f8cb3826/products/ac125a48-a4f5-4718-ac42-07791d4c6a43/add'
    And request {quantity: 2}
    When method post
    Then status 404

