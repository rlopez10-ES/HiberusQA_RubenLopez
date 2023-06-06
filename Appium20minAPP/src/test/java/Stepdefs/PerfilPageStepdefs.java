package Stepdefs;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.PagesFactory;
import Pages.PerfilPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class PerfilPageStepdefs {

    PagesFactory pagesFactory = PagesFactory.getInstance();
    LoginPage loginPage = PagesFactory.getInstance().getLoginPage();
    HomePage homePage = PagesFactory.getInstance().getHomePage();
    PerfilPage perfilPage = pagesFactory.getPerfilPage();

    @And("the user clicks on perfil section")
    public void theUserClicksOnPerfilSection() {
        perfilPage.clickPerfilSection();

    }

    @When("the user clicks the button “Cerrar sesion”")
    public void theUserClicksTheButtonCerrarSesion() {
        perfilPage.clickLogout();
    }

    @Then("the user is on the welcome page")
    public void theUserIsOnTheWelcomePage() {
        assertTrue("The user is not on the WELCOME PAGE after clicking logout", perfilPage.checkWelcomePage());
    }

    @When("the user clicks the button “Gestionar tamaño texto”")
    public void theUserClicksTheButtonGestionarTamañoTexto() {
        perfilPage.clickChangeTextOption();
    }

    @And("the user selects big “Grande” font")
    public void theUserSelectsBigGrandeFont() {
        perfilPage.clickBigOption();
    }

    @Then("the font size is big “Grande”")
    public void theFontSizeIsBigGrande() {
        assertTrue("The font has not changed", perfilPage.checkOptionChecked());
    }
}
