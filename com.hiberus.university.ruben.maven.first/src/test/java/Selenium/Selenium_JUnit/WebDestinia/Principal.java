package Selenium.Selenium_JUnit.WebDestinia;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.DriverManager;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Principal {

    WebDriver driver;
    WebDriverWait wait;
    Select selectOption;
    Actions action;

    String url = "https://destinia.com/";


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

        driver.findElement(By.xpath("//button[@data-qa='oil-YesButton']"))
                .click();



    }

    /*
    1. Seleccionar la ubicacion de Benidorn
    2. La fecha de entrada será 2 semanas a la fecha de hoy y la fecha de salida 5 dias despues a la fecha de entrada
    3. Para la ocupacion que sea para 4 personal adultas
    4. Verificar que nos lleva a otra pagina
    https://online.destinia.com/online/hotels/search?date_unix=1683532708634087061&sortType=bestbuy
    https://online.destinia.com/online/hotels/search?date_unix=1683532738553829061&sortType=bestbuy
     */

    @Test
    public void searchHotel(){

        action = new Actions(driver);

        WebElement setLocation = driver.findElement(By.xpath("//input[@id='hotelsearchwidget0-location']"));

        setLocation.sendKeys("Benidorm");
        setLocation.sendKeys(Keys.DOWN);
        setLocation.sendKeys(Keys.ENTER);


        //OBTENEMOS LA FECHA ACTUAL Y CALCULAMOS LAS FECHAS DE SALIDA Y VUELTA, 4 DIAS EN BASE A LA FECHA ACTUAL LA FECHA DE IDA
        // Y LA FECHA DE VUELTA A TRES DIAS DE LA FECHA DE IDA
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");

        LocalDate now = LocalDate.now();
        LocalDate fechaSalida = now.plusDays(14);
        LocalDate fechaVuelta = fechaSalida.plusDays(5);

        String fechaSalidaFormat = fechaSalida.format(formatter);
        String primeraLetraFechaSalida = fechaSalidaFormat.substring(0, 1).toUpperCase();
        String restoSalida = fechaSalidaFormat.substring(1);

        fechaSalidaFormat = primeraLetraFechaSalida + restoSalida;


        String fechaVueltaFormat = fechaVuelta.format(formatter);
        String primeraLetraFechaVuelta = fechaVueltaFormat.substring(0, 1).toUpperCase();
        String restoVuelta = fechaVueltaFormat.substring(1);

        fechaVueltaFormat = primeraLetraFechaVuelta + restoVuelta;


        driver.findElement(By.xpath("//input[@id='hotelsearchwidget0-checkdates_checkin-flatpickr']"))
                        .click();

        driver.findElement(By.xpath("//span[@aria-label='" + fechaSalidaFormat + "']")).click();
        driver.findElement(By.xpath("//span[@aria-label='" + fechaVueltaFormat + "']")).click();

        driver.findElement(By.xpath("//button[@id='hotelsearchwidget0-occupancy-customelement-menubt']"))
                        .click();





    }//*[@id="hotelsearchwidget0-occupancy-customelement"]/div[2]/div[1]/div/div[2]/div[2]/button[2]


    @After
    public void tearDown() {
        driver.close();
    }
}
