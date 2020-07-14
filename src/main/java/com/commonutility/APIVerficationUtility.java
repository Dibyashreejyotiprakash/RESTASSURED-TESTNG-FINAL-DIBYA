package com.commonutility;

import static org.testng.Assert.assertEquals;

import org.json.JSONArray;
import org.json.JSONObject;

import com.aventstack.extentreports.Status;
import com.initiate.Base;
import com.jayway.jsonpath.JsonPath;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;

public class APIVerficationUtility extends Base{
	
	public static void validateResponseCode(Response resp,int expectedstatuscode) {
		try {
			assertEquals(resp.getStatusCode(),expectedstatuscode);
			test.log(Status.PASS, "Status code pass and Status code is :"+ resp.getStatusCode());
		}
		catch(Exception e) {
			test.log(Status.FAIL,"Status code validation Failed "+ resp.getStatusCode() );
		}
	}
	
	public static void validateResponseTime(Response resp,int expresponsetime) {
		try {
			if(resp.getTime()<=expresponsetime) {
				test.log(Status.PASS, "Response time is :"+ resp.getTime());
			}else {
				test.log(Status.FAIL, "Resonse time is "+ resp.getTime());
			}
			
		}
		catch(Exception e) {
			test.log(Status.FAIL,"Response time validation Failed "+ resp.getTime());
		}
	}
	
	public static void validateJsonKeyWithJsonArray(Response resp,String key, String expectedvalue) {
		try {
			JSONArray array = new JSONArray(resp.getBody().asString());
			for(int i=0; i<array.length();i++) {
				JSONObject obj = array.getJSONObject(i);
				test.log(Status.PASS, "Validetd values are  " + obj.get(key));
				assertEquals(obj.get(key), expectedvalue);
			}
		}
		catch(Exception e) {
			test.fail(e);
		}
	}
	
	public static void validateSingleJsonKey(Response resp,String key,String expectedvalue) {
		try {
			
			assertEquals(resp.path(key).toString(),expectedvalue);
			test.log(Status.PASS,"Expected key is : "+resp.path(key).toString()+" and found " +expectedvalue);
		}
		catch(Exception e) {
			test.fail(e);
		}
	}
	
	public static void validateSingleJsonKey(Response resp,String key,int expectedvalue) {
		try {
			assertEquals(resp.path(key).toString(),expectedvalue);
			test.log(Status.PASS,"Expected key is : "+resp.path(key).toString()+" and found " +expectedvalue);
		}
		catch(Exception e) {
			test.fail(e);
		}
	}
	
	
	
	

}
