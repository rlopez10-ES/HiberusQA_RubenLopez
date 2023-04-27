package EJVueling;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.LocalDate;

public class TestVueling {

    static String url = "https://tickets.vueling.com/";
    static WebDriver driver;
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();

        driver = new ChromeDriver(chromeOptions);
        WebDriverWait wait = new WebDriverWait(driver, 5); //LE DAMOS UN TIEMPO DE ESPERA PARA QUE SALGAN LOS ELEMENTOS
        driver.manage().window().maximize();

        //ABRIMOS LA PAGINA WEB
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


        // BUSCAMOS LOS ELEMENTOS QUE REPRESENTAS LAS FECHAS DE SALIDA Y VUELTA Y LES HACEMOS CLICK
        WebElement departureElement = driver.findElement(By.xpath("//td[@data-month='" + fechaSalida.getMonth() + "' and @data-year='" + fechaSalida.getYear() + "']"));
        departureElement.click();
        WebElement diaSalida = driver.findElement(By.xpath("//a[@class='ui-state-default']"));
        diaSalida.sendKeys();
        WebElement returnElement = driver.findElement(By.xpath("//div[@class='DayPicker-Day' and not(contains(@class, 'DayPicker-Day--disabled'))][@data-full='" + fechaVuelta.toString() + "']"));
        returnElement.click();

        int gfg =2;



    }
}
