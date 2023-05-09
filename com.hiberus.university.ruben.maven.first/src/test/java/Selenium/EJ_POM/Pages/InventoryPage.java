package Selenium.EJ_POM.Pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Slf4j
public class InventoryPage extends AbstactPage{

    public static final String PAGE_URL = "https://www.saucedemo.com/inventory.html";

    @FindBy(id = "react-burger-menu-btn")
    private WebElement openMenu;

    @FindBy(id = "shopping_cart_container")
    private WebElement shoppingCartElement;

    @FindBy(xpath = "//div[@class='inventory_item']")
    private List<WebElement> inventoryList;

    @FindBy(xpath = "//select[@data-test='product_sort_container']")
    private WebElement productSortContainerSelect;

    @FindBy(xpath = "//select[@data-test='product_sort_container']/option")
    private List<WebElement> options;

    @FindBy(xpath = "//a[@id='logout_sidebar_link']")
    private WebElement logoutButton;



    InventoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return null;
    }

    public void itemAddOrRemove(String itemName, String action) {
        String name = itemName.replace(" ", "-").toLowerCase();
        String xpath = "//button['" + action + "-" + name + "]";
        WebElement itemElem = getDriver().findElement(By.xpath(xpath));
        itemElem.click();
    }

    public boolean existProductInInventoryList(String itemName) {
        boolean itemExists = false;

        for(int i = 0 ; i < inventoryList.size() ; i++){
            WebElement item = inventoryList.get(i);
            String itemNameToConfirm = item.findElement(By.xpath("//div[@class='inventory_item_name']")).getText();

            if (itemName.equalsIgnoreCase(itemName)) {
                itemExists = true;
                break; //SALIMOS DEL BUCLE CON BREAK SI ENCONTRAMOS EL ELEMENTO
            }
        }
        return itemExists;
    }

    public int numberItemList() {
        int totalItems = inventoryList.size();

        return totalItems;
    }
/*
    public void remove(){
        log.info("Removing in ....");

        try {
            .click();
        } catch (TimeoutException ex) {
            log.info("Timeout clicking Remove : " + ex.getClass().getSimpleName());
        }
    }

 */
}


/*
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class InventoryPage extends AbstractPage {
  public static final String PAGE_URL = "https://www.saucedemo.com/inventory.html";

  @FindBy(css = "#inventory_container")
  private WebElement inventoryContainer;

  @FindBy(xpath = "//div[@class='inventory_item_name']")
  private List<WebElement> inventoryNameList;

  @FindBy(xpath = "//div[@class='inventory_item_price']")
  private List<WebElement> inventoryPriceList;

  @FindBy(xpath = "//select[@data-test='product_sort_container']")
  private Select sortSelect;

  public InventoryPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  @Override
  public WebElement getPageLoadedTestElement() {
    return inventoryContainer;
  }

  public void selectOption(String option) {
    sortSelect.selectByValue(option);
  }

  public boolean existProductInInventoryList(String itemName) {
    boolean isProductPresent = false;
    for (WebElement webElement : inventoryNameList) {
      if (webElement.getText().equals(itemName)) {
        isProductPresent = true;
        break;
      }
    }
    return isProductPresent;
  }

  public List<WebElement> getInventoryPriceList() {
    return inventoryPriceList;
  }

  public void addItemToCartByName(String itemName) {
    String name = itemName.replace(" ", "-").toLowerCase();
    String xpath = "//button['add-to-cart-" + name + "]";
    WebElement itemElem = getDriver().findElement(By.xpath(xpath));
    itemElem.click();
  }
}
 */
