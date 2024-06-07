package cucumber.Options;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(groups = {"sanity"}, enabled = true)
@CucumberOptions(features = "src/test/java/features",plugin = "json:target/jsonReports/cucumber-report.json",glue= {"stepDefinitions"},tags= "@DeletePlace")
public class TestRunner extends AbstractTestNGCucumberTests {

}
