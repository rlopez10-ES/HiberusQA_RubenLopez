package Selenium.EJ_POM.runner;

import io.cucumber.junit.*;
import org.junit.runner.*;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {"pretty"},
    glue = {"com/hiberus/university/ruben/maven/first/src/test/java/Selenium/EJ_POM/Stepdefs",
            "com/hiberus/university/ruben/maven/first/src/test/java/Selenium/EJ_POM/support"},
    features = {"com/hiberus/university/ruben/maven/first/src/test/resources"}
)
public class CucumberRunnerTest {
}
