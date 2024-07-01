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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class productSection {

	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait wait, imageWait, elementWait;
	Actions action;
	ChromeOptions chromeoptions;
	
	@BeforeEach
	void setUp() throws Exception {
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
	void DANPageTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		//Direct URL
//		driver.get("https://novoproso.com/DAN.html");

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

		WebElement productsLink = driver.findElement(By.xpath("//li/a[@href='#products']"));
		WebElement productsDropdownElement = driver.findElements(By.xpath("//li/ul")).get(1);
		WebElement danElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'DAN.html')]"));
		
		//wait until about us link appears and hover over it
		elementWait.until(ExpectedConditions.visibilityOf(productsLink));
		highlightElement(driver, productsLink);
		action.moveToElement(productsLink).perform();
		Thread.sleep(1000);
		
		//wait until about us dropdown appears
		elementWait.until(d -> productsDropdownElement.isDisplayed());
		Thread.sleep(1000);

		//click localSports element to go that page
		highlightElement(driver, danElement);
		danElement.click();

		//wait until page loads (waiting until page main heading loads)
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		
		//get Current URL
		String DANUrl = driver.getCurrentUrl();
		System.out.println("Current URL: " + DANUrl);
		
		//assert page title
		String pageTitle = driver.getTitle();
		assertEquals("Novo ProSo, Inc.", pageTitle);
		
		//assert main heading
		WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
		highlightElement(driver, mainHeading);
		assertEquals("Data Analytics by Novo ProSo (DAN)", mainHeading.getText());
		
		//sub heading
		WebElement subHeading = driver.findElement(By.cssSelector("h4"));
		assertEquals("Unlocking Hidden Insights!", subHeading.getText());
		assertEquals("rgba(8, 117, 3, 0.8)", subHeading.getCssValue("color"));
		assertEquals("1.5px", subHeading.getCssValue("letter-spacing"));
		assertEquals("27px", subHeading.getCssValue("line-height"));
		assertEquals("600", subHeading.getCssValue("font-weight"));
		highlightElement(driver, subHeading);
		
		//hr
		assertTrue(driver.findElement(By.cssSelector("hr")).isDisplayed());

		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		highlightElement(driver, imageElement);
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/idea.png", imageElement.getDomAttribute("src"));
		
		//assert ul, li tags
		List<WebElement> ulElements = driver.findElements(By.cssSelector("ul"));
		assertEquals(7, ulElements.size());
		assertEquals(new Dimension(616,80), ulElements.get(0).getSize());
		System.out.printf(ulElements.get(1).getSize().toString(),ulElements.get(2).getSize().toString(),ulElements.get(3).getSize().toString());
//		assertEquals(new Dimension(750, 140), ulElements.get(1).getSize());
//		assertEquals(new Dimension(750, 140), ulElements.get(2).getSize());
//		assertEquals(new Dimension(750, 140), ulElements.get(3).getSize());
		assertEquals(new Dimension(750, 96), ulElements.get(4).getSize());
		assertEquals(new Dimension(750, 120), ulElements.get(5).getSize());
		assertEquals(new Dimension(124, 36), ulElements.get(6).getSize());

		System.out.println(ulElements.getLast().findElement(By.tagName("li")).getText());
		//whether ul tags are displayed or not
		assertTrue(ulElements.get(0).isDisplayed());
		assertFalse(ulElements.get(1).isDisplayed());		
		assertFalse(ulElements.get(2).isDisplayed());
		assertFalse(ulElements.get(3).isDisplayed());
		assertTrue(ulElements.get(4).isDisplayed());
		assertTrue(ulElements.get(5).isDisplayed());

		//check for li tags
		
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		Thread.sleep(1000);

		highlightElement(driver, ulElements.get(6));
		assertTrue(ulElements.get(6).isDisplayed());
	}

	@Disabled
	@Test
	void BDIngensionPageTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		//Direct URL
		driver.get("https://novoproso.com/BDIngension.html");

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

		WebElement productsLink = driver.findElement(By.xpath("//li/a[@href='#products']"));
		WebElement productsDropdownElement = driver.findElements(By.xpath("//li/ul")).get(1);
		WebElement bdIngensionElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'BDIngension.html')]"));
		
		//wait until about us link appears and hover over it
		elementWait.until(ExpectedConditions.visibilityOf(productsLink));
		highlightElement(driver, productsLink);
		action.moveToElement(productsLink).perform();
		Thread.sleep(1000);
		
		//wait until about us dropdown appears
		elementWait.until(d -> productsDropdownElement.isDisplayed());
		Thread.sleep(1000);

		//click localSports element to go that page
		highlightElement(driver, bdIngensionElement);
		bdIngensionElement.click();

		//wait until page loads (waiting until page main heading loads)
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		
		//get Current URL
		String bdIngensionUrl = driver.getCurrentUrl();
		System.out.println("Current URL: " + bdIngensionUrl);
		
		//assert page title
		String pageTitle = driver.getTitle();
		assertEquals("Novo ProSo, Inc.", pageTitle);
		
		//assert main heading
		List<WebElement> mainHeading = driver.findElements(By.cssSelector("h2"));
		WebElement subHeading = driver.findElement(By.cssSelector("h4"));

		//it included commented h2 tag too
		highlightElement(driver, mainHeading.getLast());
		assertEquals("BigData-Ingension", mainHeading.getLast().getText());

		//sub heading
		assertEquals("Unleashing the Power of Raw Data!", subHeading.getText());
		assertEquals("rgba(8, 117, 3, 0.8)", subHeading.getCssValue("color"));
		assertEquals("1.5px", subHeading.getCssValue("letter-spacing"));
		assertEquals("27px", subHeading.getCssValue("line-height"));
		assertEquals("600", subHeading.getCssValue("font-weight"));
		highlightElement(driver, subHeading);
		
		//hr
		assertTrue(driver.findElement(By.cssSelector("hr")).isDisplayed());

		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		highlightElement(driver, imageElement);
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/idea.png", imageElement.getDomAttribute("src"));
		
		//assert ul, li tags
		List<WebElement> ulElements = driver.findElements(By.cssSelector("ul"));
		assertEquals(7, ulElements.size());
		assertEquals(new Dimension(616,80), ulElements.get(0).getSize());
		System.out.printf(ulElements.get(1).getSize().toString(),ulElements.get(2).getSize().toString(),ulElements.get(3).getSize().toString());
//		assertEquals(new Dimension(750, 140), ulElements.get(1).getSize());
//		assertEquals(new Dimension(750, 140), ulElements.get(2).getSize());
//		assertEquals(new Dimension(750, 140), ulElements.get(3).getSize());
		assertEquals(new Dimension(750, 120), ulElements.get(4).getSize());
		assertEquals(new Dimension(750, 120), ulElements.get(5).getSize());
		assertEquals(new Dimension(124, 36), ulElements.get(6).getSize());

		//whether ul tags are displayed or not
		assertTrue(ulElements.get(0).isDisplayed());
		assertFalse(ulElements.get(1).isDisplayed());		
		assertFalse(ulElements.get(2).isDisplayed());
		assertFalse(ulElements.get(3).isDisplayed());
		assertTrue(ulElements.get(4).isDisplayed());
		assertTrue(ulElements.get(5).isDisplayed());
		
		jsExecutor.executeScript("window.scrollBy(0,600)");
		Thread.sleep(1000);

		highlightElement(driver, ulElements.get(6));
		assertTrue(ulElements.get(6).isDisplayed());
	}
}
