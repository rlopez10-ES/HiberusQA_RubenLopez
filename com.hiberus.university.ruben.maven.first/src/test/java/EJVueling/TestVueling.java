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
    public static void main(String[] args) throws InterruptedException {
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


        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='AvailabilitySearchInputSearchView_TextBoxMarketDestination1']")));

        // BUSCAMOS LOS ELEMENTOS QUE REPRESENTAS LAS FECHAS DE SALIDA Y VUELTA Y LES HACEMOS CLICK
        WebElement elementoFechaSalida = driver.findElement(By.xpath("//td[@data-handler='selectDay' and @data-month='" + (fechaSalida.getMonthValue()-1) + "' and @data-year='" + fechaSalida.getYear() + "']//a[text()='" + fechaSalida.getDayOfMonth() + "']"));
        elementoFechaSalida.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@data-handler='selectDay']")));

        WebElement elementoFechaVuelta = driver.findElement(By.xpath("//td[@data-handler='selectDay' and @data-month='" + (fechaVuelta.getMonthValue()-1) + "' and @data-year='" + fechaVuelta.getYear() + "']//a[text()='" + fechaVuelta.getDayOfMonth() + "']"));
        elementoFechaVuelta.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@data-handler='selectDay']")));

        //SELECCIONAMOS QUE SEA PARA 2 ADULTOS Y 1 NIÑO
        int numAdultos = 2;
        int children = 1;

        driver.findElement(By.xpath("//a[@data-n-adults='" + numAdultos + "' and @id='DropDownListPassengerType_ADT_2']")).click();

        driver.findElement(By.xpath("//select[@id='AvailabilitySearchInputSearchView_DropDownListPassengerType_CHD']//option[@value='" +  children + "']")).click();

        //PULSAMOS EL BOTON DE BUSCAR
        driver.findElement(By.xpath("//div[@id='divButtonBuscadorNormal']")).click();



    }
}
