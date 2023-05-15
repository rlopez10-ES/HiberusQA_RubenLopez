package Selenium.EJ_POM.Pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class CheckoutStepSecondPage extends AbstactPage{


    @FindBy(className = "summary_subtotal_label")
    private WebElement itemTotalElement;

    @FindBy(className = "summary_tax_label")
    private WebElement taxElement;

    @FindBy(className = "summary_info_label summary_total_label")
    private WebElement totalElement;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(xpath = "//div[@class='complete-text']")
    private WebElement finalText;



    CheckoutStepSecondPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return null;
    }


    public void clickCancelButton() {
        log.info("Canceling in ....");

        try {
            cancelButton.click();
        } catch (TimeoutException ex) {
            log.info("Timeout clicking Cancel : " + ex.getClass().getSimpleName());
        }
    }

    public void clickFinishButton() {
        log.info("Finishing in ....");

        try {
            finishButton.click();
        } catch (TimeoutException ex) {
            log.info("Timeout clicking Finish : " + ex.getClass().getSimpleName());
        }
    }

    public boolean getFinalText() {
        return finalText.isDisplayed();
    }

    public double getItemTotal() {
        String itemTotalText[] = itemTotalElement.getText().split("\\$");
        Double itemTotal = Double.parseDouble(itemTotalText[1]);

        return itemTotal;
    }

    public double getTax() {
        String taxText = taxElement.getText().substring(1);
        Double tax = Double.parseDouble(taxText);

        return tax;
    }

    public double getTotal() {
        String totalText = totalElement.getText().substring(1);
        Double total = Double.parseDouble(totalText);

        return total;
    }


}
