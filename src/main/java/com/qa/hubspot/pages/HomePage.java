package com.qa.hubspot.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.TimeUtil;

public class HomePage extends BasePage {

	// 1. page factory -- page objects
	@FindBy(xpath = "//h1[@class='private-page__title']")
	WebElement homePageHeader;

	@FindBy(xpath = "//span[@class='account-name ']")
	WebElement accountName;

	@FindBy(id = "nav-primary-contacts-branch")
	WebElement parentContactsMenu;

	@FindBy(id = "nav-secondary-contacts")
	WebElement childContactsMenu;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// page actions:
	public String getHomePageTitle() {
		return driver.getTitle();
	}

	public String getHomePageHeaderText() {
		return homePageHeader.getText();
	}

	public boolean verifyHomePageHeader() {
		return homePageHeader.isDisplayed();
	}

	public String getAccountNameValue() {
		return accountName.getText();
	}

	public boolean verifyAccountName() {
		return accountName.isDisplayed();
	}

	public void clickOnContacts() {
		parentContactsMenu.click();
		TimeUtil.shortWait();
		childContactsMenu.click();
	}

	public ContactsPage goToContactsPage() {
		clickOnContacts();
		return new ContactsPage(driver);
	}

}
