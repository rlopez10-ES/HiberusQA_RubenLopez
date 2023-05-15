package Selenium.EJ_POM.Stepdefs;

import Selenium.EJ_POM.Pages.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

@Slf4j
public class LoginPageSteps {

    //PagesFactory pagesFactory = PagesFactory.getInstance();
    LoginPage loginPage = PagesFactory.getInstance().getLoginPage();

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

    @When("the user clicks the login button")
    public void theUserClicksTheLoginButton() {
        loginPage.clickLogin();
    }

    @Then("the user is logged successfully and is into the inventory page")
    public void theUserIsLoggedSuccessfullyAndIsIntoTheInventoryPage() {
        Assert.assertEquals("the URL is not the inventory Page", InventoryPage.PAGE_URL, PagesFactory.getInstance().getDriver() );
    }

    @Then("the user should be shown an invalid message")
    public void theUserShouldBeShownAnInvalidMessage() {
        Assert.assertTrue(loginPage.hasUsernamePasswordError());
    }
}
