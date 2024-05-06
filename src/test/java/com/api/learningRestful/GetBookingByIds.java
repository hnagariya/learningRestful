package com.api.learningRestful;
import static io.restassured.RestAssured.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GetBookingByIds {
	@BeforeMethod
	public void set() {
		baseURI="https://restful-booker.herokuapp.com";
	}
	@Test
	public void getBookingByIds() {
		given()
		.when().log().all().get("/booking")
		.then().log().all().statusCode(200);
	}
	
	
	
	
	
	
	
	
	
	
	


}
