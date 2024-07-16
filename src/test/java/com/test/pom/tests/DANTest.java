package com.test.pom.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.test.pom.pages.DANPOMPage;
import com.test.pom.pages.DANPage;

@TestInstance(Lifecycle.PER_CLASS)
class DANTest {
	WebDriver driver;
	DANPage danPage;
	DANPOMPage danpomPage;
	
	@BeforeAll
	void setUpBeforeClass() throws Exception {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	@AfterAll
	void tearDownAfterClass() throws Exception {
		driver.quit();
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		danPage = new DANPage(driver);
		assertTrue(danPage.isElementPresent());
		danPage.printHeading();
	}

	@Test
	void POMtest() {
		danpomPage = new DANPOMPage(driver);
		assertTrue(danpomPage.isElementPresent());
		danpomPage.printHeading();
	}
}
