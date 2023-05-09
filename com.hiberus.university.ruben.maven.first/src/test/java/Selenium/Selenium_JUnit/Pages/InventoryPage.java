package Selenium.Selenium_JUnit.Pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Slf4j
public class InventoryPage extends AbstactPage{

    @FindBy(id = "first-name")
    private WebElement openMenu;

    @FindBy(id = "first-name")
    private WebElement shoppingCartElement;

    @FindBy(id = "first-name")
    private List<WebElement> inventoryList;

    @FindBy(id = "first-name")
    private WebElement productSortContainerSelect;



    InventoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return null;
    }
}
