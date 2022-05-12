package testRunner;

import java.io.IOException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import utilities.general.Property;
import utilities.selenium.Context;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(features={
//"classpath:features/SecurityTests/SecurityTest.feature",
		"classpath:features"

},

tags= {""},

//tags = {"@5WorkItemCreation_TFSAgile"},
//tags = {"@6IBValidation_TFSAgile"},


//tags = {"@5WorkItemCreation_TFSScrum"},
//tags = {"@6IBValidation_TFSScrum"},

//tags = {"@5WorkItemCreation_ADOPJira"},
//tags = {"@6IBValidation_ADOPJira"},

//tags = {"@5WorkItemCreation_ADTJira"},
//tags = {"@6IBValidation_ADTJira"},

//tags = {"@5WorkItemCreation_CloudJira"},
//tags = {"@6IBValidation_CloudJira"},

//tags = {"@3GenericUploader_NoToolInstance"},
//tags = {"@7GenericUploader_NoTool_IBVerfification"},

//tags = {"@1WorkItemCreation_AIDT"},
//tags = {"@2IBValidation_AIDT"},

//tags = {"@MSPS_1_AddingTitleinRule"},
//tags = {"@MSPS_2_EntityCreation_InUI"},
//tags = {"@2MSPS_API_IB_Verfification"},
		

//dryRun = true,


	

glue = { "stepDefs" })




public class TestRunner {
	@BeforeClass
	public static void setContext() throws IOException{
		Context.getInstance().setContext("local","windows","chrome","","","",Property.getProperty("targetApp"));
	}
//	@AfterClass
//	public static void markForClosure(){
//		RunnerMain.checkForBatches.add(new Boolean("TRUE"));
//	}
}





