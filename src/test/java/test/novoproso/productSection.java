package test.novoproso;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import test.novoproso.utils.BrowserSetUp;
import test.novoproso.utils.footerHighlight;
import test.novoproso.utils.highLightElement;
import test.novoproso.utils.mouseHoverJS;

class productSection {
	
	//chrome, msedge, firefox
	static String browser = "chrome";
	static RemoteWebDriver driver; 
	
//	static WebDriver driver;
	static JavascriptExecutor jsExecutor;
	static WebDriverWait wait, elementWait;
	static Actions action;
	static mouseHoverJS hoverJS;
	static footerHighlight footerHighlightClass;
	static highLightElement highLightElementClass;
	static BrowserSetUp browserSetUp = new BrowserSetUp();
	
	@BeforeAll
	static void setUp() throws Exception {
//		driver = browserSetUp.getBrowserSetUp(browser);
		
		driver = browserSetUp.getBrowserGridSetUp(browser);

				
		//actions
		action = new Actions(driver);
		
		//JavaScriptExecutor
		jsExecutor = (JavascriptExecutor) driver;
		
		hoverJS = new mouseHoverJS();
		highLightElementClass = new highLightElement();
		footerHighlightClass = new footerHighlight();
	}

	@AfterAll
	static void tearDown() throws Exception {
		driver.quit();
	}

//	@Disabled
	@Test
	void DANPageTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		//Direct URL
//		driver.get("https://novoproso.com/DAN.html");

		//From home page
		driver.get("https://novoproso.com");
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(600));
		
		wait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		
		//click start now button
		elementWait.until(d -> startnow.isDisplayed());
		highLightElementClass.highlightElement(driver, startnow);
		startnow.click();

		//wait until about us link appears and hover over it
		wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());
		wait.until(d -> driver.findElement(By.xpath("//section[contains(@id,'about-us')]//div[contains(@class,'about-info')]/h2")).isDisplayed());
		WebElement productsLink = driver.findElement(By.xpath("//li/a[@href='#products']"));
		WebElement productsDropdownElement = driver.findElements(By.xpath("//li/ul")).get(1);
		WebElement danElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'DAN.html')]"));
		
		highLightElementClass.highlightElement(driver, productsLink);
		hoverJS.mouseHoverJScript(productsLink, driver);
//		action.moveToElement(productsLink).perform();
//		action.moveToElement(productsLink).perform();
//		action.moveToElement(productsLink).perform();
//		action.moveToElement(productsLink).perform();
//		action.moveToElement(productsLink).perform();
		
		//wait until about us dropdown appears
		elementWait.until(d -> productsDropdownElement.isDisplayed());

		//click localSports element to go that page
		highLightElementClass.highlightElement(driver, danElement);
		danElement.click();

		//wait until page loads (waiting until page main heading loads)
		wait.until(ExpectedConditions.urlToBe("https://novoproso.com/DAN.html"));
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());

		//get Current URL
		String DANUrl = driver.getCurrentUrl();
		System.out.println("Current URL: " + DANUrl);
		
		//assert page title
		String pageTitle = driver.getTitle();
		assertEquals("Novo ProSo, Inc.", pageTitle);
		
		//assert main heading
		WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
		highLightElementClass.highlightElement(driver, mainHeading);
		assertEquals("Data Analytics by Novo ProSo (DAN)", mainHeading.getText());
		
		//sub heading
		WebElement subHeading = driver.findElement(By.cssSelector("h4"));
		assertEquals("Unlocking Hidden Insights!", subHeading.getText());
		assertEquals("rgba(8, 117, 3, 0.8)", subHeading.getCssValue("color"));
		assertEquals("1.5px", subHeading.getCssValue("letter-spacing"));
		assertEquals("27px", subHeading.getCssValue("line-height"));
		assertEquals("600", subHeading.getCssValue("font-weight"));
		highLightElementClass.highlightElement(driver, subHeading);
		
		//hr
		assertTrue(driver.findElement(By.cssSelector("hr")).isDisplayed());

		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		highLightElementClass.highlightElement(driver, imageElement);
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/idea.png", imageElement.getDomAttribute("src"));
		
		//assert ul, li tags
		List<WebElement> ulElements = driver.findElements(By.cssSelector("ul"));
		List<WebElement> content = driver.findElements(By.xpath("//div[contains(@class,'service-info')]/p"));
		
		//whether ul tags are displayed or not
		assertEquals(7, ulElements.size());
		assertTrue(ulElements.get(0).isDisplayed());
		assertFalse(ulElements.get(1).isDisplayed());		
		assertFalse(ulElements.get(2).isDisplayed());
		assertFalse(ulElements.get(3).isDisplayed());
		assertTrue(ulElements.get(4).isDisplayed());
		assertTrue(ulElements.get(5).isDisplayed());

		//check for li tags
		highLightElementClass.highlightElement(driver, content.get(0));		
		highLightElementClass.highlightElement(driver, ulElements.get(4));
		highLightElementClass.highlightElement(driver, content.get(1));		
		highLightElementClass.highlightElement(driver, ulElements.get(5));
		highLightElementClass.highlightElement(driver, content.get(2));		


		//Footer
		footerHighlightClass.footerHighlightElement(driver, jsExecutor, wait);
	}

