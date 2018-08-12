package com.qa.sweety.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.sweety.base.TestBase;

public class EntryPage extends TestBase {
	
	@FindBy(linkText="Levels")
	WebElement level;
	
	@FindBy(linkText="Add new")
	WebElement addNew;
	
	@FindBy(id="entry_level")
	WebElement entryLevel ;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement submit;
	
	public EntryPage(){
		PageFactory.initElements(driver,this);
	}
	
	public void enterGlucoseLevel() {
		level.click();
		addNew.click();
		entryLevel.sendKeys("10");
		submit.click();
		
	}
	

}
