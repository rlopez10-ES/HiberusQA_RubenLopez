package support;

import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class Hooks {
    @Before
    public void before() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/";
    }
}