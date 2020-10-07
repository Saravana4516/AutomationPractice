package utilities.reporting;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import utilities.general.Property;

public class LogUtil {
	public static Logger logger = Logger.getLogger("com.acn.uc.cukes");
	static FileHandler fh = null;
//	Hooks h = new Hooks();
	
	static {
		try {
//			fh = new FileHandler(Property.getProperty("Log.dir")+"\\TAFLogs_"+(new Timestamp(System.currentTimeMillis())).toString().replace(" ","_").replace(":", "_").substring(0, 19)+".log");
			
//			fh = new FileHandler("C://Users//sonal.harish.nagda//Downloads//Automation projects"+".log");
			fh = new FileHandler(System.getProperty("user.dir")+".log");
			fh.setFormatter(new SimpleFormatter());
			logger.setLevel(Level.INFO);
			logger.addHandler(fh);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
	