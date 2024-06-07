Feature: Validating Place API's

@AddPlace
  Scenario Outline: Verify if Place is being Successfully added using AddPlaceAPI
        Given Add Place Payload with "<name>" "<language>" "<address>"
        When user calls "AddPlaceAPI" with "Post" http request
        Then the API call get success with status code 200
        And  "status" in response body is "OK"
        And "scope" in response body is "APP"
        And verify place_id created maps to "<name>" using "getPlaceAPI"

        Examples:
          | name    | language | address            |  |
          | AAhouse | English  | World cross center |  |
#          | BBhouse | Spanish  | Sea Cross Center   |  |

@DeletePlace
  Scenario: Verify if Delete place functionality is working
      Given DeletePlace Payload
      When user calls "deletePlaceAPI" with "Post" http request
      Then the API call get success with status code 200
      And  "status" in response body is "OK"