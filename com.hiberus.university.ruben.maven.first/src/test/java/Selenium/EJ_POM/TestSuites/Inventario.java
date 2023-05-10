package Selenium.EJ_POM.TestSuites;

import Selenium.EJ_POM.Pages.InventoryPage;
import Selenium.EJ_POM.Pages.LoginPage;
import Selenium.EJ_POM.Pages.PagesFactory;
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

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Inventario {
    WebDriver driver;
    WebDriverWait wait;
    Select selectOption;

    String []action = {"add-to-cart", "remove"};


    public LoginPage loginPage;
    public InventoryPage inventoryPage;


    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        PagesFactory.start(driver);

        driver.get(LoginPage.PAGE_URL);

        PagesFactory pagesFactory = PagesFactory.getInstance();
        loginPage = pagesFactory.getLoginPage();
        inventoryPage = pagesFactory.getInventoryPage();

        //HACEMOS EL LOGIN
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
    }
/*
    Validar que el numero de resultados es 6:
        5. Validar que el numero de productos mostrados es igual a 6.
*/
    @Test
    public void validationNumberProductsIs6() {

        Assert.assertEquals("ERROR: No hay 6 productos y hay: " + inventoryPage.numberItemList(), 6, inventoryPage.numberItemList());

    }

/*
    Validar que existe el producto Sauce Labs Bolt T-Shirt en el inventario:
        5. Validar que el producto Sauce Labs Bolt T-Shirt aparece en el inventario.
 */
    @Test
    public void validationProductExist() {

        String nombreProducto = "Sauce Labs Bolt T-Shirt";

        Assert.assertTrue("El producto que trata de buscar no se encuentra en el inventario", inventoryPage.existProductInInventoryList(nombreProducto));

    }


/*
    Añadir el producto Sauce Labs Bolt T-Shirt al carrito:
        5. Agregar al carrito el producto Sauce Labs Bolt T-Shirt
        6. Validar que, en el icono del carrito, se ha agregado 1 producto.
 */
    @Test
    public void addProductToCart() {

        String item = "Sauce Labs Bolt T-Shirt";
        inventoryPage.itemAddOrRemove(item, action[0]);

        Assert.assertEquals("No se agrego el producto ya que hay: " + inventoryPage.numberProductsCart() + " productos en el carrito", "1" , inventoryPage.numberProductsCart());

    }

/*
    Eliminar producto del carrito desde el inventario:
        5. Agregar al carrito el producto Sauce Labs Bolt T-Shirt
        6. Eliminar el producto Sauce Labs Bolt T-Shirt (Boton remove)
        7. Validar que en el icono del carrito se ha eliminado el producto.
 */

    @Test
    public void removeProduct() {

        String item = "Sauce Labs Bolt T-Shirt";
        inventoryPage.itemAddOrRemove(item, action[0]);

        inventoryPage.itemAddOrRemove(item, action[1]);

        Assert.assertEquals("No se elimino el producto: " + inventoryPage.numberProductsCart() + " productos en el carrito", "" , inventoryPage.numberProductsCart());


    }


/*
    Agregar al carrito 3 productos:
        5. Agregar al carrito los 3 productos elegidos al azar.
        6. Validar que, en el icono del carrito, se han agregado los 3 productos.
 */
    @Test
    public void addProductsToCart() {

        int expectedItems = 3;
        inventoryPage.addRandomProducts(3);

        Assert.assertEquals("No hay 3 productos y hay: " + inventoryPage.numberProductsCart() , String.valueOf(expectedItems), inventoryPage.numberProductsCart() );
    }

/*
    Ordenar el inventario por orden alfabético (Z a la A):
        5. Seleccionar el filtro NAME (Z TO A)
        6. Validar que el filtro seleccionado, ordena por el orden alfabético de la Z a la A
 */
    @Test
    public void orderInventoryZtoA() {

        //OBTEMOS LA LISTA ORIGINAL
        List<WebElement> originalListItem = driver.findElements(By.xpath("//div[@class='inventory_item']"));


        inventoryPage.sortInventory("Name (Z to A)");


        //CREAMOS OTRA LISTA COGIENDO LOS ELEMENTOS ACTUALES DE LA PAGINA TRAS APLICAR EL FILTRO
        List<WebElement> actualList = driver.findElements(By.xpath("//div[@class='inventory_item']"));

        //PASO 6: VALIDAR QUE EL FILTRO REALMENTE ORDENA DE Z TO A

        //ORDENAMOS LA LISTA REALMENTE COGIENDO LA LISTA ORIGINAL
        List<WebElement> orderList = new ArrayList<>(originalListItem);
        orderList.sort((e1, e2) -> {
            String nombre1 = e1.getText().toLowerCase();
            String nombre2 = e2.getText().toLowerCase();

            return nombre2.compareTo(nombre1);
        });


        Assert.assertEquals("La lista no se ordeno al aplicar el filtro", orderList, actualList);

    }

