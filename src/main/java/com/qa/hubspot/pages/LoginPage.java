package com.qa.hubspot.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.TimeUtil;

import io.qameta.allure.Step;

public class LoginPage extends BasePage {

	// 1. page factory -- page objects
	@FindBy(id = "username")
	WebElement emailId;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(id = "loginBtn")
	WebElement loginButton;

	@FindBy(linkText = "Sign up")
	WebElement signUpLink;

	// 2. create the constructor of Loginpage class and initialize your page
	// objects
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// 3. Page Actions/ page lib:
	@Step("getting login page title and returning the page title step....")
	public String getLoginPageTitle() {
		return driver.getTitle();
	}

	@Step("verifying sign up link is diplayed step....")
	public boolean verifySigupLinkDisplayed() {
		return signUpLink.isDisplayed();
	}

	@Step("login with : {0} and {1}")
	public HomePage doLogin(String username, String pwd) {
		System.out.println("credetials: " + username + "/" + pwd);
		emailId.sendKeys(username);
		password.sendKeys(pwd);
		loginButton.click();
		TimeUtil.mediumWait();
		return new HomePage(driver);
	}

}
