package Stepdefs;

import Pages.*;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

public class SectionMenuStepdefs {
    PagesFactory pagesFactory = PagesFactory.getInstance();
    LoginPage loginPage = PagesFactory.getInstance().getLoginPage();
    HomePage homePage = PagesFactory.getInstance().getHomePage();
    PerfilPage perfilPage = pagesFactory.getPerfilPage();
    SectionMenuPage sectionMenuPage = pagesFactory.getSectionMenuPage();


    @When("the user clicks {string} section")
    public void theUserClicksA(String option) {
        sectionMenuPage.clickOptionMenu(option);
    }

    @Then("the user is on the selected {string}")
    public void theUserIsOnTheSelectedSection(String option) {
        assertTrue("This is not " + option + "section", sectionMenuPage.checkMenuOptionSelected(option));
    }
}
