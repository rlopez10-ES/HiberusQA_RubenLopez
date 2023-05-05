package Selenium.LoginTestSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class validarLogin_Incorrecto {
    static private String url = "https://www.saucedemo.com/" ;
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
        username.sendKeys("standard_use");

        //ESCRIBIMOS LA CONTRASEÑA
        WebElement password = driver.findElement(By.xpath("//input[@data-test='password']"));
        enviarTecxto(password , "secret_sauce");

        //PULSAMOS EL BOTON DE LOGIN
        WebElement buttonLogin = driver.findElement(By.xpath("//input[@data-test='login-button']"));
        buttonLogin.click();

        //COMPROBAMOS QUE SALTE EL MENSAJE DE ERROR

        try {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h3[@data-test='error']") ) ) );
        } catch (TimeoutException ex) {
            System.out.println("ERROR: el elemento no es visible " + ex);
        }
        WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
        wait.until(ExpectedConditions.visibilityOf(errorMessage));

        if(errorMessage.isDisplayed()) {
            System.out.println("Se desplego el mensaje de error");
        } else {
            System.out.println("No se ha desplegado el mensaje de error");
        }

        driver.close();
    }

    static void enviarTecxto(WebElement elemento, String texto) {
        elemento.clear();
        elemento.sendKeys(texto);
    }

}
