package runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(

features = "src/test/java/feature/TrivagoBooking.feature", glue="stepDefinition")   /*Req 2*/
//features = "src/test/java/feature/", glue="stepDefinition") 						  /*Both Req 1 and Req 2*/
//features = "src/test/java/feature/CreateUserAccount.feature", glue="stepDefinition")   /*Req 1*/
public class TestRunner {
}
