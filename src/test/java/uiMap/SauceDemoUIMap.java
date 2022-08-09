package uiMap;

import org.openqa.selenium.By;



public class SauceDemoUIMap {

    public static By User_Name=By.xpath("//input[@class='input_error form_input' and @data-test='username']");
    public static By Password_pd=By.xpath("//input[@class='input_error form_input' and @data-test='password']");
 public static By Login_btn=By.xpath("//input[@class='submit-button btn_action' and @data-test='login-button']");
//div[@class='header_label']/child::div[@class="app_logo"]
public static By logo=By.xpath("//div[@class='header_label']/child::div[@class=\"app_logo\"]");




}
