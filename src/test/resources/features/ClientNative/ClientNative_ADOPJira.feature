@Browser 
Feature: Client native test for ADOP JIRA

Scenario Outline: Client native test for ADOP JIRA(excluding iteration) 
	Given i login to application "<applicationname>" 
		And i navigate to the homepage of "<applicationname>" from "AIFusionPage"
	And i click on tile "Product Configuration" 
	Then i select client and DC for "<applicationname>" 
	And i select the "ADOP JIRA" in Manage Product Configuration page 
	And i navigate to "Product Instance Entities" section 
	And i verify the ClientNative details for "<toolname>" 
	
	Examples: 
		| applicationname |toolname|
		| MyWizard        |ADOP JIRA|
		
Scenario Outline: Client native test for ADOP JIRA(iteration only) 
	Given i login to application "<applicationname>" 
	#	And i navigate to the homepage of "<applicationname>" from "AIFusionPage"
	And i click on tile "Product Configuration" 
	Then i select client and DC for "<applicationname>" 
	And i select the "mywizardinstance" in Manage Product Configuration page
	And i navigate to "Product Instance Entities" section 
	And i verify the ClientNative details for "<toolname>" 
	
	Examples: 
		| applicationname |toolname|
		| MyWizard        |ADOP JIRA_Iteration|
		
