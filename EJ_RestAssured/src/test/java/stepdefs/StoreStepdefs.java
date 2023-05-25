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

public class StoreStepdefs {

    private Response postStore = null;
    private Response putStore = null;
    private Response deleteStore = null;



    @Given("the following post request that add orders")
    public void theFollowingPostRequestThatAddOrders() {
        String fileName = "bodyRequestStorePost";
        File file = new File("src/test/resources/data", fileName + ".json");

        postStore = given().contentType(ContentType.JSON).body(file).post("/store/order");
    }

    @And("the response code is {int} for the post in store")
    public void theResponseCodeIsStatusCodeForThePost(int num) {
        assertEquals("The response is not 200", postStore.statusCode(), num);
    }

    @And("the body contains key petId")
    public void theBodyContainsKeyShipDate() {
        postStore.then().body("$", hasKey("petId"));
    }

    @Then("the body response contains the {int} of the order created")
    public void theBodyResponseContainsTheOfTheOrderCreated(int valueId) {
        postStore.then().body("petId", equalTo(valueId));
    //assertEquals("The value of the petId field is not the expected", valueId, jsonStore);
    }

    @And("the following delete request that delete order")
    public void theFollowingDeleteRequestThatDeleteOrder() {
        JsonPath jsonPathStore = new JsonPath(postStore.body().asString());
        int jsonIdCreate = jsonPathStore.getInt("id");
        deleteStore = given().accept(ContentType.JSON).delete("/store/order/" + jsonIdCreate);
    }

    @And("the response code is {int} for the delete in store")
    public void theResponseCodeIsStatusCodeForTheDeleteInStore(int num) {
        assertEquals("The response is not 200, it is " + num, num, deleteStore.statusCode());
    }

    @Then("the body order response key message is {string}")
    public void theBodyResponseHasMessage(String expectedValue) {
        JsonPath jsonPathPets = new JsonPath(deleteStore.body().asString());
        String jsonMessage = jsonPathPets.getString("message");
        assertEquals("The value of the field name is not the expected", expectedValue, jsonMessage);
    }

    @Given("the following get request that brings us the orders by id {int}")
    public Response getOrder(int orderId) {
        return given().log().all().get("/store/order/" + orderId);
    }

    @And("the response is {int} for the id {int} in order")
    public void theResponseIsStatusCodeForTheIdOrderIdInOrder(int num, int orderId) {
        assertEquals("The response is not 200 it is " + num, num, getOrder(orderId).statusCode());
    }

    @Then("the total id contains {int} in order")
    public void theTotalIdContains(int valueId) {
        getOrder(valueId);
    }


    @Given("the following get request that brings us the orders by status")
    public Response getOrderByStatus() {
        return given().log().all().get("/store/inventory");
    }

    @And("the response code is {int} for the get")
    public void theResponseCodeIsStatusCodeForTheGet(int num) {
        assertEquals("The response is not 200 it is " + num, getOrderByStatus().statusCode(), num);
    }

    @Then("the body response contains {string} , {string}, {string}")
    public void theBodyResponseContains(String sold, String available, String pending) {
        getOrderByStatus().then().body("$", hasKey(sold));
        getOrderByStatus().then().body("$", hasKey(available));
        getOrderByStatus().then().body("$", hasKey(pending));
    }
}
