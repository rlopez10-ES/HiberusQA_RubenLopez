package Selenium.Selenium_JUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClaseAction {

    WebDriver driver;
    WebDriverWait wait;
    Actions action;
    Select selectOption;


    @Before
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 15, 750);

        action = new Actions(driver);

    }


    /*
    Ejercicio 1, ¿Cómo hacer click derecho con Selenium?
        1. Iniciamos sitio web “https://the-internet.herokuapp.com/context_menu”
        2. Hacemos click derecho sobre el cuadrado:
        3. Aceptar el mensaje de alerta.
        4. Cerrar el navegador para finalizar.
     */
    @Test
    public void rightClick() {

        driver.get("https://the-internet.herokuapp.com/context_menu");

        action = new Actions(driver);

        WebElement rightClickBox = driver.findElement(By.xpath("//div[@id='hot-spot']"));

        action.contextClick(rightClickBox).perform();

        driver.switchTo().alert().accept();
    }


    /*
    Ejercicio 2, ¿Cómo hacer doble click con Selenium?
        1. Iniciamos sitio web “https://demoqa.com/buttons”
        2. Hacemos doble click en el botón “Double Click Me”
        3. Validamos que nos muestra el mensaje “You have done a double click”
     */
    @Test
    public void doubleClick() {

        driver.get("https://demoqa.com/buttons");

        action = new Actions(driver);

        WebElement doubleClickButton = driver.findElement(By.xpath("//button[@id='doubleClickBtn']"));

        action.doubleClick(doubleClickButton).perform();

        Assert.assertTrue("El elemto no se ha mostrado", driver.findElement(By.xpath("//p[@id='doubleClickMessage']")).isDisplayed() );
    }


    /*
    Ejercicio 1, ¿Cómo hacer Drag and Drop con Selenium?
        1. Iniciamos sitio web “https://demoqa.com/droppable/”
        2. Encontramos el elemento de origen requerido, es decir, el objeto “Drag me”
        3. Encontramos el elemento de destino requerido, es decir, el objeto “Drop here”
        4. Ahora, realizamos Drag and Drop del elemento “Drag me” al elemento “Drop here”
        5. Verificar el mensaje “Dropped” que se muestra en el elemento “Drop here”
        6. Cerrar el navegador.
     */
    @Test
    public void dropAndDrag() {

        driver.get("https://demoqa.com/droppable/");

        WebElement elementToDrag = driver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement elementToDrop = driver.findElement(By.xpath("//div[@id='droppable']"));

        action.dragAndDrop(elementToDrag, elementToDrop).perform();


        Assert.assertEquals("Elemento no presente", "Dropped!", elementToDrop.getText());
    }



    /*
    Ejercicio 1, ¿Cómo hacer Mouse Hover con Selenium?
        1. Iniciamos sitio web “https://demoqa.com/menu/”
        2. Encontrar el elemento “Main Item 2”
        3. Ahora mover el mouse sobre el elemento “Main Item 2”
        4. Encontrar el elemento requerido “SUB LIST”
        5. Mover el mouse al elemento “SUB SUB LIST”
        6. Encontrar el elemento “Sub Sub Item 2”
        7. Hacer click en el element “Sub Sub Item 2”
        8. Cerrar el navegador.
     */
    @Test
    public void mouseHover() {

        driver.get("https://demoqa.com/menu/");

        WebElement targetMainItem2 = driver.findElement(By.xpath("//a[contains(text(),'Main Item 2')]"));
        action.moveToElement(targetMainItem2).perform();

        WebElement targetSubSubList = driver.findElement(By.xpath("//a[text()='SUB SUB LIST »']"));
        action.moveToElement(targetSubSubList).perform();

        WebElement targetSubSubItem2 = driver.findElement(By.xpath("//a[text()='Sub Sub Item 2']"));
        action.moveToElement(targetSubSubItem2).perform();
    }


    /*
       Ejercicio 2, ¿Cómo hacer Mouse Hover con Selenium?
           1. Iniciamos sitio web “https://the-internet.herokuapp.com/hovers”
           2. Encontrar el elemento, en este caso será la tercera imagen
           3. Ahora mover el mouse sobre el elemento encontrado anteriormente.
           4. Encontrar el elemento requerido “View Profile”
           5. Mover el mouse al elemento “View Profile”
           6. Hacer click en el element “View Profile”
           7. Validar que accedemos a la URL https://the-internet.herokuapp.com/users/3
           8. Cerrar el navegador.
        */
    @Test
    public void mouseHover02() {

        driver.get("https://the-internet.herokuapp.com/hovers");

        WebElement image = driver.findElement(By.xpath("//div[3]/img")); //"(//div[@class='figure'])[3]"

        action.moveToElement(image).perform();

        WebElement viewProfile = driver.findElement(By.xpath("//a[contains(@href, '3')]"));
        action.moveToElement(viewProfile).perform();

        viewProfile.click();

        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals("No estamos en la pagina esperada", "https://the-internet.herokuapp.com/users/3", actualUrl);

    }


    /*
 Ejercicio 1, ¿Cómo hacer Keyboard Events con Selenium?
     1. Navegue a “https://demoqa.com/text-box”
     2. Ingresar el nombre completo: “El de uno mismo”
     3. Ingresar “email”: “prueba@pruebaQA.test”
     4. Ingresar la “Current Address”: “La que uno quiera”
     5. Hacer click en el cuadro de texto de la “Current Address” y copiar la “Current Address”.
     6. Pegar la dirección copiada en el cuadro de texto de la “Permanent Address”
     7. Validar que el texto de la “Current Address” y de la “Permanent Address” son el  mismo.
  */

    @Test
    public void keyBoardEvents() {

        driver.get("https://demoqa.com/text-box");
        action = new Actions(driver);

        driver.findElement(By.xpath("//input[@id='userName']"))
                .sendKeys("Ruben Lopez");

        driver.findElement(By.xpath("//input[@id='userEmail']"))
                .sendKeys("prueba@pruebaQA.test");

        WebElement currentAddress = driver.findElement(By.xpath("//input[@id='currentAddress']"));
        currentAddress.sendKeys("hgdfghdfghdslghkzjdhfguihrguhGFYHUGigfirgh");

        currentAddress.sendKeys(Keys.CONTROL);
        currentAddress.sendKeys("A");

        currentAddress.sendKeys(Keys.CONTROL);
        currentAddress.sendKeys("C");

        currentAddress.sendKeys(Keys.TAB);

        WebElement permanentAddress=driver.findElement(By.id("permanentAddress"));

        currentAddress.sendKeys(Keys.CONTROL);
        currentAddress.sendKeys("V");

        Assert.assertEquals(currentAddress.getAttribute("value"),permanentAddress.getAttribute("value"));

    }



    @After
    public void tearDown() {
        driver.close();
    }
}
