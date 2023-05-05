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
    public void menejoAlert() {
        driver.get("https://demoqa.com/alerts");


        //EJ01
        driver.findElement(By.xpath("//button[@id='alertButton']")).click();

        //CON ESTO ACEPTAMOS LA ALERTA ENTRANTE .dimiss() PARA CANCELA
        driver.switchTo().alert().accept();


        //EJ02
        driver.findElement(By.xpath("//button[@id='promtButton']")).click();

        driver.switchTo().alert().sendKeys("I have entered a text");
        driver.switchTo().alert().accept();


        //EJ03
        driver.findElement(By.xpath("//button[@id='confirmButton']")).click();

        driver.switchTo().alert().accept();

        Assert.assertEquals("No aparece el texto tras aceptar el alert", "You selected Ok", driver.findElement(By.xpath("//span[@id='confirmResult']")).getText() );


        //EJ04
        driver.findElement(By.xpath("//button[@id='confirmButton']")).click();

        driver.switchTo().alert().dismiss();

        Assert.assertEquals("No el texto tras cancelar el alert", "You selected Cancel", driver.findElement(By.xpath("//span[@id='confirmResult']")).getText() );
    }



    @After
    public void tearDown() {
        driver.close();
    }
}
