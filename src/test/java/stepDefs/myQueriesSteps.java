package stepDefs;
import static utilities.reporting.LogUtil.logger;
import static utilities.selenium.SeleniumDSL.*;

import java.io.IOException;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import uiMap.JiraUIMap;
import utilities.general.Property;
import testobjects.*;

public class myQueriesSteps  {
	
	@Then("^i \"([^\"]*)\" the query for \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_the_query_for_and_and(String createOrEditOrRun, String toolName, String entityType, String workItemType)  throws Throwable {
	if(createOrEditOrRun.equalsIgnoreCase("create"))
	{
		myQueries.createQuery(toolName,entityType,workItemType);
	}
	else if(createOrEditOrRun.equalsIgnoreCase("edit"))
	{
		myQueries.editQuery();
	}
	else if(createOrEditOrRun.equalsIgnoreCase("delete"))
	{
		myQueries.deleteQuery();
	}
	}
	@Then("^i \"([^\"]*)\" the column options for \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_the_column_options_for_and_and(String edit,String toolName, String entityType, String workItemType) throws Throwable {
	    myQueries.editColumnOptions();
	}
	@Given("^i login to application \"([^\"]*)\" using \"([^\"]*)\"$")
	public void i_login_to_application_using(String AppName, String username) throws Throwable {
	     myQueries.loginwithusername2();
	}

	@Then("^i verify the shared query$")
	public void i_verify_the_shared_query() throws Throwable {
	    myQueries.checkForSharedQuery();
	    
	}
//	@Then("^i create the query and validate the product instance$")
//	public void i_create_the_query_and_validate_the_product_instance() throws Throwable {
//	    myQueries.validateVisibilityOfPI();
//	}

	@Then("^i create the query and validate the product instance$")
	public void i_create_the_query_and_validate_the_product_instance() throws Throwable {
		 myQueries.validateVisibilityOfPI();
	}


}
