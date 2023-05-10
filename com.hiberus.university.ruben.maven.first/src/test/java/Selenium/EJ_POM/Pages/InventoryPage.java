package Selenium.EJ_POM.Pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class InventoryPage extends AbstactPage{

    public static final String PAGE_URL = "https://www.saucedemo.com/inventory.html";

    @FindBy(id = "react-burger-menu-btn")
    private WebElement openMenu;

    @FindBy(id = "shopping_cart_container")
    private WebElement shoppingCartElement;

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    private WebElement shoppingCartNumber;

    @FindBy(xpath = "//div[@class='inventory_item']")
    private List<WebElement> inventoryList;

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    private List<WebElement> inventoryNameList;

    @FindBy(xpath = "//select[@data-test='product_sort_container']/option")
    private Select options;

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
        String xpath = "//button[@id='" + action + "-" + name + "']";
        WebElement itemElem = getDriver().findElement(By.xpath(xpath));
        itemElem.click();
    }

    public boolean existProductInInventoryList(String itemName) {

        boolean itemExists = false;
        for (int i = 0; i < inventoryNameList.size(); i++) {
            if (inventoryNameList.get(i).getText().equals(itemName)) {
                itemExists = true;
                break; //SALIMOS DEL BUCLE CON BREAK SI ENCONTRAMOS EL ELEMENTO
            }
        }
        return itemExists;
    }

    public void addRandomProducts(int numProductAdd){

        ArrayList<Integer> selectedItems = new ArrayList<>();
        while (selectedItems.size() < numProductAdd) {
            int index = new Random().nextInt(inventoryList.size());
            if (!selectedItems.contains(index)) {
                selectedItems.add(index);
            }
        }

        for (int i = 0 ; i < selectedItems.size() ; i++) {
            WebElement productElement = inventoryList.get(selectedItems.get(i));
            productElement.findElement(By.xpath(".//button[@class='btn btn_primary btn_small btn_inventory']"))
                    .click();
        }
    }


    public void sortInventory(String sortOption) {


        //options.selectByIndex(2);
        options.selectByVisibleText(sortOption);
        //options.selectByValue("hilo");

    }


    public int numberItemList() {
        int totalItems = inventoryList.size();

        return totalItems;
    }

    public String numberProductsCart() {
        String totalCartItems = shoppingCartNumber.getText();

        return totalCartItems;
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
