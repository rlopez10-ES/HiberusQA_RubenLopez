package Selenium_JUnit.EJ_2_4;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Checkout {

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

        wait = new WebDriverWait(driver, 10);


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
        Comprobar el precio final del Checkout de varios productos:
            5. Agregar al carrito los 3 productos elegidos al azar.
            6. Ir al carrito.
            7. Realizar el Checkout del producto.
            8. Rellenar datos del checkout y continuar.
            9. Finalizar Checkout
            10. Validar que el precio total del pedido (Item total) es la suma del importe de los productos seleccionados en el inventario
     */
    @Test
    public void checkFinalPrice() {

        List<WebElement> itemsList = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));

        //PASO 5: AGREGAMOS 3 PRODUCTOS AL AZAR

        //SELECCIONAMOS 3 PRODUCTOS SIN REPETICION
        int productosAdd = 3;

        ArrayList<Integer> selectedItems = new ArrayList<>();
        while (selectedItems.size() < productosAdd) {
            int index = new Random().nextInt(itemsList.size());
            if (!selectedItems.contains(index)) {
                selectedItems.add(index);
            }
        }

        //REALIZAMOS LA SUMA DE LOS PRODUCTOS QUE SE HAN SELECCIONADO
        double sumaProductos = 0.0 ;
        String sumaTotal = "";

        //AÑADIMOS LOS PRODUCTOS AL CARRITO
        for (int i = 0 ; i < selectedItems.size() ; i++) {
            WebElement productElement = itemsList.get(selectedItems.get(i));

            String price = productElement.findElement(By.xpath("..//div[@class='inventory_item_price']")).getText().replace("$", "");
            sumaTotal = String.valueOf(sumaProductos += Double.parseDouble(price));

            productElement.findElement(By.xpath("..//button[text()='Add to cart']"))
                    .click();
        }

        //PASO 6: VAMOS AL CARRITO
        driver.findElement(By.xpath("//div[@class='shopping_cart_container']"))
                .click();

        //PASO 7: HACEMOS EL CHECKOUT
        driver.findElement(By.xpath("//button[@data-test='checkout']"))
                .click();

        //PASO 8: RELLENAMOS LOS DATOS DEL CHECKOUT Y PULSAMOS CONTINUAR
        driver.findElement(By.xpath("//input[@data-test='firstName']"))
                .sendKeys("Ruben");

        driver.findElement(By.xpath("//input[@data-test='lastName']"))
                .sendKeys("Lopez");

        driver.findElement(By.xpath("//input[@data-test='postalCode']"))
                .sendKeys("12345");

        //PULSAMOS CONTINUAR
        driver.findElement(By.xpath("//input[@data-test='continue']"))
                .click();


        //PASO 10: VALIDAR QUE EL PRECIO TOTAL ES LA SUMA DE LOS PRODUCTOS SELEECIONADOS

        //RECOGEMOS EL PRECIO TOTAL QUE CALCULA LA PAGINA
        WebElement totalPrice = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='summary_subtotal_label']")));

        String []priceText = totalPrice.getText().split("\\$");

        Assert.assertEquals("El total no se corresponde a la suma de los articulos seleccionados", Double.parseDouble(priceText[1]), sumaProductos, 0.01);

    }


    /*
        Realizar un pedido:
            5. Agregar al carrito 1 producto elegido al azar.
            6. Ir al carrito.
            7. Realizar el Checkout del producto.
            8. Rellenar datos del checkout y continuar.
            9. Finalizar Checkout
            10. Validar que el pedido a finalizado correctamente mostrando el mensaje “Your order has been dispatched, and will arrive just as fast as the pony can get there!”
     */
    @Test
    public void makeOrder() {

        List<WebElement> itemsList = driver.findElements(By.xpath("//div[@class='inventory_item']"));

        //PASO 5: AGREGAMOS 3 PRODUCTOS AL AZAR

        //SELECCIONAMOS 3 PRODUCTOS SIN REPETICION
        int productosAdd = 1;

        ArrayList<Integer> selectedItems = new ArrayList<>();
        while (selectedItems.size() < productosAdd) {
            int index = new Random().nextInt(itemsList.size());
            if (!selectedItems.contains(index)) {
                selectedItems.add(index);
            }
        }

        //AÑADIMOS LOS PRODUCTOS AL CARRITO
        for (int i = 0 ; i < selectedItems.size() ; i++) {
            WebElement productElement = itemsList.get(selectedItems.get(i));
            WebElement addToCartButton = productElement.findElement(By.xpath("//button[text()='Add to cart']"));
            addToCartButton.click();
        }

        //PASO 6: VAMOS AL CARRITO
        driver.findElement(By.xpath("//div[@class='shopping_cart_container']"))
                .click();

        //PASO 7: HACEMOS EL CHECKOUT
        driver.findElement(By.xpath("//button[@data-test='checkout']"))
                .click();

        //PASO 8: RELLENAMOS LOS DATOS DEL CHECKOUT Y PULSAMOS CONTINUAR
        driver.findElement(By.xpath("//input[@data-test='firstName']"))
                .sendKeys("Ruben");

        driver.findElement(By.xpath("//input[@data-test='lastName']"))
                .sendKeys("Lopez");

        driver.findElement(By.xpath("//input[@data-test='postalCode']"))
                .sendKeys("12345");

        //PULSAMOS CONTINUAR
        driver.findElement(By.xpath("//input[@data-test='continue']"))
                .click();

        //PASO 9: HACEMOS EL CHECKOUT FINAL PULSANDO FINISH
        driver.findElement(By.xpath("//button[@data-test='finish']"))
                .click();

        //PASO 10: VERIFICAMOS QUE SALE EL MENSAJE DE PEDIDO REALIZADO
        String expectedText = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";

        WebElement validation = driver.findElement(By.xpath("//div[@class='complete-text']"));
        String actualText = validation.getText();

        Assert.assertEquals("No aparece el mismo texto", expectedText, actualText);
    }


    @After
    public void tearDown() {
        driver.close();
    }
}
