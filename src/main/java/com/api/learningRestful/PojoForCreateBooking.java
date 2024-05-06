package com.api.learningRestful;

public class PojoForCreateBooking {
	
		 private String firstname;
		 private String lastname;
		 private double totalprice;
		 private boolean depositpaid;
		 Bookingdates bookingdates;
		 private String additionalneeds;


		 // Getter Methods 

		 public String getFirstname() {
		  return firstname;
		 }

		 public String getLastname() {
		  return lastname;
		 }

		 public double getTotalprice() {
		  return totalprice;
		 }

		 public boolean getDepositpaid() {
		  return depositpaid;
		 }

		 public Bookingdates getBookingdates() {
		  return bookingdates;
		 }

		 public String getAdditionalneeds() {
		  return additionalneeds;
		 }

		 // Setter Methods 

		 public void setFirstname(String firstname) {
		  this.firstname = firstname;
		 }

		 public void setLastname(String lastname) {
		  this.lastname = lastname;
		 }

		 public void setTotalprice(double totalprice) {
		  this.totalprice = totalprice;
		 }

		 public void setDepositpaid(boolean depositpaid) {
		  this.depositpaid = depositpaid;
		 }

		 public void setBookingDates(Bookingdates bd) {
		  this.bookingdates = bd;
		 }

		 public void setAdditionalneeds(String additionalneeds) {
		  this.additionalneeds = additionalneeds;
		 }
		}
		


