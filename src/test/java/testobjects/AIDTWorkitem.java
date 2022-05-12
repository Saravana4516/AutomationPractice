package testobjects;

import static utilities.reporting.LogUtil.logger;
import static utilities.reporting.Reporting.create_logs_and_report;
import static utilities.selenium.SeleniumDSL.*;


import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.testng.Assert;
import dataobjects.WorkItemDO;
import uiMap.TFSUIMap;
import utilities.general.DataManager;
import utilities.general.Property;

public class AIDTWorkitem extends Baseclass {
    private Baseclass base;

    public AIDTWorkitem() {

    }

    public AIDTWorkitem(Baseclass base) {
        this.base = base;
    }

    public static String Workitem_Img_path = System.getProperty("user.dir")
            + File.separator + "src" + File.separator + "test" + File.separator
            + "resources" + File.separator + "testdata" + File.separator + "AIDT" + File.separator + "Workitem_Img" + File.separator;

    public static String testDataPath = System.getProperty("user.dir")
            + File.separator + "src" + File.separator + "test" + File.separator
            + "resources" + File.separator + "testdata" + File.separator + "AIDT" + File.separator + "JSON" + File.separator;

    public static void CreateWorkitem(String workitem, String toolname) throws IOException {

        try {


            WorkItemDO wi = DataManager.getData(testDataPath, "WorkItem", WorkItemDO.class).item.get(workitem);
            String workitemURL = GoToWorkitemURL(workitem);
            EnterWorkItemDetails(workitem, workitemURL, wi);
            CaptureWorkitemID(workitem);
        } catch (Exception e1) {
            e1.printStackTrace();
            logger.info("Issue creating workitem " + workitem);
        }
    }
			
		
		/*public static void EnterWorkItemDetails(String workitem,String workitemURL , WorkItemDO wi ) throws InterruptedException {
			try{ 
				waitPageToLoad();
				if(CheckIfElementExists(TFSUIMap.title_txtbox))
				{				
					ExpWaitForCondition(TFSUIMap.title_txtbox);
					enterText(TFSUIMap.title_txtbox,wi.Summary);
					Thread.sleep(2000);
				}
				else if(CheckIfElementExists(TFSUIMap.title_txtbox))
				{
					Thread.sleep(5000);
					ExpWaitForCondition(TFSUIMap.title_txtbox);
					enterText(TFSUIMap.title_txtbox,wi.Summary);
					Thread.sleep(2000);
				}
				else
					{
					driver().get(workitemURL);
					Thread.sleep(5000);
					}
				if(!CheckIfElementExists(TFSUIMap.title_txtbox))
				{
					logger.info("page not loading for workitem "+workitem);
				}
				waitPageToLoad();
				
				// Risk Category
				if(!wi.RiskCategory.equalsIgnoreCase("NA")) 
				{
					EnterDataInTheField(wi.RiskCategory,TFSUIMap.RiskCategory_drpdwn);
					Thread.sleep(2000);
					sendEntr();
				}

				//MilestoneType
				if(!wi.MilestoneType.equalsIgnoreCase("NA"))
				{
					EnterDataInTheField(wi.MilestoneType,TFSUIMap.MilestoneType_drpdwn);
					Thread.sleep(2000);
					sendEntr();

				}
//RequirementType
				if(!wi.RequirementType.equalsIgnoreCase("NA"))
				{
					EnterDataInTheField(wi.RequirementType,TFSUIMap.RequirementType_drpdwn);
					Thread.sleep(2000);
					sendEntr();

				}
//Sponsor
				if(!wi.Sponsor.equalsIgnoreCase("NA"))
				{
					singleClick(TFSUIMap.Sponsor_drpdwn);
					ExpWaitForCondition(TFSUIMap.Sponsor_txtbox);
					Thread.sleep(2000);
					enterText(TFSUIMap.Sponsor_txtbox, wi.Sponsor);
// EnterDataInTheField(wi.Sponsor,TFSUIMap.Sponsor_drpdwn);
					Thread.sleep(2000);
					sendEntr();
				
				// BaselineMitigationPlanClose
				if(!wi.BaselineMitigationPlanClose.equalsIgnoreCase("NA")) 
				{
					EnterDataInTheField(wi.BaselineMitigationPlanClose,TFSUIMap.BaselineMitigationPlanClose_drpdwn);
					Thread.sleep(2000);
					sendEntr();
				}
				
				//Priority
				if(!wi.Priority.equalsIgnoreCase("NA")) 
				{
					EnterDataInTheField(wi.Priority,TFSUIMap.Priority_drpdown);
					Thread.sleep(2000);
					sendEntr();
				}

				//DecisionType
				if(!wi.DecisionType.equalsIgnoreCase("NA")) 
				{
					EnterDataInTheField(wi.DecisionType,TFSUIMap.DecisionType_drpdwn);
					Thread.sleep(2000);
					sendEntr();
				}
				
				// Description
				if(!wi.Description.equalsIgnoreCase("NA")) 
				{
					EnterDataInTheField(wi.Description,TFSUIMap.Description_txt);
					Thread.sleep(2000);
				}
				//Probability
				if(!wi.Probability.equalsIgnoreCase("NA")) 
				{
					EnterDataInTheField(wi.Probability,TFSUIMap.Probability_drpdwn);
					Thread.sleep(2000);
					sendEntr();
				}
				//Owner
				if(!wi.Owner.equalsIgnoreCase("NA")) 
				{
					singleClick(TFSUIMap.Owner_drpdwn);
					ExpWaitForCondition(TFSUIMap.Owner_txtbox);
					EnterDataInTheField(wi.Owner,TFSUIMap.Owner_txtbox);
					Thread.sleep(2000);
					sendEntr();
				}
				//RetroSteps
				if(!wi.RetroStep.equalsIgnoreCase("NA")) 
				{
					EnterDataInTheField(wi.RetroStep,TFSUIMap.RetroStep_txtbox);
					Thread.sleep(2000);
					sendEntr();
				}
				//testtype
				if(!wi.TestType.equalsIgnoreCase("NA")) 
				{
					EnterDataInTheField(wi.TestType,TFSUIMap.TestType_drpdwn);
					Thread.sleep(2000);
					sendEntr();
				}
				// DeliverableType
				if(!wi.DeliverableType.equalsIgnoreCase("NA")) 
				{
					EnterDataInTheField(wi.DeliverableType,TFSUIMap.Deliverabletype_drpdwn);
					Thread.sleep(2000);
					sendEntr();
				}
				//IssueCategory
				if(!wi.IssueCategory.equalsIgnoreCase("NA")) 
				{
					EnterDataInTheField(wi.IssueCategory,TFSUIMap.IssueCategory_drpdwm);
					Thread.sleep(2000);
					sendEntr();
				}
				// Impact
				if(!wi.Impact.equalsIgnoreCase("NA")) 
				{
					EnterDataInTheField(wi.Impact,TFSUIMap.Impact_drpdwn);
					Thread.sleep(2000);
					sendEntr();
				}
				//escalate
				if(!wi.Escalate.equalsIgnoreCase("NA")) 
				{
					EnterDataInTheField(wi.Escalate,TFSUIMap.Escalate_drpdwn);
					Thread.sleep(2000);
					sendEntr();
				}
				//ActionCategory
				if(!wi.ActionCategory.equalsIgnoreCase("NA")) 
				{
					EnterDataInTheField(wi.ActionCategory,TFSUIMap.ActionCategory_drpdwn);
					Thread.sleep(2000);
					sendEntr();
				}
				//contractual
				if(!wi.Contractual.equalsIgnoreCase("NA")) 
				{
					EnterDataInTheField(wi.Contractual,TFSUIMap.Contractual_drpdwm);
					Thread.sleep(2000);
					sendEntr();
				}
				// BaselineStart
				if(!wi.BaselineStart.equalsIgnoreCase("NA")) 
				{
					EnterDataInTheField(wi.BaselineStart,TFSUIMap.BaselineStart_drpdwn);
					Thread.sleep(2000);
					sendEntr();
				}
				//BaselineDraftClose
				if(!wi.BaselineDraftClose.equalsIgnoreCase("NA")) 
				{
					EnterDataInTheField(wi.BaselineDraftClose,TFSUIMap.BaselineDraftClose);
					Thread.sleep(2000);
					sendEntr();
				}
				//BaselineReviewClose
				if(!wi.BaselineReviewClose.equalsIgnoreCase("NA")) 
				{
					EnterDataInTheField(wi.BaselineReviewClose,TFSUIMap.BaselineReviewClose_drpdwn);
					Thread.sleep(2000);
					sendEntr();
				}
				//BaselineClose
				if(!wi.BaselineClose.equalsIgnoreCase("NA")) 
				{
					EnterDataInTheField(wi.BaselineClose,TFSUIMap.BaselineClose_drpdwn);
					Thread.sleep(2000);
					sendEntr();
					
				}
				// Stage Found
				if(!wi.StageFound.equalsIgnoreCase("NA")) 
				{
					
					switchFrame(TFSUIMap.Iframe);
					Thread.sleep(4000);
					singleClick(TFSUIMap.StageFound_drpdwn);
					sendPageDownKey();
					switchDefault();
					
					
				}
				
				// Release and Sprint
				try{
					switch(wi.Release){
					case("NA"):
						//do nothing
						break;
					case("None"):
						clickJS(TFSUIMap.Iteration_label);
						Thread.sleep(4000);
						enterText(TFSUIMap.IterationName1_txtbox,Property.getProperty("TFSProject"));
//						clickJS(prepareWebElementWithDynamicXpath(TFSUIMap.IterationValue_drpdown, Property.getProperty("TFSProject"), "projectname"));
						break;
					case("PickFromBaseclass"):
						clickJS(TFSUIMap.Iteration_label);
						Thread.sleep(2000);
						String ReleasePlusSprintName = Property.getProperty("TFSProject")+"\\"+Baseclass.getInstance().AIDT_ReleaseName+"\\"+Baseclass.getInstance().AIDT_SprintName;
						enterText(TFSUIMap.IterationName1_txtbox,ReleasePlusSprintName);
						sendEntr();
						break;
					default:
						clickJS(TFSUIMap.Iteration_label);
						String IterationDetails = Property.getProperty("TFS Scrum_Client-Native")+"\\"+wi.Release+"\\"+wi.Sprint;
						enterText(TFSUIMap.IterationName1_txtbox, IterationDetails);
						sendEntr();
						break;
					}
					}
					catch(Exception e)
					{
						e.printStackTrace();
						logger.info("issue tagging release/sprint for the workitem");
					}
				Thread.sleep(2000);
				singleClick(TFSUIMap.save_btn);
				waitPageToLoad();
				Thread.sleep(5000);
				
				
			}
			*//*catch(Exception e)
			{
				e.printStackTrace();
				logger.info("issue entering workitem details for workitem "+workitem);
			}*//*
     */

