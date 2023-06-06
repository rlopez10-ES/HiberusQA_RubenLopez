package Pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public class PagesFactory {
    private static PagesFactory pagesFactory;

    private final AndroidDriver androidDriver;

    private final LoginPage loginPage;
    private final HomePage homePage;
    private final ExploraPage exploraPage;
    private final GuardadoPage guardadoPage;
    private final SectionMenuPage sectionMenuPage;
    private final PerfilPage perfilPage;

    public PagesFactory(AndroidDriver androidDriver)  {
        this.androidDriver = androidDriver;
        loginPage = new LoginPage(androidDriver);
        homePage = new HomePage(androidDriver);
        exploraPage = new ExploraPage(androidDriver);
        guardadoPage = new GuardadoPage(androidDriver);
        sectionMenuPage = new SectionMenuPage(androidDriver);
        perfilPage = new PerfilPage(androidDriver);
    }

    public static void start(AndroidDriver androidDriver) {
        pagesFactory = new PagesFactory(androidDriver);
    }

    public AndroidDriver getAndroidDriverDriver() {
        return androidDriver;
    }

    public static PagesFactory getInstance() {
        return pagesFactory;
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }
    public HomePage getHomePage() {
        return homePage;
    }
    public GuardadoPage getGuardadoPage() {
        return guardadoPage;
    }
    public SectionMenuPage getSectionMenuPage() {
        return sectionMenuPage;
    }
    public PerfilPage getPerfilPage() {
        return perfilPage;
    }
    public ExploraPage getExploraPage() {
        return exploraPage;
    }

}
