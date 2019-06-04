//this class is the gluecode for mapping your feature file to your "webconnector classes"
package uk.co.ordnancesurvey.stepdefs;

import co.uk.ordnancesurvey.testutils.Browser;
import co.uk.ordnancesurvey.testutils.WebConnector;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CheckWeather {
	WebConnector selenium = WebConnector.getInstance();
	@Given("^customer open \"([^\"]*)\"$")
	public void customer_open(String browser) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		selenium.sharedDriver(Browser.valueOf(browser));
	}

	@Given("^customer is on bbc weather page \"([^\"]*)\"$")
	public void customer_is_on_bbc_weather_page(String URL) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    selenium.navigate(URL);
	}

	@When("^customer types \"([^\"]*)\" in the \"([^\"]*)\"$")
	public void customer_types_in_the(String text, String objectName) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	   selenium.type(text, objectName);
	}

	@And("^customer clicks \"([^\"]*)\" in \"([^\"]*)\"$")
	public void customer_clicks_in(String text,String objectName) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	   selenium.sendKeys(text, objectName);
	}

	@Then("^customer should see forcast displayed showing \"([^\"]*)\" on the page$")
	public void customer_should_see_forcast_displayed_showing_on_the_page(String southampton) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    selenium.isElementPresent(southampton);
	}

	@Then("^customer closes browser$")
	public void customer_closes_browser ()throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	   Thread.sleep(6000);
		selenium.closeBrowser();
	}
}
