package Selenium.InventarioTestSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class VisibilidadRemove {
    private static String url = "https://www.saucedemo.com/" ;
    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();

        driver = new ChromeDriver(chromeOptions);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofSeconds(7)); //LE DAMOS UN TIEMPO DE ESPERA PARA QUE SALGAN LOS ELEMENTOS
        driver.manage().window().maximize();

        //ABRIMOS LA PAGINA WEB
        driver.get(url);

        //ESCRIBIMOS EN USERNAME UN USUARIO INCORRECTO PARA FORZAR ERROR
        WebElement username = driver.findElement(By.xpath("//input[@data-test='username']"));
        username.sendKeys("standard_user");

        //ESCRIBIMOS LA CONTRASEÑA
        WebElement password = driver.findElement(By.xpath("//input[@data-test='password']"));
        enviarTexto(password , "secret_sauce");

        //PULSAMOS EL BOTON DE LOGIN
        WebElement buttonLogin = driver.findElement(By.xpath("//input[@data-test='login-button']"));
        buttonLogin.click();

        //BUSCAMOS EL BOTON DEL PRODUCTO Y LE DAMOS CLICK
        WebElement addToCart = driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-onesie']"));
        addToCart.click();

        Thread.sleep(5000);

        //COMPROBAMOS QUE AHORA ES EL BOTON DE REMOVE
        WebElement btnRemove = driver.findElement(By.xpath("//button[@data-test='remove-sauce-labs-onesie']"));
        String comprobarRmv = btnRemove.getText();

        if (comprobarRmv.equalsIgnoreCase("REMOVE")){
            System.out.println("Se muestra del boton REMOVE");
        } else {
            System.out.println("No ha cambiado a REMOVE");
        }
        /*  OTRA FORMA CON TRY CATCH
        try {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@data-test='remove-sauce-labs-onesie']"))));
        } catch (NoSuchElementException nsee) {
            System.out.println("ERROR: No se encuentra el elemento a comprobar");
        } catch (TimeoutException te) {
            System.out.println("No aparece el boton REMOVE");
        }
        */
    }


    static void enviarTexto(WebElement elemento, String texto) {
        elemento.clear();
        elemento.sendKeys(texto);
    }
}
