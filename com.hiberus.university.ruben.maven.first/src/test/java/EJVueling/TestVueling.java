package EJVueling;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

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



    }
}
