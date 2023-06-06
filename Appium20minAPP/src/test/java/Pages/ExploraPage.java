package Pages;

import Utils.Methods;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Method;

public class ExploraPage extends AbstractPage{

    @FindBy(xpath = "//android.widget.FrameLayout[@content-desc='Explora']/android.widget.FrameLayout/android.widget.ImageView")
    private WebElement exploraButton;

    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[1]")
    private WebElement titleExploraSection;

    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout")
    private WebElement nationalTheme;

    @FindBy(id = "com.pocketwidget.veinte_minutos:id/tv_header_title")
    private WebElement nationalThemeTitle;

    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[2]")
    private WebElement viewAllButton;

    @FindBy(id = "com.pocketwidget.veinte_minutos:id/rv_menu")
    private WebElement themeMenu;

    public void clickExploraSection(){
        Methods.clickButton(exploraButton);
    }

    public void clickNationalTheme(){
        Methods.clickButton(nationalTheme);
    }

    public void clickViewAll(){
        Methods.clickButton(viewAllButton);
    }

    public boolean checkIfTitleExists(){
        return Methods.checkIfElementExists(titleExploraSection);
    }

    public boolean chechIfNationalThemeExists() throws InterruptedException {
        Thread.sleep(5000);
        return Methods.checkIfElementExists(nationalThemeTitle);
    }

    public boolean checkThemeMenuExists(){
        return Methods.checkIfElementExists(themeMenu);
    }

    ExploraPage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return null;
    }
}
