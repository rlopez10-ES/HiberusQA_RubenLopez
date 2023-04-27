package com.hiberus.university.ruben.maven.first;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class EJComandosNavegacion {

    static String url = "https://www.saucedemo.com/";
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver;

        //PASO 01
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();

        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();


        //PASO 02
        driver.get(url); //get abre la pagina

        //PASO 03 y 04
        String titulo = driver.getTitle();

        System.out.println("Titulo de la pagina " +titulo+ " y su longitud " +titulo.length()+"\n");

        //PASO 05
        String urlActual = driver.getCurrentUrl();
        if(!urlActual.equalsIgnoreCase(url)) {
            System.out.println("La url actual no es la correcta");
            System.out.println("URL Actual: " +urlActual+ "\nURL :" +url);
        } else {
            System.out.println("La url actual es la correcta");
            System.out.println("URL Actual: " +urlActual+ "\nURL :" +url);
        }

        //PASO 06 y 07
        String pageSource = driver.getPageSource();
        System.out.println("Longitud del codigo fuente: " +pageSource.length());

        //ESPERA DE 5 segundos
        Thread.sleep(5000);

        //PASO 08
        System.out.println("\n");
        try {
            driver.close();
            System.out.println("El navegador se ha cerrado exitosamente");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Problemas al cerrar el navegador");
        }
    }
}
