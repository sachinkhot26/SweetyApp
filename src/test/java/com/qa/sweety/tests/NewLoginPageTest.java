package com.qa.sweety.tests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.sweety.base.TestBase;
import com.qa.sweety.pages.EntryPage;
import com.qa.sweety.pages.LoginPage;

public class NewLoginPageTest extends TestBase{
	
	
	LoginPage loginPage;
	EntryPage entryPage;
	public static String expectedValidMessage=null;
	
	
	public NewLoginPageTest(){
		super();
	}
	
	
	
	@BeforeMethod
	
	public void setUp(){
		
		initialization();
		
	    loginPage=new LoginPage();
	}
	

	
	@Test(priority=1)
	
	public void validLoginTest() throws Exception {
		
		String methodName = new Object() {}
	      .getClass()
	      .getEnclosingMethod()
	      .getName();
	      
		HashMap<Object, Object>credentials=excelReaderNew(methodName);
		loginPage.login(credentials.get("email").toString(),credentials.get("password").toString());
		String actualValidMessage=loginPage.getMessage();
		expectedValidMessage="Signed in successfully.";													
		Assert.assertEquals(actualValidMessage, expectedValidMessage);
	}
	
	@Test(priority=2)
	public void invalidPasswordTest() throws InterruptedException, Exception {
		String methodName = new Object() {}
	      .getClass()
	      .getEnclosingMethod()
	      .getName();
	      
		HashMap<Object, Object>credentials=excelReaderNew(methodName);
		loginPage.login(credentials.get("email").toString(),credentials.get("password").toString());
		String actualInvalidValidMessage=loginPage.getInvalidMessage();
		Assert.assertEquals(actualInvalidValidMessage, expectedValidMessage);
		
	}
	@Test(priority=3)
	public void invalidEmailTest() throws Exception {
		String methodName = new Object() {}
	      .getClass()
	      .getEnclosingMethod()
	      .getName();
	     
		HashMap<Object, Object>credentials=excelReaderNew(methodName);
		loginPage.login(credentials.get("email").toString(),credentials.get("password").toString());
		String actualInvalidValidMessage=loginPage.getInvalidMessage();
		Assert.assertEquals(actualInvalidValidMessage, expectedValidMessage);
		
	}

	@AfterMethod
	
	public void tearDown() {
		driver.quit();
	}
	
	

	
	
	

}
