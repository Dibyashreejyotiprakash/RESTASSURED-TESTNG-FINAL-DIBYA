package com.commonutility;

import static io.restassured.RestAssured.given;

import com.aventstack.extentreports.Status;
import com.commonutility.PropertyReader;
import com.commonutility.ReadTestData;
import com.initiate.Base;
import com.pojo.CreateUserPojo;

import io.restassured.response.Response;

public class RequestBuilder extends Base{
	
	
	public static Response resp;
	public static Response get(String endpoint) {
		
		try {
			resp = given()
					.header("Content-Type",PropertyReader.readPropertyData("contenttype"))
					.when()
					.get(endpoint);
		}
		catch(Exception e) {
			test.log(Status.FAIL,e);
		}
			return resp;
	}
	
	public static Response post(String requestbody,String endpoint) {
		try {
			resp = given().
					header("Content-Type",PropertyReader.readPropertyData("contenttype"))
					.body(requestbody)
					.when().post(endpoint);
		}
		catch(Exception e) {
			test.log(Status.FAIL,e);
		}
		return resp;
	}
	
	public static Response post(String requestbody,String endpoint,String key,String value) {
		try {
			resp = given().header("Content-Type",PropertyReader.readPropertyData("contenttype"))
					.header(key,value)
					.body(requestbody)
					.when().post(endpoint);
		}
		catch(Exception e) {
			test.log(Status.FAIL,e);
		}
		return resp;
	}
	
	public static Response delete(String endpoint) {
		try {
			resp = given()
					.when()
					.delete(endpoint);
		}
		catch(Exception e) {
			test.log(Status.FAIL,e);
		}
				return resp;
	}
	
	public static Response postWithPojo(String endpoint) {
		CreateUserPojo userpojo = new CreateUserPojo("dibya","tester");
		try {
			resp = given().header("Content-Type",PropertyReader.readPropertyData("contenttype"))
					.body(userpojo)
					.when().post(endpoint);
		}
		catch(Exception e) {
			test.log(Status.FAIL,e);
		}
				return resp;
	}
	
	

}
