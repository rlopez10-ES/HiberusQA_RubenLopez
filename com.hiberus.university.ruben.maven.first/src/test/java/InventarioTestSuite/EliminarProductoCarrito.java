package InventarioTestSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EliminarProductoCarrito {
    private static String url = "https://www.saucedemo.com/" ;
    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
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
        WebElement addToCart = driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-onesie']"));
        addToCart.click();

        Thread.sleep(5000);

        //RECOGEMOS EL BOTON DE REMOVE Y LO CLICKAMOS
        WebElement btnRemove = driver.findElement(By.xpath("//button[@data-test='remove-sauce-labs-onesie']"));
        btnRemove.click();

        Thread.sleep(5000);

        //COMPROBAMOS QUE SE HA ELIMINADO DEL CARRITO DEL PRODUCTO
        WebElement shopCart = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        String comprobacion = shopCart.getText();

        if (comprobacion.equalsIgnoreCase("")) {
            System.out.println("No hay nada en el carrito, por lo que se elimino");
        } else {
            System.out.println("Hay productos, por lo que no se elimino");
        }

    }


    static void enviarTexto(WebElement elemento, String texto) {
        elemento.clear();
        elemento.sendKeys(texto);
    }
}
