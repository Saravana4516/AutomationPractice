@IBvalidation
Feature: Workitem_ADOPJira


  Scenario Outline: ADOPJira_WorkitemCcreation
    Given i load the "ADOP Jira" project properties file
    Given i login to application "<applicationname>"
    Then i select a Project for "<applicationname>"
    And i create a "<feature>" in Jira
    And i create a "<task>" in Jira
    And i create a "<story>" in Jira
    And i create a "<risk>" in Jira
    And i create a "<bug>" in Jira
    And i create a "<impediment>" in Jira
    And i create a "<epic>" in Jira
    And i create a "<issue>" in Jira
    And i create a "<Test>" in Jira
    And i create an "<Release>" in Jira
    And i create an "<Sprint>" in Jira
    And i create entity "<TestForTestExec>" in Jira
    And i update the WorkItemExternalIDs into a New JSON file for "<applicationname>"
#And i check the overall status of workitem creation for "<applicationname>"
#And i put a explicit wait of "900000"
#    And i generate a token for "DevTest" environment

    Examples:
      | applicationname | feature             | task    | story    | risk         | issue         | bug    | feature    | impediment    | deliverable    | epic    | subtask    | Release    | Sprint    | Test    | TestForTestExec    |
      | ADOP Jira       | New Feature_ADOP_01 | Task_01 | Story_01 | Risk_ADOP_01 | Issue_ADOP_01 | Bug_01 | Feature_01 | Impediment_01 | Deliverable_01 | Epic_01 | SubTask_01 | Release_01 | Sprint_02 | Test_01 | TestForTestExec_01 |
