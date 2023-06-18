package com.orangehrm.Regression;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
/*
public class Login_02_AUTOIT {

	String downloadPath=System.getProperty("user.dir");
	
	public void test() {
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
	       // Set ChromePref and pass the download folder path with key
	        chromePrefs.put("profile.default_content_settings.popups", 0);
	        chromePrefs.put("download.default_directory", downloadPath);
	        ChromeOptions options=new ChromeOptions();

	        options.setExperimentalOption("prefs", chromePrefs);
	        

	        WebDriver driver=new ChromeDriver(options);
	        driver.get("https://www.ilovepdf.com/pdf_to_word");
	        driver.manage().window().maximize();

	        driver.findElement(By.cssSelector("a[id='pickfiles']")).click();
	        Thread.sleep(3000);
	       //autoit exe software for selecting file
	        Runtime.getRuntime().exec("C:\\Users\\PrajjawalK\\Music\\check\\Fileupload.exe");

	        Thread.sleep(2000);
	        driver.findElement(By.cssSelector("span[id='processTaskTextBtn']")).click();
	        WebDriverWait wait=new WebDriverWait(driver,30);
	        //its wait till page is totally loaded
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[id='pickfiles']")));

	        driver.findElement(By.cssSelector("a[id='pickfiles']")).click();
	        Thread.sleep(5000);
	        File f=new File(downloadPath+"/visit.docx");
	        if(f.exists())
	        {
	            Assert.assertTrue(f.exists());
	            if(f.delete())
	                System.out.println("file deleted");
	        }
	    }
	}
	

}
		*/