@Practice
Feature: AutomationPractice

  @LoginFunctionalityCheck
  Scenario Outline: Login Functionality
    Given i load the project properties file
    Given i login to application "Application Name"
    #	And i put a explicit wait of "900000"

    Examples:
      | Application Name |
      | saucedemo      |
