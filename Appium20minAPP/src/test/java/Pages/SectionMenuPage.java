package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SectionMenuPage extends AbstractPage{
    //com.pocketwidget.veinte_minutos:id/homeFragmentMenu
    @FindBy(id = "com.pocketwidget.veinte_minutos:id/homeFragmentMenu")
    private WebElement menuOption;

    public void clickOptionMenu(String option){
        String xpathId = "com.pocketwidget.veinte_minutos:id/" + option + "FragmentMenu";
        menuOption = getDriver().findElement(By.id(xpathId));
        menuOption.click();
    }

    public boolean checkMenuOptionSelected(String option){
        String xpathId = "com.pocketwidget.veinte_minutos:id/" + option + "FragmentMenu";
        menuOption = getDriver().findElement(By.id(xpathId));
        return menuOption.isSelected();
    }


    SectionMenuPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return null;
    }
}
