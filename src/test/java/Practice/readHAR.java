package Practice;
import java.io.File;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.JsonParseException;

import edu.umass.cs.benchlab.har.HarEntries;
import edu.umass.cs.benchlab.har.HarEntry;
import edu.umass.cs.benchlab.har.HarLog;
import edu.umass.cs.benchlab.har.HarPages;
import edu.umass.cs.benchlab.har.HarWarning;
import edu.umass.cs.benchlab.har.tools.HarFileReader;

public class readHAR {

     public static void main(String[] args) {
         String fileName = "C:\\Users\\SeleniumEasy.har";
         ReadHarFile(fileName);
    }

    public static void ReadHarFile(String fileName){

         File f = new File(fileName);
         HarFileReader r = new HarFileReader();

         try
         {
        	 List<HarWarning> warnings = new ArrayList<HarWarning>();
             System.out.println("Reading " + fileName);
             HarLog log = r.readHarFile(f, warnings);

             // Access all pages elements as an object
              HarPages pages = log.getPages();

              long startTime =   pages.getPages().get(0).getStartedDateTime().getTime();

              System.out.println("page start time: " + startTime);

             // Access all entries elements as an object
             HarEntries entries = log.getEntries();

             List<HarEntry> hentry = entries.getEntries();

             long loadTime = 0;
             long responseSize=0;


             int entryIndex = 0;
             //Output "response" code of entries.
             for (HarEntry entry : hentry)
             {
                 System.out.println("entry: " + entryIndex);
                 System.out.println("request code: " + entry.getRequest().getMethod()); //Output request type
                 System.out.println("request url: "
                     + entry.getRequest().getUrl()); //Output request Url
                 System.out.println("    start time: " + entry.getStartedDateTime().getTime()); // Output start time
                 System.out.println("    time: " + entry.getTime()); // Output start time
                 System.out.println("response code: "
                     + entry.getResponse().getStatus()); //Output response code
                 responseSize=entry.getResponse().getHeadersSize()+entry.getResponse().getBodySize();


                 System.out.println("response size: "
                     + responseSize); //Output response size

                 long entryLoadTime = entry.getStartedDateTime().getTime() + entry.getTime();

                 if(entryLoadTime > loadTime){
                     loadTime = entryLoadTime;
                 }

                 System.out.println();
                 entryIndex++;
             }

             long loadTimeSpan = loadTime - startTime;
             System.out.println("loadTimeSpan: " + loadTimeSpan);

             Double webLoadTime = ((double)loadTimeSpan) / 1000;
             double webLoadTimeInSeconds = Math.round(webLoadTime * 100.0) / 100.0; 
             System.out.println("Web Load Time: " + webLoadTimeInSeconds) ;

         }
         catch (JsonParseException e)
         {
             e.printStackTrace();
             System.out.println("Parsing error during test");
         }
         catch (IOException e)
         {
             e.printStackTrace();
             System.out.println("IO exception during test");
         }
     }
 }