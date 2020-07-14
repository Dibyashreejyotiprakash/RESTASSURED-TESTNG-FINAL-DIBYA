package com.commonutility;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {
	
	/*
	 * Reading Property file test data
	 */
	public static String readPropertyData(String propertykey) {
		String propertyvalue="";
		try {
			FileInputStream  fis = new FileInputStream("src\\main\\resources\\PropertyFile\\commondata.properties");
			Properties prop = new Properties();
			prop.load(fis);
			propertyvalue=prop.getProperty(propertykey);
			
		}
		catch(Exception e) {
			System.out.println("Read property failed due to "+ e);
		}
		return propertyvalue;
	}

}
