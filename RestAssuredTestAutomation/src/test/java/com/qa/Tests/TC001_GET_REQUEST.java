package com.qa.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_GET_REQUEST {

	@Test
	void getweatherDetails() {
		// specify base URI
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		// Request Object
		RequestSpecification httpRequest = RestAssured.given();

		// Response Object
		Response response = httpRequest.request(Method.GET, "/employees");

		// Print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Reponse Body:" + responseBody);

		int statusCode = response.getStatusCode();

		Assert.assertEquals(statusCode, 200);

		Assert.assertEquals(responseBody.contains("Tiger Nixo"), true);

		Headers allHeader = response.headers();
		for (Header header : allHeader) {
			System.out.println(header.getName() + "  :  " + header.getValue());
		}
		String statusLine = response.getStatusLine();
		System.out.println(statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

		String contentType = response.header("Content-Type");
		System.out.println("Content Type:" + contentType);
		Assert.assertEquals(contentType, "application/json");

		String rresponse = response.header("Response");
		System.out.println("rresponse:" + rresponse);
		Assert.assertEquals(rresponse, "200");

		String ServerInfo = response.header("Server");
		System.out.println("ServerInfo:" + ServerInfo);
		Assert.assertEquals(ServerInfo, "nginx/1.16.0");

	}

}
