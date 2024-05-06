package com.api.learningRestful;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;

public class CreateBooking {
	@BeforeMethod
	public void set() {
		baseURI = "https://restful-booker.herokuapp.com";
	}

	@Test
	public void createBooking(ITestContext context) {
		PojoForCreateBooking data = new PojoForCreateBooking();
		data.setFirstname("Scott");
		data.setLastname("Holl");
		data.setTotalprice(200.0);
		data.setDepositpaid(true);
		data.setAdditionalneeds("breakfast");

		Bookingdates bd = new Bookingdates();
		bd.setCheckin("2018-01-01");
		bd.setCheckout("2018-01-4");

		data.setBookingDates(bd);

//	given().contentType("application/json")
//	.when().log().all().body(data).post("/booking")
//	.then().log().all().statusCode(200);

		Response response = given().contentType("application/json").when().body(data).post("/booking");
		System.out.println("response is:  " + response.body().asString());
		String bookingId = response.body().jsonPath().getString("bookingid");
		System.out.println("bookingId is: " + bookingId);
		context.setAttribute("bookingId", bookingId);
	}

	@Test
	public void creatBookingByJsonFile(ITestContext context) {
		ObjectMapper om = new ObjectMapper();
		try {
			JsonNode jn = om.readTree(new File("./createBooking.json"));
			Response response = given().contentType("application/json").when().body(jn).post("/booking");
			System.out.println("response is:  " + response.body().asString());
			String bookingid = response.body().jsonPath().getString("bookingid");
			System.out.println("bookingid is: " + bookingid);
			context.setAttribute("bookingid", bookingid);
			System.out.println("BookingId is;  " + context.getAttribute("bookingid"));
			given().contentType("application/json").when().log().all()
					.get("/booking/{id}", context.getAttribute("bookingid")).then().log().all().statusCode(200);

			Assert.assertEquals(response.statusCode(), 200);
			Assert.assertEquals(response.body().jsonPath().getString("booking.firstname"), "Nancy");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void creatBookingByJsonFileUsingReadValue(ITestContext context) {
		
		ObjectMapper om = new ObjectMapper();
		try {
			PojoForCreateBooking data = new PojoForCreateBooking();
			data = om.readValue(new File("./createBooking.json"), PojoForCreateBooking.class);

			String s = om.writeValueAsString(data);
			Response response = given().contentType("application/json").when().body(s).post("/booking");
			System.out.println("response is:  " + response.body().asString());
			String bookingid = response.body().jsonPath().getString("bookingid");
			System.out.println("bookingid is: " + bookingid);
			context.setAttribute("bookingid", bookingid);
			System.out.println("BookingId is;  " + context.getAttribute("bookingid"));
			given().contentType("application/json").when().log().all()
					.get("/booking/{id}", context.getAttribute("bookingid")).then().log().all().statusCode(200)
					.assertThat().body("lastname", equalTo(data.getLastname()));

			Assert.assertEquals(response.statusCode(), 200);
			Assert.assertEquals(response.body().jsonPath().getString("booking.firstname"), "Nancy");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getBookingById(ITestContext context) {
		System.out.println("bookingId: " + context.getAttribute("bookingid"));
		given().contentType("application/json").when().log().all().get("/booking/{id}", 3211).then().log().all()
				.statusCode(200);

	}

}
