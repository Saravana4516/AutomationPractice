@Browser 
Feature: SEI ADOP JIRA

Scenario Outline: ADOP jira IB SEI
	Given i login to application "<applicationname>" 
#	And i navigate to the homepage of "<applicationname>" from "AIFusionPage"
	And i click on tile "Product Configuration"
	Then i select client and DC for "<applicationname>"  
	And i select the "ADOP JIRA" in Manage Product Configuration page 
	And i navigate to "GatewayManager/ToolGateway Integration Parameters" section 
	And i verify the existing "IB" pipelines if present and add them if missing for "<toolname>"
	And i hit the save button in Product Config page
	And i select the "ADOP JIRA" in Manage Product Configuration page
	And i navigate to "GatewayManager/ToolGateway Integration Parameters" section 
	And i deploy the pipelines and verify if successful for "<toolname>"
	And i close the browser session
		
	Examples: 
		| applicationname |toolname|
		| MyWizard        |ADOP JIRA|
	
Scenario Outline:ADOP jira OB SEI
	Given i login to application "<applicationname>"	
	Then i select only the client for "<applicationname>"
	And i select the "ADOP JIRA" in Manage Product Configuration page 
	And i navigate to "GatewayManager/ToolGateway Integration Parameters" section
	And i verify the existing "OB" pipelines if present and add them if missing for "<toolname>"
	And i hit the save button in Product Config page
	And i select the "ADOP JIRA" in Manage Product Configuration page
	And i navigate to "GatewayManager/ToolGateway Integration Parameters" section 
	And i deploy the pipelines and verify if successful for "<toolname>"
	
	Examples: 
		| applicationname |toolname|
		| MyWizard        |ADOP JIRA|
