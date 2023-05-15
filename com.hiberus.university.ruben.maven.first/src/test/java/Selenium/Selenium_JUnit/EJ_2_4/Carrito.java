package Selenium.Selenium_JUnit.EJ_2_4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Carrito {

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
    Eliminar producto desde el carrito:
        5. Agregar al carrito 2 productos elegidos al azar.
        6. Ir al carrito.
        7. Eliminar uno de los productos
        8. Validar que el producto eliminado, no aparece en el carrito.
 */
    @Test
    public void deleteFromShoppingCart() {

        List<WebElement> itemsList = driver.findElements(By.xpath("//div[@class='inventory_item']"));

        //PASO 5: AGREGAMOS 3 PRODUCTOS AL AZAR

        //SELECCIONAMOS 3 PRODUCTOS SIN REPETICION
        ArrayList<Integer> selectedItems = new ArrayList<>();
        while (selectedItems.size() < 3) {
            int index = new Random().nextInt(itemsList.size());
            if (!selectedItems.contains(index)) {
                selectedItems.add(index);
            }
        }

        //AÑADIMOS LOS PRODUCTOS AL CARRITO
        for (int i = 0; i < selectedItems.size(); i++) {
            WebElement productElement = itemsList.get(selectedItems.get(i));
            WebElement addToCartButton = productElement.findElement(By.xpath("//button[text()='Add to cart']"));
            addToCartButton.click();
        }

        //PASO 6: VAMOS AL CARRITO
        driver.findElement(By.xpath("//div[@class='shopping_cart_container']"))
                .click();

        //PASO 7: ELIMINAMOS UNO DE LOS PRODUCTOS AÑADIDOS

        Random rand = new Random();

        List<WebElement> removeButton = driver.findElements(By.xpath("//button[@class='btn btn_secondary btn_small cart_button']"));
        int removeProduct = rand.nextInt(removeButton.size());

        removeButton.get(removeProduct).click();

        List<WebElement> actualItems = driver.findElements(By.xpath("//div[@class='cart_item']"));
        Assert.assertEquals("No se elimino el producto", removeButton.size() - 1, actualItems.size());
    }


    @After
    public void tearDown() {
        driver.close();
    }
}
