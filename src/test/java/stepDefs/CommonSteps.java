package stepDefs;

import static utilities.selenium.SeleniumDSL.*;

import java.io.*;

import utilities.general.Property;


import cucumber.api.java.en.Given;
import testobjects.*;

public class CommonSteps {
	
	@Given("^i close the browser session")
	public void iClosetheBrowser()  {
		driver().quit();
	}

	
	@Given("^i load the project properties file$")
	public void iloadpropertyFile() throws Throwable {
		String propsPath = System.getProperty("user.dir")+File.separator+"Properties"+File.separator;
		String toolname = Property.getTool("Tool");

		if(toolname.trim().equalsIgnoreCase("SauceDemo")){
			CommonFunctions.copyFileContentFromOneFileToAnother(new File(propsPath+"SauceDemo.properties"),new File(propsPath+"project.properties"));
		}
		  
	}

	@Given("^i load the \"([^\"]*)\" project properties file$")
	public void i_load_the_project_properties_file(String tooln) throws IOException {
		// Write code here that turns the phrase above into concrete actions
		//throw new PendingException();

		String propsPath = System.getProperty("user.dir") + File.separator + "Properties" + File.separator;
		String toolname = tooln;

		if (toolname.trim().equalsIgnoreCase("SauceDemo")) {
			CommonFunctions.copyFileContentFromOneFileToAnother(new File(propsPath + "SauceDemo.properties"), new File(propsPath + "project.properties"));
		}


	}


	

}

	
