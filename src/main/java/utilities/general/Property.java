package utilities.general;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Property {
	
	/** 
	 * gets properties file 
	 */
	public static Properties getProperties(String propsPath) throws IOException{
		File propsFile = new File(propsPath);
		FileInputStream inputStream = new FileInputStream(propsFile);
		Properties props = new Properties();
		props.load(inputStream);
		inputStream.close();
		return props;
	}	
	
	/** 
	 * gets data from the main project properties file
	 */
	public static String getProperty(String key) throws IOException {
		String propsPath = System.getProperty("user.dir")+File.separator+"Properties"+File.separator+"project.properties";
		return getProperties(propsPath).getProperty(key);
	}

	/** 
	 * gets data from any properties file on given path
	 */
	public static String getProperty(String propsPath, String key) throws IOException {
		return getProperties(propsPath).getProperty(key);
	}

}
