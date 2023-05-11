package Selenium.EJ_POM.support;

import Selenium.EJ_POM.Pages.PagesFactory;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@Slf4j
public class Hooks {

    public static WebDriver driver;

    @Before
    public void before(Scenario scenario) {
        log.info("Starting " + scenario.getName());

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        PagesFactory.start(driver);
    }

    @After
    public void after(Scenario scenario) {
        log.info("Ending " + scenario.getName());

        driver.close();
    }

}
