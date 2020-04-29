Feature: Validating Place API

@Addplace @Regression
Scenario Outline: verify if place is being successfully being added in Google App
Given add Place Payload "<name>" "<language>" "<address>"
When User calls "AddPlaceAPI" with "Get" http request
Then API call got suceess with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_id created maps to "<name>" using "getPlaceAPI"

Examples:
|name   |language|address			|
|AAhouse|english |world class center|
#|BBHouse|Spanish |Batal road        |

@DeletePlace @Regression
Scenario: verify if delete place functionality is working
Given DeletePlace Payload 
When User calls "DeletePlaceAPI" with "Post" http request
Then API call got suceess with status code 200
And "status" in response body is "OK"