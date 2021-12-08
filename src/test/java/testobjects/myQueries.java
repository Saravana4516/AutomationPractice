package testobjects;

import static utilities.selenium.SeleniumDSL.*;
import static utilities.reporting.LogUtil.logger;
import java.io.File;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import uiMap.MyWizardUIMap;
import uiMap.TeamConfigUIMap;
import utilities.general.Property;
import uiMap.myQueriesUIMap;

public class myQueries extends Baseclass {
	private Baseclass base;
	static SoftAssert sa = new SoftAssert();
	public myQueries() {
		
	}

	public myQueries(Baseclass base) {
		this.base = base;
	}

	public static String testDataPath = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "test" + File.separator + "resources" + File.separator + "testdata" + File.separator + "Jira"
			+ File.separator + "JSON" + File.separator;

	public static void createQuery(String toolName,String entityType,String workItemType)
	{
		try{
		ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
		clickJS(myQueriesUIMap.NewQuery_text);
		ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
		String classAttribute_value = getAttribute(myQueriesUIMap.Standard_button,"class");
		sa.assertEquals(classAttribute_value.contains("active"), 1,"Standard query is not selected"); //Verify that Standard Query is selected by default
		ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
		selectDropdownByText(myQueriesUIMap.Entity_dropdown,entityType);//Select the entity from entitytype dropdown
		
		ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
		ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
		selectDropdownByText(myQueriesUIMap.workItemType_dropdown,workItemType); //Select the workitem from workitem dropdown
		ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
		Thread.sleep(3000);
		Assert.assertEquals(isVisible(myQueriesUIMap.condition_text), true,"Condition column is not available"); //Checking that 'Condition' column is available
		clickJS(myQueriesUIMap.Field_txtBox);
		specifyFieldsAndRun(); //Calling the method to run the query
		//ExpWaitForCondition(myQueriesUIMap.recordsRetrieved_staticTxt); //Verify the toaster message
		ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
		//Add code to export query
		clickJS(myQueriesUIMap.SaveQuery_txt); //Save the query
		enterText(myQueriesUIMap.QueryName_txtbox,"Auto_Regression");
		selectDropdownByText(myQueriesUIMap.QueryType_dropdown,"Shared Queries");  //Save the query to shared folder
		clickJS(myQueriesUIMap.saveQuery_button);
		ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
		//DeleteAllFileWithNameSubString("exportQueryCSV"); //Deleting all the files from downloads
		//DownloadQueryResults();                           //Exporting the results
		//ExpWaitForCondition(myQueriesUIMap.querySaved_txt);
		//revertUnsavedChanges(); // To revert the unsaved changes
		clickJS(myQueriesUIMap.navigateToQueries_txt);
		Thread.sleep(3000);
		checkForSharedQuery();
		//ExpWaitForCondition(myQueriesUIMap.savedQuery_txt);
//		Assert.assertEquals(CheckIfElementExists(myQueriesUIMap.savedQuery_txt),true,"Query is not saved"); //Assert if the query is saved
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.info("Creation of query failed");
			Assert.fail("Creation of query failed");
		}		
	}
	public static void editQuery()
	{
		try{
		//clickJS(myQueriesUIMap.savedQuery_txt);
		clickJS(myQueriesUIMap.sharedQuery_txt);
		Thread.sleep(2000); //can be removed
		clickJS(myQueriesUIMap.sharedQuerySaved_txt);
		ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
		Thread.sleep(2000);
		ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
		Thread.sleep(5000);
		singleClick(myQueriesUIMap.NewClause_txt); //Adding a new clause
		Thread.sleep(4000);
		clickJS(myQueriesUIMap.conditionColumn_txt);
		Thread.sleep(2000);
		selectDropdownByText(myQueriesUIMap.conditionColumn_dropdown,"OR");
		Thread.sleep(2000);
		clickJS(myQueriesUIMap.Field_txtBox);
		Thread.sleep(2000);
		selectDropdownByValue(myQueriesUIMap.Field_dropdown,"Priority");
		Thread.sleep(2000);
		clickJS(myQueriesUIMap.value_txtbox2);
		Thread.sleep(2000);
		selectDropdownByValue(myQueriesUIMap.Value_dropdown,"Medium");
		clickJS(myQueriesUIMap.RunQuery_button);
		clickJS(myQueriesUIMap.SaveQuery_txt); //Resave the query
		ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
		ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
		//ExpWaitForCondition(myQueriesUIMap.recordsRetrieved_staticTxt);
		clickJS(myQueriesUIMap.navigateToQueries_txt);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.info("Edit of query failed");
			Assert.fail("Edit of query failed");
		}
	}
	public static void deleteQuery()
	{
		try{
			ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
			singleClick(myQueriesUIMap.DeleteQuery_icon);
			singleClick(myQueriesUIMap.DeleteQueryYes_button);
			sa.assertEquals(CheckIfElementExists(myQueriesUIMap.savedQuery_txt), 0,"Issues deleting query"); //Assert to check that query is not available
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.info("Deletion of query failed");
			Assert.fail("Deletion of query failed");
		}
	}

