package com.qa.Tests;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;


public class TC01_Get_All_Employees extends TestBase {

	@BeforeMethod
	void getAllEmployees() throws InterruptedException {

		loggers.info("*********************Started TC01_Get_All_Employees*********************");
		// specify base URI
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

		// Request Object
		httpRequest = RestAssured.given();

		// Response Object
		response = httpRequest.request(Method.GET, "/employees");

		Thread.sleep(1000);
	}

	@Test
	void checkResponseBody() {

		loggers.info("*********************Checking Response Body*********************");
		String responseBody = response.body().asString();
		loggers.info("Response Body=>" + responseBody);
		Assert.assertTrue(responseBody != null);

	}

	@Test
	void checkStatusCode() {
		loggers.info("*********************Checking of StatusCode*********************");
		int statuCode = response.statusCode();
		loggers.info("Status Code=> " + statuCode);
		Assert.assertEquals(statuCode, 200);

	}

	@Test
	void checkResponseTime() {

		loggers.info("*********************Checking of Response time*********************");
		long responseTime = response.getTimeIn(TimeUnit.SECONDS);
		loggers.info("Response Time in Seconds =>" + responseTime);
		if (responseTime > 20)
			loggers.info("Response time is taken more than 20 Seconds");

		Assert.assertTrue(responseTime < 20);

	}

	@Test
	void checkStatusLine() {

		loggers.info("*********************Checking of Status Line*********************");
		String statusLine = response.getStatusLine();
		loggers.info("Status Line =>" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

	}

	@Test
	void checkContenttype() {

		loggers.info("*********************Checking of Content Type*********************");
		String contentType = response.header("Content-Type");
		loggers.info("Content-Type ==>" + contentType);
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");

	}

	@Test
	void checkserverType() {
		loggers.info("*********************Checking of Content Type*********************");
		String serverType = response.header("Server");
		loggers.info("Server type=>" + serverType);
		Assert.assertEquals(serverType, "[nginx/1.16.0]");

	}

	@Test
	void checkContentEncoding() {
		loggers.info("*********************Checking of Content Type*********************");
		String contentEncoding = response.header("Content-Encoding");
		loggers.info("content Encoding type=>" + contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");

	}

	@Test
	void checkContentLenght() {
		loggers.info("*********************Checking of Content Type*********************");
		String contentLength = response.header("Content-Length");
		loggers.info("Content Lenght =>" + contentLength);

		if (Integer.parseInt(contentLength) < 100) {
			loggers.info("Content Length is less than 100");
		}

		Assert.assertTrue(Integer.parseInt(contentLength) > 100);

	}
	
	@AfterClass
	void tearDown() {
		
		loggers.info("*********************Finished TC01_Get_All_Employees*********************");
	}

}
