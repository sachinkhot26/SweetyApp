package com.qa.sweety.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.sweety.base.TestBase;

public class LoginPage extends TestBase{
	
	
	@FindBy(id="user_email")
	WebElement email;
	
	@FindBy(id="user_password")
	WebElement password;
	
	@FindBy(name="commit")
	WebElement loginBtn;
	
	@FindBy(xpath="//div[@class='alert alert-info fade in']")
	WebElement message;
	
	@FindBy(xpath="//div[@class='alert alert-warning fade in']")
	WebElement invalidMessage;
	
	public LoginPage(){
		PageFactory.initElements(driver,this);
	}
	
	public String verifyPagetitle() {
		return driver.getTitle();
	}
	
	public String getMessage() {
		return message.getText();
	}
	public String getInvalidMessage() {
		return invalidMessage.getText();
	}
	
	public void login(String uname,String pwd) throws InterruptedException {
		email.sendKeys(uname);
		password.sendKeys(pwd);
		Thread.sleep(3000);
		loginBtn.click();
		
	}

}
