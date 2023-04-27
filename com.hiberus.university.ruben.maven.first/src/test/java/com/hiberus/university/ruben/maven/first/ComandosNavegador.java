package com.hiberus.university.ruben.maven.first;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class ComandosNavegador {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver;

        //PARA FIREFOX
     /*   WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        driver = new FirefoxDriver(firefoxOptions);
        driver.manage().window().maximize();*/

        //PARA CHROME
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();

        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();

        String url = "https://www.hiberus.com/";
        //driver.get(url);

        String titulo = driver.getPageSource();

        System.out.println(titulo);

        Thread.sleep(5000);

        driver.quit();
    }
}
