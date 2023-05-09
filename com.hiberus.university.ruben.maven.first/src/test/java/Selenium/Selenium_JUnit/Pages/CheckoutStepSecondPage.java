package Selenium.Selenium_JUnit.Pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

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
            cancelButton.click();
        } catch (TimeoutException ex) {
            log.info("Timeout clicking Finish : " + ex.getClass().getSimpleName());
        }
    }


    public double getItemTotal(WebElement itemTotalElement) {
        String itemTotalText = itemTotalElement.getText().substring(1);
        Double itemTotal = Double.parseDouble(itemTotalText);

        return itemTotal;
    }

    public double getTax(WebElement taxElement) {
        String taxText = taxElement.getText().substring(1);
        Double tax = Double.parseDouble(taxText);

        return tax;
    }

    public double getTotal(WebElement totalElement) {
        String totalText = totalElement.getText().substring(1);
        Double total = Double.parseDouble(totalText);

        return total;
    }


}
