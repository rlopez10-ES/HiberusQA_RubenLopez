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

public class PetStepdefs {

    private Response postPets = null;
    private Response putPets = null;
    private Response deletePets = null;
/*
    @Before
    public void before() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/";
    }
    */
    @Given("the following get request that brings us the pet by id {int}")
    public Response getPet(int num) {
        return given().log().all().get("/pet/" + num);
    }

    @Then("the response is {int} for the id {int}")
    public void validateResponse(int num, int petId) {
        assertEquals("The response is not 200 it is " + num, num, getPet(petId).statusCode());
    }

    @Given("the following post request that add pets")
    public void theFollowingPostRequestThatAddPets() {
        String fileName = "bodyRequestPetsPost";
        File file = new File("src/test/resources/data", fileName + ".json");

        postPets = given().contentType(ContentType.JSON).body(file).post("/pet");
    }

    @And("the response code is {int} for the post")
    public void theResponseCodeIsStatusCodeForThePost(int num) {
        assertEquals("The response is not 200", num, postPets.statusCode());
    }

    @And("the body contains key name")
    public void theBodyContainsKeyName() {
        postPets.then().body("$", hasKey("name"));
    }

    @Then("the body response contains the {string} of the pet created")
    public void theBodyResponseContainsTheOfThePetCreated(String valueName) {
        JsonPath jsonPathPets = new JsonPath(postPets.body().asString());
        String jsonPets = jsonPathPets.getString("name");
        assertEquals("The value of the name field is not the expected", valueName, jsonPets);
    }

    @Given("the following put request that update pets")
    public void theFollowingPutRequestThatUpdatePets() {
        //theFollowingPostRequestThatAddPets();
        //JsonPath jsonPathPets = new JsonPath(postPets.body().asString());
        //int jsonIdCreate = jsonPathPets.getInt("id");

        String fileName = "bodyRequestPetsPut";
        File file = new File("src/test/resources/data", fileName + ".json");
        //HashMap<String, String> bodyRequestMapPut = new HashMap<>();
        //bodyRequestMapPut.put("name", "RUBENLOPEZ");
        putPets = given().contentType(ContentType.JSON).body(file).put("/pet");
    }

    @And("the response code is {int} for the put")
    public void theResponseCodeIsStatusCodeForThePut(int num) {
        assertEquals("The response is not 200", num, putPets.statusCode());
    }

    @Then("the body response contains the {string} of the pet updated")
    public void theBodyResponseContainsTheOfThePetUpdated(String valueUpdatedName) {
        JsonPath jsonPathPets = new JsonPath(putPets.body().asString());
        String jsonPetName = jsonPathPets.getString("name");
        assertEquals("The value of the field name is not the expected",valueUpdatedName, jsonPetName);
    }

    @And("the following delete request that delete pet")
    public void theFollowingDeleteRequestThatDeletePet() {
        JsonPath jsonPathPets = new JsonPath(postPets.body().asString());
        int jsonIdCreate = jsonPathPets.getInt("id");
        deletePets = given().accept(ContentType.JSON).delete("/pet/" + jsonIdCreate);
    }

    @And("the response code is {int} for the delete")
    public void theResponseCodeIsStatusCodeForTheDelete(int num) {
        assertEquals("The response is not 200, it is " + num, num, deletePets.statusCode());
    }

    @Then("the body response key message is {string}")
    public void theBodyResponseHasMessage(String expectedValue) {
        JsonPath jsonPathPets = new JsonPath(deletePets.body().asString());
        String jsonMessage = jsonPathPets.getString("message");
        assertEquals("The value of the field name is not the expected", expectedValue, jsonMessage);
    }

    @Then("the total id contains {int}")
    public void theTotalIdContains(int valueId) {
        getPet(valueId);
    }


    @Given("the following get request that brings us the pets by {string}")
    public Response getPetByStatus(String valueStatus) {
        return  given().log().all().param("status", valueStatus).get("pet/findByStatus");
    }

    @And("the response code is {int} for the get and {string}")
    public void theResponseCodeIsStatusCodeForTheGet(int num, String valueStatus) {
        assertEquals("The response is not 200 it is " + num, num, getPetByStatus(valueStatus).statusCode());
    }

    @Then("the total status contains {string}")
    public void theTotalStatusContains(String valueStatus) {
        //JsonPath jsonPath = new JsonPath(getPetByStatus(valueStatus).body().asString());
        getPetByStatus(valueStatus).then().body("status", hasItems(valueStatus));

    }
}


