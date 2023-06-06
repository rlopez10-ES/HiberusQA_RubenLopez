package Pages;

import Utils.Methods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PerfilPage extends AbstractPage{


    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.view.ViewGroup[5]/android.widget.FrameLayout/androidx.cardview.widget.CardView/android.widget.RelativeLayout")
    private WebElement logoutButton;
    @FindBy(id = "com.pocketwidget.veinte_minutos:id/perfilFragmentMenu")
    private WebElement perfilSectionButton;

    @FindBy(id = "com.pocketwidget.veinte_minutos:id/mb_select_sign_in")
    private WebElement loginOption;
    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.view.ViewGroup[4]/android.widget.FrameLayout/androidx.cardview.widget.CardView/android.widget.RelativeLayout")
    private WebElement changeTextSizeOption;
    @FindBy(id = "com.pocketwidget.veinte_minutos:id/rg_text_size_large")
    private WebElement bigSizeOption;

    public void clickPerfilSection(){
        Methods.clickButton(perfilSectionButton);
    }

    public void clickLogout() {
        Methods.clickButton(logoutButton);
    }

    public boolean checkWelcomePage() {
        return Methods.checkIfElementExists(loginOption);
    }

    public void clickChangeTextOption() {
        Methods.clickButton(changeTextSizeOption);
    }

    public void clickBigOption() {
        Methods.clickButton(bigSizeOption);
    }

    public boolean checkOptionChecked() {
        return Boolean.parseBoolean(bigSizeOption.getAttribute("checked"));
    }

    PerfilPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return null;
    }
}
