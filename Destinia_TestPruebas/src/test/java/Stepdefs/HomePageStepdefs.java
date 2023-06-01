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

    @When("the user adds {int} adults and {int} children")
    public void theUserAddsAdultsAndChildren(int numAdults, int numChildren) {
        homePage.setNumAdultsChildren(numAdults, numChildren);
    }

    @Then("the delete button is disable")
    public void theDeleteButtonIsDisable() {
        assertFalse("The button is enable and it should not", homePage.checkIfDeleteButtonAdultEnable());
    }

    @Then("the add buttons of adults and children are disable")
    public void theAddButtonsOfAdultsAndChildrenAreDisable() {
        assertFalse("The button to add adults is enable and it should not", homePage.checkIfAddButtonAdultEnable());
        assertFalse("The button to add children is enable and it should not", homePage.checkIfAddButtonChildEnable());
    }

    @Then("the add button of children is disable")
    public void theAddButtonOfChildrenIsDisable() {
        assertFalse("The button to add children is enable and it should not", homePage.checkIfAddButtonChildEnable());
    }

    @Then("an age box will appear")
    public void anAgeBoxWillAppear() {
        assertTrue("The age box has not appeared", homePage.checkIfSetAgeIsDisplayed());
    }

    @Then("a warning message will appear that the user must specify the age of the child")
    public void aWarningMessageWillAppearThatTheUserMustSpecifyTheAgeOfTheChild() {
        assertTrue("The message has not appeared", homePage.checkIfMessageIsDisplayed());
    }

    @When("there are {int} people")
    public void thereArePeople(int arg0) {
        //POR DEFECTO CUANDO AÑADIMOS HABITACIONES LO HACE CON CON ADULTOS
        homePage.setNumAdultsChildren(1,0);
    }

    @And("the user adds {int} rooms")
    public void theUserAddsRooms(int numRooms){
        homePage.addRooms(numRooms);
    }

    @Then("the button of add rooms is disable")
    public void theButtonOfAddRoomsIsDisable() {
        assertFalse("The button to add rooms is displayed and it should not", homePage.checkIfAddRoomIsDisplayed());
    }
}
