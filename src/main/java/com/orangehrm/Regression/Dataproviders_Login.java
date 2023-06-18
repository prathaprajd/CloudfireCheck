package com.orangehrm.Regression;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Dataproviders_Login {
	protected WebDriver driver = null;
	protected WebDriverWait wait= null;

	@BeforeSuite
	public void launchApp() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.j2store.org/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		wait = new WebDriverWait(driver,Duration.ofMillis(30000));
		// launch the app... ten lines of code

	}

	@DataProvider
	public Object[][] getData() {

		Object[][] data = new Object[3][2];
		data[0][0] = "cloudfire";
		data[0][1] = "cloudfire";
		data[1][0] = "cloudfire";
		data[1][1] = "cloudfire1";
		data[2][0] = "cloudfire";
		data[2][1] = "cloudfire";

		return data;

	}

	@Test(testName = "jsstore", dataProvider = "getData")
	public void testLogin(String username, String password) throws InterruptedException {
		driver.findElement(By.xpath("//li/a[contains(@href,'login')]")).click();
		
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.xpath("(//button)[3]")).click();
		String title=driver.getTitle();
		System.out.println(title);
		if (title.contains("Login")) {
			System.out.println("Login Failed");
			
		}else if(title.contains("Joomla Shopping Cart and eCommerce Extension. Create your online store today")) {
			Assert.assertTrue(driver.findElement(By.xpath("//li/a[contains(text(),'My Account ')]")).isDisplayed(),"verify My_Accounts");
			
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//li/a[contains(text(),'My Account ')]"))).moveToElement(driver.findElement(By.xpath("(//ul[@class='dropdown-menu'])[8]//*[contains(text(),'Logout')]"))).click().build().perform();
			
		}
		
		
		
		
		
	}
	
	@AfterSuite
	public void closeBrowser() {
	driver.quit();
}
}
