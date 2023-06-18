package com.orangehrm.Regression;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Auto_IT_File_Upload {
	protected static WebDriver driver = null;
	protected Wait<WebDriver> wait = null;

	@BeforeSuite
	public void openBrowser() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);

	}
@BeforeMethod
	public void setUp() {
		driver.manage().window().maximize();
		wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofSeconds(1))
				.ignoring(Exception.class);
		driver.navigate().to("https://demo.guru99.com/test/autoit.html");
	}


@Test
	public void fileUpload() throws IOException, InterruptedException {
	
	JavascriptExecutor js = (JavascriptExecutor)driver;
	js.executeScript("scrollBy(0,900);", "");
	
	driver.switchTo().frame("JotFormIFrame-72320244964454");
	//	driver.findElement(By.id("postjob")).click();	
	
	    driver.findElement(By.id("input_3")).sendKeys("Gaurav");                                 					
	    driver.findElement(By.id("input_4")).sendKeys("test.test@gmail.com");	
	    Actions action = new Actions(driver);
	    action.moveToElement(driver.findElement(By.id("input_4"))).sendKeys(Keys.TAB,Keys.ENTER).build().perform();
	    Thread.sleep(5000);
	    // below line execute the AutoIT script .
	     Runtime.getRuntime().exec("C:\\Users\\admin\\Desktop\\frameworks\\FileUpload.exe");		
	    driver.findElement(By.id("input_6")).sendKeys("AutoIT in Selenium");					
	    driver.findElement(By.id("input_2")).click();
	}
}
