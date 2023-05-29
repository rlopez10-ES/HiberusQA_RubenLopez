package Pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class HomePage extends AbstractPage{

    public static final String PAGE_URL = "https://destinia.com/";

    @FindBy(xpath = "//*[@id='hotelsearchwidget0-occupancy-customelement']/div[2]/div[1]/div/div[2]/div[2]/button[2]")
    private WebElement buttonAddAdult;

    @FindBy(xpath = "//*[@id='hotelsearchwidget0-occupancy-customelement']/div[2]/div[1]/div/div[2]/div[2]/button[1]")
    private WebElement buttonDeleteAdult;

    @FindBy(xpath = "//*[@id='hotelsearchwidget0-occupancy-customelement']/div[2]/div[1]/div/div[2]/div[2]/input")
    private WebElement adultsBox;



    HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return null;
    }

    public void setNumAdults(int numAdults) {

        String xpath = "//*[@id='hotelsearchwidget0-occupancy-customelement']/div[2]/div[1]/div/div[2]/div[2]/input[@value='" + numAdults + "']";
        //String box = adultsBox.
    }

    public boolean checkIfButtonEnable(){
        boolean isEnable;

        if (buttonDeleteAdult.getText().contains("disable")) {
            isEnable = false;
        } else {
            isEnable = true;
        }

        return isEnable;
    }

}
