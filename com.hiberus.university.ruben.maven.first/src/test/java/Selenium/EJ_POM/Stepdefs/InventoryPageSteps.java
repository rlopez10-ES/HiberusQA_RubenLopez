package Selenium.EJ_POM.Stepdefs;

import Selenium.EJ_POM.Pages.InventoryPage;
import Selenium.EJ_POM.Pages.LoginPage;
import Selenium.EJ_POM.Pages.PagesFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;


public class InventoryPageSteps {

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

    @When("the user counts the products")
    public int theUserCountsTheProducts() {
        return inventoryPage.numberItemList();
    }

    @Then("there should be {int} products")
    public void thereShouldBeProducts(int numItems) {
        Assert.assertEquals("No hay seis productos", numItems, theUserCountsTheProducts());
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


    @Then("the shopping cart should be empty")
    public void theShoppingCartShouldBeEmpty() {
        Assert.assertEquals("El carrito no esta vacio" , "", inventoryPage.numberProductsCart());
    }

    @When("the user clicks the button {string} of {int} products")
    public void theUserClicksTheButtonOfProducts(String button, int num) {
        inventoryPage.addRandomProducts(num);
    }

    @Then("the cart icon should have a {int}, meaning three products")
    public void theCartIconShouldHaveAMeaningThreeProducts(int num) {
        Assert.assertEquals("No hay tres productos en el carrito", String.valueOf(num), inventoryPage.numberProductsCart());
    }

    @When("the users selects the filter {string}")
    public void theUsersSelectsTheFilter(String filterOption) {
        inventoryPage.sortInventory(filterOption);
    }

    @Then("the products should be ordered form Z to A")
    public void theProductsShouldBeOrderedFormZToA() {
        ArrayList listas = (ArrayList) inventoryPage.orderZtoA();
        Assert.assertEquals("No se ordeno la lista", listas.get(0), listas.get(1));
    }

    @Then("the products should be ordered from price low to high")
    public void theProductsShouldBeOrderedFromPriceLowToHigh() {
        ArrayList listas = (ArrayList) inventoryPage.orderByPrice("LowToHigh");
        Assert.assertEquals("No se ordeno la lista", listas.get(0), listas.get(1));
    }

    @Then("the products should be ordered from price high to low")
    public void theProductsShouldBeOrderedFromPriceHighToLow() {
        ArrayList listas = (ArrayList) inventoryPage.orderByPrice("HighToLow");
        Assert.assertEquals("No se ordeno la lista", listas.get(0), listas.get(1));
    }
}
