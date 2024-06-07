package stepDefinitions;

import POJO.AddPlace;
import POJO.Location;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import resources.APIResources;
import resources.TestDataBuild;
import resources.utils;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class placeValidationStepDefinition extends utils {
    private RequestSpecification res;
    ResponseSpecification resspec;

    Response response;
    TestDataBuild ts = new TestDataBuild();
    static String place_id;

    @Given("Add Place Payload with {string} {string} {string}")
    public void addPlacePayload(String name, String language, String address) throws IOException {

        res=given().spec(requestSpecification())
                .body(ts.addPlacePayload(name, language, address));

    }

    @When("user calls {string} with {string} http request")
    public void userCallsWithHttpRequest(String resource, String method) {
        //call constructor to get the match the value of resource
        System.out.println("Request Specification:"+res);
        APIResources resourceAPI = APIResources.valueOf(resource);
        resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        if(method.equalsIgnoreCase("POST"))
            response =res.when().post(resourceAPI.getResource());
         else if (method.equalsIgnoreCase("GET"))
            response =res.when().get(resourceAPI.getResource());
    }

    @Then("the API call get success with status code {int}")
    public void theAPICallGetSuccessWithStatusCode(int arg0) {
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String keyValue, String ExpectedValue) {

        Assert.assertEquals(getJsonPath(response,keyValue), ExpectedValue);

    }

    @And("verify place_id created maps to {string} using {string}")
    public void verifyPlace_idCreatedMapsToUsing(String expectedName, String resource) throws IOException {
        place_id = getJsonPath(response,"place_id");
        res=given().spec(requestSpecification()).queryParam("place_id",place_id);
        userCallsWithHttpRequest(resource,"GET");
        String actualName = getJsonPath(response,"name");
        Assert.assertEquals(actualName,expectedName);
    }

    @Given("DeletePlace Payload")
    public void deleteplacePayload() throws IOException {
        res = given().spec(requestSpecification()).body(ts.deletePlacePayload(place_id));
    }
}
