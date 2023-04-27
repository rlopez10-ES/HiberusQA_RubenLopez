package InventarioTestSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IncrementoValorCarrito {

    private static String url = "https://www.saucedemo.com/" ;
    static WebDriver driver;

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();

        driver = new ChromeDriver(chromeOptions);
        WebDriverWait wait = new WebDriverWait(driver, 5); //LE DAMOS UN TIEMPO DE ESPERA PARA QUE SALGAN LOS ELEMENTOS
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
        WebElement addToCart = driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-bolt-t-shirt']"));
        addToCart.click();

        //COMPROBAMOS QUE SE AÑADE AL CARRITO Y CAMBIA A 1
        WebElement numCart = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        String comprobacion = numCart.getText();

        if (!comprobacion.equalsIgnoreCase("1")) {
            System.out.println("No hay o se agregaron mas de un producto al carrito");
        } else {
            System.out.println("Se agrego correctamente al carrito");
        }
    }


    static void enviarTexto(WebElement elemento, String texto) {
        elemento.clear();
        elemento.sendKeys(texto);
    }
}
