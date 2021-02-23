package com.qa.Tests;

import java.io.IOException;

import com.qa.utils.XLUtils;

public class WriteData{
		


	
	
	
	public static void main(String[] args) throws IOException {
		

		//ReadData from Excel

		String dataPath = System.getProperty("user.dir") + "\\src\\main\\java\\com\\qa\\data\\EmpData.xlsx";

		int rownum = XLUtils.getRowCount(dataPath, "empDataSheet");
	
		int colcount = XLUtils.getCellCount(dataPath, "empDataSheet", 1);

		XLUtils.setcellData(dataPath, "empDataSheet", 3, 0, "Charamshuukh");
		//String empData[][] = new String[rownum][colcount];

//		for (int i = 1; i <= rownum; i++) {
//
//			for (int j = 0; j < colcount; j++) {
//				empData[i - 1][j] = XLUtils.getCellData(dataPath, "empDataSheet", i, j);
//
//			}
//		}

//		String[][] empData = { { "name1", "3000","20" }, { "name2", "5000","40" }, {"name3", "4000","30" } };
	


	}

}
