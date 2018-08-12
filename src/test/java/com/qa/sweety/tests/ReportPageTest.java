package com.qa.sweety.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import com.qa.sweety.base.TestBase;
import com.qa.sweety.pages.LoginPage;
import com.qa.sweety.pages.ReportPage;

public class ReportPageTest extends TestBase {

	LoginPage loginPage;
	ReportPage reportPage;
	
	ReportPageTest(){
		super();
	}
	
	
	@BeforeMethod
	
	public void setUp(){
		
		initialization();
		
	    loginPage=new LoginPage();
	    reportPage=new ReportPage();
	}
	
	@Test(priority=1)
	public void verifyReport() throws Exception {
		loginPage.login(prop.getProperty("email"),prop.getProperty("password"));
		Thread.sleep(2000);
		reportPage.viewReport();
		Thread.sleep(2000);
		screenShot();
	}
	
	
	@AfterMethod
	
	public void tearDown() {
		driver.quit();
	}
	
}
