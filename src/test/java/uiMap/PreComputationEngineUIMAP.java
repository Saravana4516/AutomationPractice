package uiMap;

import org.openqa.selenium.By;



public class PreComputationEngineUIMAP {
	
	// ADD PROCESS
	public static By Portfolio_dc =By.xpath("//span[@id=\"a11562b9-7302-46ec-b665-4c80cf981005\"]");
	public static By Program_dc  =By.xpath("//span[@id=\"0f5801b0-58ae-4bcc-b06c-df61a73c08aa\"]");
	public static By PreComputation_txt =By.xpath("//div[@class='tile-title'][text()='{tilename}']");
	public static By Computation_header =By.xpath("//h6[@class=\"mb-0 myw-page-header-title\"]");
	public static By AddProcess_link =By.xpath("//a[@class='stretched-link']");
	public static By Name_txtbox =By.xpath("//input[@name='processName']");
    public static By Description_txtbox =By.xpath("//textarea[@name='txtdescription']");
	public static By ProcessType_drpdown =By.xpath("//label[text()='Trigger Based On']//preceding::*[@name='processType'][1]");
	public static By TriggerBasedOn_drpdown =By.xpath("//label[text()='Trigger Based On']//following::*[@id='Pre_TriggerType']");
	public static By Entitytype_drpdown =By.xpath("//label[text()='Entity']//following::*[@id='Pre_EntityUId']");
	public static By Entitytype_drpdown1 =By.xpath("//input[@role='combobox']");
	public static By workitem_drpdown =By.xpath("//*[text()='Applications']//preceding::*[@name='wt'][1]");
	 public static By Entityevent_drpdown =By.xpath("//*[text()='Entity Event Name']//following::*[@name='entityEvent'][2]");
	public static By Application_dropdown =By.xpath("//span[@class='dropdown-btn']");
	public static By Search =By.xpath("//input[@placeholder='Search']");
	public static By Story_Readiness_txt =By.xpath("//div[text()='Story Readiness Assistant']");
	public static By Save_btn=By.xpath("//button[@title='Save']");
	public static By SaveFormula_btn=By.xpath("//img[@alt='Save']");
	public static By SaveSuccess_Msg=By.xpath("//div[contains(text(),'Saved Successfully')]");
    public static By Search_Box =By.xpath("//input[@class='ng-valid ng-dirty ng-touched']");
    public static By cancel_btn =By.xpath("//button[@title='Cancel']");
    public static By empty_space =By.xpath("//div[@class='modal-body']");
    // EDIT PROCESS
    public static By viewmore_text =By.xpath("//h6[text()='Total Process']//following::*[text()='View more'][1]");
    public static By definition_txtbox=By.xpath("//textarea[@id='definition']");
    public static By edit_option =By.xpath("//input[@name='processName']//following::img[@alt=\"More Options\"][1]");
    public static By configurecalculation_link =By.xpath("//a[text()='Configure Calculation']");
    public static By edit_txt =By.xpath("//input[@name='processName']//following::a[text()='Edit'][1]");
    public static By compute_node =By.xpath("//a[@title='Compute Node']");
    public static By empty_area =By.xpath("//span[text()=' You have not created any process node yet.']");
    public static By Editcalculation_btn =By.xpath("//a[text()='Edit Calculation']");
    public static By Result_storedin_txtbox =By.xpath("//input[@name='txtOutcome']");
    public static By Select_field_for_entity_drpdown =By.xpath("//span[@class='ng-arrow-wrapper']");
    public static By Level1_associated_enity_dropdown =By.xpath("//*[text()='Level 1 Associated Entity']//following::*[@class='form-control control-border ng-valid ng-dirty ng-touched']");
    public static By Entity_associated_level1_dropdown =By.xpath("//*[text()='Entity Associated to level 1']//following::*[@class='form-control control-border ng-valid ng-dirty ng-touched']");
    public static By BussinessValue_txt =By.xpath("//*[@type='checkbox']//following::*[text()='BusinessValue']");
    public static By Formula_area =By.xpath("//textarea[@id='txtFormula']");
    public static By user2 =By.xpath("//span[@class='pce-select-chips']//following::span[text()='UserStory.UserStory.Task.BusinessValue']");
    public static By user1 =By.xpath("//span[text()='UserStory.UserStory.BusinessValue'][1]");
    public static By clear_btn  =By.xpath("//*[@id='txtFormula']//preceding::a[text()='Clear'][1]");
	 public static By Save_Img =By.xpath("//img[@alt='Save']");
	 public static By back_btn =By.xpath("//a[contains(text(),'Compute Process for Node')]");
	 
	
	 
   
	 
	 
	 public static String TestProcessName = "//h6[text()='{Test_6_9}']";
	 public static String TestProcess_MoreOptions_Drpdown = "//h6[text()='{testprocessname}']//following::a[@class='myw-action'][1]";
	 public static String TestProcess_Edit_Link = "//h6[text()='{testprocessname}']//following::a[text()='Edit'][1]";


	 //added by sonal
	 public static By BusinessValue_checkbox = By.xpath("//span[text()='BusinessValue']//preceding::input[1]");
	 public static By BusinessValue_label = By.xpath("//span[@class='pce-select-chips']/child::span[contains(text(),'BusinessValue')]");
	 public static By StoryPointEstimated_checkbox = By.xpath("	//span[text()='StoryPointEstimated']//preceding::input[1]");
	 public static By StoryPointEstimated_label = By.xpath("//span[@class='pce-select-chips']/child::span[contains(text(),'StoryPointEstimated')][1]");
	 public static By RiskReduction_checkbox = By.xpath("//span[text()='RiskReduction']//preceding::input[1]");	
	 public static By RiskReduction_label = By.xpath("//span[@class='pce-select-chips']/child::span[contains(text(),'RiskReduction')][1]");
	 public static By Level1AssociationToTask_drpdown = By.xpath("//span[text()='Level 1 Associated Entity']//following::select[1]");
	 
