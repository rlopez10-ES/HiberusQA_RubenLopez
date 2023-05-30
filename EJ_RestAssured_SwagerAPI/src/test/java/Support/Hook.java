package Support;

import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class Hook {
    @Before
    public void before() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/";
    }
}