    public static void EnterWorkItemDetails(String workitem, String workitemURL, WorkItemDO wi) {
        try {
            waitPageToLoad();
            if (CheckIfElementExists(TFSUIMap.title_txtbox)) {
                ExpWaitForCondition(TFSUIMap.title_txtbox);
                enterText(TFSUIMap.title_txtbox, wi.Summary);
                Thread.sleep(2000);
            } else if (CheckIfElementExists(TFSUIMap.title_txtbox)) {
                Thread.sleep(5000);
                ExpWaitForCondition(TFSUIMap.title_txtbox);
                enterText(TFSUIMap.title_txtbox, wi.Summary);
                Thread.sleep(2000);
            } else {
                driver().get(workitemURL);
                Thread.sleep(5000);
            }
            if (!CheckIfElementExists(TFSUIMap.title_txtbox)) {
                logger.info("page not loading for workitem " + workitem);
            }
            waitPageToLoad();

// Risk Category
            if (!wi.RiskCategory.equalsIgnoreCase("NA")) {
                EnterDataInTheField(wi.RiskCategory, TFSUIMap.RiskCategory_drpdwn);
                Thread.sleep(2000);
                sendEntr();
            }

// BaselineMitigationPlanClose
            if (!wi.BaselineMitigationPlanClose.equalsIgnoreCase("NA")) {
                EnterDataInTheField(wi.BaselineMitigationPlanClose, TFSUIMap.BaselineMitigationPlanClose_drpdwn);
                Thread.sleep(2000);
                sendEntr();
            }

//Priority
            if (!wi.Priority.equalsIgnoreCase("NA")) {
                EnterDataInTheField(wi.Priority, TFSUIMap.Priority_drpdown);
                Thread.sleep(2000);
                sendEntr();
            }


//DecisionType
            if (!wi.DecisionType.equalsIgnoreCase("NA")) {
                EnterDataInTheField(wi.DecisionType, TFSUIMap.DecisionType_drpdwn);
                Thread.sleep(2000);
                sendEntr();
            }

// Description
            if (!wi.Description.equalsIgnoreCase("NA")) {
                EnterDataInTheField(wi.Description, TFSUIMap.Description_txt);
                Thread.sleep(2000);
            }
//Probability
            if (!wi.Probability.equalsIgnoreCase("NA")) {
                EnterDataInTheField(wi.Probability, TFSUIMap.Probability_drpdwn);
                Thread.sleep(2000);
                sendEntr();
            }
//Owner
            if (!wi.Owner.equalsIgnoreCase("NA")) {
                singleClick(TFSUIMap.Owner_drpdwn);
                ExpWaitForCondition(TFSUIMap.Owner_txtbox);
                EnterDataInTheField(wi.Owner, TFSUIMap.Owner_txtbox);
                Thread.sleep(2000);
                sendEntr();
            }
//RetroSteps
            if (!wi.RetroStep.equalsIgnoreCase("NA")) {
                EnterDataInTheField(wi.RetroStep, TFSUIMap.RetroStep_txtbox);
                Thread.sleep(2000);
                sendEntr();
            }
//testtype
            if (!wi.TestType.equalsIgnoreCase("NA")) {
                EnterDataInTheField(wi.TestType, TFSUIMap.TestType_drpdwn);
                Thread.sleep(2000);
                sendEntr();
            }
// DeliverableType
            if (!wi.DeliverableType.equalsIgnoreCase("NA")) {
                EnterDataInTheField(wi.DeliverableType, TFSUIMap.Deliverabletype_drpdwn);
                Thread.sleep(2000);
                sendEntr();
            }
//IssueCategory
            if (!wi.IssueCategory.equalsIgnoreCase("NA")) {
                EnterDataInTheField(wi.IssueCategory, TFSUIMap.IssueCategory_drpdwm);
                Thread.sleep(2000);
                sendEntr();
            }
// Impact
            if (!wi.Impact.equalsIgnoreCase("NA")) {
                EnterDataInTheField(wi.Impact, TFSUIMap.Impact_drpdwn);
                Thread.sleep(2000);
                sendEntr();
            }
//escalate
            if (!wi.Escalate.equalsIgnoreCase("NA")) {
                EnterDataInTheField(wi.Escalate, TFSUIMap.Escalate_drpdwn);
                Thread.sleep(2000);
                sendEntr();
            }
//ActionCategory
            if (!wi.ActionCategory.equalsIgnoreCase("NA")) {
                EnterDataInTheField(wi.ActionCategory, TFSUIMap.ActionCategory_drpdwn);
                Thread.sleep(2000);
                sendEntr();
            }
//contractual
            if (!wi.Contractual.equalsIgnoreCase("NA")) {
                EnterDataInTheField(wi.Contractual, TFSUIMap.Contractual_drpdwm);
                Thread.sleep(2000);
                sendEntr();
            }
// BaselineStart
            if (!wi.BaselineStart.equalsIgnoreCase("NA")) {
                EnterDataInTheField(wi.BaselineStart, TFSUIMap.BaselineStart_drpdwn);
                Thread.sleep(2000);
                sendEntr();
            }
//BaselineDraftClose
            if (!wi.BaselineDraftClose.equalsIgnoreCase("NA")) {
                EnterDataInTheField(wi.BaselineDraftClose, TFSUIMap.BaselineDraftClose);
                Thread.sleep(2000);
                sendEntr();
            }
//BaselineReviewClose
            if (!wi.BaselineReviewClose.equalsIgnoreCase("NA")) {
                EnterDataInTheField(wi.BaselineReviewClose, TFSUIMap.BaselineReviewClose_drpdwn);
                Thread.sleep(2000);
                sendEntr();
            }
//BaselineClose
            if (!wi.BaselineClose.equalsIgnoreCase("NA")) {
                EnterDataInTheField(wi.BaselineClose, TFSUIMap.BaselineClose_drpdwn);
                Thread.sleep(2000);
                sendEntr();

            }
//MilestoneType
            if (!wi.MilestoneType.equalsIgnoreCase("NA")) {
                EnterDataInTheField(wi.MilestoneType, TFSUIMap.MilestoneType_drpdwn);
                Thread.sleep(2000);
                sendEntr();

            }
//RequirementType
            if (!wi.RequirementType.equalsIgnoreCase("NA")) {
                EnterDataInTheField(wi.RequirementType, TFSUIMap.RequirementType_drpdwn);
                Thread.sleep(2000);
                sendEntr();

            }
//Sponsor
            if (!wi.Sponsor.equalsIgnoreCase("NA")) {
                singleClick(TFSUIMap.Sponsor_drpdwn);
//              ExpWaitForCondition(TFSUIMap.Sponsor_txtbox);
                singleClick(TFSUIMap.Sponsor_drpdwn);
                Thread.sleep(2000);
                enterText(TFSUIMap.Sponsor_txtbox, wi.Sponsor);
// EnterDataInTheField(wi.Sponsor,TFSUIMap.Sponsor_drpdwn);
                Thread.sleep(2000);
                sendEntr();

            }

// Stage Found
            if (!wi.StageFound.equalsIgnoreCase("NA")) {

                switchFrame(TFSUIMap.Iframe);
                Thread.sleep(4000);
                singleClick(TFSUIMap.StageFound_drpdwn);
                sendPageDownKey();
                switchDefault();


            }


// team
            if (!wi.TeamArea.equalsIgnoreCase("NA")) {

            	 Thread.sleep(3000);
                clickJS(TFSUIMap.TeamArea_label);
                Thread.sleep(3000);
                enterText(TFSUIMap.TeamArea_label1, Property.getProperty("TFSProject") + "\\" + wi.TeamArea);
                Thread.sleep(3000);
                sendEntr();


            }

// Release and Sprint
            try {
                switch (wi.Release) {
                    case ("NA"):
//do nothing
                        break;
                    case ("None"):
                        clickJS(TFSUIMap.Iteration_label);
                        Thread.sleep(4000);
                        enterText(TFSUIMap.IterationName1_txtbox, Property.getProperty("TFSProject"));
// clickJS(prepareWebElementWithDynamicXpath(TFSUIMap.IterationValue_drpdown, Property.getProperty("TFSProject"), "projectname"));
                        break;
                    case ("PickFromBaseclass"):
                        clickJS(TFSUIMap.Iteration_label);
                        Thread.sleep(2000);
                        String ReleasePlusSprintName = Property.getProperty("TFSProject") + "\\" + Baseclass.getInstance().AIDT_ReleaseName + "\\" + Baseclass.getInstance().AIDT_SprintName;
                        enterText(TFSUIMap.IterationName1_txtbox, ReleasePlusSprintName);
                        sendEntr();
                        break;
                    default:
                        clickJS(TFSUIMap.Iteration_label);
                        String IterationDetails = Property.getProperty("TFS Scrum_Client-Native") + "\\" + wi.Release + "\\" + wi.Sprint;
                        enterText(TFSUIMap.IterationName1_txtbox, IterationDetails);
                        sendEntr();
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.info("issue tagging release/sprint for the workitem");
            }
            Thread.sleep(2000);
            singleClick(TFSUIMap.save_btn);
            waitPageToLoad();
            Thread.sleep(5000);


        } catch (Exception e) {
            e.printStackTrace();
            logger.info("issue entering workitem details for workitem " + workitem);
        }
    }

    private static void CaptureWorkitemID(String workitem) {
        try {
            String workitem_sp[] = workitem.split("_");
            switch (workitem_sp[0].toLowerCase()) {

                case "bug":

                    Baseclass.getInstance().WorkItemExternalId_Bug = getText(TFSUIMap.captureWorkItemID2_statictxt);
                    System.out.println(workitem + " id is " + getText(TFSUIMap.captureWorkItemID2_statictxt));
                    create_logs_and_report(workitem+" id is "+getText(TFSUIMap.captureWorkItemID2_statictxt),"pass");

                    break;
                    
                case "epic":
                    Baseclass.getInstance().WorkItemExternalId_Epic = getText(TFSUIMap.captureWorkItemID2_statictxt);
                    System.out.println(workitem + " id is " + getText(TFSUIMap.captureWorkItemID2_statictxt));
                    create_logs_and_report(workitem+" id is "+getText(TFSUIMap.captureWorkItemID2_statictxt),"pass");
                    break;
                    
                case "risk":
                    Baseclass.getInstance().WorkItemExternalId_Risk = getText(TFSUIMap.captureWorkItemID2_statictxt);
                    System.out.println(workitem + " id is " + getText(TFSUIMap.captureWorkItemID2_statictxt));
                    create_logs_and_report(workitem+" id is "+getText(TFSUIMap.captureWorkItemID2_statictxt),"pass");
                 break;
                    
                case "feature":
                    Baseclass.getInstance().WorkItemExternalId_Feature = getText(TFSUIMap.captureWorkItemID2_statictxt);
                    System.out.println(workitem + " id is " + getText(TFSUIMap.captureWorkItemID2_statictxt));
                    create_logs_and_report(workitem+" id is "+getText(TFSUIMap.captureWorkItemID2_statictxt),"pass");
                    break;
                    
                case "issue":
                    Baseclass.getInstance().WorkItemExternalId_Issue = getText(TFSUIMap.captureWorkItemID2_statictxt);
                    System.out.println(workitem + " id is " + getText(TFSUIMap.captureWorkItemID2_statictxt));
                    create_logs_and_report(workitem+" id is "+getText(TFSUIMap.captureWorkItemID2_statictxt),"pass");
                    break;
                    
                case "task":
                    Baseclass.getInstance().WorkItemExternalId_Task = getText(TFSUIMap.captureWorkItemID2_statictxt);
                    System.out.println(workitem + " id is " + getText(TFSUIMap.captureWorkItemID2_statictxt));
                    create_logs_and_report(workitem+" id is "+getText(TFSUIMap.captureWorkItemID2_statictxt),"pass");
                    break;
                    
                case "story":
                case "user story":
                    Baseclass.getInstance().WorkItemExternalId_Story = getText(TFSUIMap.captureWorkItemID2_statictxt);
                    System.out.println(workitem + " id is " + getText(TFSUIMap.captureWorkItemID2_statictxt));
                    create_logs_and_report(workitem+" id is "+getText(TFSUIMap.captureWorkItemID2_statictxt),"pass");
                    break;

                case "Deliverable":
                    Baseclass.getInstance().WorkItemExternalId_Deliverable = getText(TFSUIMap.captureWorkItemID2_statictxt);
                    System.out.println(workitem + " id is " + getText(TFSUIMap.captureWorkItemID2_statictxt));
                    create_logs_and_report(workitem+" id is "+getText(TFSUIMap.captureWorkItemID2_statictxt),"pass");
                    break;
                    
                case "productbacklog":
                    Baseclass.getInstance().WorkItemExternalId_Story = getText(TFSUIMap.captureWorkItemID2_statictxt);
                    System.out.println(workitem + " id is " + getText(TFSUIMap.captureWorkItemID2_statictxt));
                    create_logs_and_report(workitem+" id is "+getText(TFSUIMap.captureWorkItemID2_statictxt),"pass");
                    break;
                    
                case "action":
                    Baseclass.getInstance().WorkItemExternalId_Action = getText(TFSUIMap.captureWorkItemID2_statictxt);
                    System.out.println(workitem + " id is " + getText(TFSUIMap.captureWorkItemID2_statictxt));
                    create_logs_and_report(workitem+" id is "+getText(TFSUIMap.captureWorkItemID2_statictxt),"pass");
                    break;
                    
                case "decision":
                    Baseclass.getInstance().WorkItemExternalId_Decision = getText(TFSUIMap.captureWorkItemID2_statictxt);
                    System.out.println(workitem + " id is " + getText(TFSUIMap.captureWorkItemID2_statictxt));
                    create_logs_and_report(workitem+" id is "+getText(TFSUIMap.captureWorkItemID2_statictxt),"pass");
                    break;


                case "deliverable":
                    Baseclass.getInstance().WorkItemExternalId_Deliverable = getText(TFSUIMap.captureWorkItemID2_statictxt);
                    System.out.println(workitem + " id is " + getText(TFSUIMap.captureWorkItemID2_statictxt));
                    create_logs_and_report(workitem+" id is "+getText(TFSUIMap.captureWorkItemID2_statictxt),"pass");
                    break;
                    
                case "impediment":
                    Baseclass.getInstance().WorkItemExternalId_Impediment = getText(TFSUIMap.captureWorkItemID2_statictxt);
                    System.out.println(workitem + " id is " + getText(TFSUIMap.captureWorkItemID2_statictxt));
                    create_logs_and_report(workitem+" id is "+getText(TFSUIMap.captureWorkItemID2_statictxt),"pass");
                    break;

                case "requirement":
                    Baseclass.getInstance().WorkItemExternalId_Requirement = getText(TFSUIMap.captureWorkItemID2_statictxt);
                    System.out.println(workitem + " id is " + getText(TFSUIMap.captureWorkItemID2_statictxt));
                    create_logs_and_report(workitem+" id is "+getText(TFSUIMap.captureWorkItemID2_statictxt),"pass");
                    break;

                case "milestone":
                    Baseclass.getInstance().WorkItemExternalId_Milestone = getText(TFSUIMap.captureWorkItemID2_statictxt);
                    System.out.println(workitem + " id is " + getText(TFSUIMap.captureWorkItemID2_statictxt));
                    create_logs_and_report(workitem+" id is "+getText(TFSUIMap.captureWorkItemID2_statictxt),"pass");
                    break;

                default:
                    throw new IllegalArgumentException("Invalid workitem: " + workitem);
            }


        } catch (Exception e) {
            System.out.println("Issue with capturing workitem ID");
            e.printStackTrace();
        }


    }

    public static void CreateReleaseAndSprint(String Release, String Sprint) {
        try {
            Thread.sleep(4000);
            click(TFSUIMap.settingsIcon_Img);
            Thread.sleep(4000);
            if (!CheckIfElementExists(TFSUIMap.ProjectConfiguration_link)) {
                click(TFSUIMap.settingsIcon_Img);
                Thread.sleep(10000);
            }
            ExpWaitForCondition(TFSUIMap.ProjectConfiguration_link);
            click(TFSUIMap.ProjectConfiguration_link);
            Thread.sleep(4000);
            ExpWaitForCondition(TFSUIMap.NewChild_link);
            click(TFSUIMap.NewChild_link);

            WorkItemDO wi_release = DataManager.getData(testDataPath, "WorkItem", WorkItemDO.class).item.get(Release);
            WorkItemDO wi_sprint = DataManager.getData(testDataPath, "WorkItem", WorkItemDO.class).item.get(Sprint);

            Random rnd = new Random();
            int randomNumbForRelease = 1000 + rnd.nextInt(9000);
            //enter release data
            String newReleasewithAppendedNumb = wi_release.IterationName + randomNumbForRelease;
            clear(TFSUIMap.IterationName_txtbox);
            enterText(TFSUIMap.IterationName_txtbox, newReleasewithAppendedNumb);
            enterText(TFSUIMap.StartDate_txtbox, wi_release.ReleaseStartDate);
            enterText(TFSUIMap.EndDate_txtbox, wi_release.ReleaseEndDate);
            click(TFSUIMap.saveAndClose_btn);

            //enter sprint data
            Thread.sleep(3000);
            move(prepareWebElementWithDynamicXpath(TFSUIMap.ReleaseRow_statictxt, newReleasewithAppendedNumb, "releasename"));
            click(prepareWebElementWithDynamicXpath(TFSUIMap.newSprint_statictxt, newReleasewithAppendedNumb, "releasename"));
            click(TFSUIMap.NewChild_link);
            clear(TFSUIMap.IterationName_txtbox);
            int randomNumbForSprint = 1000 + rnd.nextInt(9000);
            String newSprintwithAppendedNumb = wi_sprint.IterationName + randomNumbForSprint;
            enterText(TFSUIMap.IterationName_txtbox, newSprintwithAppendedNumb);
            enterText(TFSUIMap.StartDate_txtbox, wi_sprint.StartDate);
            enterText(TFSUIMap.EndDate_txtbox, wi_sprint.EndDate);
            Thread.sleep(5000);
            ExpWaitForCondition(TFSUIMap.saveAndClose_btn);
            click(TFSUIMap.saveAndClose_btn);
            Thread.sleep(4000);
            Baseclass.getInstance().AIDT_ReleaseName = newReleasewithAppendedNumb;
            Baseclass.getInstance().AIDT_ReleaseStartDate = wi_release.ReleaseStartDate;
            Baseclass.getInstance().AIDT_ReleaseEndDate = wi_release.ReleaseEndDate;
            Baseclass.getInstance().AIDT_SprintName = newSprintwithAppendedNumb;
            Baseclass.getInstance().AIDT_SprintStartDate = wi_sprint.StartDate;
            Baseclass.getInstance().AIDT_SprintEndDate = wi_sprint.EndDate;


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.info("Issue creating release/sprint");
            Assert.fail("Issue creating release/sprint");
        }

    }

    private static void EnterDataInTheField(String datatobeentered, By field) {
        try {

            if (CheckIfElementExists(field)) {
                clickJS(field);
                clear(field);
                enterText(field, datatobeentered);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("issue entering data in the field " + field);
        }

    }

    public static String GoToWorkitemURL(String workitem) {
        try {
            String workitemURL;
            if (workitem.contains("TestCase"))
                workitemURL = Property.getProperty("TFS_URL") + "/" + Baseclass.getInstance().TFSProject + "/_workitems/create/" + "Test Case";
            else if (workitem.contains("Work Request")) {
                workitemURL = Property.getProperty("TFS_URL") + "/" + Baseclass.getInstance().TFSProject + "/_workitems/create/" + "change request";
            } else if (workitem.split("_")[0].contains("Story")) {
                workitemURL = Property.getProperty("TFS_URL") + "/" + Baseclass.getInstance().TFSProject + "/_workitems/create/" + "User Story";
                driver().get(workitemURL);
                Thread.sleep(5000);
                if (CheckIfElementExists(TFSUIMap.ServerError_txt))
                    workitemURL = Property.getProperty("TFS_URL") + "/" + Baseclass.getInstance().TFSProject + "/_workitems/create/" + "Product Backlog Item";
            } else if (workitem.split("_")[0].contains("ProductBacklog"))
                workitemURL = Property.getProperty("TFS_URL") + "/" + Baseclass.getInstance().TFSProject + "/_workitems/create/" + "Product Backlog Item";
            else
                workitemURL = Property.getProperty("TFS_URL") + "/" + Baseclass.getInstance().TFSProject + "/_workitems/create/" + workitem.split("_")[0];

            driver().get(workitemURL);
            Thread.sleep(5000);
            return workitemURL;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


}