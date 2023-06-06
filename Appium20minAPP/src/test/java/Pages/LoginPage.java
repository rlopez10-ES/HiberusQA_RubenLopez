package Pages;


import Support.Hooks;
import Utils.Methods;

import io.appium.java_client.android.AndroidDriver;
import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



@Slf4j
public class LoginPage extends AbstractPage {

    @FindBy(id = "com.pocketwidget.veinte_minutos:id/mb_select_sign_in")
    private WebElement loginOption;

    @FindBy(id = "com.pocketwidget.veinte_minutos:id/mb_select_sign_up")
    private WebElement registerOption;

    @FindBy(id = "com.pocketwidget.veinte_minutos:id/tiet_sign_in_username")
    private WebElement mailInput;

    @FindBy(id = "com.pocketwidget.veinte_minutos:id/tiet_sign_in_password")
    private WebElement passwordInput;

    @FindBy(id = "com.pocketwidget.veinte_minutos:id/btn_sign_in_button")
    private WebElement loginButton;

    @FindBy(id = "android:id/button2")
    private WebElement erroPopUp;

    LoginPage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return null;
    }


    public void clickLoginOption() {
        log.info("Logging in ....");

        try {

            loginOption.click();
        } catch (TimeoutException ex) {
            log.info("Timeout clicking login option : " + ex.getClass().getSimpleName());
        }
    }

    public void clickLoginButton() {
        log.info("Logging in ....");

        try {
            //ad.hideKeyboard();

            loginButton.click();
        } catch (TimeoutException ex) {
            log.info("Timeout clicking login button: " + ex.getClass().getSimpleName());
        }
    }

    public boolean checkIfLoginOptionExists() {
        return Methods.checkIfElementExists(loginOption);
    }

    public boolean checkIfErrorPopUpAppeared() {
        return Methods.checkIfElementExists(erroPopUp);
    }

    public void enterMail(String mail) {
        mailInput.click();
        mailInput.sendKeys(mail);

    }

    public void enterPassword(String password) {
        passwordInput.click();


        passwordInput.sendKeys(password);

    }

}
