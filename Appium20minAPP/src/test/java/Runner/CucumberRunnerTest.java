package Runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //publish = true,
        plugin = {
                "pretty",
                //"usage",
                //"json:target/surefire-reports/cucumber.json",
                //"junit:target/cucumber-reports/Cucumber.xml",
                //"html:target/cucumber-html-report"
        },
        glue = {
                "/Stepdefs",
                "/Support"
        },
        features = {
                "src/test/resources/Features"
        }
)

public class CucumberRunnerTest {
}