	public static void editColumnOptions() {
		try{
			clickJS(myQueriesUIMap.sharedQuery_txt);
			clickJS(myQueriesUIMap.sharedQuerySaved_txt);
			ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
			Thread.sleep(2000);	
			ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
			Thread.sleep(5000);
			Assert.assertEquals(isVisible(myQueriesUIMap.ColumnOptions_txt), true,"Column options are not available");
			clickJS(myQueriesUIMap.ColumnOptions_txt);
			clickJS(myQueriesUIMap.deleteColumnOption_button); //Deleting the first column option in the list
			if(CheckElementsSize(myQueriesUIMap.deleteColumnOptions_button)!=4)
				Assert.fail("Deletion of existing column option failed");
			Thread.sleep(1000);
			selectDropdownByText(myQueriesUIMap.firstColumnOption_dropdown,"State");
			Thread.sleep(3000);//can be removed
			clickJS(myQueriesUIMap.saveColumnOption_button);
			clickJS(myQueriesUIMap.RunQuery_button); //Run the query
			ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
			//Editing column option and verifying in the query results if its added
			try{
			singleClick(myQueriesUIMap.workitemtypeColumnOption_txt);
			Thread.sleep(2000);
			for(int i=1;i<=7;i++){
				moveRight();
			}
			ExpWaitForCondition(myQueriesUIMap.stateuidColumnOption_txt);
			}
			catch(Exception e){
				e.printStackTrace();
				logger.info("Edited column option is not available in Query results");
				Assert.fail("Edited column option is not available in Query results");
			}
			//Adding new column option and verifying in the query results if its added
			try{
			clickJS(myQueriesUIMap.ColumnOptions_txt);//Click on column option
			clickJS(myQueriesUIMap.AddField_txt); //Adding column option
			Thread.sleep(2000); //can be removed
			selectDropdownByText(myQueriesUIMap.newColumnOption_dropdown,"Project");
			clickJS(myQueriesUIMap.saveColumnOption_button);
			clickJS(myQueriesUIMap.RunQuery_button);
			ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
			singleClick(myQueriesUIMap.workitemtypeColumnOption_txt);
			for(int i=1;i<=11;i++){
				moveRight();
			}
			ExpWaitForCondition(myQueriesUIMap.projectColumnOption_txt);
			}
			catch(Exception e){
				e.printStackTrace();
				logger.info("Added column option is not available in Query results");
				Assert.fail("Added column option is not available in Query results");
			}
			clickJS(myQueriesUIMap.navigateToQueries_txt);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.info("Edit of column options failed");
			Assert.fail("Edit of column options failed");
		}
		
	}
	public static void specifyFieldsAndRun(){
		try{
			selectDropdownByValue(myQueriesUIMap.Field_dropdown,"Title"); //Select the Title attribute 
			Thread.sleep(2000);
			clickJS(myQueriesUIMap.Operator_txtBox);
			selectDropdownByText(myQueriesUIMap.Operator_dropdown,"Contains"); // Select the operator
			Thread.sleep(2000);
			enterText(myQueriesUIMap.value_txtbox1,"Automation"); 
			clickJS(myQueriesUIMap.RunQuery_button);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.info("Failed to run the query");
			Assert.fail("Failed to run the query");
		}
	}
	public static void revertUnsavedChanges(){
		singleClick(myQueriesUIMap.NewClause_txt); //Adding a new clause
		clickJS(myQueriesUIMap.revertChanges_button); 
		if(CheckIfElementExists(myQueriesUIMap.condition_txt)){
			logger.info("Failed to revert unsaved changes in the query");
			Assert.fail("Failed to revert unsaved changes in the query");
		}
		
	}
	
	
	public static void DownloadQueryResults() {
		        try{
		       clickJS(myQueriesUIMap.exportResults_button); //click on go btn
		       clickJS(myQueriesUIMap.IUnderstand_button);// click on i understand dialogue box
		        Thread.sleep(7000);
		        sendEntr();
		        Thread.sleep(5000);
		        }
		        catch(Exception e)
		        {
		            e.printStackTrace();
		            logger.info("issue downloading my queries excel sheet");
		            Assert.fail("issue downloading my queries excel sheet");
		        }
		    }
		   
