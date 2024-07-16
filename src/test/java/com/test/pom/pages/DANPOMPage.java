package com.test.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DANPOMPage {

	WebDriver driver;
	
	By heading = By.tagName("h1");
	
	private static String pageUrl = "https://www.novoproso.com/DAN.html";
	

	public DANPOMPage(WebDriver driver) {
		this.driver = driver;
		driver.get(pageUrl);
	}

	public void printHeading() {
		String text = driver.findElement(heading).getText();
		System.out.println(text);
	}
	
	public boolean isElementPresent() {
		return driver.findElement(heading).isDisplayed();
	}
}
