package Selenium.EJ_POM.Pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.ScriptTimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeoutException;

@Slf4j
public abstract class AbstactPage {
    private final WebDriver driver;
    protected Wait<WebDriver> wait;

    AbstactPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10, 500);
    }

    protected WebDriver getDriver() {
        return driver;
    }

    /*
    public void waitForPageLoad() {
        WebElement testElement = getPageLoadedTestElement;
        wait.until(ExpectedConditions.visibilityOf(testElement));
    }
     */

    protected boolean isPageLoaded(WebElement element) {
        boolean isLoaded = false;

        try {
            isLoaded = element.isDisplayed();
        } catch (NoSuchElementException nsee) {
            nsee.printStackTrace();
        }

        return isLoaded;
    }

    public abstract WebElement getPageLoadedTestElement();


    public void navigateTo(String url) {
        try {
            driver.navigate().to(url);
        } catch (java.lang.Exception e) {
            if (e instanceof TimeoutException) {
                log.info("Timeout loading home page");
            } else if (e instanceof ScriptTimeoutException) {
                log.info("Script timeout loading home page");
            } else {
                log.error(e.getMessage());
            }
        }


    }
}
