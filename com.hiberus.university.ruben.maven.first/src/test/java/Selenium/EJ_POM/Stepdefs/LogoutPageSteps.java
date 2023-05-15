package Selenium.EJ_POM.Stepdefs;

import Selenium.EJ_POM.Pages.InventoryPage;
import Selenium.EJ_POM.Pages.LoginPage;
import Selenium.EJ_POM.Pages.PagesFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

@Slf4j
public class LogoutPageSteps {

    LoginPage loginPage = PagesFactory.getInstance().getLoginPage();
    InventoryPage inventoryPage = PagesFactory.getInstance().getInventoryPage();

    @Given("the user is on the home page")
    public void theUserIsOnTheHomePage() {
        log.info("The user is on the home page");
        loginPage.navigateTo(LoginPage.PAGE_URL);
    }

    @And("the user provides the username {string} and password {string}")
    public void theUserProvidesTheUsernameAndPassword(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @And("the user clicks the login button")
    public void theUserClicksTheLoginButton() {
        loginPage.clickLogin();
    }

    @And("the user is logged successfully and is into the inventory page")
    public void theUserIsLoggedSuccessfullyAndIsIntoTheInventoryPage() {
        Assert.assertEquals("the URL is not the inventory Page", InventoryPage.PAGE_URL, PagesFactory.getInstance().getDriver() );
    }

    @When("the user logout")
    public void theUserLogout() {
        inventoryPage.clickLogout();
    }

    @Then("the user should be on the home page")
    public void theUserShouldBeOnTheHomePage() {
        Assert.assertEquals("No se volvio a la home paga tras hacer logout", LoginPage.PAGE_URL, PagesFactory.getInstance().getDriver());
    }
}
