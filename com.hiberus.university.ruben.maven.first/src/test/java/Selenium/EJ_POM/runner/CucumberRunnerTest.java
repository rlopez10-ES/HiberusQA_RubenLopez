package Selenium.EJ_POM.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
    publish = true,
    plugin = {
            "pretty",
            "json:target/surefire-reports/cucumber.json",
            "html:target/cucumber-html-report.html"
    },
    glue = {
            "Selenium/EJ_POM/Stepdefs",
            "Selenium/EJ_POM/support"
    },
    features = {
            "src/test/resources"
    }
)
public class CucumberRunnerTest {
}
