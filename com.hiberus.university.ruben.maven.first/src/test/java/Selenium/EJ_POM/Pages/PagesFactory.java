package Selenium.EJ_POM.Pages;

import org.openqa.selenium.WebDriver;


public class PagesFactory {

    private static PagesFactory pagesFactory;

    private final WebDriver driver;

    private final LoginPage loginPage;
    private final CartPage cartPage;
    private final CheckoutStepOnePage checkoutStepOnePage;
    private final CheckoutStepSecondPage checkoutStepSecondPage;
    private final InventoryPage inventoryPage;

    private PagesFactory(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);
        checkoutStepOnePage = new CheckoutStepOnePage(driver);
        checkoutStepSecondPage = new CheckoutStepSecondPage(driver);
        inventoryPage = new InventoryPage(driver);

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

    public LoginPage getLoginPage() {
        return loginPage;
    }
    public InventoryPage getInventoryPage() {
        return inventoryPage;
    }
    public CartPage getCartPage() {
        return cartPage;
    }
    public CheckoutStepOnePage getCheckoutStepOnePage() {
        return checkoutStepOnePage;
    }
    public CheckoutStepSecondPage getCheckoutStepSecondPage() {
        return checkoutStepSecondPage;
    }
}
