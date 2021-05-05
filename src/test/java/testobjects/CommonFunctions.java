package testobjects;
import java.io.*; 
import java.util.*; 
import static utilities.reporting.LogUtil.logger;
import static utilities.reporting.Reporting.create_logs_and_report;

import static utilities.selenium.SeleniumDSL.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import uiMap.MyWizardUIMap;
import utilities.general.Property;

public class CommonFunctions {
	
	public static String convertdatetogivenformat(String datetobeconverted,String fromdateformat,String todateformat){
		
		try{
		SimpleDateFormat sdf = new SimpleDateFormat(fromdateformat);
	    SimpleDateFormat sdf2 = new SimpleDateFormat(todateformat);
	    return sdf2.format(sdf.parse(datetobeconverted));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.info("issue with date format or converting date format");
			return "";
		}
		
	}
	public static String SpiltWorkitem(String fullworkitemID)
	{
		
			if(fullworkitemID!=null)
			{
			String fullworkitem_sp [] = fullworkitemID.split(" ");
			System.out.println(fullworkitem_sp.length);
			int s = fullworkitem_sp.length;
			return fullworkitem_sp[fullworkitem_sp.length-1];
			}
		else
			return null;
}

	public static void copyFileContentFromOneFileToAnother(File A, File B) throws IOException { 
        FileInputStream in = new FileInputStream(A); 
        FileOutputStream out = new FileOutputStream(B); 
  
        try { 
  
            int n; 
  
            // read() function to read the 
            // byte of data 
            while ((n = in.read()) != -1) { 
                // write() function to write 
                // the byte of data 
                out.write(n); 
            } 
        } 
        finally { 
            if (in != null) { 
  
                // close() function to close the 
                // stream 
                in.close(); 
            } 
            // close() function to close 
            // the stream 
            if (out != null) { 
                out.close(); 
            } 
        } 
        System.out.println("Property File loaded"); 
        logger.info("Property File loaded"); 
    } 
	public static void generateToken(String env) throws IOException {
		try{
		switch(env){
		case "DevTest":
		case "devtest":
			String PostUrl="https://login.microsoftonline.com/f3211d0e-125b-42c3-86db-322b19a65a22/oauth2/token";
			 Response response = RestAssured
					    .given()
					    .header("Content-Type", "application/x-www-form-urlencoded")
					    .formParam("grant_type", "client_credentials")
					    .formParam("client_id", "af3d00bb-72bd-4ad9-8ea6-b5dd5c650aed")
					    .formParam("resource","af3d00bb-72bd-4ad9-8ea6-b5dd5c650aed")
					    .formParam("client_secret", "Y1N2T2VzIyV3dFJ4JUJnNA==")
					    .request()
					    .post(PostUrl);
			 Thread.sleep(10000);
			 if(response.getStatusCode()!=200)
				 {
					logger.info("Got "+response.getStatusCode()+" reponse code for "+env+" for the Token Generation ");
						 Assert.fail("Got "+response.getStatusCode()+" reponse code for "+env+" for the Token Generation ");
						
				 }
				 Assert.assertEquals(response.getStatusCode(), 200);
				 logger.info("token gen comlpete");
				 JsonPath js = response.jsonPath();
					String token = js.get("access_token");
					Property.setProperty("Token", token);
			break;
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Issue generating the token for "+env+" environment");
		}
	}

	public static void checkoverallstatusofworkitemcreation(String env) {
		if(Baseclass.getInstance().workitemcreation_fail)
			Assert.fail("Issue with workitem creation for "+env);
		
	}
	
	public static int GenerateRandomNumber()
	{
		Random rnd = new Random();
		return (1000 + rnd.nextInt(9000));
		
	}
	public static void captureIterationExternalID(String toolOrRMP, String toolname) {
		try{
		ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
		Thread.sleep(5000);
		ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
		ExpWaitForCondition(MyWizardUIMap.selectmyquery);
		clickJS(MyWizardUIMap.selectmyquery);
		ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
		Thread.sleep(5000);
		ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
		String releasename="";
		String sprintname="";
		if(toolOrRMP.equalsIgnoreCase("tool")){
		HashMap<String,String> hm = Tools.getReleaseAndSprintDetails(toolname);
		releasename = hm.get("ReleaseName");
		sprintname = hm.get("SprintName");
		}
		else if(toolOrRMP.equalsIgnoreCase("RMP"))
		{
			HashMap<String,String> hm = RMP.getReleaseAndSprintDetailsCreatedInRMP(toolname);
			releasename = hm.get("ReleaseName_FromRMP");
			sprintname = hm.get("SprintName_FromRMP");
		}
		doubleClick(MyWizardUIMap.QueryValue_txtbox);
		Thread.sleep(4000);
		
		enterText(MyWizardUIMap.QueryValueInput_txtbox,releasename);
		clickJS(MyWizardUIMap.runQuery_btn);
		ExpWaitForCondition(MyWizardUIMap.QueryRunSuccess_Msg);
		boolean morethanonerow_QueryResult = CheckIfElementExists(MyWizardUIMap.GetIterationExternalID_MoreThanoneRow_statictxt);
		if(morethanonerow_QueryResult)
		{
			Assert.fail("More than one row in search result for the searched release or sprint ID");
		}
		String release_IterationExternalID="";
		if(CheckIfElementExists(MyWizardUIMap.GetIterationExternalID_statictxt)){
		release_IterationExternalID = getText(MyWizardUIMap.GetIterationExternalID_statictxt);
		}
		else
		{
			Assert.fail("Release not flown for the tool"+toolname);
		}
		
//		enter sprint details
		clickJS(MyWizardUIMap.QueryValue_txtbox);
		sendDelete();
		Thread.sleep(4000);
		enterText(MyWizardUIMap.QueryValueInput_txtbox,sprintname);
		clickJS(MyWizardUIMap.runQuery_btn);
		ExpWaitForCondition(MyWizardUIMap.QueryRunSuccess_Msg);
		Thread.sleep(5000);
		boolean morethanonerow_QueryResult_Sprint = CheckIfElementExists(MyWizardUIMap.GetIterationExternalID_MoreThanoneRow_statictxt);
		if(morethanonerow_QueryResult_Sprint)
		{
			Assert.fail("More than one row in search result for the searched sprint ID");
		}
		String sprint_IterationExternalID="";
		if(CheckIfElementExists(MyWizardUIMap.GetIterationExternalID_statictxt)){
			sprint_IterationExternalID = getText(MyWizardUIMap.GetIterationExternalID_statictxt);
		}
		else
		{
			Assert.fail("Sprint not flown for the tool"+toolname);
		}
		Baseclass.getInstance().release_IterationExternalID = release_IterationExternalID;
		Baseclass.getInstance().sprint_IterationExternalID =sprint_IterationExternalID; 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.info("issue running the query in myqueiries to fectch IterationExternalID");
		}
	}

	
	
	
	
		
	}
