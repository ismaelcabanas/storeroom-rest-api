Feature: Health end-point

  Background:
    * url baseUrl

  Scenario: check the healthy endpoint

    Given path '/actuator/health'
    When method get
    Then status 200