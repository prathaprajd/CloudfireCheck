package com.orangehrm.mainSuites;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class MainSuite {
	
	public static void main(String[] args) {
		TestNG runner = new TestNG();
		List<String> suiteFiles = new ArrayList<>();
		suiteFiles.add(System.getProperty("user.dir") + File.separator + "testng.xml");
		runner.setTestSuites(suiteFiles);
		runner.run();

	}

}