	 public static By SelectFieldForEntity_dropdown = By.xpath("//span[contains(text(),'Select Field for')]//following::input[1]");
	 public static By SelectFieldForEntity_txt = By.xpath("//span[text()='Select Field for UserStory Entity']");

	//OPERATORS
		 public static By add_icon =By.xpath("//a[text()='+']");
		 public static By subtract_icon =By.xpath("//a[text()='-']");
		 public static By multiply_icon =By.xpath("//a[text()='*']");
		 public static By div_icon =By.xpath("//a[text()='/']");
		 public static By leftbracket_icon =By.xpath("//a[text()='(']");
		 public static By rightbracket_icon =By.xpath("//a[text()=')']");
		 public static By equal_icon =By.xpath("//a[text()='==']");
		 public static By notequals_icon =By.xpath("//a[text()='!=']");

		 public static By max_icon =By.xpath("//a[text()='MAX']");
		 public static By min_icon =By.xpath("//a[text()='MIN']");
		 public static By avg_icon =By.xpath("//a[text()='AVG']");
		 public static By sum_icon =By.xpath("//a[text()='SUM']");
		 public static By SystemDate_icon =By.xpath("//a[text()='SYSTEMDATE']");

//Property check
public static By Search_Imgintile=By.xpath("//div[@class='myw-navbar-action myw-navbar-search']//child::input[1]");
		//input[@placeholder='Search...']//input[@id='searchCleartextbox']

//public static By Search_Imgintile=By.xpath("//span[text()='Weightage configuration for multi-list values']//preceding::input[1]");
public static By More_options=By.xpath("//a[@title='More Options']");		 
public static By ViewNode_txt=By.xpath("//a[text()='View Node']");		 
public static By EditNode_txt=By.xpath("//a[text()='Edit Node']");		 
public static By Userstoryfield_txt=By.xpath("//span[text()='Select Field for UserStory Entity']");		 
public static By Back_button=By.xpath("//a[@title='Back']");
public static By Requirementfield_txt=By.xpath("//span[text()='Select Field for Requirement Entity']");	
public static String Property_checkbox="//span[text()='{PropertyName}']//preceding::input[1]";	
public static By Userguide_icon=By.xpath("//div[text()='Precomputation Engine']//preceding::a[1]");

//View
public static String Disabled_items="//a[@class='myw-navbar-action show disabled']//following::span[text()='{disabled}']";


//weightage 
public static By Weighatge_img=By.xpath("//img[@alt='Entity Computation Property']");
public static By SelectEntity_dropdown=By.xpath("//select[@name='selectedEntityL1']");
public static By AddRow_icon=By.xpath("//a[@title='Add Row']");
public static By Selectinfirstrow=By.xpath("//th[contains(text(),'Multi-list field')]//following::select[1]");
public static By Group_select=By.xpath("//th[contains(text(),'Group')]//following::select[2]");
public static By Name_select=By.xpath("//th[contains(text(),'Name')]//following::select[3]");
public static By Value_txtbox=By.xpath("//th[contains(text(),'Name')]//following::input[1]");
public static By Saveweightage_btn=By.xpath("//button[@title='Save']");
public static By Sucesstoaster_msg=By.xpath("//*[text()=' Saved Successfully ']");
public static String DeleteProperty_option="//tr[{R}]//td[5]//a[1]//*[local-name()='svg']";
public static By YesAfterDelete_btn=By.xpath("//button[@title='Yes']");

public static String Property_dropdown="//tr[{R}]//td[1]//select[1]";



public static String DeleteProperty_icon="//'{D}'//td[1]//select[1]//following::a[@title='Delete']";
public static By Nextpage_option=By.xpath("//a[text()='2']");

//filter
public static By Filter_img=By.xpath("//img[@alt='Filter']");
public static By Triggerbasedon_drpdown=By.xpath("//select[@name='typeUId']");
public static By Status_dropdown=By.xpath("//select[@name='stateUId']");
public static By Type_dropdown=By.xpath("//select[@name='processType']");
public static By Entity_dropdown=By.xpath("//select[@name='entity']");
public static By Workitem_drpdown=By.xpath("//select[@name='wt']");
public static By Entityevent_dropdown=By.xpath("//select[@name='entityEvent']");
public static By Active_txt=By.xpath("//div[@class='form-group mb-3']//small[@class='pl-2 myw-inact-val'][contains(text(),'Active')]");
public static By Apply_btn=By.xpath("//button[@class='btn btn-primary ml-auto'][text()='Apply']");
public static By Elementtobefiltered=By.xpath("//h6[@class='card-title cursor-pointer']");

//filter criteria in formula page
public static By AddFilter_optn=By.xpath("//a[text()='Add Filter Criteria']");
public static By StateUId_select=By.xpath("//span[text()='StateUId']//preceding::input[1]");
public static By StateUid_label=By.xpath("//span[text()='UserStory.StateUId']");
public static By Filter_txtarea=By.xpath("//textarea[@placeholder='Sample Filter Criteria:- (UserStory.StateUId == New)']");
}

