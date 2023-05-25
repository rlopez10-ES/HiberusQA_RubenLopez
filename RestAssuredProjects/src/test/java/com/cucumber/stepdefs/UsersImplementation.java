package com.cucumber.stepdefs;

import groovy.util.logging.Slf4j;
import io.cucumber.java.*;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.File;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@Slf4j
public class UsersImplementation {
    private Response postUsers = null;
    private Response putUsers = null;
    private Response deleteUsers = null;

    @Before
    public void before() {
        /*
         Response responseGetUsers = given().baseUri("https://reqres.in/api/users?page=2").when().get();
        given().param("page",2).baseUri("https://reqres.in/api/users").get();

           HashMap<String, String> bodyRequestMap = new HashMap<>();
           bodyRequestMap.put("name", "ramlr93");
           bodyRequestMap.put("job", "leader");
        */
        RestAssured.baseURI = "https://reqres.in/api/";
    }

    @Given("the following get request that brings us the users")
    public Response getUsers() {
        Response responseGetUsers = given().log().all().param("page", 2).get("/users");
        return responseGetUsers;
        //return given().log().all().param("page", 2).get("/users");
    }


    @Then("the response is {int}")
    public void validateResponse(int num) {
        assertEquals("The response is not 200", getUsers().statusCode(), num);
    }

    @And("the body response contains the corresponding ids")
    public void theBodyResponseContainsTheCorrespondingIds() {
        getUsers().then().body("data.id", hasItems(7,8,9,10,11,12));

    }


    @Then("the total page contains {int}")
    public void theTotalPageContainsPage(int numPage) {
        getUsers().then().body("total_pages", equalTo(numPage));
    }

    @Given("the following post request that add users")
    public void theFollowingPostRequestThatAddUsers() {
        String fileName = "bodyRequestUsersPost";
        File file = new File("src/test/resources/data", fileName + ".json");

        postUsers = given().contentType(ContentType.JSON).body(file).post("/users");
    }

    @And("the response is {int} for the post")
    public void theResponseIsForThePost(int num) {
        assertEquals("The response is not 201", postUsers.statusCode(), num);
    }

    @And("the body response contains key name")
    public void theBodyResponseContainsKeyName() {
        postUsers.then().body("$", hasKey("id"));
    }

    @Then("the body response contains the {string} of the users created")
    public void theBodyResponseContainsTheOfTheUsersCreated(String valueName) {
        JsonPath jsonPathUsers = new JsonPath(postUsers.body().asString());
        String jsonUsers = jsonPathUsers.getString("name");
        assertEquals("The value of the name field is not the expected", valueName, jsonUsers);
    }

    @Given("the following put request that update users")
    public void theFollowingPutRequestThatUpdateUsers() {
        theFollowingPostRequestThatAddUsers();
        JsonPath jsonPathUsers = new JsonPath(postUsers.body().asString());
        String jsonIdCreate = jsonPathUsers.getString("id");
        HashMap<String, String> bodyRequestMapPut = new HashMap<>();
        bodyRequestMapPut.put("name", "rubenLopez");
        putUsers = given().contentType(ContentType.JSON).body(bodyRequestMapPut).put("/user/" + jsonIdCreate);
                //= given().contentType(ContentType.JSON).body("{\"name\":\"juan\"}").put("/users/2")
    }

    @And("the response is {int} for the put")
    public void theResponseIsForThePut(int num) {
        assertEquals("The response is not 200", putUsers.statusCode(), num);
    }

    @Then("the body response contains update {string}")
    public void theBodyResponseContainsUpdate(String valueUpdatedName) {
        JsonPath jsonPathUsers = new JsonPath(putUsers.body().asString());
        String jsonUserName = jsonPathUsers.getString("name");
        assertEquals("The value of the field name is not the expected",valueUpdatedName, jsonUserName);
    }

    @And("the following delete request that delete user")
    public void theFollowingDeleteRequestThatDeleteUser() {
        JsonPath jsonPathUsers = new JsonPath(postUsers.body().asString());
        String jsonIdCreate = jsonPathUsers.getString("id");
        deleteUsers = given().accept(ContentType.JSON).delete("/users/" + jsonIdCreate);
    }

    @And("the response is {int} for the delete")
    public void theResponseIsForTheDelete(int num) {
        assertEquals("The response is not 204", deleteUsers.statusCode(), num);
    }

    @Then("the body response is empty")
    public void theBodyResponseIsEmpty() {
        assertTrue("The value of the field name is not the expected", deleteUsers.body().asString().isEmpty());
    }
}
