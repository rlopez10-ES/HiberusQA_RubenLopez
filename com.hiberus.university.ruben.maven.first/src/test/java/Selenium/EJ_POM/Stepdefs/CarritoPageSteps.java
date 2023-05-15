package Selenium.EJ_POM.Stepdefs;

import Selenium.EJ_POM.Pages.CartPage;
import Selenium.EJ_POM.Pages.InventoryPage;
import Selenium.EJ_POM.Pages.LoginPage;
import Selenium.EJ_POM.Pages.PagesFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class CarritoPageSteps {
    LoginPage loginPage = PagesFactory.getInstance().getLoginPage();
    InventoryPage inventoryPage = PagesFactory.getInstance().getInventoryPage();
    CartPage cartPage = PagesFactory.getInstance().getCartPage();

    @And("add {int} products")
    public void addProducts(int num) {
        inventoryPage.addRandomProducts(num);
    }

    @And("clicks the shopping cart icon")
    public void clicksTheShoppingCartIcon() {
        inventoryPage.clickShoppingCart();
    }

    @And("is into the shopping cart")
    public void isIntoTheShoppingCart() {
        Assert.assertEquals("No es la pagina del carrito", InventoryPage.PAGE_URL, PagesFactory.getInstance().getDriver() );
    }

    @When("the user clicks the button {string} of one product")
    public void theUserClicksTheButtonOfOneProduct(String button) {
        cartPage.clickRemove(0);
    }

    @Then("there should be {int} product on the shopping cart")
    public void thereShouldBeProductOnTheShoppingCart(int num) {
        Assert.assertEquals("No se elimino el producto", "1", inventoryPage.numberProductsCart());
    }
}
