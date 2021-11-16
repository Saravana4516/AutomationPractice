@3IBValidation_TFSAgile_DeleteFunctionality_checkFlowBeforeDeleteion
Feature: TFSAgile_IB_Validation_DeleteFunctionality(before delete check IB)


Scenario: TFSAgile_Task_flow_beforeDelete 
Given i load the project properties file 
	And i generate a token for "DevTest" environment
	And i verify if "Task" has "flown" which was "NA" for "TFS Agile" for "delete" functionality 


Scenario: TFSAgile_Epic_flow_beforeDelete 

	And i verify if "Epic" has "flown" which was "NA" for "TFS Agile" for "delete" functionality 

Scenario: TFSAgile_Feature_flow_beforeDelete 

	And i verify if "Feature" has "flown" which was "NA" for "TFS Agile" for "delete" functionality 
	

Scenario: TFSAgile_Story_flow_beforeDelete 
	And i verify if "Story" has "flown" which was "NA" for "TFS Agile" for "delete" functionality 


Scenario: TFSAgile_Risk_flow_beforeDelete 
	And i verify if "Risk" has "flown" which was "NA" for "TFS Agile" for "delete" functionality 
	 
	
Scenario: TFSAgile_Issue_flow_beforeDelete 
And i verify if "Issue" has "flown" which was "NA" for "TFS Agile" for "delete" functionality 
	 

Scenario: TFSAgile_Bug_flow_beforeDelete
And i verify if "Bug" has "flown" which was "NA" for "TFS Agile" for "delete" functionality 

Scenario: TFSAgile_Action_flow_beforeDelete
And i verify if "Action" has "flown" which was "NA" for "TFS Agile" for "delete" functionality 


Scenario: TFSAgile_Decision_flow_beforeDelete
And i verify if "Decision" has "flown" which was "NA" for "TFS Agile" for "delete" functionality 

Scenario: TFSAgile_Deliverable_flow_beforeDelete
And i verify if "Deliverable" has "flown" which was "NA" for "TFS Agile" for "delete" functionality 

Scenario: TFSAgile_Milestone_flow_beforeDelete
And i verify if "Milestone" has "flown" which was "NA" for "TFS Agile" for "delete" functionality 

Scenario: TFSAgile_Requirement_flow_beforeDelete
And i verify if "Requirement" has "flown" which was "NA" for "TFS Agile" for "delete" functionality 

Scenario: TFSAgile_WorkRequest_flow_beforeDelete 
And i verify if "Work Request" has "flown" which was "NA" for "TFS Agile" for "delete" functionality 


Scenario: TFSAgile_Team_flow_beforeDelete 
And i verify if "Team" has "flown" which was "NA" for "TFS Agile" for "delete" functionality 

Scenario Outline: TFSAgile_Deletefunctionality_IBVerification_Release_Sprint
	Given i login to application "<applicationname>" 
	And i navigate to the homepage of "<applicationname>" from "AIFusionPage"
	And i click on tile "my Queries"
	Then i select client and DC for "<applicationname>"
	And i capture the IterationExternalID for Iteration created from "tool" for tool "TFS Agile" 
	And i generate a token for "DevTest" environment 	
	And i verify if "Release" has "flown" which was "NA" for "TFS Agile" for "normal" functionality
	And i verify if "Sprint" has "flown" which was "NA" for "TFS Agile" for "normal" functionality

		Examples: 
		| applicationname |toolname|
		| MyWizard        |TFS Agile|	
	
	 
	 

