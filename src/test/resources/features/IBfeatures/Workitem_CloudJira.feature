@IBvalidation
Feature: Workitem_CloudJira

  Scenario Outline: CloudJira_WorkitemCcreation
    Given i load the "Cloud Jira" project properties file
    Given i login to application "<applicationname>"
    Then i select a Project for "<applicationname>"
    And i create a "<task>" in CloudJira
    And i create a "<story>" in CloudJira
    And i create a "<bug>" in CloudJira
    And i create a "<epic>" in CloudJira
    And i create an "<Release>" in Jira
    And i create an "<Sprint>" in Jira
    And i update the WorkItemExternalIDs into a New JSON file for "<applicationname>"
    And i check the overall status of workitem creation for "<applicationname>"
#    And i put a explicit wait of "900000"
#    And i generate a token for "DevTest" environment
#
    Examples:
      | applicationname | feature              | task    | story    | risk          | issue          | bug    | feature    | impediment    | deliverable    | epic    | subtask    | Release    | Sprint           |
      | Cloud Jira      | New Feature_Cloud_01 | Task_01 | Story_01 | Risk_Cloud_01 | Issue_Cloud_01 | Bug_01 | Feature_01 | Impediment_01 | Deliverable_01 | Epic_01 | SubTask_01 | Release_02 | Sprint_CloudJira |

#@8CloudJira_OB_ValidationInTool
#this is pending
#Scenario Outline: CloudJira_OB_ValidationInTool
#And i put a explicit wait of "600000"
#    Given i login to application "<applicationname>"
#   Then i select a Project for "<applicationname>"
##    And i validate the outbound flow

#    Examples:
#      | applicationname |
#      | Jira            |

