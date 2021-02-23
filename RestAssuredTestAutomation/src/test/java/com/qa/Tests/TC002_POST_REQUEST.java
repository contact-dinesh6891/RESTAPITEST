package com.qa.Tests;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.utils.XLUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_POST_REQUEST {
	
	static String dataPath = System.getProperty("user.dir") + "\\src\\main\\java\\com\\qa\\data\\EmpData.xlsx";
	int i=1;
	int j =3;

	@Test(dataProvider = "empdataprovider")
	void getweatherDetails(String ename, String esalary, String eage) throws IOException {
		// specify base URI
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

		// Request Object
		RequestSpecification httpRequest = RestAssured.given();

		// Request Payload sending with post request
		JSONObject requestParam = new JSONObject();
		requestParam.put("name", ename);
		requestParam.put("Salary", esalary);
		requestParam.put("age", eage);

		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParam.toJSONString());

		// Response Object
		Response response = httpRequest.request(Method.POST, "/create");

		// Print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Reponse Body:" + responseBody);
		
		XLUtils.setcellData(dataPath, "empDataSheet", i, j, "PASS");

		i = i+1;
		
//		int statusCode = response.getStatusCode();
//
//		Assert.assertEquals(statusCode, 200);
//
//		// Success Code Validation
//		String succuessCode = response.jsonPath().get("status");
//		Assert.assertEquals(succuessCode, "success");
		

	}

	@DataProvider(name = "empdataprovider")
	String[][] getDataForEmp() throws IOException {
		
		//ReadData from Excel

		//String dataPath = System.getProperty("user.dir") + "\\src\\main\\java\\com\\qa\\data\\EmpData.xlsx";

		int rownum = XLUtils.getRowCount(dataPath, "empDataSheet");
		int colcount = XLUtils.getCellCount(dataPath, "empDataSheet", 1);

		String empData[][] = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {

			for (int j = 0; j < colcount; j++) {
				empData[i - 1][j] = XLUtils.getCellData(dataPath, "empDataSheet", i, j);

			}
		}

		return empData;

	}

}
