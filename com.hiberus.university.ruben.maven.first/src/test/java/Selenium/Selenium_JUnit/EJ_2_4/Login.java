package Selenium.Selenium_JUnit.EJ_2_4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {

    WebDriver driver;
    WebDriverWait wait;

    String url = "https://www.saucedemo.com/";
    String username = "standard_user";
    String usernameBad = "standard";
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
        //PASO 2: ESCRIBIR EL USERNAME
        driver.findElement(By.xpath("//input[@data-test='username']"))
                .sendKeys(usernameBad);

        //PASO 3: ESCRIBIR LA CONTRASEÑA
        driver.findElement(By.xpath("//input[@data-test='password']"))
                .sendKeys(password);

        //PASO 4: PULSAR EL BOTON LOGIN
        driver.findElement(By.xpath("//input[@data-test='login-button']"))
                .click();

        //PASO 5: VALIDAR QUE APARECE EL WEBELEMENT DEL MENSAJE DE ERROR
        WebElement errorMessage = null;
        try {
            errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
            System.out.println("Se muestra: "+ errorMessage.getText());
        } catch (NoSuchElementException nsee) {
            Assert.fail("ERROR: El mensaje de error en el login no se visualiza");
        }


/* COMO PASA TODO ESTO NO ES NECESARIO
        boolean isPresentErrorMenssege = errorMessage.isDisplayed();

        Assert.assertTrue( isPresentErrorMenssege );
*/

    }

    @After
    public void tearDown() {
        driver.close();
    }
}
