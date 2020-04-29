package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResourses;
import resources.TestDataBuild;
import resources.utils;

public class StepDefination extends utils{
	ResponseSpecification resspec;
	RequestSpecification res;
	Response response;
	static String place_id;

	TestDataBuild td=new TestDataBuild();
	@Given("add Place Payload {string} {string} {string}")
	public void add_Place_Payload(String name, String language, String address) throws IOException {


		res=given().spec(requestSpecification())
				.body(td.addPlace(name,language,address));


	}
	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resourses, String method) {
		//Constructor will be called of enum class with value of recourses you can pass
		APIResourses api=APIResourses.valueOf(resourses);
		System.out.println(api.getResourses());

		resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if (method.equalsIgnoreCase("POST"))
		{
			response =res.when().post(api.getResourses());

		}
		else if(method.equalsIgnoreCase("GET"))
		{
			response =res.when().get(api.getResourses());

		}

	}

	@Then("API call got suceess with status code {int}")
	public void api_call_got_suceess_with_status_code(Integer int1) {

		//System.out.println(responseString);
		assertEquals(response.getStatusCode(),200);

	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is1(String key, String value) {

		assertEquals(getResponse(response, key),value);

	}


	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expected, String resourse) throws IOException {

		 place_id=getResponse(response, "place_id");
		res=given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resourse, "GET");
		String actualName=getResponse(response, "name");
		assertEquals(actualName,expected) ;
	}


	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
		
		res=given().spec(requestSpecification()).body(td.deletePayload(place_id));
		
	}


}
