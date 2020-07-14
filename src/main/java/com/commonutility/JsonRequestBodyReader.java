package com.commonutility;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;

public class JsonRequestBodyReader {
	

	/*
	 * Reading JSON Data
	 */
	public static String readJson(String filename) {
		String requestbody="";
		try {
		FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\JsonData\\"+filename));
		requestbody= IOUtils.toString(fis,"UTF-8");
		}
		catch(Exception e) {
			System.out.println("Read Json failed due to "+ e);
		}
		return requestbody;
	}

}
