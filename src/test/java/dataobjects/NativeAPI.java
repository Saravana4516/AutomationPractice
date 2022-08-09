package dataobjects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.general.Property;

import org.json.simple.JSONObject;
import org.junit.Assert;

public class NativeAPI {
	
	public static Map<String, List<Object>> NativeAPI(String String1,String String2,String String3,String String4,String String5,Object MultiValuedFields){
		try{
			
//			convert object MultiValuedFields to string
			String AllMultiValuedFields1 = String.valueOf(MultiValuedFields);
			String[] AllMultiValuedFields_removeBrace1 = String.valueOf(AllMultiValuedFields1).split("\\[");
		    String[] AllMultiValuedFields_removeBrace2 = AllMultiValuedFields_removeBrace1[1].split("\\]");
		    String[] AllMultiValuedFields = AllMultiValuedFields_removeBrace2[0].split(",");
					
			 RequestSpecification request = RestAssured.given();
			 	
			 request.header("Content-Type", "application/json")
			        .header("Authorization","Bearer "+Property.getToken("Token"));

			 
			 JSONObject requestParams = new JSONObject();
			 
			 
			 requestParams.put("header1", String1);

			 
			 request.body(requestParams.toJSONString());
			 
			 
			 String URL = Property.getProperty("URL");
			 String[] URL_Sp = URL.split(".com");
			URL = URL_Sp[0]+".com/core";
			URL = URL.replace("string1", "string2");
			 
			 	 
			 String url = URL+"/v1/="+Property.getProperty("Something")+"&Random="+Property.getProperty("Something")+"\")";

			 Response response = request.post(url);
			 Assert.assertEquals(response.getStatusCode(), 200);
//			 System.out.println(response.getStatusCode());
			 
			 
			 if((int) response.getStatusCode()==200)
					 {
					 JsonPath js = response.jsonPath();
					 
					 
					 int size = js.getInt("random.size()");
					
					 Map<String, List<Object>> alldata = new HashMap<>();
					 
								 for(int p=0; p<AllMultiValuedFields.length;p++)
								 {
								 
									 for (int i = 0; i < size; i++) {
									        String MultivaluedFeild = js.getString("random[" + i + "].Name");
									        if (MultivaluedFeild.equalsIgnoreCase(AllMultiValuedFields[p].trim())) {
									            List<Object> EntityPropertyValue = js
									                    .getList("random[" + i + "].Values.random");
									            List<Object> ProductPropertyValueUId = js
									                    .getList("random[" + i + "].Values.random");
									            List<Object> ProductPropertyValueDisplayName = js
									                    .getList("random[" + i + "].Values.random");
					//				            System.out.println("Values for EntityPropertyValue : " + AllMultiValuedFields[p]+  EntityPropertyValue);
					//				            System.out.println("Values for ProductPropertyValueUId : " +AllMultiValuedFields[p] + ProductPropertyValueUId);
						//			            System.out.println("Values for ProductPropertyValueDisplayName : " +sp2[p] + ProductPropertyValueDisplayName);
									          
									          alldata.put(AllMultiValuedFields[p]+"_Property", EntityPropertyValue);
					//				          alldata.put(AllMultiValuedFields[p]", ProductPropertyValueDisplayName);
									          alldata.put(AllMultiValuedFields[p]+"_GUId", ProductPropertyValueUId);
									           
									            break;
									        }
									    }
								 }
					 //comment out the below part
					 for(Map.Entry<String, List<Object>> entry : alldata.entrySet())
					 {
						 System.out.println(entry.getKey() + " = " + entry.getValue());
					 }
					 System.out.println("_________________________________");
					 
					 return alldata;
					
				}
		
		else
				Assert.fail("API response is "+response.getStatusCode());
			
			}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Problem fetching repsonse from  api");
		}
		return null;
	}
	
	
}
