@IBvalidation
Feature: Workitem_GenericUploader

  Scenario Outline: GenericUploader_NoTool
    Given i load the "ADT Jira" project properties file
    Given i login to application "<applicationname>"
    Then i select client and DC for No Tool Instance
    And i navigate to the homepage of "<applicationname>" from "AIFusionPage"
    And i prepare the excel data for tool "NoToolInstance" in "Generic Uploader" DataLoader
    And i click on tile "Generic Uploader"    
    And i select the Data Entity as "Epic" for "noToolInstance" and upload the excel file
    And i select the Data Entity as "Feature" for "noToolInstance" and upload the excel file
    And i select the Data Entity as "Task" for "noToolInstance" and upload the excel file
    And i select the Data Entity as "Bug" for "noToolInstance" and upload the excel file
    And i select the Data Entity as "Issue" for "noToolInstance" and upload the excel file
    And i select the Data Entity as "Impediment" for "noToolInstance" and upload the excel file
    And i select the Data Entity as "Risk" for "noToolInstance" and upload the excel file
    And i select the Data Entity as "Action" for "noToolInstance" and upload the excel file
    And i select the Data Entity as "Decision" for "noToolInstance" and upload the excel file
    And i select the Data Entity as "Iteration" for "noToolInstance" and upload the excel file


    Examples:
      | applicationname |
      | MyWizard        |
