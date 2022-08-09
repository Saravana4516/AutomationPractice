package Practice;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;

import static org.testng.Assert.assertEquals;

import java.io.*;

import java.util.Random;

public class dum {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, ParseException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		String fileToBeValidated = "C:\\Users\\Downloads\\GM-SOLMAN-Inbound.config.xml";
//		String fileToBeValidated = "C:\\Users\\Downloads\\With Macro_GM-SOLMAN-Inbound_GM-SOLMAN-Inbound.config.xml";
		String referenceJSON = "C:\\Users\\Downloads\\configTest.json";
		Document document = builder.parse(new File(fileToBeValidated));
		document.getDocumentElement().normalize();

		String[] MacronizedNodes = listAllTagNames(referenceJSON);
		
		for(int i=0; i<MacronizedNodes.length; i++)
		{
			NodeList entries = document.getElementsByTagName(MacronizedNodes[i]);
	        
	        int num = entries.getLength();
	         
	        for (int j=0; j<num; j++) {
	            Element node = (Element) entries.item(j);
	            listAllAttributes(node);
	        }
		}
		
		
         
    }
	public static void listAllAttributes(Element element) throws FileNotFoundException, IOException, ParseException {
        
//        System.out.println("List attributes for node: " + element.getNodeName());
         
        // get a map containing the attributes of this node 
        NamedNodeMap attributes = element.getAttributes();
 
        // get the number of nodes in this map
        int numAttrs = attributes.getLength();
 
        for (int i = 0; i < numAttrs; i++) {
            Attr attr = (Attr) attributes.item(i);
             
            String attrName = attr.getNodeName();
            String attrValue = attr.getNodeValue();
            if(!attrValue.contains("{") && !attrValue.contains("}") )
            {
            	if(checkifNodeisPresentinJson(attrName))
            		assertEquals(getNodeValuefromJson(attrName), attrValue);
            		
            }
            else
            	System.out.println("micronized data for "+attrName);
//            	checkifNodeisPresentinJson(attrName);
//            	assertEquals(getNodeValuefromJson(attrName), attrValue);
            	
//            	System.out.println(attrName);
//            System.out.println("Found attribute: " + attrName + " with value: " + attrValue);
             
        }
    }
	
	public static String getNodeValuefromJson(String nodename) throws FileNotFoundException, IOException, ParseException {
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("C:\\Users\\sonal.harish.nagda\\Downloads\\configTest.json"));
		return (String) jsonObject.get(nodename);
	}
	
	public static boolean checkifNodeisPresentinJson(String nodename) throws FileNotFoundException, IOException, ParseException {
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject1 = (JSONObject) jsonParser.parse(new FileReader("C:\\Users\\sonal.harish.nagda\\Downloads\\configTest.json"));
		if(jsonObject1.containsKey(nodename))
			return true;
		else
			return false;
	
	}
	
	public static String[] listAllTagNames(String filepath) throws FileNotFoundException, IOException, ParseException
	{
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject1 = (JSONObject) jsonParser.parse(new FileReader(filepath));
		 String a= (String) jsonObject1.get("NodesWithMacroData");
		 String[] split = a.split("&&");
		 return split;
	}
		 

}
