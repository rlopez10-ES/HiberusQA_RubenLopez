package Selenium_JUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VentanasEmergentes {

    WebDriver driver;
    WebDriverWait wait;
    Actions action;
    Select selectOption;

   // String url= "https://demoqa.com/buttons";

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

    @Test
    public void ventaEmergente() {




        //PASO 1: IR A LA PAGINA WEB
        driver.get("https://demoqa.com/buttons");

        WebElement doubleClickButton = driver.findElement(By.xpath("//button[@id='doubleClickBtn']"));
        action.doubleClick(doubleClickButton).perform();


    }

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
        WebElement targetSubSubList = driver.findElement(By.xpath("//a[text()='SUB SUB LIST »']"));
        WebElement targetSubSubItem2 = driver.findElement(By.xpath("//a[text()='Sub Sub Item 2']"));

        action.moveToElement(targetMainItem2).perform();
        action.moveToElement(targetSubSubList).perform();
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


    public void tearDown() {
        driver.close();
    }
}
