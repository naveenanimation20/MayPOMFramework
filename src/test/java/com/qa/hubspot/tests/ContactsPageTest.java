package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.ExcelUtil;

//@Listeners(pdfListener.class)
public class ContactsPageTest {
	
	
	WebDriver driver;
	Properties prop;
	BasePage basePage;
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	
	@BeforeMethod
	public void setUp(){
		basePage = new BasePage();
		prop = basePage.initialize_Properties();
		driver = basePage.initialize_driver(prop);
		loginPage = new LoginPage(driver);
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		contactsPage = homePage.goToContactsPage();
	}
	
	
	@DataProvider(name="getContactsData")
	public Object[][] getContactsTestData(){
		Object contactsData[][] = ExcelUtil.getTestData("contacts");
		return contactsData;
	}
	
	
	@Test(dataProvider="getContactsData")
	public void createNewContactTest(String email, String firstName, String lastName, String jobTitle){
		contactsPage.createNewContact(email,firstName,lastName,jobTitle);
	}
	
	
	
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
	
	
	
	
	
	

}
