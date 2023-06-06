package Stepdefs;

import Pages.*;
import Support.Hooks;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

import static org.junit.Assert.*;

@Slf4j
public class LoginPageStepdefs {

    PagesFactory pagesFactory = PagesFactory.getInstance();
    LoginPage loginPage = PagesFactory.getInstance().getLoginPage();
    HomePage homePage = PagesFactory.getInstance().getHomePage();



    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        assertTrue("This is not the LOGIN page", loginPage.checkIfLoginOptionExists());
    }

    @When("the user click on login option")
    public void theUserClickOnLoginOption() {
        loginPage.clickLoginOption();
    }

    @And("put a valid {string} and {string}")
    public void putAValidAnd(String mail, String password) {
        loginPage.enterMail(mail);
        loginPage.enterPassword(password);

    }

    @And("the user clicks the login button")
    public void theUserClicksTheLoginButton() {
        pagesFactory.getAndroidDriverDriver().hideKeyboard();
        loginPage.clickLoginButton();
    }

    @Then("the user is on the home page")
    public void theUserIsOnTheHomePage() {
        homePage.clickCookiesAndNotifications();
        assertTrue("This is not the HOME page", homePage.checkIfHomeButtonExists());
    }

    @And("the user put a invalid {string} and {string}")
    public void theUserPutAInvalidAnd(String mail, String password) {
        loginPage.enterMail(mail);
        loginPage.enterPassword(password);
    }

    @Then("an error pop up will appear")
    public void anErrorPopUpWillAppear() {
        assertTrue("The ERROR pop up has not appeared", loginPage.checkIfErrorPopUpAppeared());
    }


}