//	@Disabled
	@Test
	void BDIngensionPageTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		//Direct URL
		driver.get("https://novoproso.com/BDIngension.html");

		//From home page
		driver.get("https://novoproso.com");
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(600));
		
		wait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		
		//click start now button
		elementWait.until(d -> startnow.isDisplayed());
		highLightElementClass.highlightElement(driver, startnow);
		startnow.click();

		//wait until about us link appears and hover over it
		wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());
		wait.until(d -> driver.findElement(By.xpath("//section[contains(@id,'about-us')]//div[contains(@class,'about-info')]/h2")).isDisplayed());
		WebElement productsLink = driver.findElement(By.xpath("//li/a[@href='#products']"));
		WebElement productsDropdownElement = driver.findElements(By.xpath("//li/ul")).get(1);
		WebElement bdIngensionElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'BDIngension.html')]"));
		
		//wait until about us link appears and hover over it
		elementWait.until(ExpectedConditions.visibilityOf(productsLink));
		highLightElementClass.highlightElement(driver, productsLink);
		hoverJS.mouseHoverJScript(productsLink, driver);
//		action.moveToElement(productsLink).perform();
//		action.moveToElement(productsLink).perform();
//		action.moveToElement(productsLink).perform();
//		action.moveToElement(productsLink).perform();
//		action.moveToElement(productsLink).perform();
		
		//wait until about us dropdown appears
		elementWait.until(d -> productsDropdownElement.isDisplayed());

		//click localSports element to go that page
		highLightElementClass.highlightElement(driver, bdIngensionElement);
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
		highLightElementClass.highlightElement(driver, mainHeading.getLast());
		assertEquals("BigData-Ingension", mainHeading.getLast().getText());

		//sub heading
		assertEquals("Unleashing the Power of Raw Data!", subHeading.getText());
		assertEquals("rgba(8, 117, 3, 0.8)", subHeading.getCssValue("color"));
		assertEquals("1.5px", subHeading.getCssValue("letter-spacing"));
		assertEquals("27px", subHeading.getCssValue("line-height"));
		assertEquals("600", subHeading.getCssValue("font-weight"));
		highLightElementClass.highlightElement(driver, subHeading);
		
		//hr
		assertTrue(driver.findElement(By.cssSelector("hr")).isDisplayed());

		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		highLightElementClass.highlightElement(driver, imageElement);
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/idea.png", imageElement.getDomAttribute("src"));
		
		//assert ul, li tags
		List<WebElement> ulElements = driver.findElements(By.cssSelector("ul"));
		List<WebElement> content = driver.findElements(By.xpath("//div[contains(@class,'service-info')]/p"));

		//whether ul tags are displayed or not
		assertEquals(7, ulElements.size());
		assertTrue(ulElements.get(0).isDisplayed());
		assertFalse(ulElements.get(1).isDisplayed());		
		assertFalse(ulElements.get(2).isDisplayed());
		assertFalse(ulElements.get(3).isDisplayed());
		assertTrue(ulElements.get(4).isDisplayed());
		assertTrue(ulElements.get(5).isDisplayed());

		highLightElementClass.highlightElement(driver, content.get(0));
		highLightElementClass.highlightElement(driver, ulElements.get(4));
		highLightElementClass.highlightElement(driver, content.get(1));
		highLightElementClass.highlightElement(driver, ulElements.get(5));
		highLightElementClass.highlightElement(driver, content.get(2));

		//Footer
		footerHighlightClass.footerHighlightElement(driver, jsExecutor, wait);
	}
}