		    public static void DeleteAllFileWithNameSubString(String exportQueryCSV )
		    {
		        try{
		        File directory = new File("C:\\Users\\"+Property.getProperty("UserName_LocalUserName")+"\\Downloads\\");  
		        System.out.print(directory);
		        //File directory = new File("C:\\Users\\"+"\\Downloads\\");  
		        File[] files = directory.listFiles();
		        for (File f : files)
		        {
		            System.out.println(f.getName());
		            if (f.getName().startsWith(exportQueryCSV))
		            {
		              f.delete();
		            }
		        }
		        }
		        catch(Exception e)
		        {
		            e.printStackTrace();
		            logger.info("issue deleting files from downloads for myquery test");
		            Assert.fail("issue deleting files from downloads for myquery test");
		        }
		       
		    }
//		   
//		    public static boolean CheckIfSpecificFileExists(String filename)
//		    {
//		        try{
//		        boolean fileexists = false;
//		        File f = new File("C:\\Users\\"+Property.getProperty("UserName_LocalUserName")+"\\Downloads\\exportQueryCSV.csv");
//		        if(f.exists() && !f.isDirectory())
//		            return true;
//		        }
//		        catch(Exception e)
//		        {
//		            e.printStackTrace();
//		       
//		        }
//		        return false;
//		        }
//		   
//		}

			public static void loginwithusername2() {
				try{
					
					driver().get(Property.getProperty("MyWizard_URL"));
					waitPageToLoad();
					Thread.sleep(10000);
//					driver().manage().window().maximize();
					
					
					if(CheckIfElementExists(MyWizardUIMap.signInWithUserNameSaved_txtbox)){
						clear(MyWizardUIMap.signInWithUserNameSaved_txtbox);
						enterText(MyWizardUIMap.signInWithUserNameSaved_txtbox, Property.getProperty("MyWizard_Username2"));
						 enterText(MyWizardUIMap.Pwd_txtbox1,CommonFunctions.decrypt(Property.getProperty("MyWizard_Password")));
						 click(MyWizardUIMap.signIn_btn1);
						 Thread.sleep(10000);
					}
					
					//if sign in with email id page shows up
					if(CheckIfElementExists(MyWizardUIMap.signIn_txtbox)){
						enterText(MyWizardUIMap.signIn_txtbox, Property.getProperty("MyWizard_Username2"));
						clickJS(MyWizardUIMap.Next_btn);
						ExpWaitForCondition(MyWizardUIMap.Pwd_txtbox1);
						 enterText(MyWizardUIMap.Pwd_txtbox1,CommonFunctions.decrypt(Property.getProperty("MyWizard_Password")));
						 click(MyWizardUIMap.signIn_btn1);
						 Thread.sleep(10000);
					}
					
					//if pick an account page shows up
					if(CheckIfElementExists(MyWizardUIMap.PickAnAccount_staticTxt)){
					clickJS(MyWizardUIMap.UserAnotherAccount_link);
					Thread.sleep(5000);
					ExpWaitForCondition(MyWizardUIMap.signIn_txtbox);
					enterText(MyWizardUIMap.signIn_txtbox, Property.getProperty("MyWizard_Username2"));
					clickJS(MyWizardUIMap.Next_btn);
					ExpWaitForCondition(MyWizardUIMap.Pwd_txtbox1);
					Thread.sleep(5000);
					 enterText(MyWizardUIMap.Pwd_txtbox1,CommonFunctions.decrypt(Property.getProperty("MyWizard_Password")));
					 click(MyWizardUIMap.signIn_btn1);
//					 Thread.sleep(10000);
					}
					
					
					
					//if stay signed in page shows up
					ExpWaitForCondition(MyWizardUIMap.Yes_btn);	
						clickJS(MyWizardUIMap.Yes_btn);
					
					
					 ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
					 ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
					 
					 //notification icon mywizard works best in google chrome
//					 if(CheckIfElementExists(MyWizardUIMap.MywizChromeNotification_btn))
//					 {
//						 clickJS(MyWizardUIMap.MywizChromeNotification_btn);
//					 }
					
				     ExpWaitForCondition(MyWizardUIMap.Dashboard_Checkbox);
				                clickJS(MyWizardUIMap.Dashboard_Checkbox);
				                 clickJS(MyWizardUIMap.Dashboard_Confirm_btn);

					 ExpWaitForCondition(MyWizardUIMap.SettingIcon_Image);
//					 
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
					logger.info("MyWizard page could not be loaded");
					Assert.fail("MyWizard page could not be loaded");
				}
				
			}

