package testobjects;
import java.io.*; 
import java.util.*;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import static utilities.reporting.LogUtil.logger;
import static utilities.reporting.Reporting.create_logs_and_report;

import static utilities.selenium.SeleniumDSL.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;

import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import dataobjects.WorkItemDO;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import uiMap.MyWizardUIMap;
import uiMap.myQueriesUIMap;
import utilities.general.DataManager;
import utilities.general.Property;
import uiMap.MyWizardUIMap;
import utilities.general.Property;
import utilities.selenium.SeleniumDSL;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import org.apache.commons.codec.binary.Base64;
public class CommonFunctions {
	
	public static String testDataPath = System.getProperty("user.dir")
			+ File.separator + "src" + File.separator + "test" + File.separator
			+ "resources" + File.separator + "testdata" + File.separator;
	
	private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private static KeySpec ks;
    private static SecretKeyFactory skf;
    private static Cipher cipher;
    static byte[] arrayBytes;
    private static String myEncryptionKey;
    private static String myEncryptionScheme;
    static SecretKey key;
    
    
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
	
	public static WorkItemDO GetFieldValueFromWorkItemJSON(String toolname, String workitem) {
		try{
			String testDataPath = System.getProperty("user.dir")+File.separator + "src" + File.separator + "test" + File.separator+ "resources" + File.separator + "testdata" + File.separator;
			
			String testDataPath_WorkItemExternalIDs="";
			if((toolname.equalsIgnoreCase("ADT Jira") || toolname.equalsIgnoreCase("ADOP Jira") || toolname.contains("Jira") || toolname.contains("JIRA")))
			{
					testDataPath_WorkItemExternalIDs = testDataPath + "Jira" + File.separator + "JSON" +  File.separator + "WorkItem.json" ;
			}
			
			else if((toolname.equalsIgnoreCase("TFS Agile") || toolname.equalsIgnoreCase("TFS Scrum") || toolname.contains("TFS")))
			{
				testDataPath_WorkItemExternalIDs = testDataPath + "TFS" + File.separator + "JSON" +  File.separator; 
			}		
				WorkItemDO wi = DataManager.getData(testDataPath_WorkItemExternalIDs, "WorkItem",WorkItemDO.class).item.get(workitem);
				return wi;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
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
		Thread.sleep(10000);
		boolean morethanonerow_QueryResult_Sprint = CheckIfElementExists(MyWizardUIMap.GetIterationExternalID_MoreThanoneRow_statictxt);
		if(morethanonerow_QueryResult_Sprint)
		{
			Assert.fail("More than one row in search result for the searched sprint ID");
		}
		String sprint_IterationExternalID="";
		Thread.sleep(5000);
		if(CheckIfElementExists(MyWizardUIMap.GetIterationExternalID_statictxt)){
			sprint_IterationExternalID = getText(MyWizardUIMap.GetIterationExternalID_statictxt);
		}
		else
		{
			Assert.fail("Sprint not flown for the tool"+toolname);
		}
		Baseclass.getInstance().release_IterationExternalID = release_IterationExternalID;
		Baseclass.getInstance().sprint_IterationExternalID =sprint_IterationExternalID; 
		writeIterationExternalIDs(release_IterationExternalID,sprint_IterationExternalID,toolname);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.info("issue running the query in myqueiries to fectch IterationExternalID");
		}
	}
	public static void writeIterationExternalIDs(String release_IterationExternalID, String sprint_IterationExternalID,
			String toolname) {
		
		try{
		String writeIterationExternalIDs_Details="";
		
			if(toolname.contains("Jira") || toolname.contains("JIRA")){
				writeIterationExternalIDs_Details = testDataPath + "Jira" + File.separator + "JSON" +  File.separator + "IterationExternalIDs.json" ;
			}
			else if(toolname.contains("TFS") || toolname.contains("Tfs")){
				writeIterationExternalIDs_Details = testDataPath + "TFS" + File.separator + "JSON" +  File.separator + "IterationExternalIDs.json" ;
			}
		
			FileReader reader = new FileReader(writeIterationExternalIDs_Details);
	        JSONParser jsonParser = new JSONParser();
	        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
	        jsonObject.put("IterationExternalID_Release",release_IterationExternalID);
	        jsonObject.put("IterationExternalID_Sprint",sprint_IterationExternalID);
	        FileOutputStream outputStream = new FileOutputStream(writeIterationExternalIDs_Details);
			 byte[] strToBytes = jsonObject.toString().getBytes(); outputStream.write(strToBytes);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void writeTeamDetails(String toolname) {
		
		try{
		String writeIterationExternalIDs_Details="";
		
			if(toolname.contains("Jira") || toolname.contains("JIRA")){
				writeIterationExternalIDs_Details = testDataPath + "Jira" + File.separator + "JSON" +  File.separator + "WorkItemExternalIDs.json" ;
			}
			else if(toolname.contains("TFS") || toolname.contains("Tfs")){
				writeIterationExternalIDs_Details = testDataPath + "TFS" + File.separator + "JSON" +  File.separator + "WorkItemExternalIDs.json" ;
			}
		
			FileReader reader = new FileReader(writeIterationExternalIDs_Details);
	        JSONParser jsonParser = new JSONParser();
	        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
	        jsonObject.put("TeamUId",Baseclass.getInstance().TeamUId);
	        jsonObject.put("Team_Name",Baseclass.getInstance().teamName);
	        FileOutputStream outputStream = new FileOutputStream(writeIterationExternalIDs_Details);
			 byte[] strToBytes = jsonObject.toString().getBytes(); outputStream.write(strToBytes);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void setIterationExternalID(String toolname) {
		
		String FileToFetch="";
			try{
			if(toolname.contains("Jira") || toolname.contains("JIRA")){
				FileToFetch = testDataPath + "Jira" + File.separator + "JSON" +  File.separator + "IterationExternalIDs.json" ;
			}
			else if(toolname.contains("TFS") || toolname.contains("Tfs")){
				FileToFetch = testDataPath + "TFS" + File.separator + "JSON" +  File.separator + "IterationExternalIDs.json" ;
			}
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader(FileToFetch));
			JSONObject jsonObject = (JSONObject) obj;
			
			Baseclass.getInstance().release_IterationExternalID= (String) jsonObject.get("IterationExternalID_Release");
			Baseclass.getInstance().sprint_IterationExternalID = (String) jsonObject.get("IterationExternalID_Sprint");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	  public static String encrypt(String unencryptedString) {
	        String encryptedString = null;
	        try {
	        	myEncryptionKey = "ThisIsSpartaThisIsSparta";
		        myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
		        arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
		        ks = new DESedeKeySpec(arrayBytes);
		        skf = SecretKeyFactory.getInstance(myEncryptionScheme);
		        cipher = Cipher.getInstance(myEncryptionScheme);
		        key = skf.generateSecret(ks);
	            cipher.init(Cipher.ENCRYPT_MODE, key);
	            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
	            byte[] encryptedText = cipher.doFinal(plainText);
	            encryptedString = new String(Base64.encodeBase64(encryptedText));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return encryptedString;
	    }


	    public static String decrypt(String encryptedString) {
	        String decryptedText=null;
	        try {
	        	myEncryptionKey = "ThisIsSpartaThisIsSparta";
		        myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
		        arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
		        ks = new DESedeKeySpec(arrayBytes);
		        skf = SecretKeyFactory.getInstance(myEncryptionScheme);
		        cipher = Cipher.getInstance(myEncryptionScheme);
		        key = skf.generateSecret(ks);
	            cipher.init(Cipher.DECRYPT_MODE, key);
	            byte[] encryptedText = Base64.decodeBase64(encryptedString);
	            byte[] plainText = cipher.doFinal(encryptedText);
	            decryptedText= new String(plainText);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return decryptedText;
	    }

		public static void captureEntityDetails(String entitydetailsToCapture, String toolname) {
			try{
			ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
			Thread.sleep(5000);
			ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
			ExpWaitForCondition(MyWizardUIMap.selectmyquery);
			clickJS(MyWizardUIMap.selectmyquery);
			ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
			Thread.sleep(5000);
			ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
			
			doubleClick(MyWizardUIMap.QueryValue_txtbox);
			Thread.sleep(4000);
			
	
			
			String[] MSPSEntities = {"RelForMSPS","Initiative","FunctionalArea","Milestone","Deliverable"}; 
		
			for(String entity:MSPSEntities){
			
				switch(entity){
				
				case "RelForMSPS":
					HashMap<String,String> hm = Tools.getReleaseAndSprintDetails(toolname);
					EnterEntityNameAndcheckIfQueryExecutedSuccessfully(hm.get("ReleaseName"),toolname);
					Baseclass.getInstance().release_IterationExternalID=getText(MyWizardUIMap.GetIterationExternalID_statictxt);
					System.out.println(entity +" external ID is "+getText(MyWizardUIMap.GetIterationExternalID_statictxt));
					clickJS(MyWizardUIMap.QueryValue_txtbox);
					sendDelete();
					Thread.sleep(2000);
					break;
					
					case "Initiative":
						
						EnterEntityNameAndcheckIfQueryExecutedSuccessfully(entity,toolname);
						Baseclass.getInstance().InitiativeExternalID=getText(MyWizardUIMap.GetIterationExternalID_statictxt);
						System.out.println(entity +" external ID is "+getText(MyWizardUIMap.GetIterationExternalID_statictxt));
						clickJS(MyWizardUIMap.QueryValue_txtbox);
						sendDelete();
						Thread.sleep(2000);
					break;
				
				case "FunctionalArea":
					EnterEntityNameAndcheckIfQueryExecutedSuccessfully(entity,toolname);
					Baseclass.getInstance().FunctionalAreaExternalID=getText(MyWizardUIMap.GetIterationExternalID_statictxt);
					System.out.println(entity +" external ID is "+getText(MyWizardUIMap.GetIterationExternalID_statictxt));
					clickJS(MyWizardUIMap.QueryValue_txtbox);
					sendDelete();
					Thread.sleep(2000);
					break;
				case "Milestone":
					clickJS(myQueriesUIMap.Queries_link);
					ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
					clickJS(myQueriesUIMap.selectMilestoneQuery_link);
					ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
					Thread.sleep(4000);
					ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
					EnterEntityNameAndcheckIfQueryExecutedSuccessfully(entity,toolname);
					Baseclass.getInstance().MilestoneExternalID=getText(MyWizardUIMap.GetIterationExternalID_statictxt);
					System.out.println(entity +" external ID is "+getText(MyWizardUIMap.GetIterationExternalID_statictxt));
					break;
				case "Deliverable":
					clickJS(myQueriesUIMap.Queries_link);
					ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
					clickJS(myQueriesUIMap.selectDeliverableQuery_link);
					ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
					Thread.sleep(4000);
					ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
					EnterEntityNameAndcheckIfQueryExecutedSuccessfully(entity,toolname);
					Baseclass.getInstance().MilestoneExternalID=getText(MyWizardUIMap.GetIterationExternalID_statictxt);
					System.out.println(entity +" external ID is "+getText(MyWizardUIMap.GetIterationExternalID_statictxt));
					break;
		
					
				}
			}
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		public static void EnterEntityNameAndcheckIfQueryExecutedSuccessfully(String entitydetailsToCapture,String toolname) {
			try{
				singleClick(By.xpath("//div[@role='row']/child::div[@col-id='Value']"));
				enterText(MyWizardUIMap.QueryValueInput_txtbox,Tools.getWorkItemExternalID(entitydetailsToCapture, toolname));
				clickJS(MyWizardUIMap.runQuery_btn);
				ExpWaitForCondition(MyWizardUIMap.QueryRunSuccess_Msg);
				}
				catch(Exception e)
				{
					logger.info("Query not run successfully in myqueries tile while fetching MSPS details for "+entitydetailsToCapture);
					Assert.fail("Query not run successfully in myqueries tile while fetching MSPS details for "+entitydetailsToCapture);
				}
				boolean morethanonerow_QueryResult = CheckIfElementExists(MyWizardUIMap.GetIterationExternalID_MoreThanoneRow_statictxt);
				if(morethanonerow_QueryResult)
				{
					Assert.fail("More than one row in search result for the entity "+entitydetailsToCapture+" in myqueries tile");
				}
			
		}

	
	
	
	
		
	}
