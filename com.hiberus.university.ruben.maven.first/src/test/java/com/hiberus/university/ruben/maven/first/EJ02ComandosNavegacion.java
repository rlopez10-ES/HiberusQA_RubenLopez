package com.hiberus.university.ruben.maven.first;

import io.github.bonigarcia.wdm.WebDriverManager;
import javafx.scene.layout.Background;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class EJ02ComandosNavegacion {

    static String url = "https://www.hiberus.com/" ;
    public static void main(String[] args) {

        //INICIAMOS UN NUEVO NAVEGADOR
        WebDriver driver;

        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();

        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();

        //ABRIMOS LA PAGINA WEB
        driver.get(url);


        //NOS DIRIGIMOS AL ENLACE DE CONSULTORIA Y ESTRATEGIA
        WebElement element = driver.findElement(By.xpath("//a[@href='/desarrollo-y-outsourcing-tecnologico']"));
        element.click();


        //USAMOS EL COMADNO BACK PAR VOLVER ATRAS
        esperarCuatroS();
        driver.navigate().back();


        //USAMOS EL COMANDO FORWARD PARA VOLVER A DELANTE
        esperarCuatroS();
        driver.navigate().forward();

        //USAMOS EL COMANDO TO PARA NAVEGAR A UN SITIO EN ESPECIFICO
        esperarCuatroS();

        String url02 = "https://www.hiberus.com/tecnologias-diferenciales"; // /tecnologias-diferenciales
        driver.navigate().to(url02);

        //USAMOS EL COMANDO REFRESH PARA REFRESCAR LA PAGINA
        esperarCuatroS();
        driver.navigate().refresh();

        esperarCuatroS();
        driver.close();
    }

    private static void esperarCuatroS(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
