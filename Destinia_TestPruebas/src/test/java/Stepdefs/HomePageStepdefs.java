package Stepdefs;

import Pages.*;
import io.cucumber.java.en.*;
import lombok.extern.slf4j.Slf4j;

import static org.junit.Assert.*;

@Slf4j
public class HomePageStepdefs {

    PagesFactory pagesFactory = PagesFactory.getInstance();
    HomePage homePage = pagesFactory.getHomePage();
    @Given("the user is on the home page")
    public void theUserIsOnTheHomePage() {
        log.info("The user is on the home page");
        homePage.navigateTo(HomePage.PAGE_URL);
    }

    @When("the user selects {int} adult")
    public void theUserSelectsAdult(int numAdults) {
        homePage.setNumAdults(numAdults);
    }

    @Then("the delete button is disable")
    public void theDeleteButtonIsDisable() {
        boolean asd = homePage.checkIfButtonEnable()
;        assertFalse("The button is enable and it should not", asd);
    }
}
