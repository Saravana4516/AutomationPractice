package uiMap;

import org.openqa.selenium.By;

public class myQueriesUIMap {

public static By ExistingQueries_textbox=By.xpath("//div[@class='col-7 phx-caret']"); //xpath for 2 elements myqueries and shared queries

public static By SharedQueries_txtbox=By.xpath("//div[contains(text(),'Shared Queries')]");

//creating a standard query
public static By NewQuery_text=By.xpath("//span[text()='New Query']");
public static By Standard_button=By.xpath("//button[text()='Standard']");
public static By New_text=By.xpath("//span[text()='New']");
public static By Entity_dropdown=By.xpath("//select[@name='selectEntity']");
public static By workItemType_dropdown=By.xpath("//select[@name='selectWorkItemTypeStandard']");
public static By condition_text=By.xpath("//span[text()='Condition']");
public static By Condition_tableheader=By.xpath("//span[text()=\"Condition\"]");
public static By Field_txtBox=By.xpath("//div[text()='AcceptanceCriteria']");
public static By titleField_txtBox=By.xpath("//div[text()='Title']");
public static By Field_dropdown=By.xpath("//div[@role='presentation']//following::select[@class='ag-cell-edit-input']");
public static By Operator_tableheader=By.xpath("//span[text()='Operator']");
public static By Operator_txtBox=By.xpath("//div[text()='=']");
public static By Operator_dropdown=By.xpath("//div[@role='gridcell']//following::select[@class='ag-cell-edit-input']");
public static By Value_dropdown=By.xpath("//div[@col-id='Value']//following::select[@class='ag-cell-edit-input']");
public static By value_txtbox1=By.xpath("//div[@col-id='Value'][@role='gridcell']");
public static By value_txtbox2=By.xpath("(//div[@col-id='Value'][@role='gridcell'])[2]");
public static By recordsRetrieved_staticTxt = By.xpath("//div[@id='toast-container']//child::div[contains(@aria-label,'records have been fetched')");

//validation of column options
public static By ColumnOptions_txt=By.xpath("//span[text()='Column Options']");
public static By AddField_txt=By.xpath("//span[contains(text(),'Add Field')]");
public static By deleteColumnOption_button=By.xpath("(//a[@class='phx-pane-close'][@title='Close'])[2]");
public static By deleteColumnOptions_button=By.xpath("//a[@class='phx-pane-close'][@title='Close']");
public static By firstColumnOption_dropdown=By.xpath("(//div[@class='row phx-select-wrap']/select)[1]");
public static By newColumnOption_dropdown=By.xpath("(//div[@class='row phx-select-wrap']/select)[4]");
public static By saveColumnOption_button=By.xpath("(//button[text()='Save'])[2]");
public static By workitemtypeColumnOption_txt=By.xpath("(//div[@col-id='WorkItemTypeUId'])[2]"); //column option in the query result
public static By stateuidColumnOption_txt=By.xpath("(//div[@col-id='StateUId'])[1]"); //column option in the query result
public static By projectColumnOption_txt=By.xpath("(//div[@col-id='Project'])[1]");

//running a query
public static By RunQuery_button=By.xpath("//span[text()='Run Query']");

//validating the output
public static By PriorityUId_tableheader=By.xpath("//span[text()='PriorityUId']");
public static By ClientUId_tableheader=By.xpath("//span[text()='ClientUId']");
public static By State_tableheader=By.xpath("//div//child::span[text()='State']");
public static By project_tableheader=By.xpath("//span[text()='Project']");

//adding a clause
public static By NewClause_txt=By.xpath("//span[text()='Add New Clause']");//for others refer to standard query //a[@title='Filter']//child::
public static By conditionColumn_txt=By.xpath("//div[text()='AND']");
public static By conditionColumn_dropdown=By.xpath("//div[@col-id='Condition']//following::select[@class='ag-cell-edit-input']");


//saving a query
public static By SaveQuery_txt=By.xpath("//span[text()='Save Query']");
public static By QueryName_txtbox=By.xpath("//input[@name='queryName']");
public static By QueryType_dropdown=By.xpath("//select[@name='queryType']");
public static By saveQuery_button=By.xpath("(//button[text()='Save'])[3]");
public static By querySaved_txt=By.xpath("//div[contains(text(),'Saved successfully')]");
public static By navigateToQueries_txt=By.xpath("//a[@title='Queries View']");

//Shared query
public static By sharedQuery_txt=By.xpath("//div[@href='#collapsePublicQueries']");
public static By sharedQuerySaved_txt=By.xpath("//div[@id='collapsePublicQueries']//table[@class='phx-myqueries-table']//tbody//tr[1]//td//span[text()='Auto_Regression']");
public static By sharedQuerySaved_txt_1=By.xpath("//div[@id='collapsePublicQueries']//table[@class='phx-myqueries-table']//tr[2]//td//span[text()='Auto_Regression']");
//edit existing queryBy.xpath("//div[@id='collapsePublicQueries']//table[@class='phx-myqueries-table']//tbody//tr[1]//td//span[text()='Auto_Regression']");
public static By savedQuery_txt=By.xpath("//span[text()='Auto_Regression']");

//Deleting a Query
public static By DeleteQuery_icon=By.xpath("//a[@title='Delete Query']");
////div[@id='collapseMyQueries']//table[@class='phx-myqueries-table']//tbody//tr[1]//td//a[@title='Delete Query']
public static By DeleteQueryYes_button=By.xpath("//button[@title='Click Yes to delete the query']");

//product instance dropdown
public static By ProductInstance_dropdown=By.xpath("//select[@name='selectProductInstance']");

//Association column
public static By WorkItemAssociation_tableheader=By.xpath("//span[text()='WorkItemAssociations']");

//Revert changes
public static By revertChanges_button=By.xpath("//span[text()='Revert Changes']");
public static By condition_txt=By.xpath("//div[@col-id='Condition'][text()='AND']");


//export the results
public static By exportResults_button=By.xpath("//div[text()='Go']");
public static By IUnderstand_button=By.xpath("//button[text()='I Understand']");

//verify
public static By workitem_type_dropdown=By.xpath("//div//select[contains(@name,'selectWorkItemTypeStandard')]");
public static By select_cross_btn=By.xpath("//div[@row-id='1']/div[@col-id='delete']");
public static By column_option_dropdown=By.xpath("//div[@class='phx-incident-filter m-0']//child::select");
public static By column_value_dropdown=By.xpath("//option[text()=' RiskReduction ']//parent::select");
public static By Tablecolumn_header=By.xpath("//span[text()='RiskReduction']"); 
public static By Queries_link=By.xpath("//a[text()=' Queries > ']");
public static By selectMilestoneQuery_link=By.xpath("//span[text()='QueryToFetchMilestoneDetails_AutomationDONOTEDIT']");
public static By selectDeliverableQuery_link=By.xpath("//span[text()='QueryToFetchDeliverableDetails_AutomationDONOTEDIT']");
public static By Save_As_btn=By.xpath("//span[text()='Save As..']");
public static By QueryName_input=By.xpath("//input[@name='queryName']");
public static By QuerySave_btn=By.xpath("//div[@class='modal-dialog modal-lg']//div//div//button[text()='Save']");
public static By Queryname_text=By.xpath("//span[text()='query_name']");
public static By workitemUidColumn_header=By.xpath("//div[@ref='eBody']//div//div[@row-index=\"0\"]//div[@col-id='WorkItemUId']");

//Advance query 
public static By QueryFormat_text=By.xpath("//label[text()='Query Format']");
public static By Advanced_query_btn=By.xpath("//span//button[text()='Advanced']");
public static By workItemEntity_dropdown=By.xpath("//option[text()=' WorkItem ']//parent::select");
public static By workitem_type_advance_dropdown=By.xpath("//div//select[contains(@name,'selectWorkItemTypeAdvance')]");
public static By Query_textarea=By.xpath("//textarea[@placeholder='Please type your JSON query here.']");
public static By workitemtype_column_header=By.xpath("//span[text()='WorkItemTypeUId']");
public static By toaster_msg=By.xpath("//div[text()=' Unable to run the query. ']");

}	