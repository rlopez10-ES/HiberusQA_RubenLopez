package Selenoum_JUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {

    WebDriver driver;
    WebDriverWait wait;

    String url = "https://www.saucedemo.com/";
    String username = "standard_user";
    String password = "secrect_sauce";
    String expectedUrl = "https://www.saucedemo.com/inventory.html";

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 5);

        //PASO 1: IR A LA PAGINA WEB
        driver.get(url);
    }

    @Test
    public void validationLoginCorrecto() {
        //PASO 2: ESCRIBIR EL USERNAME
        driver.findElement(By.xpath("//input[@data-test='username']"))
                .sendKeys(username);

        //PASO 3: ESCRIBIR LA CONTRASEÑA
        driver.findElement(By.xpath("//input[@data-test='password']"))
                .sendKeys(password);

        //PASO 4: PULSAR EL BOTON LOGIN
        driver.findElement(By.xpath("//input[@data-test='login-button']"))
                .click();

        //PASO 5: VALIDAR QUE ESTAMOS EN EL HOME
        String urlActual = driver.getCurrentUrl();

        Assert.assertEquals("ERROR: No nos encontramos en la pagina que corresponde "+ urlActual, expectedUrl, urlActual);

    }

    @Test
    public void validationLoginIncorrecto() {

    }

    @After
    public void tearDown() {
        driver.close();
    }
}
