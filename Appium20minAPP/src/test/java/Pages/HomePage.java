package Pages;

import Utils.Methods;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Member;

public class HomePage extends AbstractPage{

    @FindBy(id = "com.pocketwidget.veinte_minutos:id/homeFragmentMenu")
    private WebElement homeButton;

    @FindBy(id = "com.pocketwidget.veinte_minutos:id/button_notice_footer_agree")
    private WebElement aceptCookiesButton;

    @FindBy(id = "android:id/button2")
    private WebElement noNotificationsButton;

    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.TextView")
    private WebElement titlePieceNewInside;

    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.view.ViewGroup/android.view.ViewGroup/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView")
    private WebElement titlePieceNewHome;


    public void clickCookiesAndNotifications() {
        Methods.clickButton(aceptCookiesButton);
        Methods.clickButton(noNotificationsButton);
    }

    public boolean checkIfHomeButtonExists(){
        return Methods.checkIfElementExists(homeButton);
    }

    public boolean clickPieceOfNew(){
        String text = Methods.acortarTextoA5Palabras(titlePieceNewHome.getText());//AQUI ESTA EL PROBLEMA
        Methods.clickButton(titlePieceNewHome);
        String text2 = Methods.acortarTextoA5Palabras(titlePieceNewInside.getText());
        return text.equals(text2);
    }
/*
    public boolean checkPieceNewAfterClicking(){
       // text = Methods.acortarTextoA5Palabras();
        //Methods.clickButton(titlePieceNewHome);
        String text2 = titlePieceNewInside.getText();

        return text.equals(text2);
    }*/


    HomePage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return null;
    }
}
