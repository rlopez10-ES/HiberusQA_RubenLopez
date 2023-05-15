package Selenium.Selenium_JUnit.EJ_2_4;

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

import java.time.Duration;

public class LogOut {

    WebDriver driver;
    WebDriverWait wait;
    Select selectOption;

    String url = "https://www.saucedemo.com/";
    String username = "standard_user";
    String usernameBad = "standard";
    String password = "secret_sauce";
    String expectedUrl = "https://www.saucedemo.com/inventory.html";


    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofSeconds(7));


        //PASO 1: IR A LA PAGINA WEB
        driver.get(url);

        //PASO 2: ESCRIBIR EL USERNAME
        driver.findElement(By.xpath("//input[@data-test='username']"))
                .sendKeys(username);

        //PASO 3: ESCRIBIR LA CONTRASEÑA
        driver.findElement(By.xpath("//input[@data-test='password']"))
                .sendKeys(password);

        //PASO 4: PULSAR EL BOTON LOGIN
        driver.findElement(By.xpath("//input[@data-test='login-button']"))
                .click();
    }

/*
    Comprobar el Logout
        5. Realizar el Logout.
        6. Validar que el logout se ha realizado llevándonos a la página del Login.
 */
    @Test
    public void checkLogOut() {

        //PASO 5: HACEMOS EL LOGOUT
        driver.findElement(By.xpath("//a[@id='logout_sidebar_link']"))
                .click();

        String expectedURL = url;
        String actualURL = driver.getCurrentUrl();

        Assert.assertEquals("No se ha hecho el logout", expectedURL , actualURL);

    }

    @After
    public void tearDown() {
        driver.close();
    }

}
