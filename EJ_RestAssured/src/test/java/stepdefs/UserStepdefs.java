package stepdefs;


import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class UserStepdefs {

    private Response postUser = null;
    private Response postUserList = null;
    private Response putUser = null;
    private Response deleteUser = null;


    @Given("the following post request that add users")
    public void theFollowingPostRequestThatAddUsers() {
        String fileName = "bodyRequestUserPost";
        File file = new File("src/test/resources/data", fileName + ".json");

        postUser = given().contentType(ContentType.JSON).body(file).post("/user");
    }

    @And("the response code is {int} for the post in user")
    public void theResponseCodeIsStatusCodeForThePostInUser(int num) {
        assertEquals("The response is not 200 it is " + num, num, postUser.statusCode());
    }



    @And("the body user contains key message")
    public void theBodyUserContainsKeyAnd() {
        postUser.then().body("$", hasKey("message"));
    }

    @Then("the body response contains the {string} of the user created")
    public void theBodyResponseContainsTheIdOfTheUserCreated(String valueId) {
        postUser.then().body("message", equalTo(valueId));
    }

    @Given("the following post request that add users with a list")
    public void theFollowingPostRequestThatAddUsersWithAList() {
        String fileName = "bodyRequestUserListPost";
        File file = new File("src/test/resources/data", fileName + ".json");

        postUserList = given().contentType(ContentType.JSON).body(file).post("/user/createWithList");
    }

    @And("the response code is {int} for the post list in user")
    public void theResponseCodeIsStatusCodeForThePostListInUser(int num) {
        assertEquals("The response is not 200 it is " + num, num, postUserList.statusCode());
    }

    @Then("the body response contains the message {string}")
    public void theBodyResponseContainsTheNameAndPasswordOfTheUserCreated(String message) {
        JsonPath jsonPathUser = new JsonPath(postUserList.body().asString());
        String jsonMessage = jsonPathUser.getString("message");
        assertEquals("The response does not contains the message ok", message, jsonMessage);
    }


    @Given("the following put request that update users by {string}")
    public void theFollowingPutRequestThatUpdateUsersBy(String valueUsername) {
        String fileName = "bodyRequestUserPut";
        File file = new File("src/test/resources/data", fileName + ".json");
        putUser = given().contentType(ContentType.JSON).body(file).put("/user/" + valueUsername);

    }

    @And("the response code is {int} for the put in user")
    public void theResponseCodeIsStatusCodeForThePutInUser(int num) {
        assertEquals("The response is not 200", num, putUser.statusCode());
    }

    @Then("the body response contains the {string} of the user updated")
    public void theBodyResponseContainsTheOfTheUserUpdated(String valueUpdatedUsername) {
        JsonPath jsonPathUsers = new JsonPath(putUser.body().asString());
        String jsonUserName = jsonPathUsers.getString("message");
        assertEquals("The value of the field name is not the expected",valueUpdatedUsername, jsonUserName);
    }

    @And("the following delete request that delete user by {string}")
    public void theFollowingDeleteRequestThatDeleteUser(String username) {
        JsonPath jsonPathStore = new JsonPath(postUser.body().asString());
        deleteUser = given().accept(ContentType.JSON).delete("/user/" + username);
    }

    @And("the response code is {int} for the delete in user")
    public void theResponseCodeIsStatusCodeForTheDeleteInUser(int num) {
        assertEquals("The response is not 200, it is " + num, num, deleteUser.statusCode());
    }

    @Then("the body user response key message is {string}")
    public void theBodyUserResponseKeyMessageIs(String expectedValue) {
        JsonPath jsonPathPets = new JsonPath(deleteUser.body().asString());
        String jsonMessage = jsonPathPets.getString("message");
        assertEquals("The value of the field name is not the expected", expectedValue, jsonMessage);
    }

    @Given("the following get request that brings us the users by {string}")
    public Response getUserByUsername(String username) {
        return given().log().all().get("/user/" + username);
    }

    @And("the response is {int} for the user {string} in user")
    public void theResponseIsStatusCodeForTheUserInUser(int num, String username) {
        assertEquals("The response is not 200 it is " + num, num, getUserByUsername(username).statusCode());
    }

    @Then("the body user of the response contains the {string}")
    public void theBodyUserOfTheResponseContainsThe(String expectedUser) {
        JsonPath jsonPathUsers = new JsonPath(getUserByUsername("ruben").body().asString());
        String jsonUserName = jsonPathUsers.getString("username");
        assertEquals("The value of the field name is not the expected",expectedUser, jsonUserName);
    }

    @Given("the following get request that login the user")
    public Response theFollowingGetRequestThatLoginTheUser() {
        return given().log().all().param("username", "ruben").param("password", "123456").get("/user/login");
    }


    @And("the response code is {int} for the get in user")
    public void theResponseCodeIsStatusCodeForTheGetInUser(int num) {
        assertEquals("The response is not 200 it is " + num, num, theFollowingGetRequestThatLoginTheUser().statusCode());
    }

    @Then("the body user response contains a message {int}")
    public void theBodyUserResponseContainsAMessageLogged(int num) {
        theFollowingGetRequestThatLoginTheUser().then().body("code", equalTo(num));
    }

    @And("the user logout")
    public Response theUserLogout() {
        return given().log().all().get("/user/logout");
    }

    @Then("the body user response logout contains a message {string}")
    public void theBodyUserResponseLogoutContainsAMessage(String message) {
        theUserLogout().then().body("message", equalTo(message));
    }
}
