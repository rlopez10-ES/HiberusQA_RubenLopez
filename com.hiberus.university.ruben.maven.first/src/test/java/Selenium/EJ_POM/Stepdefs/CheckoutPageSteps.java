package Selenium.EJ_POM.Stepdefs;

import Selenium.EJ_POM.Pages.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

@Slf4j
public class CheckoutPageSteps {
    LoginPage loginPage = PagesFactory.getInstance().getLoginPage();
    InventoryPage inventoryPage = PagesFactory.getInstance().getInventoryPage();
    CartPage cartPage = PagesFactory.getInstance().getCartPage();
    CheckoutStepOnePage checkoutStepOnePage = PagesFactory.getInstance().getCheckoutStepOnePage();
    CheckoutStepSecondPage checkoutStepSecondPage = PagesFactory.getInstance().getCheckoutStepSecondPage();


    @When("the user adds {int} products")
    public void theUserAddsProducts(int num) {
        inventoryPage.addRandomProducts(num);
    }

    @And("clicks the button Checkout")
    public void clicksTheButtonCheckout() {
        cartPage.clickCheckoutButton();
    }

    @And("the user provides the Firsts Name {string}, Last Name {string} and Postal Code {string}")
    public void theUserProvidesTheFirstsNameLastNameAndPostalCode(String firstName, String lastName, String zipCode) {
        checkoutStepOnePage.enterFirstName(firstName);
        checkoutStepOnePage.enterLastName(lastName);
        checkoutStepOnePage.enterPostalCode(zipCode);
    }
    @And("clicks the button Continue")
    public void clicksTheButtonContinue() {
        checkoutStepOnePage.clickContinue();
    }

    @Then("the item total should be the sum of the selected products")
    public void theItemTotalShouldBeTheSumOfTheSelectedProducts() {
        Assert.assertEquals("La suma no corresponde", checkoutStepSecondPage.getItemTotal(), cartPage.sumItemTotalPrice(), 0.01);
    }


    @When("the user adds {int} product")
    public void theUserAddsProduct(int num) {
        inventoryPage.addRandomProducts(num);
    }

    @And("clicks the button Finish")
    public void clicksTheButtonFinish() {
        checkoutStepSecondPage.clickFinishButton();
    }


    @Then("the order should be made and a message should be displayed")
    public void theOrderShouldBeMadeAndAMessageShouldBeDisplayed() {
        Assert.assertTrue("No se mostro el mensaje", checkoutStepSecondPage.getFinalText());
    }



}
