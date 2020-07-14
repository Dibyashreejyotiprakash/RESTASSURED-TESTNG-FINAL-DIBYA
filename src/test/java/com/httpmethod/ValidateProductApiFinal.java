package com.httpmethod;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.commonutility.APIVerficationUtility;
import com.commonutility.JsonRequestBodyReader;
import com.commonutility.RequestBuilder;
import com.initiate.Base;

import io.restassured.response.Response;

public class ValidateProductApiFinal extends Base {

	String token="";
	String productid="";
	@Test(priority=1,description= "*********Validate SignIn to Product Api***********")
	public void validateSignIn() {
		
		Response resp = RequestBuilder.post(JsonRequestBodyReader.readJson("signinproductapi.json"),"/user/signin");
		APIVerficationUtility.validateResponseCode(resp, 200);
		APIVerficationUtility.validateResponseTime(resp,3000);
		test.log(Status.INFO, resp.getContentType());
		System.out.println("Token is -----------"+ resp.path("data.token"));
		token = resp.path("data.token");	
	}
	
	@Test(priority=2,description= "*********Validate Add Product***********")
	public void validateAddProduct() {
		
		Response resp = RequestBuilder.post(JsonRequestBodyReader.readJson("addproductbody.json"),"/api/v1/products","Authorization",token);
		System.out.println("Status code is ----"+ resp.getStatusCode());
		System.out.println("Response body is---"+ resp.getBody().asString());
	}
	
	@Test(priority=3, description="***********Validate Search Product with Id***********")
	public void validatSearchWithProductId() {
		
		Response resp = RequestBuilder.get("/api/v1/products/search/"+productid);
		System.out.println("Response body is--"+ resp.getBody().asString());
		System.out.println("Status code is ---"+ resp.getStatusCode());
		
	}
	
	@Test(priority=4, description="***********Validate Delete Product with Id***********")
	public void validateDeleteProduct() {
		
		Response resp= RequestBuilder.delete("/api/v1/products/"+productid);
		System.out.println("Status code is--"+ resp.getStatusCode());
	}
}
