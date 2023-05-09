package Selenium.EJ_POM.Pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Slf4j
public class CartPage extends AbstactPage{

    @FindBy(className = "btn btn_secondary btn_small cart_button")
    private List<WebElement> removeButton;

    @FindBy(id = "checkout")
    private WebElement checkOutButton;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    @FindBy(id = "cart_item")
    private List<WebElement> itemList;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement openMenu;

    CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return null;
    }


    public void clickCheckout() {
        log.info("Logging in ....");

        try {
            checkOutButton.click();
        } catch (TimeoutException ex) {
            log.info("Timeout clicking Checkout : " + ex.getClass().getSimpleName());
        }
    }

    public void clickContinueShoppingButton() {
        log.info("Logging in ....");

        try {
            continueShoppingButton.click();
        } catch (TimeoutException ex) {
            log.info("Timeout clicking Continue Shopping : " + ex.getClass().getSimpleName());
        }
    }

    public int getItemCount(List<WebElement> itemList) {
        int itemCount = itemList.size();

        return itemCount;
    }


}
