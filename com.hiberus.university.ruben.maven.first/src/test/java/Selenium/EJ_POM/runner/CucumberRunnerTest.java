package Selenium.EJ_POM.runner;

import io.cucumber.junit.*;
import org.junit.runner.*;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {"pretty"},
    glue = {"Selenium/EJ_POM/Stepdefs", "Selenium/EJ_POM/support"},
    features = {"src/test/resources"}
)
public class CucumberRunnerTest {
}
