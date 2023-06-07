Feature: pets


  Background:
    Given url 'https://petstore.swagger.io/v2/'

  Scenario: create a pet and verify the status
    And def pet =
      """
      {
        "id": 13042001,
        "category": {
          "id": 0,
          "name": "string"
        },
        "name": "ruben",
        "photoUrls": [
          "string"
        ],
        "tags": [
          {
            "id": 0,
            "name": "string"
          }
        ],
        "status": "available"
      }
      """

    Given path 'pet'
    And request pet
    When method post
    Then status 200

    * def id = response.id
    * print 'created id is: ', id

    Given path id
    # When method get
    # Then status 200
    # And match response contains user

  Scenario: update the status and verify the change
    And def pet =
      """
      {
        "id": 13042001,
        "category": {
          "id": 0,
          "name": "string"
        },
        "name": "ruben",
        "photoUrls": [
          "string"
        ],
        "tags": [
          {
            "id": 0,
            "name": "string"
          }
        ],
        "status": "sold"
      }
      """

    Given url 'https://petstore.swagger.io/v2/pet'
    And request pet
    When method put
    Then status 200
    And match response.status == 'sold'

  Scenario: update the status and verify the change

    Given path 'pet/findByStatus'
    And param status = 'sold'
    When method get
    Then status 200
    And match response[0].status == 'sold'

  Scenario: delete pet and verify is not avaliable
    And def pet =
      """
      {
        "id": 13042001,
        "category": {
          "id": 0,
          "name": "string"
        },
        "name": "ruben",
        "photoUrls": [
          "string"
        ],
        "tags": [
          {
            "id": 0,
            "name": "string"
          }
        ],
        "status": "available"
      }
      """
    Given path 'pet'
    And request pet
    When method post
    Then status 200

    * def responsePetPost = response
    * print responsePetPost

    Given path 'pet', responsePetPost.id
    When method delete
    Then status 200

    Given path 'pet', responsePetPost.id
    When method get
    Then status 404
