package Selenium.EJ_POM.Stepdefs;

import Selenium.EJ_POM.Pages.InventoryPage;
import Selenium.EJ_POM.Pages.LoginPage;
import Selenium.EJ_POM.Pages.PagesFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

@Slf4j
public class LogoutPageSteps {

    InventoryPage inventoryPage = PagesFactory.getInstance().getInventoryPage();


    @When("the user logout")
    public void theUserLogout() {
        inventoryPage.clickLogout();
    }

    @Then("the user should be on the home page")
    public void theUserShouldBeOnTheHomePage() {
        Assert.assertEquals("No se volvio a la home paga tras hacer logout", LoginPage.PAGE_URL, PagesFactory.getInstance().getDriver().getCurrentUrl() );
    }
}
