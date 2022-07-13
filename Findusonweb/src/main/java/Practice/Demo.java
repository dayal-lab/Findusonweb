package Practice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Demo 
{
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	String path;

//constructor of file location to store data in excel file
 Demo(String path)
 {
	 this.path = path;
 }
 
 
 //gets excel file's sheet name
 public int getRowCount (String sheetName) throws IOException
 {
	fi = new FileInputStream (path);
	workbook = new XSSFWorkbook (fi);
	sheet = workbook.getSheet(sheetName);
	int rowcount = sheet.getLastRowNum();
	workbook.close();
	fi.close();
	return rowcount;
}
 
 //gets sheet name and rownumber
 public int getCellCount (String sheetName, int rownum) throws IOException
 {
	fi = new FileInputStream (path);
	workbook = new XSSFWorkbook (fi);
	sheet = workbook.getSheet(sheetName);
	int cellcount = row.getLastCellNum();
	workbook.close();
	fi.close();
	return cellcount;
}
 
 //gets sheetname, rownumber, column
 public String getCellData(String sheetName, int rownum, int column) throws IOException
 {
	 fi = new FileInputStream (path);
	workbook = new XSSFWorkbook (fi);
	sheet = workbook.getSheet(sheetName);
	row = sheet.getRow(rownum);
	cell.getRow().getCell(column);
	
	DataFormatter formatter =  new DataFormatter();
	String data;
	try
	{
		data = formatter.formatCellValue(cell);
	}
	
	catch (Exception e)
	{
		data = "";
	}
	
	workbook.close();
	fi.close();
	return data;
 }
 
 
 public void setCellData (String sheetName, int rownum, int column, String data) throws IOException
 {
	 File xlfile = new File(path);
	 
	 if (!xlfile.exists()) //if file not exists then create new file
	 {
		 //then create new excel file
		 workbook = new XSSFWorkbook();
		 fo = new FileOutputStream (path);
		 workbook.write(fo);
	}
	 
	 //if file already exists then open that file by inputstream and workbook and proceed further 
	 fi = new FileInputStream (path);  
	 workbook = new XSSFWorkbook(fi);
	 
	 
	//After checking excel file's existent, it check sheet existent ,if sheet not exists then create new sheet in excel file
	 if(workbook.getSheetIndex(sheetName)==-1)  //if sheet present already, then its starts from "0", if not present then from "-1"
		 workbook.createSheet(sheetName);		// created new sheet in excel if sheet not present.
	 sheet= workbook.getSheet(sheetName);
	 
	 
	 //after sheet's existence in excel fiel, check rows present or not
	 if(sheet.getRow(rownum)==null)  //if row not exists then create new row
		 sheet.createRow(rownum);
	 row = sheet.getRow(rownum);
	 
	 cell  = row.createCell(column);
	 cell.setCellValue(data);
	 fo = new FileOutputStream (path);
	 workbook.write(fo);
	 workbook.close();
	 fi.close();
	 fo.close();
	 
 }
 
 
 
}
 
 
 
 
 
 
 
 
 