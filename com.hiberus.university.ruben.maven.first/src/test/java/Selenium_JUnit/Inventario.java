package Selenium_JUnit;

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
import org.openqa.selenium.support.ui.WebDriverWait;

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
    public void validationNumberProductsIs6() throws InterruptedException {

        //PASO 5: VALIDAR QUE HAY 6 PRODUCTOS
        int expectedCantidad = driver.findElements(By.xpath("//div[@class='inventory_item']")).size();

        Assert.assertEquals("ERROR: No hay 6 productos y hay: " + expectedCantidad, 6, expectedCantidad);

    }

/*
    Validar que existe el producto Sauce Labs Bolt T-Shirt en el inventario:
        5. Validar que el producto Sauce Labs Bolt T-Shirt aparece en el inventario.
 */
    @Test
    public void validationProductExist() {

        //PASO 5: VALIDAR QUE EN EL INVENTARIO EXISTE UN PRODUCTO

        //OBTENEMOS LOS ITEMS DE LA LISTA
        List<WebElement> itemsList = driver.findElements(By.className("inventory_item"));

        boolean itemExists = false;
        String itemSearch = "Sauce Labs Bolt T-Shirt";

        //RECORREMOS LA LISTA CON UN FOR
        for(int i = 0 ; i < itemsList.size() ; i++){
            WebElement item = itemsList.get(i);
            String itemName = item.findElement(By.className("inventory_item_name")).getText();

            if (itemName.equalsIgnoreCase(itemSearch)) {
                itemExists = true;
                break; //SALIMOS DEL BUCLE CON BREAK SI ENCONTRAMOS EL ELEMENTO
            }
        }

        //VERIFICAMOS SI EL ELEMENTO ESTA EN LA LISTA
        Assert.assertTrue("El producto Sauce Labs Bolt T-Shirt no se encentra en la lista", itemExists);
    }


/*
    Añadir el producto Sauce Labs Bolt T-Shirt al carrito:
        5. Agregar al carrito el producto Sauce Labs Bolt T-Shirt
        6. Validar que, en el icono del carrito, se ha agregado 1 producto.
 */
    @Test
    public void addProductToCart() {

        //PASO 5: AGREGAMOS EL PRODUCTO Sauce Labs Bolt T-Shirt
        String item = "Sauce Labs Bolt T-Shirt";

        WebElement addToCartButton = driver.findElement(By.xpath("//div[text()='" + item + "']/ancestor::div[@class='inventory_item_description']//button"));
        addToCartButton.click();

        //PASO 6: VERIFICAMOS QUE SE HA AÑADIDO AL CARRITO
        WebElement numCart = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        String comprobacion = numCart.getText();

        Assert.assertEquals("No se agrego el producto ya que hay: " + numCart.getText() + " productos en el carrito", "1" , comprobacion);
    }

/*
    Eliminar producto del carrito desde el inventario:
        5. Agregar al carrito el producto Sauce Labs Bolt T-Shirt
        6. Eliminar el producto Sauce Labs Bolt T-Shirt (Boton remove)
        7. Validar que en el icono del carrito se ha eliminado el producto.
 */

    @Test
    public void removeProduct() {

        //PASO 5: AGREGAMOS EL PRODUCTO Sauce Labs Bolt T-Shirt
        String item = "Sauce Labs Bolt T-Shirt";

        WebElement addToCartButton = driver.findElement(By.xpath("//div[text()='" + item + "']/ancestor::div[@class='inventory_item_description']//button[@data-test='add-to-cart-sauce-labs-bolt-t-shirt']"));
        addToCartButton.click();

        //PASO 6: ELIMINAMO EL PRODUCTO
        WebElement removeButton = driver.findElement(By.xpath("//div[text()='" + item + "']/ancestor::div[@class='inventory_item_description']//button[@data-test='remove-sauce-labs-bolt-t-shirt']"));
        removeButton.click();

        //PASO 7: VALIDAMOS QUE SE ELIMINO EL PRODUCTO
        WebElement numCart = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        String comprobacion = numCart.getText();

        Assert.assertEquals("No se elimino el producto ya que hay: " + numCart.getText() + " productos en el carrito", "" , comprobacion);

    }


/*
    Agregar al carrito 3 productos:
        5. Agregar al carrito los 3 productos elegidos al azar.
        6. Validar que, en el icono del carrito, se han agregado los 3 productos.
 */
    @Test
    public void addProductsToCart() {

    }



    @After
    public void tearDown() {
        driver.close();
    }
}
