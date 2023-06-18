package com.orangehrm.Regression;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Dataproviders_Login2 {
	protected WebDriver driver = null;
	protected WebDriverWait wait = null;
	protected ReadExcel read_report = null;
	protected int value = 0;

	@DataProvider
	public String[][] getData() throws InvalidFormatException, IOException {
		ReadExcel read = new ReadExcel();

		return read.getCellData(
				System.getProperty("user.dir") + File.separator + "src\\test\\resources\\Credentials.xlsx",
				"credentials");

	}

	@BeforeSuite
	public void launchApp() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.j2store.org/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		wait = new WebDriverWait(driver, Duration.ofMillis(30000));

	}

	@BeforeMethod
	public void reports_gen() {
		read_report = new ReadExcel();

	}

	@Test(testName = "jsstore", dataProvider = "getData")
	public void testLogin(String username, String password) throws InterruptedException, IOException {
		

		driver.findElement(By.xpath("//li/a[contains(@href,'login')]")).click();

		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.xpath("(//button)[3]")).click();
		String title = driver.getTitle();
		System.out.println(title);
		if (title.contains("Login")) {
	//		read_report.generateExcelReports(System.getProperty("user.dir") + File.separator + "src\\test\\resources","Credentials.xlsx", 0, 1, 2, "FAIL");

		} else if (title.contains("Joomla Shopping Cart and eCommerce Extension. Create your online store today")) {
			Assert.assertTrue(driver.findElement(By.xpath("//li/a[contains(text(),'My Account ')]")).isDisplayed(),
					"verify My_Accounts");
			read_report.generateExcelReports(System.getProperty("user.dir") + File.separator + "src\\test\\resources\\","Credentials.xlsx", 0, 1, 2, "PASS");
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//li/a[contains(text(),'My Account ')]")))
					.moveToElement(driver
							.findElement(By.xpath("(//ul[@class='dropdown-menu'])[8]//*[contains(text(),'Logout')]")))
					.click().build().perform();

		}

	}

	@AfterSuite
	public void closeBrowser() {
		driver.quit();
	}
}