package stepDefs;

import static utilities.selenium.SeleniumDSL.*;

import org.testng.Assert;

import cucumber.api.java.en.Then;
import net.lightbody.bmp.proxy.CaptureType;
import testobjects.MappingRules;
import testobjects.MyWizardHomePage;
import uiMap.MyWizardUIMap;
import utilities.selenium.DriverFactory;

public class MyWizardSteps {
	
	@Then("^i select client and DC for \"([^\"]*)\"$")
	public void i_select_client_and_DC(String appname) throws Throwable {
		
		MyWizardHomePage.SelectClientAndDC();
	
	
		
	}
	
	@Then("^i select only the client for \"([^\"]*)\"$")
	public void i_select_only_the_client_for(String arg1) throws Throwable {
	
		MyWizardHomePage.SelectOnlyClient();
		
	}

	
	@Then("^i click on tile \"([^\"]*)\"$")
	public void i_click_on_tile(String Tilename) throws Throwable {
		
	  MyWizardHomePage.clickOnTile(Tilename);

	
	}
	
	@Then("^i navigate to the homepage of \"([^\"]*)\" from \"([^\"]*)\"$")
	public void i_navigate_to_the_homepage_of_from(String arg1, String arg2) throws Throwable {
		MyWizardHomePage.NavigateToHomePage();
	}
	
	@Then("^i verify and add the rules if missing for the \"([^\"]*)\"$")
	public void i_verify_rules(String toolname) throws Throwable {

		String[] JIRA_WorkItems = {"Task", "Epic", "Feature", "UserStory", "Bug", "Impediment", "Issue", "Risk"};
//		String[] JIRA_WorkItems = {"Task", "Epic"};
		
		String[] ADTJira_NonWorkItems = {"Test","Deliverable","Requirement","Iteration"};
		
		String[] ADOPJira_NonWorkItems = {"Iteration"};
		String[] TFSAgile_WorkItems = {"Task", "Epic", "Feature", "UserStory", "Bug", "Impediment", "Issue"};
		String[] TFSScrum_WorkItems = {"Task", "Epic", "Feature", "UserStory", "Bug", "Impediment"};
		String[] TFS_NonWorkItems = {"Iteration", "Test", "Deliverable"};
	
//		String[] JIRA_WorkItems = {};
//		String[] ADTJira_NonWorkItems = {"Deliverable","Iteration"};
		
		if(toolname.equalsIgnoreCase("ADT JIRA"))
		{
			MappingRules.VerifyAndAddRulesIfMissing(toolname,JIRA_WorkItems,ADTJira_NonWorkItems);
		}
		else if(toolname.equalsIgnoreCase("ADOP JIRA"))
		{
			MappingRules.VerifyAndAddRulesIfMissing(toolname,JIRA_WorkItems,ADOPJira_NonWorkItems);
		}
		else if(toolname.equalsIgnoreCase("TFS Agile"))
		{
			MappingRules.VerifyAndAddRulesIfMissing("myWizard-TFS",TFSAgile_WorkItems,TFS_NonWorkItems);
		}
		else if(toolname.equalsIgnoreCase("TFS Scrum"))
		{
			MappingRules.VerifyAndAddRulesIfMissing("myWizard-TFS",TFSScrum_WorkItems,TFS_NonWorkItems);
		}
		else
			Assert.fail("tool not found");
	
	}

}
