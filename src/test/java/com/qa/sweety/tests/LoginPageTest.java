package com.qa.sweety.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.Assert;
import org.openqa.selenium.NoSuchElementException;

import com.qa.sweety.base.TestBase;
import com.qa.sweety.pages.EntryPage;
import com.qa.sweety.pages.LoginPage;



public class LoginPageTest extends TestBase{
	
	LoginPage loginPage;
	EntryPage entryPage;
	
	
	public LoginPageTest(){
		super();
	}
	
	
	
	@BeforeMethod
	
	public void setUp(){
		
		initialization();
		
	    loginPage=new LoginPage();
	}
	
	@Test(priority=1)
	
	public void pageTitleTest() throws InterruptedException {
		
		String actualTitle= loginPage.verifyPagetitle();
		String expectedTitle="Simple Sidebar - Start Bootstrap Template";
		Assert.assertEquals(actualTitle, expectedTitle);
		Thread.sleep(3000);
	}
	
	@Test(priority=2, dataProvider="Authentication")
	
	public void loginTest(String email, String password) throws Exception{
		loginPage.login(email,password);
		try {
		String actualValidMessage=loginPage.getMessage();
		String expectedValidMessage="Signed in successfully.";													
		Assert.assertEquals(actualValidMessage, expectedValidMessage);
		}catch(NoSuchElementException exe) {
			screenShot();
			System.out.println("Invalid Password");
		}
		Thread.sleep(3000);
		
	}
	

	@AfterMethod
	
	public void tearDown() {
		driver.quit();
	}
	
	
	 @DataProvider
	 
	    public Object[][] Authentication() throws Exception{
	 
	         Object[][] loginData = excelReader();
	 
	         return (loginData);
	 
		}

}
