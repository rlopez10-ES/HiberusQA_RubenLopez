package Stepdefs;

import Pages.GuardadoPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.PagesFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import static org.junit.Assert.*;

public class GuardadoPageStepdefs {

    PagesFactory pagesFactory = PagesFactory.getInstance();
    LoginPage loginPage = PagesFactory.getInstance().getLoginPage();
    HomePage homePage = PagesFactory.getInstance().getHomePage();
    GuardadoPage guardadoPage = PagesFactory.getInstance().getGuardadoPage();


    @And("save piece of new")
    public void savePieceOfNew() {
        guardadoPage.savePieceNew();
    }

    @And("the user click on guardado section")
    public void theUserClickOnGuardadoSection() {
        guardadoPage.clickGuardadoSection();
    }

    @Then("the piece of new is saved on “Guardado” section")
    public void thePieceOfNewIsSavedOnGuardadoSection() {
        assertTrue("The title is not the same", guardadoPage.checkTitles());
    }
}
