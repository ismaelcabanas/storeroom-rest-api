Feature: Health end-point

  Background:
    * configure logPrettyRequest = true
    * configure logPrettyResponse = true
    * url baseUrl

  Scenario: check the healthy endpoint

    Given path '/actuator/health'
    When method get
    Then status 200