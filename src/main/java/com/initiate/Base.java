package com.initiate;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.commonutility.PropertyReader;

import io.restassured.RestAssured;


public class Base {
    public static ExtentHtmlReporter extentHtmlReporter;
	public static ExtentTest test;
	public static ExtentReports extentReports;
	
	
    @BeforeClass
    public void baseUriSetUp()
    {
    	
    	extentHtmlReporter = new ExtentHtmlReporter("./Reports/extentreport.html");
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentHtmlReporter);
		test = extentReports.createTest(getClass().getSimpleName());
		extentHtmlReporter.config().setDocumentTitle("*** Dibya - API AUTOMATION ***");
		extentHtmlReporter.config().setReportName("DIBYASHREE JYOTI PRAKASH");
		extentHtmlReporter.config().setTheme(Theme.DARK);
		
		extentReports.attachReporter(extentHtmlReporter);
		extentReports.setSystemInfo("Environment", "QA");
		extentReports.setSystemInfo("Author", "Dibyashree Jyoti");
		
		
		
		String environment = getEnvironment();
		String apiname= getApiName();
		
		if(apiname.equalsIgnoreCase("productapi")) {
			if(environment.equalsIgnoreCase("dev")) {
				RestAssured.baseURI = PropertyReader.readPropertyData("productdevbaseuri");
			}else if(environment.equalsIgnoreCase("qa")) {
				RestAssured.baseURI = PropertyReader.readPropertyData("productqabaseuri");
			}else if(environment.equalsIgnoreCase("stage")) {
				RestAssured.baseURI = PropertyReader.readPropertyData("productstagebaseuri");
			}else if(environment.equalsIgnoreCase("prod")) {
				RestAssured.baseURI = PropertyReader.readPropertyData("productprodbaseuri");
			}	
		}
		else if(apiname.equalsIgnoreCase("reqresapi")) {
			if(environment.equalsIgnoreCase("dev")) {
				RestAssured.baseURI = PropertyReader.readPropertyData("reqresdevbaseuri");
			}else if(environment.equalsIgnoreCase("qa")) {
				RestAssured.baseURI = PropertyReader.readPropertyData("reqresqabaseuri");
			}else if(environment.equalsIgnoreCase("stage")) {
				RestAssured.baseURI = PropertyReader.readPropertyData("reqresstagebaseuri");
			}else if(environment.equalsIgnoreCase("prod")) {
				RestAssured.baseURI = PropertyReader.readPropertyData("reqresprodbaseuri");
			}	
		}
		else {
			System.out.println("Api name is not found");
		}
    }
    	
    /**
     * 
     * Get Environment Name from property file
     */
    public String getEnvironment()
    {
        String environment = PropertyReader.readPropertyData("env");
        return environment;
    }
    
    /**
     * 
     * Get Api Name from property file
     */
    public String getApiName() {
    	String apiname = PropertyReader.readPropertyData("apiname");
        return apiname;
    }

    
    
    

    @AfterClass
    public void CleanUp() 
    {  
    	    extentReports.flush();
	} 	

}
