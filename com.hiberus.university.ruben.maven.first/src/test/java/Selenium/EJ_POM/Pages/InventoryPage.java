package Selenium.EJ_POM.Pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

@Slf4j
public class InventoryPage extends AbstactPage{

    public static final String PAGE_URL = "https://www.saucedemo.com/inventory.html";

    @FindBy(id = "react-burger-menu-btn")
    private WebElement openMenu;

    @FindBy(id = "shopping_cart_container")
    private WebElement shoppingCart;

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    private WebElement shoppingCartNumber;

    @FindBy(xpath = "//div[@class='inventory_item']")
    private List<WebElement> inventoryList;

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    private List<WebElement> inventoryNameList;

    @FindBy(xpath = "//div[@class='inventory_item_price']")
    private List<WebElement> inventoryPriceList;

    @FindBy(xpath = "//select[@data-test='product_sort_container']")
    private WebElement options;

    @FindBy(xpath = "//a[@id='logout_sidebar_link']")
    private WebElement logoutButton;

    @FindBy(xpath = "//button[@id='react-burger-menu-btn']")
    private WebElement burgerMenu;


    InventoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return null;
    }

    public void clickLogout(){
        log.info("Logging out ....");

        try {
            burgerMenu.click();
            logoutButton.click();
        } catch (TimeoutException ex) {
            log.info("Timeout clicking logout button : " + ex.getClass().getSimpleName());
        }
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

    public void sortInventory(String sortOption){

        Select selectOption = new Select(options);
        //selectOption.selectByVisibleText(sortOption);
        //selectOption.selectByIndex(2);
        selectOption.selectByValue(sortOption);
    }

    public List<WebElement> orderZtoA() {


        //CREAMOS OTRA LISTA COGIENDO LOS ELEMENTOS ACTUALES DE LA PAGINA TRAS APLICAR EL FILTRO
        List<WebElement> actualList = new ArrayList<>(inventoryNameList);

        //ORDENAMOS LA LISTA REALMENTE COGIENDO LA LISTA ORIGINAL
        List<WebElement> orderList = new ArrayList<>(inventoryNameList);
        orderList.sort((e1, e2) -> {
            String nombre1 = e1.getText().toLowerCase();
            String nombre2 = e2.getText().toLowerCase();

            return nombre2.compareTo(nombre1);
        });

        ArrayList listas = new ArrayList();

        listas.add(actualList);
        listas.add(orderList);

        return listas;
    }

    public List<WebElement> orderByPrice(String tipoSort) {

        //CREAMOS OTRA LISTA COGIENDO LOS ELEMENTOS ACTUALES DE LA PAGINA TRAS APLICAR EL FILTRO
        List<WebElement> actualList = new ArrayList<>(inventoryPriceList);

        //ORDENAMOS LA LISTA REALMENTE COGIENDO LA LISTA ORIGINAL
        List<WebElement> orderList = new ArrayList<>(inventoryPriceList);
        Collections.sort(orderList, new Comparator<WebElement>() {
            @Override
            public int compare(WebElement e1, WebElement e2) {
                String precio1 = e1.getText().substring(1);
                String precio2 = e2.getText().substring(1);

                double precioDouble1 = Double.parseDouble(precio1);
                double precioDouble2 = Double.parseDouble(precio2);

                int prueba = 0;
                if(tipoSort.equals("LowToHigh")){
                     prueba = Double.compare(precioDouble1, precioDouble2);
                } else if(tipoSort.equals("HighToLow")){
                     prueba = Double.compare(precioDouble2, precioDouble1);
                }
                return prueba;
            }
        });

        ArrayList listas = new ArrayList();
        listas.add(actualList);
        listas.add(orderList);

        return listas;
    }


    public int numberItemList() {
        int totalItems = inventoryList.size();

        return totalItems;
    }

    public String numberProductsCart() {
        String totalCartItems = shoppingCartNumber.getText();

        return totalCartItems;
    }

    public void clickShoppingCart() {
        log.info("Logging in ....");

        try {
            shoppingCart.click();
        } catch (TimeoutException ex) {
            log.info("Timeout clicking the shopping cart : " + ex.getClass().getSimpleName());
        }
    }
}


