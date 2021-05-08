package stepDefs;
import static utilities.reporting.LogUtil.logger;

import static utilities.selenium.SeleniumDSL.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;

import utilities.general.Property;
import utilities.selenium.Context;
import org.json.simple.JSONObject;

import org.testng.Assert;




import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.AssertionFailedError;
import testobjects.*;
import uiMap.JiraUIMap;
import uiMap.TFSUIMap;
import uiMap.MyWizardUIMap;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
public class CommonSteps {
	
	@Given("^i close the browser session")
	public void iClosetheBrowser()  {
		driver().quit();
	}

	
	@Given("^i load the project properties file$")
	public void iloadpropertyFile() throws Throwable {
		String propsPath = System.getProperty("user.dir")+File.separator+"Properties"+File.separator;
		String toolname = Property.getTool("Tool");

		if(toolname.trim().equalsIgnoreCase("TFS Agile")){
			CommonFunctions.copyFileContentFromOneFileToAnother(new File(propsPath+"TFSAgile.properties"),new File(propsPath+"project.properties"));
		}
		if(toolname.trim().equalsIgnoreCase("ADT Jira")){
			CommonFunctions.copyFileContentFromOneFileToAnother(new File(propsPath+"ADTJira.properties"),new File(propsPath+"project.properties"));
		}
		if(toolname.trim().equalsIgnoreCase("Rally")){
			CommonFunctions.copyFileContentFromOneFileToAnother(new File(propsPath+"Rally.properties"),new File(propsPath+"project.properties"));
		}
		if(toolname.trim().equalsIgnoreCase("Cloud Jira")){
			CommonFunctions.copyFileContentFromOneFileToAnother(new File(propsPath+"CloudJira.properties"),new File(propsPath+"project.properties"));
		}
		if(toolname.trim().equalsIgnoreCase("ADOP Jira")){
			CommonFunctions.copyFileContentFromOneFileToAnother(new File(propsPath+"ADOPJira.properties"),new File(propsPath+"project.properties"));
		}
		if(toolname.trim().equalsIgnoreCase("TFS Scrum")){
			CommonFunctions.copyFileContentFromOneFileToAnother(new File(propsPath+"TFSScrum.properties"),new File(propsPath+"project.properties"));
		}
		  
	}
	
	@Given("^i capture the IterationExternalID for deleted Iteration created from \"([^\"]*)\" for tool \"([^\"]*)\"$")
	public void icaptureIterationExternalID_deletedIteration(String toolOrRMP, String toolname) throws Throwable {
		CommonFunctions.setIterationExternalID(toolname);
	}
	
	@Given("^i capture the IterationExternalID for Iteration created from \"([^\"]*)\" for tool \"([^\"]*)\"$")
	public void icaptureIterationExternalID(String toolOrRMP, String toolname) throws Throwable {
		CommonFunctions.captureIterationExternalID(toolOrRMP, toolname);
	}
	
	
	@Given("^i login to application \"([^\"]*)\"$")
	public void iLoginToApplicationWith(String AppName) throws Throwable {
		
		try{
 		switch(AppName)
		{
			case "Jira":
			case "ADT Jira":
			case "ADOP Jira":
			{
				CommonAcrossApps.loginToJira();
				
			}
			break;
			case "Cloud Jira":
			case "cloud jira":
			{
				CommonAcrossApps.loginToCloudJira();
				
			}
			break;
			case "MyWizard":
			case "mywizard":
			{
				CommonAcrossApps.LoginToMyWizard();
			}
			break;
			case "TFS Agile":
			case "TFS Scrum":
			case "TFS":
			case "tfs":
			{
				CommonAcrossApps.LoginToTFS();
				
			}
			break;
			
			case "Rally":
			case "rally":
			{
				CommonAcrossApps.LoginToRally();
				
			}
			break;
			
		
			default:
	        throw new IllegalArgumentException("Invalid Applicationname: " + AppName);	
		}}
	catch(Exception e)
	{
		e.printStackTrace();
		Assert.fail();
		
	}}

	@SuppressWarnings("unchecked")
	@And("^i update the WorkItemExternalIDs into a JSON file for \"([^\"]*)\"$")
	public void iUpdateTheWorkItemExternalIDsIntoAJSONFile(String appname) throws Throwable {
		
		CommonAcrossApps.UpdateWorkItemExternalIDsForApps(appname);
		
		
		}	
	

	
	@And("^i update the WorkItemExternalIDs into a JSON file for \"([^\"]*)\" for \"([^\"]*)\" functionality$")
	public void iUpdateTheWorkItemExternalIDsIntoAJSONFile(String appname,String MoveProjectOrIssue) throws Throwable {
		
		CommonAcrossApps.UpdateWorkItemExternalIDsForAppsForMoveProjectOrIssue(appname);
		
		
		}	
		
	
	@Then("^i validate the outbound flow for \"([^\"]*)\"$")
	public void iValidateTheOutboundFlow(String application) throws Throwable {
		if(application.contains("Jira"))
			JiraWorkitem.ValidateOB(application);
		if(application.contains("TFS"))
			TFSWorkitem.ValidateOB(application);
	}
	
	@Then("^i select a Project for \"([^\"]*)\"$")
	public void iSelectAProject(String AppName) throws Throwable {
		
		if(AppName.equalsIgnoreCase("jira") || AppName.equalsIgnoreCase("ADT Jira") || AppName.equalsIgnoreCase("ADOP Jira"))
		{
		JiraWorkitem.SelectProject();
		Baseclass.getInstance().workitemcreation_fail = false;
		}
		if(AppName.equalsIgnoreCase("cloud jira"))
		{
		JiraWorkitem.selectCloudJiraProject();
		Baseclass.getInstance().workitemcreation_fail = false;
		Thread.sleep(20000);
		}
		if(AppName.equalsIgnoreCase("Rally"))
		{
		RallyWorkitem.SelectProjectForrally(AppName);
		Baseclass.getInstance().workitemcreation_fail = false;
		}
		if(AppName.equalsIgnoreCase("TFS Scrum") || AppName.equalsIgnoreCase("TFS Agile") || AppName.contains("TFS") || AppName.contains("tfs"))
		{
			TFSWorkitem.SelectProject();
			String currentproject_sp[] = driver().getCurrentUrl().split(Property.getProperty("TFS_URL")+"/");
			Baseclass.getInstance().TFSProject = currentproject_sp[1];
		}
	}
	
	@Then("^i verify the Release and Sprint for \"([^\"]*)\"$")
	public void i_verify_the_Release_and_Sprint_for(String AppName) throws Throwable {
		if(AppName.equalsIgnoreCase("jira"))
			JiraWorkitem.VerifyReleaseAndSprintFromRMP(AppName);
		if(AppName.equalsIgnoreCase("TFS"))
			TFSWorkitem.VerifyReleaseAndSprintFromRMP(AppName);
	}
	
	@Then("^i put a explicit wait of \"([^\"]*)\"$")
	public void i_put_explicit_wait(int waitInSec) throws Throwable {
		try{
			Thread.sleep(waitInSec);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	@Then("^i generate a token for \"([^\"]*)\" environment$")
	public void generateToken(String Env) throws Throwable {
		try{
			CommonFunctions.generateToken(Env);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	@Then("^i check the overall status of workitem creation for \"([^\"]*)\"$")
	public void checkoverallstatusofworkitemcreation(String Env) throws Throwable {
		try{
			CommonFunctions.checkoverallstatusofworkitemcreation(Env);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	


	




	

}

	
