package Stepdefs;

import Pages.*;
import io.cucumber.java.en.*;

import static org.junit.Assert.assertTrue;

public class HomePageStepdefs {

    PagesFactory pagesFactory = PagesFactory.getInstance();
    LoginPage loginPage = PagesFactory.getInstance().getLoginPage();
    HomePage homePage = PagesFactory.getInstance().getHomePage();

    @When("the user put a valid {string} and {string}")
    public void theUserPutAValidAnd(String mail, String password) {
        loginPage.enterMail(mail);
        loginPage.enterPassword(password);
    }

    @When("the user clicks on a piece of new")
    public void theUserClicksOnAPieceOfNew() {
    }

    @Then("the title of the chosen piece of new is the same on the chosen piece of new page")
    public void theTitleOfTheChosenPieceOfNewIsTheSameOnTheChosenPieceOfNewPage() {
        assertTrue("The title does not match", homePage.clickPieceOfNew());
    }
}
