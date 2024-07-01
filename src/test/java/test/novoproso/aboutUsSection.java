package test.novoproso;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class aboutUsSection {

	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait wait, imageWait, elementWait;
	Actions action;
	ChromeOptions chromeoptions;
	
	@BeforeEach
	void setUp() throws Exception {
		//has to do later

		//Chrome Browser
		chromeoptions = new ChromeOptions();
		chromeoptions.addArguments("start-maximized");
		driver = new ChromeDriver(chromeoptions);

		//Edge Browser
//		driver = new EdgeDriver();
		
		//Firefox Browser
//		driver = new FirefoxDriver();
		
		//actions
		action = new Actions(driver);
		
		//JavaScriptExecutor
		jsExecutor = (JavascriptExecutor) driver;
	}

	@AfterEach
	void tearDown() throws Exception {
		//has to do later
		driver.quit();
	}

	public void highlightElement(WebDriver driver, WebElement element) {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].setAttribute('style', 'background: yellow;border: 2px solid red;')", element);
	}

	@Disabled
	@Test
	void CSRPageTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		//Direct Url
//		driver.get("https://novoproso.com/csr.html");
		
		//From home page
		driver.get("https://novoproso.com");
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		imageWait = new WebDriverWait(driver, Duration.ofMillis(3000));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(600));
		
		imageWait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		
		//click start now button
		elementWait.until(d -> startnow.isDisplayed());
		highlightElement(driver, startnow);
		startnow.click();
		Thread.sleep(1000);

		WebElement aboutUsLink = driver.findElement(By.xpath("//li/a[@href='#about-us']"));
		WebElement aboutUsDropdownElement = driver.findElements(By.xpath("//li/ul")).get(0);
		WebElement csrElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'csr.html')]"));
		
		//wait until about us link appears and hover over it
		elementWait.until(ExpectedConditions.visibilityOf(aboutUsLink));
		highlightElement(driver, aboutUsLink);
		action.moveToElement(aboutUsLink).perform();
		Thread.sleep(1000);
		
		//wait until about us dropdown appears
		elementWait.until(d -> aboutUsDropdownElement.isDisplayed());
		Thread.sleep(1000);

		//click csr element to go that page
		highlightElement(driver, csrElement);
		csrElement.click();

		//wait until page loads (waiting until page main heading loads)
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		
		//get Current URL
		String csrUrl = driver.getCurrentUrl();
		System.out.println("Current URL: " + csrUrl);
		
		//assert page title
		String pageTitle = driver.getTitle();
		assertEquals("Novo ProSo, Inc.", pageTitle);
		
		//assert main heading
		WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
		assertEquals("Corporate Social Responsibility", mainHeading.getText());
		
		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/idea.png", imageElement.getDomAttribute("src"));
		
		//assert ul, li tags
		List<WebElement> ulElements = driver.findElements(By.cssSelector("ul"));
		assertEquals(6, ulElements.size());
		assertEquals(new Dimension(616,80), ulElements.get(0).getSize());
		System.out.printf(ulElements.get(1).getSize().toString(),ulElements.get(2).getSize().toString(),ulElements.get(3).getSize().toString());
//		assertEquals(new Dimension(750, 140), ulElements.get(1).getSize());
//		assertEquals(new Dimension(750, 140), ulElements.get(2).getSize());
//		assertEquals(new Dimension(750, 140), ulElements.get(3).getSize());
		assertEquals(new Dimension(750, 140), ulElements.get(4).getSize());
		assertEquals(new Dimension(124, 36), ulElements.get(5).getSize());

		//whether ul tags are displayed or not
		assertTrue(ulElements.get(0).isDisplayed());
		assertFalse(ulElements.get(1).isDisplayed());		
		assertFalse(ulElements.get(2).isDisplayed());
		assertFalse(ulElements.get(3).isDisplayed());
		assertTrue(ulElements.get(4).isDisplayed());
		assertTrue(ulElements.get(5).isDisplayed());
		//check for li tags
		List<WebElement> aboutUsInfoList = driver.findElements(By.className("oli"));
		assertEquals(4, aboutUsInfoList.size());
		assertEquals("Diversity and inclusion.", aboutUsInfoList.get(0).getText());
		assertEquals("Environment and energy.", aboutUsInfoList.get(1).getText());
		assertEquals("Governance.", aboutUsInfoList.get(2).getText());
		assertEquals("Community Outreach.", aboutUsInfoList.get(3).getText());
	}

	@Disabled
	@Test
	void localSportsPageTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		//Direct Url
//		driver.get("https://novoproso.com/localSports.html");
		
		//From home page
		driver.get("https://novoproso.com");
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		imageWait = new WebDriverWait(driver, Duration.ofMillis(3000));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(600));
		
		imageWait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		
		//click start now button
		elementWait.until(d -> startnow.isDisplayed());
		highlightElement(driver, startnow);
		startnow.click();
		Thread.sleep(1000);

		WebElement aboutUsLink = driver.findElement(By.xpath("//li/a[@href='#about-us']"));
		WebElement aboutUsDropdownElement = driver.findElements(By.xpath("//li/ul")).get(0);
		WebElement localSportsElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'localSports.html')]"));
		
		//wait until about us link appears and hover over it
		elementWait.until(ExpectedConditions.visibilityOf(aboutUsLink));
		highlightElement(driver, aboutUsLink);
		action.moveToElement(aboutUsLink).perform();
		Thread.sleep(1000);
		
		//wait until about us dropdown appears
		elementWait.until(d -> aboutUsDropdownElement.isDisplayed());
		Thread.sleep(1000);

		//click localSports element to go that page
		highlightElement(driver, localSportsElement);
		localSportsElement.click();

		//wait until page loads (waiting until page main heading loads)
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		
		//get Current URL
		String localSportsUrl = driver.getCurrentUrl();
		System.out.println("Current URL: " + localSportsUrl);
		
		//assert page title
		String pageTitle = driver.getTitle();
		assertEquals("Novo ProSo, Inc.", pageTitle);
		
		//assert main heading
		WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
		assertEquals("Local Sports Sponsorship", mainHeading.getText());
		
		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/idea.png", imageElement.getDomAttribute("src"));
		
		//assert ul, li tags
		List<WebElement> ulElements = driver.findElements(By.cssSelector("ul"));
		assertEquals(5, ulElements.size());
		assertEquals(new Dimension(616,80), ulElements.get(0).getSize());
		System.out.printf(ulElements.get(1).getSize().toString(),ulElements.get(2).getSize().toString(),ulElements.get(3).getSize().toString());
//		assertEquals(new Dimension(750, 140), ulElements.get(1).getSize());
//		assertEquals(new Dimension(750, 140), ulElements.get(2).getSize());
//		assertEquals(new Dimension(750, 140), ulElements.get(3).getSize());
		assertEquals(new Dimension(124, 36), ulElements.get(4).getSize());

		//whether ul tags are displayed or not
		assertTrue(ulElements.get(0).isDisplayed());
		assertFalse(ulElements.get(1).isDisplayed());		
		assertFalse(ulElements.get(2).isDisplayed());
		assertFalse(ulElements.get(3).isDisplayed());
		assertTrue(ulElements.get(4).isDisplayed());

	}

}
