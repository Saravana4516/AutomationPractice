Feature: ADTJira_OB_ToolValidation_DIY

@6ADTJira_OB_ValidationInTool_DIY 
Scenario Outline: ADTJira_OB_ValidationInTool 
	And i put a explicit wait of "600000" 
	Given i login to application "<applicationname>" 
	Then i select a Project for "<applicationname>" 
	And i validate the outbound flow for "<applicationname>" 
	
	Examples: 
		| applicationname | 
		| ADT Jira            | 