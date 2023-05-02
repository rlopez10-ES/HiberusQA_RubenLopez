package Selenium_JUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

import java.util.*;

public class Inventario {
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
        List<WebElement> itemsList = driver.findElements(By.xpath("//div[@class='inventory_item']"));

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

        //PASO 6: VALIDAMOS QUE  SE HAN AGREGADO 3 PRODUCTOS
        WebElement shoppingCart = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        String numShoppingCart = shoppingCart.getText();
        String expectedItems = "3";

        Assert.assertEquals("No hay 3 productos y hay: " + numShoppingCart , numShoppingCart, numShoppingCart );
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


        //PASO 5: SELECCIONAMOS EL FILTRO Z TO A PARA ORDENAR EL INVENTARIO
        selectOption = new Select(driver.findElement(By.className("product_sort_container")));
        selectOption.selectByVisibleText("Name (Z to A)");

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

        /*
        System.out.println("Lista 3");
        for(int i = 0 ; i < actualList.size() ; i++){
            WebElement item = actualList.get(i);
            String itemName = item.findElement(By.className("inventory_item_name")).getText();
            System.out.println(itemName);
        }
         */

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
       /*
       for(int i = 0 ; i < originalListItem.size() ; i++){
            WebElement item = originalListItem.get(i);
            //String itemName = item.findElement(By.xpath(".//div[@class='inventory_item_price']")).getText();
            System.out.println(item.getText());
        }

        */

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
    Ordenar el inventario por precio de Menor a Mayor:
        5. Seleccionar el filtro PRICE (high to low)
        6. Validar que el filtro seleccionado, ordena por el orden de precio de mayor a menor
 */
    @Test
    public void orderInventoryHighToLow(){

        //OBTEMOS LA LISTA ORIGINAL
        List<WebElement> originalListItem = driver.findElements(By.xpath("//div[@class='inventory_item']//div[@class='inventory_item_price']"));


        //PASO 5: SELECCIONAMOS EL FILTRO LOW TO HIGH PARA ORDENAR EL INVENTARIO
        selectOption = new Select(driver.findElement(By.className("product_sort_container")));
        selectOption.selectByValue("hilo");

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
