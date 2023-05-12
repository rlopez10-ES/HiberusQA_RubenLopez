package Selenium.EJ_POM.Stepdefs;

import Selenium.EJ_POM.Pages.InventoryPage;
import Selenium.EJ_POM.Pages.LoginPage;
import Selenium.EJ_POM.Pages.PagesFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class InventoriPageSteps {

    LoginPage loginPage = PagesFactory.getInstance().getLoginPage();
    InventoryPage inventoryPage = PagesFactory.getInstance().getInventoryPage();

    @And("the user provide the username {string} and password {string}")
    public void theUserProvideTheUsernameAndPassword(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @And("is into the inventory page")
    public void isIntoTheInventoryPage() {
        Assert.assertEquals("the URL is not the inventory Page", InventoryPage.PAGE_URL, PagesFactory.getInstance().getDriver() );
    }

    @When("the user is counts the products")
    public int theUserIsCountsTheProducts() {
        return inventoryPage.numberItemList();
    }

    @Then("there should be {int} products")
    public void thereShouldBeProducts(int numItems) {
        Assert.assertEquals("No hay seis productos", numItems, theUserIsCountsTheProducts());
    }

    @When("the user shearch the product {string}")
    public boolean theUserShearchTheProduct(String productName) {
        return inventoryPage.existProductInInventoryList(productName);
    }

    @Then("there should be a product named product {string}")
    public void thereShouldBeAProductNamedProduct(String productName) {
        Assert.assertTrue(productName, theUserShearchTheProduct(productName));
    }

    @When("the user clicks the button {string} of the product {string}")
    public void theUserClicksTheButtonOfTheProduct(String button, String productName) {
        inventoryPage.itemAddOrRemove(button, productName);
    }

    @Then("the cart icon will have {int} product added")
    public void theCartIconWillHaveProductAdded(int num) {
        Assert.assertEquals(num, inventoryPage.numberProductsCart());
    }


}
