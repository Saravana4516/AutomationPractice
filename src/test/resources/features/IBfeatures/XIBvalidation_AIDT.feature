@IBvalidation
Feature: XIBvalidation_AIDT


 Scenario: AIDT_Task 
	Given i load the "AIDT" project properties file
	And i generate a token for "DevTest" environment 
	And i verify the "Inbound" "Task" details for "AIDT" 
	

Scenario: AIDT_Epic 
	And i verify the "Inbound" "Epic" details for "AIDT" 
	

Scenario: AIDT_Story 
	And i verify the "Inbound" "Story" details for "AIDT" 


Scenario: AIDT_Issue 
	And i verify the "Inbound" "Issue" details for "AIDT" 
	

Scenario: AIDT_Bug
	And i verify the "Inbound" "Bug" details for "AIDT" 
	
Scenario: AIDT_Impediment
And i verify the "Inbound" "Impediment" details for "AIDT"	

Scenario: AIDT_Feature 
	And i verify the "Inbound" "Feature" details for "AIDT" 
	


Scenario: AIDT_Risk
	 	And i verify the "Inbound" "Risk" details for "AIDT" 			


Scenario: AIDT_Deliverable
	 	And i verify the "Inbound" "Deliverable" details for "AIDT"
	
	
Scenario: AIDT_Action
		And i verify the "Inbound" "Action" details for "AIDT"
	

Scenario: AIDT_Decision
	And i verify the "Inbound" "Decision" details for "AIDT"

	Scenario: AIDT_Requirement
		And i verify the "Inbound" "Requirement" details for "AIDT"

	Scenario: AIDT_Milestone
		And i verify the "Inbound" "Milestone" details for "AIDT"
	
		
Scenario Outline: AIDT_IBVerification_Release_Sprint 
	Given i login to application "<applicationname>" 
	Then i select client and DC for "<applicationname>"
	And i navigate to the homepage of "<applicationname>" from "AIFusionPage"
	And i click on tile "my Queries"	
	And i capture the "IterationExternalID" for Entities created from "tool" for tool "AIDT" 
	And i generate a token for "DevTest" environment 	
	And i verify if "Release" has "flown" which was "NA" for "AIDT" for "Normal" functionality
	And i verify if "Sprint" has "flown" which was "NA" for "AIDT" for "Normal" functionality

		Examples: 
		| applicationname |toolname|
		| MyWizard        |AIDT|
		
