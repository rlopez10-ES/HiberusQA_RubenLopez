package Selenium.EJ_POM.TestSuites;

import Selenium.EJ_POM.Pages.CartPage;
import Selenium.EJ_POM.Pages.InventoryPage;
import Selenium.EJ_POM.Pages.LoginPage;
import Selenium.EJ_POM.Pages.PagesFactory;
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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Carrito {

    WebDriver driver;

    public LoginPage loginPage;
    public InventoryPage inventoryPage;
    public CartPage cartPage;

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

        //HACEMOS EL LOGIN
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
    }
/*
    Eliminar producto desde el carrito:
        5. Agregar al carrito 2 productos elegidos al azar.
        6. Ir al carrito.
        7. Eliminar uno de los productos
        8. Validar que el producto eliminado, no aparece en el carrito.
 */
    @Test
    public void deleteFromShoppingCart() {

        int expectedItems = 2;
        inventoryPage.addRandomProducts(2);

        inventoryPage.clickShoppingCart();

        cartPage.clickRemove(1);

        Assert.assertEquals("No se elimino el producto", expectedItems - 1, cartPage.getItemCount());
    }


    @After
    public void tearDown() {
        driver.close();
    }
}
