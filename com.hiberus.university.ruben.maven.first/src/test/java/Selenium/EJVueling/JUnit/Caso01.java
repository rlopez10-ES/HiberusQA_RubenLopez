package Selenium.EJVueling.JUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Caso01 {
    WebDriver driver;
    WebDriverWait wait;
    Select selectOption;

    String url = "https://tickets.vueling.com/";


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

        //ACEPTAMOS LAS COOKIES
        WebElement cookies = driver.findElement(By.xpath("//button[@id='onetrust-accept-btn-handler']"));
        cookies.click();

        //DECLARAMOS QUE EL BILLETE SEA IDA Y VUELTA
        WebElement btnIdaVuelta = driver.findElement(By.xpath("//label[@for='AvailabilitySearchInputSearchView_RoundTrip']"));
        btnIdaVuelta.click();

        //PONEMOMOS A BARCELONA COMO ORIGEN Y MADRID COMO DESTINO
        driver.findElement(By.xpath("//input[@id='AvailabilitySearchInputSearchView_TextBoxMarketOrigin1']")).click();
        WebElement selectMadrid = driver.findElement(By.xpath("//a[@data-id-code='BCN']"));
        selectMadrid.click();


        //OTRA FORMA PERO YA LO HACEMOS CON MADRID
        WebElement origen = driver.findElement(By.xpath("//input[@id='AvailabilitySearchInputSearchView_TextBoxMarketDestination1']"));
        origen.sendKeys("MADRID");
        Actions actions = new Actions(driver);
        actions.sendKeys(origen, Keys.ENTER).build().perform();


        //OBTENEMOS LA FECHA ACTUAL Y CALCULAMOS LAS FECHAS DE SALIDA Y VUELTA, 4 DIAS EN BASE A LA FECHA ACTUAL LA FECHA DE IDA
        // Y LA FECHA DE VUELTA A TRES DIAS DE LA FECHA DE IDA
        java.time.LocalDate now = java.time.LocalDate.now();
        java.time.LocalDate fechaSalida = now.plusDays(4);
        java.time.LocalDate fechaVuelta = fechaSalida.plusDays(3);


        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='AvailabilitySearchInputSearchView_TextBoxMarketDestination1']")));

        // BUSCAMOS LOS ELEMENTOS QUE REPRESENTAS LAS FECHAS DE SALIDA Y VUELTA Y LES HACEMOS CLICK

        //WebElement elementoFechaSalida = driver.findElement(By.xpath("//td[@data-handler='selectDay' and @data-month='" + (fechaSalida.getMonthValue()-1) + "' and @data-year='" + fechaSalida.getYear() + "']//a[text()='" + fechaSalida.getDayOfMonth() + "']"));
        WebElement elementoFechaSalida = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[@data-handler='selectDay' and @data-month='" + (fechaSalida.getMonthValue()-1) + "' and @data-year='" + fechaSalida.getYear() + "']//a[text()='" + fechaSalida.getDayOfMonth() + "']")));
        elementoFechaSalida.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@data-handler='selectDay']"))).click();

        WebElement elementoFechaVuelta = driver.findElement(By.xpath("//td[@data-handler='selectDay' and @data-month='" + (fechaVuelta.getMonthValue()-1) + "' and @data-year='" + fechaVuelta.getYear() + "']//a[text()='" + fechaVuelta.getDayOfMonth() + "']"));
        elementoFechaVuelta.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@data-handler='selectDay']"))).click();
    }

/*
    1 SCENARIO: Comprar un billete siendo mayor de 16 años
    GIVEN: dado una persona mayor de 16 años, adulto
    WHEN: cuando trata de comprar un billete de avión
    THEN: lo puede comprar

 */
    @Test
    public void oneAdult() {
        //SELECCIONAMOS LAS PERSONAS
        int numAdultos = 1;


        driver.findElement(By.xpath("//a[@data-n-adults='" + numAdultos + "']")).click();

        //PULSAMOS EL BOTON DE BUSCAR
        driver.findElement(By.xpath("//div[@id='divButtonBuscadorNormal']")).click();


    }

    @Test
    public void oneChild() {
        int children = 1;

        selectOption = new Select(driver.findElement(By.xpath("//select[@id='AvailabilitySearchInputSearchView_DropDownListPassengerType_CHD']")));
        selectOption.selectByValue(""+children+"");

        //PULSAMOS EL BOTON DE BUSCAR
        driver.findElement(By.xpath("//div[@id='divButtonBuscadorNormal']")).click();

    }


    @After
    public void tearDown() {
        //driver.close();
    }
}
