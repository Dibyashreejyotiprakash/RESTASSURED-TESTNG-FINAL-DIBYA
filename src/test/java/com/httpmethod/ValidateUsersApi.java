package com.httpmethod;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import com.aventstack.extentreports.Status;
import com.commonutility.*;
import com.initiate.Base;
import com.pojo.CreateUserPojo;
import com.relevantcodes.extentreports.LogStatus;


public class ValidateUsersApi extends Base
{
	
	@Test(priority=1, description="Validate all existing users details")
	public  void validateAllExistingUsersDetails() {
		test.log(Status.INFO,"Validate All Existing User Deatils");
		Response resp=RequestBuilder.get(EndPoint.alluser);
		APIVerficationUtility.validateResponseCode(resp, 200);
		APIVerficationUtility.validateResponseTime(resp,3000);
		test.log(Status.INFO, resp.getBody().asString());
	}
	
	@Test(priority=2, description= "Validate single user details")
	public void validateSingleUserDetails() {
		test.log(Status.INFO,"Validate Single User Deatils");
		Response resp = RequestBuilder.get(EndPoint.singleuser);
		APIVerficationUtility.validateResponseCode(resp, 200);
		String body = resp.getBody().asString();
		test.log(Status.INFO, body);	
		APIVerficationUtility.validateSingleJsonKey(resp, "data.email","janet.weaver@reqres.in");
	}
	
	@Test(priority=3, description = "Validate create single user")
	public void validateCreateSingleUser() {
		test.log(Status.INFO,"Validate Create User Deatils");	
		Response resp = RequestBuilder.post(JsonRequestBodyReader.readJson("createuser.json"),EndPoint.createsingleuser);
		APIVerficationUtility.validateResponseCode(resp, 201);
		test.log(Status.INFO, resp.getBody().asString());
	}
	
	@Test(priority=4, description = "Validate delete single user")
	public void validateDeleteUser() {
		test.log(Status.INFO,"Validate Delete User Deatils");
		Response resp = RequestBuilder.delete(EndPoint.deleteuser);
		APIVerficationUtility.validateResponseCode(resp, 204);
	}
	
	
	@Test(priority=5, description = "Validate create single user")
	public void validateCreateSingleUserWithPojo() {
		CreateUserPojo userpojo = new CreateUserPojo("dibya", "tester");
		test.log(Status.INFO,"Validate Create User Deatils");	
		Response resp = RequestBuilder.postWithPojo(EndPoint.createsingleuser);
		APIVerficationUtility.validateResponseCode(resp, 201);
		test.log(Status.INFO, resp.getBody().asString());
	}
}
