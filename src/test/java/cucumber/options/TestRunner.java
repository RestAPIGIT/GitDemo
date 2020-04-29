package cucumber.options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features" ,plugin= "json:target/jsonreports/cucumber-reports.json",
glue= {"stepDefinations"})
public class TestRunner {

	//tagging from command line
	// mvn test -Dcucumber.options="--tag @Addplace"
	
	//to parameterize the test in jenkins using tag
	//test verify -Dcucumber.options="--tag @$name of variable""
}
