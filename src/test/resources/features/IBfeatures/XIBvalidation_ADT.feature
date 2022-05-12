@IBvalidation
Feature: XIBvalidation_ADT



Scenario: ADTJIRA_Task 
	Given i load the "ADT Jira" project properties file 
	And i generate a token for "DevTest" environment 	
	And i verify the "Inbound" "Task" details for "ADT JIRA"
	
	 

Scenario: ADTJIRA_Epic 

	And i verify the "Inbound" "Epic" details for "ADT JIRA" 
	

Scenario: ADTJIRA_Story 
	And i verify the "Inbound" "Story" details for "ADT JIRA" 


Scenario: ADTJIRA_Risk 

	And i verify the "Inbound" "Risk" details for "ADT JIRA" 
	

Scenario: ADTJIRA_Impediment 

	And i verify the "Inbound" "Impediment" details for "ADT JIRA" 

Scenario: ADTJIRA_Issue 

	And i verify the "Inbound" "Issue" details for "ADT JIRA" 
	

Scenario: ADTJIRA_Bug

	And i verify the "Inbound" "Bug" details for "ADT JIRA" 
	

Scenario: ADTJIRA_Feature 

	And i verify the "Inbound" "Feature" details for "ADT JIRA" 
	
Scenario: ADTJIRA_Deliverable 

	And i verify the "Inbound" "Deliverable" details for "ADT JIRA" 
	
Scenario: ADTJIRA_Decision	

	And i verify the "Inbound" "Decision" details for "ADT JIRA"	
	

Scenario: ADTJIRA_Test 

	And i verify the "Inbound" "Test" details for "ADT JIRA" 
	

Scenario: ADTJIRA_Requirement 

	And i verify the "Inbound" "Requirement" details for "ADT JIRA" 
	

Scenario: ADTJIRA_Milestone 
	
	And i verify the "Inbound" "Milestone" details for "ADT JIRA" 
	
Scenario: ADTJIRA_Action
	
	And i verify the "Inbound" "Action" details for "ADT JIRA"

	Scenario: ADTJIRA_WorkRequest
 
	And i verify the "Inbound" "Work Request" details for "ADT JIRA"

Scenario: ADTJIRA_TestExecution 
	
	And i verify the "Inbound" "Test Execution" details for "ADT JIRA" 

#Scenario: ADTJIRA_Team 
#
#	And i verify the "Inbound" "Team" details for "ADT JIRA" 	

#
Scenario Outline: ADTJira_DIY_IBVerification_Release_Sprint 
	Given i login to application "<applicationname>" 
	Then i select client and DC for "<applicationname>"
	And i navigate to the homepage of "<applicationname>" from "AIFusionPage"
	And i click on tile "my Queries"	
	And i capture the "IterationExternalID" for Entities created from "tool" for tool "ADT Jira" 
	And i generate a token for "DevTest" environment 	
	And i verify if "Release" has "flown" which was "NA" for "ADT Jira" for "Normal" functionality
	And i verify if "Sprint" has "flown" which was "NA" for "ADT Jira" for "Normal" functionality
	
		Examples: 
		| applicationname |toolname|
		| MyWizard        |ADT JIRA|
