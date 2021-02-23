package com.qa.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveAuthProvider;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_GET_REQUEST {
	
	

	@Test
	void getweatherDetails() {
		// specify base URI
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

		//authentication
		PreemptiveBasicAuthScheme authscheme = new PreemptiveBasicAuthScheme();
		authscheme.setUserName("aaaa");
		authscheme.setPassword("ads332");
		RestAssured.authentication = authscheme;
		
		
		// Request Object
		RequestSpecification httpRequest = RestAssured.given();

		// Response Object
		Response response = httpRequest.request(Method.GET, "/employees/1");
		
		JsonPath jsonPath = response.jsonPath();
		
				// List winnerIds = get("/lotto").jsonPath().getList("lotto.winnders.winnerId");

		System.out.println(jsonPath.get("employee_name"));
		//Assert.assertEquals(jsonPath.get("employee_name"), "Tiger Nixon");
		
	
		
		
		
	}


}
