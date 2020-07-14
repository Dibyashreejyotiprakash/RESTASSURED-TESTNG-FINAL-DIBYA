package com.commonutility;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.aventstack.extentreports.Status;
import com.initiate.Base;

import io.restassured.path.json.JsonPath;


public class ReadTestData extends Base{
	
	public String readJsonTestData(String key) {
		String testdata="";
		String jsontestdatafilepath = PropertyReader.readPropertyData("jsontestdatafilepath");
		try {
			File file= new File(jsontestdatafilepath);
			JsonPath path = new JsonPath(file);
			testdata = path.getString(key);
		}
		catch(Exception e) {
			test.log(Status.FAIL, e);
		}
		return testdata;
	}
	
	
	public static String readTestExcelData(String Sheetname,int rownum,int colnum) throws Exception 
	{
		String data="";
		String path = PropertyReader.readPropertyData("exceltestdatafilepath");
		try
		{
			FileInputStream fis=new FileInputStream(path);	
			Workbook wb=WorkbookFactory.create(fis);
			Sheet sh=wb.getSheet(Sheetname);
			Row row=sh.getRow(rownum);
			data=row.getCell(colnum).getStringCellValue();		
		}
		catch(Exception e)
		{
			test.log(Status.FAIL, e);
		}
		return data;
		
	}
	
}
