package test_runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "step_defs"
)
public class UserInfoSessionTest extends AbstractTestNGCucumberTests {
}