/*
    Ordenar el inventario por precio de Menor a Mayor:
        5. Seleccionar el filtro PRICE (low to high)
        6. Validar que el filtro seleccionado, ordena por el orden de precio de menor a mayor
 */
    @Test
    public void orderInventoryLowToHigh(){

        //OBTEMOS LA LISTA ORIGINAL
        List<WebElement> originalListItem = driver.findElements(By.xpath("//div[@class='inventory_item']//div[@class='inventory_item_price']"));


        //PASO 5: SELECCIONAMOS EL FILTRO LOW TO HIGH PARA ORDENAR EL INVENTARIO
        selectOption = new Select(driver.findElement(By.className("product_sort_container")));
        selectOption.selectByIndex(2);

        //CREAMOS OTRA LISTA COGIENDO LOS ELEMENTOS ACTUALES DE LA PAGINA TRAS APLICAR EL FILTRO
        List<WebElement> actualList = driver.findElements(By.xpath("//div[@class='inventory_item']//div[@class='inventory_item_price']"));


        //PASO 6: VALIDAR QUE EL FILTRO REALMENTE ORDENA DE MENOR A MAYOR

        //ORDENAMOS LA LISTA REALMENTE COGIENDO LA LISTA ORIGINAL
        List<WebElement> orderList = new ArrayList<>(originalListItem);
        Collections.sort(orderList, new Comparator<WebElement>() {
            @Override
            public int compare(WebElement e1, WebElement e2) {
                String precio1 = e1.getText().substring(1);
                String precio2 = e2.getText().substring(1);

                double precioDouble1 = Double.parseDouble(precio1);
                double precioDouble2 = Double.parseDouble(precio2);

                return Double.compare(precioDouble1, precioDouble2);
            }
        });

        Assert.assertEquals("La lista no se ordeno al aplicar el filtro", orderList, actualList);

    }

/*
    Ordenar el inventario por precio de Mayor a menor:
        5. Seleccionar el filtro PRICE (high to low)
        6. Validar que el filtro seleccionado, ordena por el orden de precio de mayor a menor
 */
    @Test
    public void orderInventoryHighToLow(){

        //OBTEMOS LA LISTA ORIGINAL
        List<WebElement> originalListItem = driver.findElements(By.xpath("//div[@class='inventory_item']//div[@class='inventory_item_price']"));


        //PASO 5: SELECCIONAMOS EL FILTRO HIHG TO LOW PARA ORDENAR EL INVENTARIO
        selectOption = new Select(driver.findElement(By.className("product_sort_container")));
        selectOption.selectByValue("hilo");

        //CREAMOS OTRA LISTA COGIENDO LOS ELEMENTOS ACTUALES DE LA PAGINA TRAS APLICAR EL FILTRO
        List<WebElement> actualList = driver.findElements(By.xpath("//div[@class='inventory_item']//div[@class='inventory_item_price']"));


        //PASO 6: VALIDAR QUE EL FILTRO REALMENTE ORDENA DE MAYOR A MENOR

        //ORDENAMOS LA LISTA REALMENTE COGIENDO LA LISTA ORIGINAL
        List<WebElement> orderList = new ArrayList<>(originalListItem);
        Collections.sort(orderList, new Comparator<WebElement>() {
            @Override
            public int compare(WebElement e1, WebElement e2) {
                String precio1 = e1.getText().substring(1);
                String precio2 = e2.getText().substring(1);

                double precioDouble1 = Double.parseDouble(precio1);
                double precioDouble2 = Double.parseDouble(precio2);

                return Double.compare(precioDouble2, precioDouble1);
            }
        });

        Assert.assertEquals("La lista no se ordeno al aplicar el filtro", orderList, actualList);
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
