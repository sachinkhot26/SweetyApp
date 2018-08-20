package com.qa.sweety.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class Login {
	public static WebDriver driver;
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/drivers/chromedriver");
		driver=new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		
		driver.get("http://damp-sands-8561.herokuapp.com/");
		driver.findElement(By.id("user_email")).sendKeys("sachinkhot26@gmail.com");
		driver.findElement(By.id("user_password")).sendKeys("codetheoryio123");
		driver.findElement(By.name("commit")).click();
		
		System.out.println(driver.findElement(By.xpath("//div[@class='alert alert-info fade in']")).isDisplayed());
		
//		String message=driver.findElement(By.xpath("//.[contains(text(),'A part following '@' should not contain the symbol '@'.')]")).getText();
//		System.out.println(message);
		
		
	}

}
