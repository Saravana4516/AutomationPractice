@IBvalidation
Feature: XIBvalidation_CloudJira


  Scenario: CloudJIRA_Task 
	Given i load the "Cloud Jira" project properties file 
	And i generate a token for "DevTest" environment 
	And i verify the "Inbound" "Task" details for "Cloud JIRA"
	
Scenario: CloudJIRA_Epic 

	And i verify the "Inbound" "Epic" details for "Cloud JIRA" 
	

Scenario: CloudJIRA_Story 
	And i verify the "Inbound" "Story" details for "Cloud JIRA" 

Scenario: CloudJIRA_Bug

	And i verify the "Inbound" "Bug" details for "Cloud JIRA" 

Scenario Outline: CloudJira_DIY_IBVerification_Release_Sprint 
	Given i login to application "<applicationname>" 
	Then i select client and DC for "<applicationname>"
	And i navigate to the homepage of "<applicationname>" from "AIFusionPage"
	And i click on tile "my Queries"	
	And i capture the "IterationExternalID" for Entities created from "tool" for tool "Cloud Jira" 
	And i generate a token for "DevTest" environment 	
	And i verify if "Release" has "flown" which was "NA" for "Cloud Jira" for "Normal" functionality
	And i verify if "Sprint" has "flown" which was "NA" for "Cloud Jira" for "Normal" functionality
	
		Examples: 
		| applicationname |toolname|
		| MyWizard        |Cloud JIRA|

