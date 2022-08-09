package testobjects;
import java.io.*; 
import java.util.*;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import static utilities.reporting.LogUtil.logger;

import java.text.SimpleDateFormat;

import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.general.Property;

import java.security.spec.KeySpec;
import javax.crypto.SecretKey;

public class CommonFunctions {
	
	public static String testDataPath = System.getProperty("user.dir")
			+ File.separator + "src" + File.separator + "test" + File.separator
			+ "resources" + File.separator + "testdata" + File.separator;
	
	private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private static KeySpec ks;
    private static SecretKeyFactory skf;
    private static Cipher cipher;
    static byte[] arrayBytes;
    private static String myEncryptionKey;
    private static String myEncryptionScheme;
    static SecretKey key;
    
    
	public static String convertdatetogivenformat(String datetobeconverted,String fromdateformat,String todateformat){
		
		try{
		SimpleDateFormat sdf = new SimpleDateFormat(fromdateformat);
	    SimpleDateFormat sdf2 = new SimpleDateFormat(todateformat);
	    return sdf2.format(sdf.parse(datetobeconverted));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.info("issue with date format or converting date format");
			return "";
		}
		
	}


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

	public static void copyFileContentFromOneFileToAnother(File A, File B) throws IOException { 
        FileInputStream in = new FileInputStream(A); 
        FileOutputStream out = new FileOutputStream(B); 
  
        try { 
  
            int n; 
  
            // read() function to read the 
            // byte of data 
            while ((n = in.read()) != -1) { 
                // write() function to write 
                // the byte of data 
                out.write(n); 
            } 
        } 
        finally { 
            if (in != null) { 
  
                // close() function to close the 
                // stream 
                in.close(); 
            } 
            // close() function to close 
            // the stream 
            if (out != null) { 
                out.close(); 
            } 
        } 
        System.out.println("Property File loaded"); 
        logger.info("Property File loaded"); 
    } 
	public static void generateToken(String env) throws IOException {
		try{
		switch(env){
		case "sample":
		case "SAMPLE":
			String PostUrl="";
			 Response response = RestAssured
					    .given()
					    .header("header1", "value1")
					    .formParam("header2", "value2")
					    .request()
					    .post(PostUrl);
			 Thread.sleep(10000);
			 if(response.getStatusCode()!=200)
				 {
					logger.info("Got "+response.getStatusCode()+" reponse code for "+env+" for the Token Generation ");
						 Assert.fail("Got "+response.getStatusCode()+" reponse code for "+env+" for the Token Generation ");
						
				 }
				 Assert.assertEquals(response.getStatusCode(), 200);
				 logger.info("comlpete");
				 JsonPath js = response.jsonPath();
					String token = js.get("value");
					Property.setProperty("Token", token);
			break;
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Issue generating the value for "+env+" sample");
		}
	}

	
	public static int GenerateRandomNumber()
	{
		Random rnd = new Random();
		return (1000 + rnd.nextInt(9000));
		
	}

	public static void writeValues(String string1, String string2,
			String string3) {
		
		try{
		String writeDetails="";
		
			if(string1.contains("sample") || string1.contains("Sample")){
				writeDetails = testDataPath + "sample" + File.separator + "JSON" +  File.separator + "IDs.json" ;
			}

			FileReader reader = new FileReader(writeDetails);
	        JSONParser jsonParser = new JSONParser();
	        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
	        jsonObject.put("vale1",string2);
	        jsonObject.put("value2",string3);
	        FileOutputStream outputStream = new FileOutputStream(writeDetails);
			 byte[] strToBytes = jsonObject.toString().getBytes(); outputStream.write(strToBytes);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	  public static String encrypt(String unencryptedString) {
	        String encryptedString = null;
	        try {
	        	myEncryptionKey = "ThisIsSpartaThisIsSparta";
		        myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
		        arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
		        ks = new DESedeKeySpec(arrayBytes);
		        skf = SecretKeyFactory.getInstance(myEncryptionScheme);
		        cipher = Cipher.getInstance(myEncryptionScheme);
		        key = skf.generateSecret(ks);
	            cipher.init(Cipher.ENCRYPT_MODE, key);
	            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
	            byte[] encryptedText = cipher.doFinal(plainText);
	            encryptedString = new String(Base64.encodeBase64(encryptedText));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return encryptedString;
	    }


	    public static String decrypt(String encryptedString) {
	        String decryptedText=null;
	        try {
	        	myEncryptionKey = "ThisIsSpartaThisIsSparta";
		        myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
		        arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
		        ks = new DESedeKeySpec(arrayBytes);
		        skf = SecretKeyFactory.getInstance(myEncryptionScheme);
		        cipher = Cipher.getInstance(myEncryptionScheme);
		        key = skf.generateSecret(ks);
	            cipher.init(Cipher.DECRYPT_MODE, key);
	            byte[] encryptedText = Base64.decodeBase64(encryptedString);
	            byte[] plainText = cipher.doFinal(encryptedText);
	            decryptedText= new String(plainText);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			System.out.println(decryptedText);
	        return decryptedText;

	    }

}
