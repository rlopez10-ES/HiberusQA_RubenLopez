package Pages;

import org.openqa.selenium.WebDriver;


public class PagesFactory {

    private static PagesFactory pagesFactory;

    private final WebDriver driver;

    private final HomePage homePage;


    private PagesFactory(WebDriver driver) {
        this.driver = driver;
        homePage = new HomePage(driver);


        // inicializar nuestras Pages
    }

    public static void start(WebDriver driver) {
        pagesFactory = new PagesFactory(driver);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public static PagesFactory getInstance() {
        return pagesFactory;
    }

    public HomePage getHomePage() {
        return homePage;
    }

}
