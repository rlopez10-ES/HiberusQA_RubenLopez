package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
    //publish = true,
    publish = true,
    plugin = {
            "pretty",
            //"usage",
            "json:target/surefire-reports/cucumber.json",
            //"junit:target/cucumber-reports/Cucumber.xml",
            "html:target/cucumber-html-report"
    },
    glue = {
            "stepdefs",
            "support"
    },
    features = {
            "resources/features"
    }
)
public class CucumberRunnerTest {
}


