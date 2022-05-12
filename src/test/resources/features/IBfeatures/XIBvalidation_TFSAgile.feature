@IBvalidation
Feature: XIBvalidation_TFSAgile


Scenario: TFSAgile_Task 

	And i generate a token for "DevTest" environment 
	Given i load the "TFS Agile" project properties file
	And i verify the "Inbound" "Task" details for "TFS Agile" 
	

Scenario: TFSAgile_Epic 

	And i verify the "Inbound" "Epic" details for "TFS Agile" 
	

Scenario: TFSAgile_Story 

	And i verify the "Inbound" "Story" details for "TFS Agile" 


Scenario: TFSAgile_Issue 

	And i verify the "Inbound" "Issue" details for "TFS Agile" 
	

Scenario: TFSAgile_Bug

	And i verify the "Inbound" "Bug" details for "TFS Agile" 
	

Scenario: TFSAgile_Feature 

	And i verify the "Inbound" "Feature" details for "TFS Agile" 
	
Scenario: TFSAgile_Requirement

	And i verify the "Inbound" "Requirement" details for "TFS Agile"
	

Scenario: TFSAgile_TestCase
	 
	And i verify the "Inbound" "TestCase" details for "TFS Agile" 
	

Scenario: TFSAgile_Risk
	 
	And i verify the "Inbound" "Risk" details for "TFS Agile" 			


Scenario: TFSAgile_Deliverable
	 
	And i verify the "Inbound" "Deliverable" details for "TFS Agile"
	
	
Scenario: TFSAgile_Action
	
	And i verify the "Inbound" "Action" details for "TFS Agile"
	

Scenario: TFSAgile_Decision
	And i verify the "Inbound" "Decision" details for "TFS Agile"
	
		
Scenario: TFSAgile_Milestone
	And i verify the "Inbound" "Milestone" details for "TFS Agile"
	

Scenario: TFSAgile_WorkRequest
	And i verify the "Inbound" "Work Request" details for "TFS Agile"
	
Scenario Outline: TFSAgile_DIY_IBVerification_Release_Sprint 
	Given i login to application "<applicationname>" 
	Then i select client and DC for "<applicationname>"
	And i navigate to the homepage of "<applicationname>" from "AIFusionPage"
	And i click on tile "my Queries"	
	And i capture the "IterationExternalID" for Entities created from "tool" for tool "TFS Agile" 
	And i generate a token for "DevTest" environment 	
	And i verify if "Release" has "flown" which was "NA" for "TFS Agile" for "Normal" functionality
	And i verify if "Sprint" has "flown" which was "NA" for "TFS Agile" for "Normal" functionality

		Examples: 
		| applicationname |toolname|
		| MyWizard        |TFS Agile|