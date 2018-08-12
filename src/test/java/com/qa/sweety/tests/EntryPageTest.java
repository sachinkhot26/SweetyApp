package com.qa.sweety.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import com.qa.sweety.base.TestBase;
import com.qa.sweety.pages.EntryPage;
import com.qa.sweety.pages.LoginPage;

public class EntryPageTest extends TestBase {

	LoginPage loginPage;
	EntryPage entryPage;
	EntryPageTest(){
		super();
	}
	

	@BeforeMethod
	
	public void setUp(){
		
		initialization();
		
	    loginPage=new LoginPage();
	    entryPage=new EntryPage();
	}
	
	@Test(priority=1)
	public void verifyEntry() throws Exception {
		loginPage.login(prop.getProperty("email"),prop.getProperty("password"));
		entryPage.enterGlucoseLevel();
		screenShot();
	}
	

	@AfterMethod
	
	public void tearDown() {
		driver.quit();
	}
	
}
