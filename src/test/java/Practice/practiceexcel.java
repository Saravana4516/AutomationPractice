package Practice;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class practiceexcel {

	public static void main(String[] args) {
		 String FileLoc = System.getProperty("user.dir")+ File.separator + "src" + File.separator + "test" + File.separator	+ "resources" + File.separator	+ "doc.xlsx";
		 
		 try{
				FileInputStream	 fis = new FileInputStream(new File(FileLoc));
				XSSFWorkbook workbook = new XSSFWorkbook (fis);
				XSSFSheet sheet = workbook.getSheet("Sheet1");
				int noOfColumns = sheet.getRow(0).getLastCellNum();
				int noofRows = sheet.getLastRowNum();
				HashMap<String,ArrayList<String>> listofEntities = new HashMap<String,ArrayList<String>>();
				for(int i= 1; i<noOfColumns;i++)
				{
					String entityname = sheet.getRow(0).getCell(i).getStringCellValue();
					ArrayList<String> listofentities = new ArrayList<>();
					for(int j=3;j<=noofRows;j++)
					{
						if(sheet.getRow(j).getCell(i).getStringCellValue().equalsIgnoreCase("Yes"))
							listofentities.add(sheet.getRow(j).getCell(0).getStringCellValue());
					}
//					System.out.println(entityname+" :"+ listofentities);
					listofEntities.put(entityname,listofentities );
				}
				
				for (Entry<String, ArrayList<String>> entry : listofEntities.entrySet()) {
				    String key = entry.getKey();
				    Object value = entry.getValue();
				    
				    //get entityUId or WorkitemID
				    for(int i= 0; i<noOfColumns;i++)
				    {
				    	if(sheet.getRow(0).getCell(i).getStringCellValue().equalsIgnoreCase(key))
				    	{
//				    		System.out.println(key+" :"+sheet.getRow(2).getCell(i).getStringCellValue());
				    	}
				    }
//				    String[] sp = String.valueOf(value).split("\\[");
//				    String[] sp1 = sp[1].split("\\]");
//				    String[] sp2 = sp1[0].split(",");
//				    for(int i=0;i<sp2.length;i++)
//				    {
//				    	System.out.print(sp2[i]);
//				    }
				    System.out.println(key+ " :"+String.valueOf(value));
				   
				}
				
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	}

}
