package Selenium.EJ_POM.TestSuites;

import Selenium.EJ_POM.Pages.InventoryPage;
import Selenium.EJ_POM.Pages.LoginPage;
import Selenium.EJ_POM.Pages.PagesFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Login {

    WebDriver driver;

    public LoginPage loginPage;


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

    }
/*
    Realizar Login:
        1. Ir a la página https://www.saucedemo.com
        2. Escribir el username standard_user
        3. Escribir el password secret_sauce
        4. Pulsar en el botón del Login.
        5. Validar que hemos accedido correctamente a la página, comprobando que se muestra la URL https://www.saucedemo.com/inventory.html
*/
    @Test
    public void validationLoginCorrecto() {

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        Assert.assertEquals("Login incorrecto", InventoryPage.PAGE_URL, driver.getCurrentUrl());

    }

/*
    Validar mensaje de Login incorrecto:
        1. Ir a la página https://www.saucedemo.com
        2. Escribir el username standard (Introducir mal el usuario para forzar el error)
        3. Escribir el password secret_sauce
        4. Pulsar en el botón del Login
        5. Validar que aparece el WebElement del mensaje de error.
*/
    @Test
    public void validationLoginIncorrecto() {

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("132");
        loginPage.clickLogin();

        Assert.assertTrue("Prueba fallida, no se encuentra el elemento", loginPage.hasUsernamePasswordError());

    }

    @After
    public void tearDown() {
        driver.close();
    }
}
