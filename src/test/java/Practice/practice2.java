package Practice;
import edu.umass.cs.benchlab.har.*;
import edu.umass.cs.benchlab.har.tools.HarFileReader;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
public class practice2 {

	public static void main(String[] args) throws JsonParseException, IOException {
	
		String filename = new String("C:\\Users\\SeleniumEasy.har");
	    File f = new File(filename);
	    HarFileReader r = new HarFileReader();
	    HarLog log = r.readHarFile(f);
	    
	    HarBrowser browser = log.getBrowser();
	      HarEntries entries = log.getEntries();
	      
	      
	    List<HarPage> pages = log.getPages().getPages();
	      List<HarEntry> hentry = entries.getEntries(); 

	      for (HarPage page : pages)
	      {
	        System.out.println("page start time: "
	            + ISO8601DateFormatter.format(page.getStartedDateTime()));
	        System.out.println("page id: " + page.getId());
	        System.out.println("page title: " + page.getTitle());
	        }

	      //Output "response" code of entries.
	      for (HarEntry entry : hentry)
	      {
	          System.out.println("request code: " + entry.getRequest().getMethod()); //Output request type
	          System.out.println("    response code: " + entry.getRequest().getUrl()); //Output url of request
	          System.out.println("    response code: " + entry.getResponse().getStatus()); // Output the 
	      }
	}

}
