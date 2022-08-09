package dataobjects;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.*;

			public class ExcelFunction {

				public static String ExcelFileLoc = System.getProperty("user.dir")+ File.separator + "src" + File.separator + "test" + File.separator	+ "resources" + File.separator + "testdata" + File.separator + "Excel" +File.separator +"Excel.xlsx";
				public static void MultiValuedField(String toolname)
				{
					try{
						FileInputStream	 fis = new FileInputStream(new File(ExcelFileLoc));
			XSSFWorkbook workbook = new XSSFWorkbook (fis);
			XSSFSheet sheet = workbook.getSheet(toolname);
			int noOfColumns = sheet.getRow(0).getLastCellNum();
			int noofRows = sheet.getPhysicalNumberOfRows();
			for(int i= 1; i<noOfColumns;i++)
			{
				String entityname = sheet.getRow(0).getCell(i).getStringCellValue();
				ArrayList<String> listofentities = new ArrayList<>();
				for(int j=3;j<noofRows;j++)
				{
					if(sheet.getRow(j).getCell(i).getStringCellValue().equalsIgnoreCase("Yes"))
						listofentities.add(sheet.getRow(j).getCell(0).getStringCellValue());
				}
				
			}
			
		}
		catch(Exception e)
		{
			
		}
	}

}
