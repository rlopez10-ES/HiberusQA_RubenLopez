package Selenium.EJ_POM.TestSuites;

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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LogOut {

    WebDriver driver;

    String url = "https://www.saucedemo.com/";
    public LoginPage loginPage;
    public InventoryPage inventoryPage;


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

        //HACEMOS EL LOGIN
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
    }

/*
    Comprobar el Logout
        5. Realizar el Logout.
        6. Validar que el logout se ha realizado llevándonos a la página del Login.
 */
    @Test
    public void checkLogOut() {
        inventoryPage.clickLogout();
        Assert.assertEquals("No se ha hecho el logout", LoginPage.PAGE_URL , driver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        driver.close();
    }

}
