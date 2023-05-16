package Selenium.EJ_POM.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {
            "pretty"
    },
    glue = {
            "test/java/Selenium/EJ_POM/Stepdefs",
            "test/java/Selenium/EJ_POM/support"
    },
    features = {
            "src/test/resources"
    }
)
public class CucumberRunnerTest {
}
