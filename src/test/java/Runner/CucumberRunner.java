package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features",glue = {"Steps","Hooks"},
plugin = {"pretty","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},tags = "")
public class CucumberRunner extends AbstractTestNGCucumberTests {
}

