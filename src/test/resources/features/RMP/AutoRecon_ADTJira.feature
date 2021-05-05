@ADTJiraCreateReleaseAndSprintFromRMP 
Feature: ADTJira_AutoRecon 

@1ADTJira_Autorecon_createReleaseAndSprintFromToolForAutomaticRecon
Scenario Outline: ADTJIRA_AutoRecon_ReleaseAndSprintCreationForAutoReconFromTool 
	Given i load the project properties file 
	Given i login to application "<applicationname>" 
	Then i select a Project for "<applicationname>" 
	And i create an "<Release>" in Jira 
	And i create an "<Sprint>" in Jira 
	And i update the WorkItemExternalIDs into a JSON file for "<applicationname>" 
	
	Examples: 
		| applicationname | task    | story    | risk    |Requirement| Test|issue    | bug    | feature    | impediment    | deliverable    | epic    | subtask    | Release    | Sprint    |Team|milestone|TestExecution|Action|TestForTestExec|WorkRequest|
		| Jira            | Task_01 | Story_01 | Risk_01 | Requirement_01| Test_01|Issue_01 | Bug_01 | Feature_01 | Impediment_01 | Deliverable_01 | Epic_01 | SubTask_01 | Release_01 | Sprint_01 |Team_01|Milestone_01|Test Execution_01|Action_01|TestForTestExec_01|Work Request_01|
	
@2ADTJira_Autorecon_createStoryForAutomaticRecon
Scenario Outline: ADTJIRA_AutoRecon_StoryCreationForAutoReconFromTool 
	Given i load the project properties file 
	Given i login to application "<applicationname>" 
	Then i select a Project for "<applicationname>" 
	And i create a "<story>" in Jira for "autorecon" 
	And i update the WorkItemExternalIDs into a JSON file for "<applicationname>" 
	And i put a explicit wait of "900000" 
	
	Examples: 
		| applicationname | task    | story    | risk    |Requirement| Test|issue    | bug    | feature    | impediment    | deliverable    | epic    | subtask    | Release    | Sprint    |Team|milestone|TestExecution|Action|TestForTestExec|WorkRequest|
		| Jira            | Task_01 | Story_01 | Risk_01 | Requirement_01| Test_01|Issue_01 | Bug_01 | Feature_01 | Impediment_01 | Deliverable_01 | Epic_01 | SubTask_01 | Release_01 | Sprint_01 |Team_01|Milestone_01|Test Execution_01|Action_01|TestForTestExec_01|Work Request_01|
		
@3ADTJira_Autorecon_IBVerificationofReleaseAndSprintCreatedFromTool
Scenario Outline: ADTJIRA_AutoRecon_ReleaseAndSprintIBVerification_CaptureIterationExternalId 
	Given i login to application "<applicationname>" 
	And i navigate to the homepage of "<applicationname>" from "AIFusionPage"
	And i click on tile "my Queries"
	Then i select client and DC for "<applicationname>"
	And i capture the IterationExternalID for Iteration created from "tool" for tool "ADT Jira" 
	And i generate a token for "DevTest" environment 
	And i verify if "ReleaseFromTool" has "flown" which was "NA" for "ADT Jira" for "Recon" functionality
	And i verify if "SprintFromTool" has "flown" which was "NA" for "ADT Jira" for "Recon" functionality
		Examples: 
		| applicationname |toolname|
		| MyWizard        |ADT JIRA|

@4ADTJira_Autorecon_IBVerificationofStoryVerifyiterationexternalid
Scenario: ADTJIRA_AutoRecon_Story_CheckifStoryFlown_Verifyiterationexternalid 
	And i generate a token for "DevTest" environment 
	And i verify if "Story" has "flown" which was "NA" for "ADT Jira" for "BeforeRecon" functionality
	
	
@5ADTJira_Autorecon_CreateReleaseAndSprintFromRMP 
Scenario Outline: AutoRecon_CreateReleaseAndSprintFromRMP 
	Given i load the project properties file 
	Given i login to application "<applicationname>" 
	And i navigate to RMP page 
	Then i select client and DC for "<applicationname>" for RMP
	And i and create Release and Sprint in RMP page for "AutoRecon" for the tool "ADT Jira" 
	And i put a explicit wait of "900000"
	
	
	Examples: 
		| applicationname |applicationname1|
		| MyWizard        |RMP|
	
	
@6ADTJira_Autorecon_IBVerificationofReleaseAndSprintCreatedFromRMP
Scenario: ADTJIRA_AutoRecon_CheckifReleaseAndSprintFlown_CaptureIterationExternalId 
	Given i login to application "<applicationname>" 
	And i navigate to the homepage of "<applicationname>" from "AIFusionPage"
	And i click on tile "my Queries"
	Then i select client and DC for "<applicationname>"
	And i capture the IterationExternalID for Iteration created from "RMP" for tool "ADT Jira" 
	And i generate a token for "DevTest" environment  
	And i verify if "ReleaseFromRMP" has "flown" which was "NA" for "ADT Jira" for "Recon" functionality
	And i verify if "SprintFromRMP" has "flown" which was "NA" for "ADT Jira" for "Recon" functionality
			
@7ADTJira_Autorecon_IBVerificationofStoryVerifyIterationUIdAfterRecon
Scenario: ADTJIRA_AutoRecon_CheckifStoryFlown_VerifyIterationUIdAfterRecon 
	And i generate a token for "DevTest" environment 
	And i verify if "Story" has "flown" which was "NA" for "ADT Jira" for "AfterRecon" functionality