package stepDefs;
import static utilities.reporting.LogUtil.logger;
import static utilities.selenium.SeleniumDSL.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import uiMap.JiraUIMap;
import utilities.general.Property;
import testobjects.*;

public class ToolsSteps {
	
	@Then("^i verify the \"([^\"]*)\" \"([^\"]*)\" details for \"([^\"]*)\"$")
	public void iVerifyWorkitemDetail(String InBoundOrOutBound, String workitem, String toolname) throws FileNotFoundException, IOException, ParseException {
		if(InBoundOrOutBound.equalsIgnoreCase("Inbound"))
		Tools.VerifyInBoundWorkitemDetails(workitem,toolname);
		else if(InBoundOrOutBound.equalsIgnoreCase("Outbound"))
		Tools.VerifyOutBoundWorkitemDetails(workitem,toolname);
		else Assert.fail("mention inbound or outbound only for workitem verification");
	}
	
	@Then("^i verify the \"([^\"]*)\" \"([^\"]*)\" details for \"([^\"]*)\" after removing DC$")
	public void iVerifyWorkitemDetailafterremovingDC(String InBoundOrOutBound, String workitem, String toolname) throws FileNotFoundException, IOException, ParseException {
		if(InBoundOrOutBound.equalsIgnoreCase("Inbound"))
		Tools.VerifyInBoundWorkitemDetails_DCCheck(workitem,toolname);
		else if(InBoundOrOutBound.equalsIgnoreCase("Outbound"))
		Tools.VerifyOutBoundWorkitemDetails(workitem,toolname);
		else Assert.fail("mention inbound or outbound only for workitem verification");
	}
	
	

}
;