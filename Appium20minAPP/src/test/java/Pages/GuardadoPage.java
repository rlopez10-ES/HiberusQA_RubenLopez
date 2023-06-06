package Pages;

import Utils.Methods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GuardadoPage extends AbstractPage{

    @FindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"Guardado\"]/android.widget.FrameLayout/android.widget.ImageView")
    private WebElement saveSection;

    @FindBy(id = "com.pocketwidget.veinte_minutos:id/iv_header_save")
    private WebElement saveButton;

    @FindBy(id = "com.pocketwidget.veinte_minutos:id/tv_headline_title")
    private WebElement pieceNewTitle;
    String titlePieceNew;

    @FindBy(id = "com.pocketwidget.veinte_minutos:id/tv_guardado_title")
    private WebElement pieceNewTitleSaved;
    String titlePieceNewSaved;


    public void savePieceNew(){
        Methods.clickButton(saveButton);
    }

    public void clickGuardadoSection() {
        titlePieceNew = Methods.acortarTextoA5Palabras(pieceNewTitle.getText());
        Methods.clickButton(saveSection);
    }

    public boolean checkTitles(){
        titlePieceNewSaved = Methods.acortarTextoA5Palabras(pieceNewTitleSaved.getText());
        return titlePieceNewSaved.equals(titlePieceNew);
    }

    GuardadoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return null;
    }
}
