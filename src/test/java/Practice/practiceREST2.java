package Practice;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.hamcrest.Matchers.*;
import org.json.simple.JSONObject;
import org.codehaus.groovy.*;
import groovy.json.JsonSlurper;
public class practiceREST2 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
	
		RestAssured.baseURI ="";
		 RequestSpecification request = RestAssured.given();
		 
		
		 request.header("Content-Type", "application/json");
		
		 
		 JSONObject requestParams = new JSONObject();
		 requestParams.put("header1", "sdvsdv");


		 request.body(requestParams.toJSONString());
		
		
		 Response response = request.post("");
		 System.out.println(response.getStatusCode());
		 System.out.println(response.body().toString());
		 JsonPath js = response.jsonPath();
		 
		 
		 int size = js.getInt("Values.size()");
		
		 Map<String, List<Object>> alldata = new HashMap<>();
		 
		 for (int i = 0; i < size; i++) {
		        String MultivaluedFeild = js.getString("Values[" + i + "].Name");
		        if (MultivaluedFeild.equalsIgnoreCase("")) {
		            List<Object> EntityPropertyValue = js
		                    .getList("Values[" + i + "].Values.Values");

		            System.out.println("Values for EntityPropertyValue : " + EntityPropertyValue);

		          
		          alldata.put("EntityPropertyValue", EntityPropertyValue);

		           
		            break;
		        }
		    }
		 

		 
		 
		 
		 
		 
		 
//		 System.out.println("Response body: " + response.body().asString());
//		 List<Map<String, String>> epv = response.jsonPath().getList("EntityPropertyValues");
//		 
//		 
//		for(int i=0;i<epv.size();i++)
//		{
//			if(epv.get(i).containsValue("PriorityUId"))
//			{
//				System.out.println("yes");
//				System.out.println(epv.get(i));
////				if(companies.get(i).containsKey("EntityPropertyIdValue"))
////					System.out.println(companies.get(i).values());
//				break;
//			}
//		}
//		 System.out.println(companies.get(0));
		
	
		
		 
		
	}

}
