package com.httpmethod;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class ValidateProductApi {
	
	public String token ="";
	public int productid;
	@BeforeMethod
	public void buildBaseUri() {
		RestAssured.baseURI = "http://139.59.91.96:3000/";
	}
	
	@Test(priority=1,description= "*********Validate SignIn***********")
	public void validateSignIn() {
		
		Response resp = 
				given().header("Content-Type","application/json")
				.body("{\r\n" + 
						"\"email\": \"jatinvsharma@gmail.com\",\r\n" + 
						"\"password\": \"123123123\"\r\n" + 
						"}")
				.when()
				.post("/user/signin");
		
		System.out.println("Status code is--"+ resp.getStatusCode());
		System.out.println("Statu line is --"+ resp.getStatusLine());
		System.out.println("Body is "+ resp.getBody().asString());
		System.out.println("Token is --"+ resp.path("data.token"));
		token = resp.path("data.token");
				
	}
	
	@Test(priority=2,description= "*********Validate Add Product***********")
	public void validateAddProduct() {
		
		Response resp= given()
				.header("Authorization",token).header("Content-Type","application/json")
				.body("{\r\n" + 
						"\"prod_name\": \"Product Name12\",\r\n" + 
						"\"prod_desc\": \"Hello world product new detailst\",\r\n" + 
						"\"prod_price\": 101\r\n" + 
						"}")
				.given().post("/api/v1/products");
		
		System.out.println("Status code is --"+ resp.getStatusCode());
		System.out.println("Status line is ---"+ resp.getStatusLine());
		System.out.println("Response body is ---"+ resp.getBody().asString());
		System.out.println("Product id is ---"+ resp.path("data.id"));
		productid= resp.path("data.id");
	}
	
	@Test(priority=3,description="Validate search product by id")
	public void validateSearchProductWithId() {
Response resp =
				
				given()
				.header("Authorization",token).header("Content-Type","application/json")
				.when().get("/api/v1/products/search/"+productid);

				System.out.println("Status code is --"+ resp.getStatusCode());
				System.out.println("Status line is ---"+ resp.getStatusLine());
				System.out.println("Response body is ---"+ resp.getBody().asString());
				System.out.println("Product id is ---"+ resp.path("data.id"));
	}
	
	@Test(priority=4,description="Validate Delete product id")
	public void validateDeleteProduct() {
		Response resp =
				
				given()
				.header("Authorization",token).header("Content-Type","application/json")
				.when().delete("/api/v1/products/"+productid);
		System.out.println("Status code is --"+ resp.getStatusCode());
		System.out.println("Status line is ---"+ resp.getStatusLine());
	}
}
