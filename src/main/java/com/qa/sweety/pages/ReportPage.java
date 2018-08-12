package com.qa.sweety.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.sweety.base.TestBase;

public class ReportPage extends TestBase {
	
	@FindBy(linkText="Reports")
	WebElement report;
	
	
	
	public ReportPage(){
		PageFactory.initElements(driver,this);
	}
	
	public void viewReport() {
		report.click();
	}
}
