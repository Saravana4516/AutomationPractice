package testobjects;

import org.testng.Assert;
import uiMap.SauceDemoUIMap;
import utilities.general.Property;

import static org.testng.Assert.assertTrue;
import static utilities.selenium.SeleniumDSL.*;

public class Login {



    public static void loginToSauceDemo(){
        try{
            Thread.sleep(3000);
            driver().get(Property.getProperty("URL"));
            waitPageToLoad();

//			grabScreenshotForExtentReport();
            //code change after browser remembering the login details
            String parent=driver().getWindowHandle();
            driver().switchTo().window(parent);
            if(CheckIfElementExists(SauceDemoUIMap.Login_btn)){

                ExpWaitForCondition(SauceDemoUIMap.User_Name);
                enterText(SauceDemoUIMap.User_Name,Property.getProperty("Username"));
                Thread.sleep(1000);
                enterText(SauceDemoUIMap.Password_pd,Property.getProperty("Password"));
                click(SauceDemoUIMap.Login_btn);

            }






            waitPageToLoad();
//			 grabScreenshotForExtentReport();
            //ExpWaitForCondition(SauceDemoUIMap.Create_link);




            assertTrue(CheckIfElementExists(SauceDemoUIMap.logo));
            System.out.println("login to SauceDemo page successful");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            grabScreenshotForExtentReport();
            Assert.fail("Problem logging in to SauceDemo");
        }
    }

}
