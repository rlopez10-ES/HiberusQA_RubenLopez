package Selenium.EJ_POM.support;

import Selenium.EJ_POM.Pages.PagesFactory;
import Selenium.EJ_POM.utils.Flags;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


import java.util.Date;




@Slf4j
public class Hooks {

    public static WebDriver driver;

    @Before
    public void before(Scenario scenario) {
        log.info("Starting " + scenario.getName());

        String browser = Flags.getInstance().getBrowser();
        boolean isHeadless = Flags.getInstance().isHeadless();
        if (StringUtils.isBlank(browser)) browser = "chrome";

        if(browser.equals("chrome")) {
           // WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*");
            if (isHeadless) {
                chromeOptions.addArguments("--headless");
            }
            driver = new ChromeDriver(chromeOptions);

        } else if (browser.equals("firefox")) {
           // WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            if(isHeadless) {
                options.addArguments("--headless");
            }
            driver = new FirefoxDriver(options);
            
        } else if (browser.equals("edge")) {
            //WebDriverManager.edgedriver().setup();
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--remote-allow-origins=*");
            driver = new EdgeDriver(edgeOptions);
        } else {
            throw new IllegalArgumentException("Navegador no soportado: " + browser);
        }





        //WebDriverManager.chromedriver().setup();
        //ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("--remote-allow-origins=*");
        //driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        PagesFactory.start(driver);
    }

    @After
    public void after(Scenario scenario) {
        log.info("Ending " + scenario.getName());
        if (scenario.isFailed()) {
            final byte[] screenshot = ( (TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            long time = new Date().getTime();
            String outputName = "screenshot_" + time + ".png";
            scenario.attach(screenshot, "image/png", outputName);
        }
        driver.close();
    }

}