			public static void checkForSharedQuery() {
				try{
					clickJS(myQueriesUIMap.sharedQuery_txt);
					if(!CheckIfElementExists(myQueriesUIMap.sharedQuerySaved_txt)){
						Assert.fail("Query not found in Shared queries");
					}
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
					logger.info("Query not found in Shared queries");
					Assert.fail("Query not found in Shared queries");
				}
				
			}



			public static void validateVisibilityOfPI() {
				
				try{
					ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
					clickJS(myQueriesUIMap.NewQuery_text);
					ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
					isVisible(myQueriesUIMap.ProductInstance_dropdown);
				}
				catch(Exception e){
					e.printStackTrace();
					logger.info("Product Instance is not available");
					Assert.fail("Product Instance is not available");
				}
			}

			public static void verifyQuery(String toolname, String entityType) {
				try{
					ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
					clickJS(myQueriesUIMap.NewQuery_text);
					ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
					String classAttribute_value = getAttribute(myQueriesUIMap.Standard_button,"class");
					sa.assertEquals(classAttribute_value.contains("active"), 1,"Standard query is not selected"); //Verify that Standard Query is selected by default
					ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
					selectDropdownByText(myQueriesUIMap.Entity_dropdown,entityType);//Select the entity from entitytype dropdown
					
					ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
					ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
					sa.assertEquals(CheckIfElementExists(myQueriesUIMap.workitem_type_dropdown), true,"____WORKITEMTYPE DROPDOWN FOR "+ toolname + " FOR "+entityType+" NOT EXIST_____");
					if(CheckIfElementExists(myQueriesUIMap.workitem_type_dropdown))
						System.out.println("_____WORKITEMTYPE DROPDOWN FOR "+ toolname + " FOR "+entityType+" EXIST_____");
					else 
						System.out.println("____WORKITEMTYPE DROPDOWN FOR "+ toolname + " FOR "+entityType+" NOT EXIST_____");
					ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
					Thread.sleep(5000);
					singleClick(myQueriesUIMap.NewClause_txt); //Adding a new clause
					Thread.sleep(4000);
					singleClick(myQueriesUIMap.select_cross_btn);
					sa.assertEquals(CheckIfElementExists(myQueriesUIMap.conditionColumn_txt),true,"____ROW DELETED CONDITION PASS____");
					if(CheckIfElementExists(myQueriesUIMap.conditionColumn_txt))
						System.out.println("_____Additional row exist Condition Fails_____");
					else System.out.println("____ROW DELETED CONDITION PASS____");
					clickJS(myQueriesUIMap.ColumnOptions_txt);//Click on column option
					clickJS(myQueriesUIMap.AddField_txt); //Adding column option
					Thread.sleep(3000);
					clickJS(myQueriesUIMap.column_option_dropdown);
					clickJS(myQueriesUIMap.column_value_dropdown);
					clickJS(myQueriesUIMap.saveColumnOption_button); 
					Thread.sleep(2000);
					clickJS(myQueriesUIMap.RunQuery_button);
					ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
					ScrollToEndOfPage();
					Thread.sleep(2000);
					singleClick(myQueriesUIMap.workitemUidColumn_header);
					System.out.println(CheckIfElementExists(myQueriesUIMap.workitemUidColumn_header));
					for(int i=0;i<13;i++) {
						moveRight();
					}
					sa.assertEquals(CheckIfElementExists(myQueriesUIMap.Tablecolumn_header),true,"_______NO COLUMN ADDED USING COLUMN OPTION_____");
					if(CheckIfElementExists(myQueriesUIMap.Tablecolumn_header)) System.out.println("_______COLUMN ADDED USING COLUMN OPTION_____");
					clickJS(myQueriesUIMap.Queries_link);
					ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
					clickJS(myQueriesUIMap.sharedQuery_txt);
					Thread.sleep(2000); //can be removed
					clickJS(myQueriesUIMap.sharedQuerySaved_txt_1);
					ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
					Thread.sleep(2000);
					clickJS(myQueriesUIMap.Save_As_btn);
					ExpWaitForCondition(myQueriesUIMap.QueryName_input);
					enterText(myQueriesUIMap.QueryName_input, "query_name"); 
					clickJS(myQueriesUIMap.QuerySave_btn);
					Thread.sleep(2000);
					clickJS(myQueriesUIMap.Queries_link);
					ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
					sa.assertEquals(CheckIfElementExists(myQueriesUIMap.Queryname_text),true,"____FAILED TO MOVE FROM SHARED QUERY____");
					if(CheckIfElementExists(myQueriesUIMap.Queryname_text)) System.out.println("____QUERY FROM SHARED QUERY MOVED TO MYQUERY____");
					else System.out.println("____FAILED TO MOVE FROM SHARED QUERY____");
				}
				catch(Exception e)
				{
					e.printStackTrace();
					grabScreenshotForExtentReport();
					logger.info("Verification of query failed");
					Assert.fail("Verification of query failed");
				}	
			}

