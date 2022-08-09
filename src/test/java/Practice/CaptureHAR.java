package Practice;
import utilities.selenium.Excel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import edu.umass.cs.benchlab.har.HarEntries;
import edu.umass.cs.benchlab.har.HarHeader;
import edu.umass.cs.benchlab.har.HarLog;
import edu.umass.cs.benchlab.har.HarWarning;
import edu.umass.cs.benchlab.har.tools.HarFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
//import net.lightbody.bmp.core.har.Har;
//import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import java.io.File;
import java.io.IOException;
public class CaptureHAR {
	public static WebDriver driver;
	public static BrowserMobProxy proxy;

	public static void main(String[] args) {
		
		String driverPath = "C:\\Users\\Documents\\almPT_old\\src\\test\\resources\\drivers\\chromedriver.exe";
		String sFileName = "C:\\Users\\SeleniumEasy1.har";
		BrowserMobProxyServer proxy = new BrowserMobProxyServer();
	    proxy.start(0);
	    Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
	    
	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
	    
	   
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
	    
	    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Documents\\almPT_old\\src\\test\\resources\\drivers\\chromedriver.exe");
		driver = new ChromeDriver(capabilities);
		proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
		  proxy.newHar("test");

		  driver.get("https://stackoverflow.com/");
		
		  Har har = proxy.getHar();

			// Write HAR Data in a File
			File harFile = new File(sFileName);
			try {
				har.writeTo(harFile);
			} catch (IOException ex) {
				 System.out.println (ex.toString());
			     System.out.println("Could not find file " + sFileName);
			}
			
			proxy.stop();
			
	}

}
