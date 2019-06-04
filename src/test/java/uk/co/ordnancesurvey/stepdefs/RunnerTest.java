package uk.co.ordnancesurvey.stepdefs;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

//runner class

@RunWith(Cucumber.class)
@CucumberOptions(glue = { "uk.co.ordnancesurvey.stepdefs" }, tags = "@CheckWeather",
                format = { "pretty", "html:target/cucumber-html-report",	"json:target/cucumber.json" },
	        	features = { "src/test/resources/features" })


public class RunnerTest {

}
