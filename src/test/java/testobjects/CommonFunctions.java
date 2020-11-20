package testobjects;

import static utilities.reporting.Reporting.create_logs_and_report;

import static utilities.selenium.SeleniumDSL.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.general.Property;

public class CommonFunctions {
	
	public static String SpiltWorkitem(String fullworkitemID)
	{
		
			if(fullworkitemID!=null)
			{
			String fullworkitem_sp [] = fullworkitemID.split(" ");
			System.out.println(fullworkitem_sp.length);
			int s = fullworkitem_sp.length;
			return fullworkitem_sp[fullworkitem_sp.length-1];
			}
		else
			return null;
}

	public static void generateToken(String env) throws IOException {
		try{
		switch(env){
		case "DevTest":
		case "devtest":
			String PostUrl="https://login.microsoftonline.com/f3211d0e-125b-42c3-86db-322b19a65a22/oauth2/token";
			 Response response = RestAssured
					    .given()
					    .header("Content-Type", "application/x-www-form-urlencoded")
					    .formParam("grant_type", "client_credentials")
					    .formParam("client_id", "af3d00bb-72bd-4ad9-8ea6-b5dd5c650aed")
					    .formParam("resource","af3d00bb-72bd-4ad9-8ea6-b5dd5c650aed")
					    .formParam("client_secret", "Y1N2T2VzIyV3dFJ4JUJnNA==")
					    .request()
					    .post(PostUrl);
			 Thread.sleep(10000);
			 if(response.getStatusCode()!=200)
				 {
						 Assert.fail("Got "+response.getStatusCode()+" reponse code for "+env+" for the Token Generation ");
						
				 }
				 Assert.assertEquals(response.getStatusCode(), 200);
				 JsonPath js = response.jsonPath();
					String token = js.get("access_token");
					Property.setProperty("Token", token);
			break;
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Issue generating the token for "+env+" environment");
		}
	}

	
	
	
	
		
	}
