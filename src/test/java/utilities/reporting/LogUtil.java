package utilities.reporting;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogUtil {
	public static Logger logger = Logger.getLogger("com.acn.uc.cukes");
	static FileHandler fh = null;
//	Hooks h = new Hooks();
	
	static {
		try {
//			fh = new FileHandler(Property.getProperty("Log.dir")+"\\TAFLogs_"+(new Timestamp(System.currentTimeMillis())).toString().replace(" ","_").replace(":", "_").substring(0, 19)+".log");
			
//			fh = new FileHandler("C://Users//Downloads//Automation projects"+".log");
			String fileloc = System.getProperty("user.dir")
					+ File.separator + "src" + File.separator + "test" + File.separator
					+ "resources" + File.separator+"Logs"+ File.separator +"Logs_"+(new Timestamp(System.currentTimeMillis())).toString().replace(" ","_").replace(":", "_").substring(0, 19)+".log";
//			fh = new FileHandler(System.getProperty("user.dir")+".log");
			fh = new FileHandler(fileloc);
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
	