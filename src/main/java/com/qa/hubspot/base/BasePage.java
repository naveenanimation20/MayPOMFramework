package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.hubspot.util.TimeUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author NaveenKhunteta
 *
 */
public class BasePage {

	public WebDriver driver;
	public Properties prop;
	
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();

	/**
	 * This method is used to initialize the webdriver
	 * 
	 * @param prop
	 * @return driver
	 */
	public WebDriver initialize_driver(Properties prop) {

		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver();
			//ChromeOptions co = new ChromeOptions();
			//co.addArguments("--headless");
			tldriver.set(new ChromeDriver());
			
			
		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver();
			tldriver.set(new FirefoxDriver());

			
		} else {
			System.out.println("Browser" + browserName
					+ "is not defined in properties file, please give the correct browser name");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().fullscreen();
		getDriver().get(prop.getProperty("url"));
		TimeUtil.mediumWait();
		return getDriver();

	}
	
	public static synchronized WebDriver getDriver(){
		return tldriver.get();
	}

	/**
	 * This method is used to initialize the properties and it will return
	 * properties reference
	 * 
	 * @return prop
	 */
	public Properties initialize_Properties() {

		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/main/java/com/qa/hubspot/configuration/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}
	
	
	/**
	 * take screenshot
	 */
	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("Capture Failed " + e.getMessage());
		}
		return path;
	}

}
