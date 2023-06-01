package Support;


import Pages.PagesFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Date;

@Slf4j
public class Hooks {

    public static WebDriver driver;

    @Before
    public void before(Scenario scenario) {
        log.info("Starting " + scenario.getName());


        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();

        driver.manage().deleteAllCookies();
        //driver.findElement(By.xpath("//button[@data-qa='oil-YesButton']"))
            //    .click();

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
