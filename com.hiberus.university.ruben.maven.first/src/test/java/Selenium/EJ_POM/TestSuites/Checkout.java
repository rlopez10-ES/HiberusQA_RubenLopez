package Selenium.EJ_POM.TestSuites;

import Selenium.EJ_POM.Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Checkout {

    WebDriver driver;

    String []action = {"add-to-cart", "remove"};


    public LoginPage loginPage;
    public InventoryPage inventoryPage;
    public CartPage cartPage;
    public CheckoutStepOnePage checkoutStepOnePage;
    public CheckoutStepSecondPage checkoutStepSecondPage;



    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        PagesFactory.start(driver);

        driver.get(LoginPage.PAGE_URL);

        PagesFactory pagesFactory = PagesFactory.getInstance();
        loginPage = pagesFactory.getLoginPage();
        inventoryPage = pagesFactory.getInventoryPage();
        cartPage = pagesFactory.getCartPage();
        checkoutStepOnePage = pagesFactory.getCheckoutStepOnePage();
        checkoutStepSecondPage = pagesFactory.getCheckoutStepSecondPage();

        //HACEMOS EL LOGIN
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
    }


    /*
        Comprobar el precio final del Checkout de varios productos:
            5. Agregar al carrito los 3 productos elegidos al azar.
            6. Ir al carrito.
            7. Realizar el Checkout del producto.
            8. Rellenar datos del checkout y continuar.
            9. Finalizar Checkout
            10. Validar que el precio total del pedido (Item total) es la suma del importe de los productos seleccionados en el inventario
     */
    @Test
    public void checkFinalPrice() {

        inventoryPage.addRandomProducts(3);

        inventoryPage.clickShoppingCart();

        double totalItem = cartPage.sumItemTotalPrice();

        cartPage.clickCheckout();

        checkoutStepOnePage.enterFirstName("Ruben");
        checkoutStepOnePage.enterLastName("Lopez");
        checkoutStepOnePage.enterPostalCode("132546");

        checkoutStepOnePage.clickContinue();

        Assert.assertEquals("El total no se corresponde a la suma de los articulos seleccionados", totalItem, checkoutStepSecondPage.getItemTotal(),0.01);

    }


    /*
        Realizar un pedido:
            5. Agregar al carrito 1 producto elegido al azar.
            6. Ir al carrito.
            7. Realizar el Checkout del producto.
            8. Rellenar datos del checkout y continuar.
            9. Finalizar Checkout
            10. Validar que el pedido a finalizado correctamente mostrando el mensaje “Your order has been dispatched, and will arrive just as fast as the pony can get there!”
     */
    @Test
    public void makeOrder() {

        inventoryPage.addRandomProducts(1);

        inventoryPage.clickShoppingCart();

        cartPage.clickCheckout();

        checkoutStepOnePage.enterFirstName("Ruben");
        checkoutStepOnePage.enterLastName("Lopez");
        checkoutStepOnePage.enterPostalCode("132546");

        checkoutStepOnePage.clickContinue();

        checkoutStepSecondPage.clickFinishButton();

        String expectedText = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";

        Assert.assertEquals("No aparece el mismo texto", expectedText, checkoutStepSecondPage.getFinalText());
    }


    @After
    public void tearDown() {
        driver.close();
    }
}
