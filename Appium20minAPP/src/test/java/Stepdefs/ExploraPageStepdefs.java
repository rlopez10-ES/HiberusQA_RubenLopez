package Stepdefs;

import Pages.ExploraPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.PagesFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

public class ExploraPageStepdefs {

    PagesFactory pagesFactory = PagesFactory.getInstance();
    LoginPage loginPage = PagesFactory.getInstance().getLoginPage();
    HomePage homePage = PagesFactory.getInstance().getHomePage();
    ExploraPage exploraPage = pagesFactory.getExploraPage();

    @And("the user clicks on explora section")
    public void theUserClicksOnExploraSection() {
        exploraPage.clickExploraSection();
    }

    @And("the user is on explora section")
    public void theUserIsOnExploraSection() {
        assertTrue("The user is not on EXPLORA section", exploraPage.checkIfTitleExists());
    }

    @When("the user clicks on a theme")
    public void theUserClicksOnATheme() {
        exploraPage.clickNationalTheme();

    }

    @Then("the news are about the chosen theme")
    public void theNewsAreAboutTheChosenTheme() throws InterruptedException {
        assertTrue("The user is not on NATIONAL theme", exploraPage.chechIfNationalThemeExists());
    }

    @When("the user clicks on “Ver todos” in theme section")
    public void theUserClicksOnVerTodosInThemeSection() {
        exploraPage.clickViewAll();
    }

    @Then("the user can see all themes")
    public void theUserCanSeeAllThemes() {
        assertTrue("This is not the THEME menu", exploraPage.checkThemeMenuExists());
    }
}
