package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.pages.LoginPageNPF;

public class LoginPageTestNPF {

	WebDriver driver;
	Properties prop;
	BasePage basePage;
	LoginPageNPF loginPageNpf;

	@BeforeMethod
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.initialize_Properties();
		driver = basePage.initialize_driver(prop);
		loginPageNpf = new LoginPageNPF(driver);
	}

	@Test(priority = 1)
	public void verifySignUpLinkTest() {
		Assert.assertTrue(loginPageNpf.verifySigupLinkDisplayed());
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
