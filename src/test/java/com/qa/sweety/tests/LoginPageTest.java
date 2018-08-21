package com.qa.sweety.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import java.io.IOException;
import com.qa.sweety.base.TestBase;
import com.qa.sweety.pages.EntryPage;
import com.qa.sweety.pages.LoginPage;



public class LoginPageTest extends TestBase{
	
	LoginPage loginPage;
	EntryPage entryPage;
	public static String expectedValidMessage=null;
	
	
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
	
//	@Test(priority=2, dataProvider="Authentication")
//	
//	public void loginTest(String email, String password) throws Exception{
//		loginPage.login(email,password);
//		try {
//		String actualValidMessage=loginPage.getMessage();
//		expectedValidMessage="Signed in successfully.";													
//		Assert.assertEquals(actualValidMessage, expectedValidMessage);
//		
//		}catch(NoSuchElementException exe) {
//			screenShot();
//			String actualInvalidValidMessage=loginPage.getInvalidMessage();
//			//String expectedInvalidValidMessage="Invalid email or password.";													
//			Assert.assertEquals(actualInvalidValidMessage, expectedValidMessage);
//			
//			
//		}
//		Thread.sleep(3000);
//		
//	}
	

	@Test(priority=2)
	
	public void validLoginTest() throws Exception {
		excelReader(1);
		String email= data[0][0];
		String password=data[0][1];
		loginPage.login(email,password);
		String actualValidMessage=loginPage.getMessage();
		expectedValidMessage="Signed in successfully.";													
		Assert.assertEquals(actualValidMessage, expectedValidMessage);
	}
	
	@Test(priority=3)
	public void invalidPasswordTest() throws InterruptedException, Exception {
		excelReader(2);
		String email= data[0][0];
		String password=data[0][1];
		loginPage.login(email,password);;
		String actualInvalidValidMessage=loginPage.getInvalidMessage();
		Assert.assertEquals(actualInvalidValidMessage, expectedValidMessage);
		
	}
	@Test(priority=4)
	public void invalidEmailTest() throws InterruptedException, IOException {
		excelReader(3);
		String email= data[0][0];
		String password=data[0][1];
		loginPage.login(email,password);
		String actualInvalidValidMessage=loginPage.getInvalidMessage();
		Assert.assertEquals(actualInvalidValidMessage, expectedValidMessage);
		
	}

	@AfterMethod
	
	public void tearDown() {
		driver.quit();
	}
	
	


}
