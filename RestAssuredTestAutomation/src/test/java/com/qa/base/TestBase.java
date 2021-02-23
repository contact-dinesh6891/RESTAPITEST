package com.qa.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeMethod;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {

	public static RequestSpecification httpRequest;
	public static Response	response;
	
	public String empId ="1";
	
	public Logger loggers;
	
	@BeforeMethod
	public void setUp() {
		loggers = Logger.getLogger(TestBase.class); //added logger
		//loggers = Logger.getLogger("TC01_Get_All_Employees"); //added logger
		
		PropertyConfigurator.configure(System.getProperty("user.dir") +"\\src\\main\\resources\\log4j.properties"); //added logger
		loggers.setLevel(Level.DEBUG);
	}

	
	
}
