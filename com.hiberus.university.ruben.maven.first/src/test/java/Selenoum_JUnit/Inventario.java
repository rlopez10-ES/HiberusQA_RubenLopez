package Selenoum_JUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

import java.util.List;

public class Inventario {
    WebDriver driver;
    WebDriverWait wait;

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

        wait = new WebDriverWait(driver, 5);

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
    Validar que el numero de resultados es 6:
        5. Validar que el numero de productos mostrados es igual a 6.
*/
    @Test
    public void validationNumberProductsIs6() {

        int expectedItems = 5;
        int actualItems = driver.findElements(By.xpath("//div[@class='inventory_item']")).size();

        //PASO 5: VALIDAR QUE HAY 6 PRODUCTOS
            Assert.assertEquals("No hay el numero de productos esperados", expectedItems, actualItems);

    }
/*
    Validar que existe el producto Sauce Labs Bolt T-Shirt en el inventario:
        5. Validar que el producto Sauce Labs Bolt T-Shirt aparece en el inventario.
 */
    @Test
    public void validationProductExist() {

        //PASO 5: QUE UN PRODUCTO EXISTE
        String expectedProduct = "Sauce Labs Bolt T-Shirt";
        List<WebElement> itemsList = driver.findElements(By.className("inventory_item"));

        for (int i = 0 ; i <= itemsList.size() ; i++) {
            // OBTENER EL ELEMENTO ACTUAL
            WebElement item = itemsList.get(i);

            // OBTENER EL NOMBRE DE ESE ELEMENTO
            String itemName = item.findElement(By.className("inventory_item_name")).getText();

            //COMPROBAMOS SI ES EL BUSCAMOS

        }



        Assert.assertEquals("No hay el numero de productos esperados", expectedProduct, actualItems);

    }

    @After
    public void tearDown() {
        driver.close();
    }
}
