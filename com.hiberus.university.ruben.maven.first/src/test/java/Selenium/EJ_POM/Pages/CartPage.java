package Selenium.EJ_POM.Pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CartPage extends AbstactPage{

    @FindBy(xpath = "//button[@class='btn btn_secondary btn_small cart_button']")
    private List<WebElement> removeButton;

    @FindBy(id = "checkout")
    private WebElement checkOutButton;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    @FindBy(className = "cart_item")
    private List<WebElement> itemList;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> itemListPrice;

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


    public double sumItemTotalPrice() {
        double total = 0.0;

        for(int i = 0; i < itemListPrice.size(); i++){
            String price = itemListPrice.get(i).getText().replace("$", "");
            total += Double.parseDouble(price);
        }

        return total;
    }

    public void clickRemove(int pos) {
        removeButton.get(pos).click();
    }

    public void clickCheckout() {
        log.info("Checkout ....");

        try {
            checkOutButton.click();
        } catch (TimeoutException ex) {
            log.info("Timeout clicking Checkout : " + ex.getClass().getSimpleName());
        }
    }

    public void clickContinueShoppingButton() {
        log.info("Canceling ....");

        try {
            continueShoppingButton.click();
        } catch (TimeoutException ex) {
            log.info("Timeout clicking Continue Shopping : " + ex.getClass().getSimpleName());
        }
    }

    public int getItemCount() {
        int itemCount = itemList.size();

        return itemCount;
    }


}
