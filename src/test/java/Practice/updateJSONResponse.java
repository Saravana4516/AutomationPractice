package Practice;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.uuid.Generators;
import com.google.gson.Gson;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
public class updateJSONResponse {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		String jsonString = "{\"id\":1,\"name\":\"ashwin\",\"surname\":\"karangutkar\",\"details\":{\"City\":\"Mumbai\"}}\\";

		   Configuration configuration = Configuration.builder().jsonProvider(new JacksonJsonNodeJsonProvider())
		   .mappingProvider(new JacksonMappingProvider()).build();

//		   DocumentContext json = JsonPath.using(configuration).parse(jsonString);
//		   String jsonPath = "details.City";
//		   String newValue = "Pune";
//		   System.out.println(json.set(jsonPath, newValue).jsonString());
		   File file = new File("C:\\Users\\Desktop\\workitem.json");
		   DocumentContext json = JsonPath.using(configuration).parse(file);
//		   String jsonPath = "WorkItems[0].CorrelationUId";
//		   String jsonPath  = "WorkItems[0].WorkItemAttributes[0].Value";
//		   String newValue = "new title";
//		   String jsonPath1 = "WorkItems[0].ItemState";
//		   String newValue1 = "1";
//		   json.set(jsonPath, newValue);
//		   DocumentContext finaljson = json.set(jsonPath, newValue);
		   DocumentContext finaljson;
//		   finaljson = json.set(jsonPath1, newValue1);
		   finaljson = json.set("sample", Generators.timeBasedGenerator().generate());
		   finaljson = json.set("ModifiedAtSourceOn", (new Random().nextInt(2)+2024+"-"+String.format("%02d", Integer.valueOf(String.valueOf(new Random().nextInt(12)+1)))+"-"+String.format("%02d", Integer.valueOf(String.valueOf(new Random().nextInt(28)+1)))+"T18:48:07.6972433"));
//		   String a = json.set(jsonPath1, newValue1).jsonString();
		   System.out.println(finaljson.jsonString());
		 
//		   DocumentContext context = JsonPath.parse(finaljson.jsonString());
//			HashMap<Object, Object> revenueList = context.read("WorkItems[0]");
//			System.out.println(revenueList);
		   
//		   JSONObject jsonObj, desiredObject;
//		   JSONParser parser=new JSONParser();  // parser to parse string to JSONObject
//		   jsonObj = (JSONObject) parser.parse(finaljson.jsonString()); // parse the Object using parse Method.
//		   desiredObject = (JSONObject) jsonObj.get("WorkItems"); 
//		   System.out.println(desiredObject);
		   
		   JSONParser parser = new JSONParser();
		   JSONObject jsonObject = (JSONObject) parser.parse(finaljson.jsonString());
		   String msgType = (String) jsonObject.get("dvsd").toString();
//		   System.out.println(msgType);
		   System.out.println(msgType.substring(1, msgType.length() - 1));

		 }
	

}