			public static void verifyAdvanceQuery(String toolname, String entityType) {
				try{
					ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
					clickJS(myQueriesUIMap.NewQuery_text);
					ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
					clickJS(myQueriesUIMap.Advanced_query_btn);
					ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img); 
					if(CheckIfElementExists(myQueriesUIMap.QueryFormat_text)) 
						System.out.println("Advanced_Query page loaded");
					Thread.sleep(2000);
					selectDropdownByText(myQueriesUIMap.Entity_dropdown,entityType);
					ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img); 
					sa.assertEquals(CheckIfElementExists(myQueriesUIMap.workitem_type_advance_dropdown), true,"_____WORKITEMTYPE DROPDOWN FOR "+ toolname + " FOR "+entityType+" NOT EXIST_____");
					if(CheckIfElementExists(myQueriesUIMap.workitem_type_advance_dropdown))
						System.out.println("_____WORKITEMTYPE DROPDOWN FOR "+ toolname + " FOR "+entityType+" EXIST_____");
					else 
						System.out.println("____WORKITEMTYPE DROPDOWN FOR "+ toolname + " FOR "+entityType+" NOT EXIST_____");
					selectDropdownByText(myQueriesUIMap.workitem_type_advance_dropdown,"Epic");
					ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img); 
					clickJS(myQueriesUIMap.RunQuery_button);
					ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
					sa.assertEquals(CheckIfElementExists(myQueriesUIMap.toaster_msg), true,"___NOT AVAILABLE___");
					if(CheckIfElementExists(myQueriesUIMap.toaster_msg))
						System.out.println("____Unable to run the query TOASTER MESSAGE APPEARS____");
					Thread.sleep(2000);
					String text="{aggregate:\"clients.workitems\",pipeline:[{$match:{WorkItemExternalId:\"BOM-126894\"}}],cursor: {batchSize: 10000 } }";
					enterText(myQueriesUIMap.Query_textarea,text);
					clickJS(myQueriesUIMap.RunQuery_button);
					ExpWaitForElementToDisappear(MyWizardUIMap.waitSign_Img);
					if(CheckIfElementExists(myQueriesUIMap.workitemtype_column_header))
						System.out.println("_____USER ABLE TO WRITE AND RUN QUERY___");
					
					
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
					logger.info("Verification of Advance_query failed");
					Assert.fail("Verification of Advance_query failed");
				}
				
			}
			
}