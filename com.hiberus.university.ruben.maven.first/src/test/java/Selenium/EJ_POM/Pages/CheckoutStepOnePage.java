package Selenium.EJ_POM.Pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class CheckoutStepOnePage extends AbstactPage{


    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    CheckoutStepOnePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return null;
    }


    public void clickContinue() {
        log.info("Processing in ....");

        try {
            continueButton.click();
        } catch (TimeoutException ex) {
            log.info("Timeout clicking Continue : " + ex.getClass().getSimpleName());
        }
    }

    public void clickCancelButton() {
        log.info("Canceling in ....");

        try {
            cancelButton.click();
        } catch (TimeoutException ex) {
            log.info("Timeout clicking Cancel : " + ex.getClass().getSimpleName());
        }
    }

    public void enterFirstName(String name) {
        firstNameInput.click();
        firstNameInput.sendKeys(name);
    }

    public void enterLastName(String lastName) {
        lastNameInput.click();
        lastNameInput.sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {
        postalCodeInput.click();
        postalCodeInput.sendKeys(postalCode);
    }


}
