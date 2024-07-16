package com.test.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//using page factory
public class DANPage {

	WebDriver driver;
	
	@FindBy(tagName ="h1")
	WebElement headingElement;
	
	private static String pageUrl = "https://www.novoproso.com/DAN.html";
	

	public DANPage(WebDriver driver) {
		this.driver = driver;
		driver.get(pageUrl);
		PageFactory.initElements(driver, this);
	}

	public void printHeading() {
		String text = headingElement.getText();
		System.out.println(text);
	}
	
	public boolean isElementPresent() {
		return headingElement.isDisplayed();
	}
}
