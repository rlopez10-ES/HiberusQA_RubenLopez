package Selenium.InventarioTestSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ValidadNumResultados {

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

        //VALIDAMOS QUE SON 6 LOS ITEMS QUE HAY
        List<WebElement> elementos = driver.findElements(By.xpath("//div[@class='inventory_item']"));
        int cantidad = elementos.size();

        /* O CON ESTO
        int cantidad = driver.findElements(By.xpath("//div[@class='inventory_item']")).size();
        */


        if (cantidad != 6){
            System.out.println("No hay 6 objetos en el inventario");
        } else {
            System.out.println("Hay 6 objetos en el inventario");
        }
    }

    static void enviarTexto(WebElement elemento, String texto) {
        elemento.clear();
        elemento.sendKeys(texto);
    }
}
