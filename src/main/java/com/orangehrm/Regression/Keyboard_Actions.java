package com.orangehrm.Regression;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Keyboard_Actions {

	protected WebDriver driver = null;
	protected WebDriverWait wait = null;
	protected static final String url = "https://jqueryui.com/tooltip/";;

	@BeforeClass
	public void Setup() {
		WebDriverManager.edgedriver().setup();
		EdgeOptions options = new EdgeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new EdgeDriver(options);
		driver.navigate().to(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	}

	@Test(priority = -1, enabled = false, description = "tooltip.....")
	public void MouseActions_01() throws InterruptedException {
		// As there is frame, we have to navigate to frame
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector(".demo-frame")));

		// Text box field, where we mouse hover
		WebElement element = driver.findElement(By.id("age"));

		// Use action class to mouse hover on Text box field
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		WebElement toolTipElement = driver.findElement(By.cssSelector(".ui-tooltip"));

		// To get the tool tip text and assert
		String toolTipText = toolTipElement.getText();
		Assert.assertEquals("We ask for your age only for statistical purposes.", toolTipText);

	}

	@Test(priority = 1, enabled = false, description = "tooltip.....")
	public void KeyboardActions_01() throws InterruptedException {
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("https://demoqa.com/text-box");
		// Create object of the Actions class
		Actions actions = new Actions(driver);

		// Enter the Full Name
		WebElement fullName = driver.findElement(By.id("userName"));
		fullName.sendKeys("Mr.Peter Haynes");

		// Enter the Email
		WebElement email = driver.findElement(By.id("userEmail"));
		email.sendKeys("PeterHaynes@toolsqa.com");

		// Enter the Current Address
		WebElement currentAddress = driver.findElement(By.id("currentAddress"));

		currentAddress.sendKeys("43 School Lane London EC71 9GO");

		// Select the Current Address using CTRL + A
		actions.keyDown(Keys.CONTROL);
		actions.sendKeys("a");
		actions.keyUp(Keys.CONTROL);
		actions.build().perform();

		// Copy the Current Address using CTRL + C
		actions.keyDown(Keys.CONTROL);
		actions.sendKeys("c");
		actions.keyUp(Keys.CONTROL);
		actions.build().perform();

		// Press the TAB Key to Switch Focus to Permanent Address
		actions.sendKeys(Keys.TAB);
		actions.build().perform();

		// Paste the Address in the Permanent Address field using CTRL + V
		actions.keyDown(Keys.CONTROL);
		actions.sendKeys("v");
		actions.keyUp(Keys.CONTROL);
		actions.build().perform();

		// Compare Text of current Address and Permanent Address
		WebElement permanentAddress = driver.findElement(By.id("permanentAddress"));
		assertEquals(currentAddress.getAttribute("value"), permanentAddress.getAttribute("value"));

	}

	@Test(priority = 2, enabled = false, description = "tooltip.....")
	public void KeyboardActions_02() throws InterruptedException {
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("https://demoqa.com/text-box");
		// Create object of the Actions class
		Actions actions = new Actions(driver);

		// Enter the Full Name
		WebElement fullName = driver.findElement(By.id("userName"));
		// fullName.sendKeys("Mr.Peter Haynes");

		Action act = actions.keyDown(fullName, Keys.SHIFT).sendKeys("Mr Peter Haynes").keyUp(fullName, Keys.SHIFT)
				.doubleClick().contextClick().build();
		act.perform();

	}
	
	
	
	@Test(priority = 3, enabled = true, description = "tooltip.....")
	public void KeyboardActions_03() throws InterruptedException {
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("https://jqueryui.com/slider/");
		 try
	        {
	            /* create an object for the Actions class and pass the driver argument */
	            Actions action = new Actions(driver);
	            driver.switchTo().frame(0);
	            WebElement elem_slider = driver.findElement(By.cssSelector(".ui-slider-handle"));
	            Thread.sleep(2000);
	            action.clickAndHold(elem_slider).moveByOffset(40,0).release().perform();
	 
	            System.out.println("Drag & Drop test case successful\n");
	            
	        }
	        catch (Exception e)
	        {
	            System.out.println(e.getMessage());
	        }

	}
	
	
	
	@Test(priority = 4, enabled = true, description = "tooltip.....")
	public void KeyboardActions_04() throws InterruptedException {
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("https://www.globalsqa.com/demo-site/draganddrop/");
		 // switch to Frame 
		  driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='demo-frame lazyloaded']")));
		  
		  // source/from location 
		  WebElement drag1FromImage1 = driver.findElement(By.xpath("//*[@id='gallery']//img[contains(@alt,'The peaks of High Tatras')]"));
		  WebElement drag2FromImage2 = driver.findElement(By.xpath("//*[@id='gallery']//img[contains(@alt,'The chalet at the Green mountain lake')]"));
		  WebElement drag3FromImage3 = driver.findElement(By.xpath("//*[@id='gallery']//img[contains(@alt,'Planning the ascent')]"));
		  WebElement drag4FromImage4 = driver.findElement(By.xpath("//*[@id='gallery']//img[contains(@alt,'On top of Kozi kopka')]"));
		  
		  // target/to location
		  WebElement dropImagesTo = driver.findElement(By.xpath("//div[@id='trash']"));
		  
		  //Object of Actions class
		  Actions builder = new Actions(driver);
		  
		  //Building the series of actions
		  Action drop1Image1 = builder.clickAndHold(drag1FromImage1)  // without releasing clicks the the image source location 
		             .moveToElement(dropImagesTo) //moves the mouse to the middle of target location 
		             .release(dropImagesTo) //releases the mouse at the current mouse i.e, at target location 
		             .build();  // build all the above 3 actions 
		  
		  //Performing the built actions 
		  drop1Image1.perform(); 
		  
		  Action drop2Image2 = builder.clickAndHold(drag2FromImage2)
		             .moveToElement(dropImagesTo)
		             .release(dropImagesTo)
		             .build();  
		  drop2Image2.perform();
		  
		  Action drop3Image3 = builder.clickAndHold(drag3FromImage3)
		             .moveToElement(dropImagesTo)
		             .release(dropImagesTo)
		             .build();  
		  drop3Image3.perform();
		  
		  Action drop4Image4 = builder.clickAndHold(drag4FromImage4)
		             .moveToElement(dropImagesTo)
		             .release(dropImagesTo)
		             .build();  
		  drop4Image4.perform();
		  
		  Thread.sleep(5000);
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			// driver.close();
		}
	}

}
