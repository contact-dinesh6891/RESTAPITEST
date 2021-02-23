package com.qa.utils;

import org.apache.commons.lang3.RandomStringUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class RestUtil {

	public static String empName() {

		String generateString = RandomStringUtils.randomAlphabetic(1);
		return ("Dinesh" + generateString);

	}

	public static String empSal() {

		String generateNumber = RandomStringUtils.random(5);
		return generateNumber;

	}

	public static String age() {

		String generatedString = RandomStringUtils.randomNumeric(2);
		return generatedString;
		

	}

}
