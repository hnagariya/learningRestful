package com.api.learningRestful;

import static io.restassured.RestAssured.baseURI;
import java.io.File;
import java.io.FileReader;
import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

public class CreateToken {
	@BeforeMethod
	public void set() {
		baseURI="https://restful-booker.herokuapp.com";
	}
	@Test
	public void createToken(ITestContext context) {
		PojoForCreateTokenRequestBody data=new PojoForCreateTokenRequestBody();
		data.setUsername("admin");
		data.setPassword("password123");
		//ObjectMapper om=new ObjectMapper ();
		
		
		given().contentType("application/json")
		.when().log().all().body(data).post("/auth")
		.then().log().all().statusCode(200);
		
		Response response=given().contentType("application/json")
				.when().body(data).post("/auth");
		System.out.println("response is:  "+response.body().asString());
		String token=response.body().jsonPath().getString("token");
		System.out.println("Token is: "+token);
		context.setAttribute("Token", token);
	}
	@Test
	public void jsonNodeComparision() throws JsonMappingException, JsonProcessingException {
	
	        String json1 = "{\"name\":\"John\", \"age\":30}"; 
	        String json2 = "{\"age\":30, \"name\":\"John\"}"; 
	 
	        ObjectMapper mapper = new ObjectMapper(); 
	        JsonNode node1 = mapper.readTree(json1); 
	        JsonNode node2 = mapper.readTree(json2); 
	 
	        boolean isEqual = node1.equals(node2); 
	        System.out.println("JSON objects are equal: " + isEqual); 
	        System.out.println(json1.equals(json2));
	        Assert.assertEquals(json1, json2);
	        
	    } 
	

}
