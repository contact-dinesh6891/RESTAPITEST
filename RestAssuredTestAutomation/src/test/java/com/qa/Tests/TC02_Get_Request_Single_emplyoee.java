package com.qa.Tests;

import org.junit.AfterClass;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC02_Get_Request_Single_emplyoee extends TestBase {

	RequestSpecification httpsRequest;
	Response response;

	@BeforeMethod
	void getEmployeeData() throws InterruptedException {

		loggers.info(
				"********************Started TC02_Get_Single_EmployeeData*****************************************");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		// Request Object
		httpRequest = RestAssured.given();

		// Response Object
		response = httpRequest.request(Method.GET, "/employee/1");

		Thread.sleep(3000);

	}

	@Test

	void checkResponseBody() {

		loggers.info("**************************Check for EmployeeID Exist*******************************************");
		String responseBody = response.getBody().asString();
		loggers.info(responseBody);
		Assert.assertEquals(responseBody.contains(empId), true);

	}

	@AfterClass
	void tearDown() {

		loggers.info("*********************Finished TC01_Get_All_Employees*********************");
	}
}
