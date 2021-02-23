package com.qa.Tests;

import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.junit.AfterClass;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.utils.RestUtil;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC03_Create_Request extends TestBase {

	RequestSpecification httpsRequest;
	Response response;

	String empName = RestUtil.empName();
	String empAge = RestUtil.age();
	String empSalary = RestUtil.empSal();

	@BeforeMethod
	void createEmployee() throws InterruptedException {
		loggers.info("**********************Started TC03 Create Request*******************************************");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		// Request Object
		httpRequest = RestAssured.given();

		JSONObject requestParam = new JSONObject();
		requestParam.put("name", empName);
		requestParam.put("salary", empSalary);
		requestParam.put("age", empAge);

		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParam.toJSONString());

		// Response Object
		response = httpRequest.request(Method.POST, "/create");

		Thread.sleep(5000);

	}

	@Test
	void CheckResponseBody() {

		loggers.info(
				"*****************************verify response Body Data******************************************");

		String resonseBody = response.getBody().asString();
		loggers.info("Response Body => "+resonseBody);

		Assert.assertEquals(resonseBody.contains(empName), true);
		Assert.assertEquals(resonseBody.contains(empSalary), true);
		Assert.assertEquals(resonseBody.contains(empAge), true);

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
