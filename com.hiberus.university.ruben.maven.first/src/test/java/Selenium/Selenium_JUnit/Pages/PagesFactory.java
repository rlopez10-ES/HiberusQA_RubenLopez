package Selenium.Selenium_JUnit.Pages;

import org.openqa.selenium.WebDriver;


public class PagesFactory {

    private static PagesFactory pagesFactory;

    private final WebDriver driver;

    private final LoginPage loginPage;
    private final CartPage cartPage;

    private PagesFactory(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);

        // inicializar nuestras Pages
    }

    public static void start(WebDriver driver) {
        pagesFactory = new PagesFactory(driver);
    }

    public WebDriver getDriver() {
        return driver;
    }
}
