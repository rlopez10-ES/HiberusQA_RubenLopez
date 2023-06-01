package Pages;

import Utils.Metodos;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import java.util.List;

@Slf4j
public class HomePage extends AbstractPage{

    public static final String PAGE_URL = "https://destinia.com/";

    @FindBy(xpath = "//button[@id='hotelsearchwidget0-occupancy-customelement-menubt']")
    private WebElement ocupationDropdown;

    @FindBy(xpath = "//*[@id='hotelsearchwidget0-occupancy-customelement']/div[2]/div[1]/div/div[2]/div[2]/button[2]")
    private WebElement buttonAddAdult;

    @FindBy(xpath = "//*[@id='hotelsearchwidget0-occupancy-customelement']/div[2]/div[1]/div/div[2]/div[2]/button[1]")
    private WebElement buttonDeleteAdult;

    @FindBy(xpath = "//*[@id='hotelsearchwidget0-occupancy-customelement']/div[2]/div[1]/div/div[2]/div[2]/input")
    private List<WebElement> numValueAdults;

    @FindBy(xpath = "//*[@id='hotelsearchwidget0-occupancy-customelement']/div[2]/div[1]/div/div[3]/div[2]/button[2]")
    private WebElement buttonAddChild;

    @FindBy(xpath = "//*[@id='hotelsearchwidget0-occupancy-customelement']/div[2]/div[1]/div/div[3]/div[2]/button[1]")
    private WebElement buttonDeleteChild;

    @FindBy(xpath = "//*[@id='hotelsearchwidget0-occupancy-customelement']/div[2]/div[1]/div/div[3]/div[2]/input")
    private List<WebElement> numValueChild;

    @FindBy(xpath = "//*[@id='hotelsearchwidget0-occupancy-customelement']/div[2]/div[1]/div/div[2]/div[2]/input")
    private WebElement adultsBox;

    @FindBy(xpath = "//div[@class='searchAddAge']")
    private WebElement ageBox;

    @FindBy(xpath = "//div[@class='searchAddAge__errors error_text']")
    private WebElement message;

    @FindBy(xpath = "//*[@id='hotelsearchwidget0-occupancy-customelement']//button[@class='btn btn-link']")
    private WebElement buttonAddRoom;



    HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return null;
    }

    public void setNumAdultsChildren(int numAdults, int numChildren) {
        ocupationDropdown.click();

        int numActualAdults = 0;
        int numActualChildren = 0;

        numActualAdults = Metodos.countAdultsChildren(numActualAdults, numValueAdults);
        numActualChildren = Metodos.countAdultsChildren(numActualChildren, numValueChild);

        Metodos.setAddRemove(numActualAdults, numAdults, buttonDeleteAdult, buttonAddAdult);
        Metodos.setAddRemove(numActualChildren, numChildren, buttonDeleteChild, buttonAddChild);
    }

    public void addRooms(int numRooms){
        for(int i = 1; i < numRooms; i++){
           // Thread.sleep(3500);
            wait.until(ExpectedConditions.visibilityOf(buttonAddRoom)).click();
            //buttonAddRoom.click();
        }
    }

    public boolean checkIfSetAgeIsDisplayed(){
        return ageBox.isDisplayed();
    }

    public boolean checkIfMessageIsDisplayed(){
        return message.isDisplayed();
    }

    public boolean checkIfAddRoomIsDisplayed(){
        boolean asd;
        try {
            asd = buttonAddRoom.isDisplayed();
        } catch (NoSuchElementException e) {
            asd = false;
        }
        return asd;
    }

    public boolean checkIfDeleteButtonAdultEnable(){
        return buttonDeleteAdult.isEnabled();
    }

    public boolean checkIfAddButtonAdultEnable(){
        return buttonAddAdult.isEnabled();
    }

    public boolean checkIfDeleteButtonChildEnable(){
        return buttonDeleteChild.isEnabled();
    }

    public boolean checkIfAddButtonChildEnable(){
        return buttonAddChild.isEnabled();
    }

}
