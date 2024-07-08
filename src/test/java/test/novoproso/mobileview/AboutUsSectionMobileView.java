package test.novoproso.mobileview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.TestCase;
import test.novoproso.utils.BrowserSetUp;
import test.novoproso.utils.footerHighlight;
import test.novoproso.utils.highLightElement;
import test.novoproso.utils.mouseHoverJS;

class AboutUsSectionMobileView {

	//chrome, msedge, firefox
	static String browser ="msedge";
//	static RemoteWebDriver driver; 
//	static DesiredCapabilities capabilities = new DesiredCapabilities();

	static WebDriver driver;
	static JavascriptExecutor jsExecutor;
	static WebDriverWait wait, elementWait;
	static Actions action;
	static mouseHoverJS hoverJS;
	static highLightElement highLight;
	static footerHighlight footerHighlight;
	static BrowserSetUp browserSetUpConfig = new BrowserSetUp();
	
	@BeforeAll
	static void beforesetUp() {
		driver = browserSetUpConfig.getBrowserSetUp(browser);
		driver.manage().window().setSize(new Dimension(673,690));

//		capabilities.setBrowserName("chrome");
//		capabilities.setPlatform(Platform.WIN11);
//		driver = new RemoteWebDriver(new URL("http://localhost:4444/"), capabilities);
//		driver.manage().window().maximize();
						
		//actions
		action = new Actions(driver);
		
		//JavaScriptExecutor
		jsExecutor = (JavascriptExecutor) driver;
		
		hoverJS = new mouseHoverJS();
		highLight = new highLightElement();
		footerHighlight = new footerHighlight();
	}

	@AfterAll
	static void aftertearDown() throws Exception {
		driver.quit();
	}


	
//	@Disabled
	@Test
	void CSRPageTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		//Direct Url
//		driver.get("https://novoproso.com/csr.html");
		
		//From home page
		driver.get("https://novoproso.com");
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(600));
		
		wait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		
		//click start now button
		elementWait.until(d -> startnow.isDisplayed());
		highLight.highlightElement(driver, startnow);
		startnow.click();
		
		//wait until about us section appears
		wait.until(d -> driver.findElement(By.xpath("//h1/a")).isDisplayed());
		wait.until(d -> driver.findElement(By.xpath("//section[contains(@id,'about-us')]//div[contains(@class,'about-info')]/h2")).isDisplayed());

		//click on nav element to open nav menu
		driver.findElement(By.xpath("//button[contains(@class,'navbar-toggle')]")).click();
		wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#home']")).isDisplayed());
		
		WebElement aboutUsLink = driver.findElement(By.xpath("//li/a[@href='#about-us']"));
		WebElement aboutUsDropdownElement = driver.findElements(By.xpath("//li/ul")).get(0);
		WebElement csrElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'csr.html')]"));
		
		highLight.highlightElement(driver, aboutUsLink);
		hoverJS.mouseHoverJScript(aboutUsLink, driver);
		
		//wait until about us dropdown appears
		elementWait.until(d -> aboutUsDropdownElement.isDisplayed());

		//click csr element to go that page
		highLight.highlightElement(driver, csrElement);
		csrElement.click();

		//wait until page loads (waiting until page main heading loads)
		wait.until(ExpectedConditions.urlToBe("https://novoproso.com/csr.html"));
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		
		//get Current URL
		String csrUrl = driver.getCurrentUrl();
		System.out.println("Current URL: " + csrUrl);
		
		//assert page title
		String pageTitle = driver.getTitle();
		assertEquals("Novo ProSo, Inc.", pageTitle);
		
		//assert main heading
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("h2"))));
		WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
		assertEquals("Corporate Social Responsibility", mainHeading.getText());
		highLight.highlightElement(driver, mainHeading);

		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/idea.png", imageElement.getDomAttribute("src"));
		highLight.highlightElement(driver, imageElement);
		
		//assert ul, li tags
		List<WebElement> ulElements = driver.findElements(By.cssSelector("ul"));
		assertEquals(6, ulElements.size());

		//whether ul tags are displayed or not
		assertFalse(ulElements.get(0).isDisplayed());
		assertFalse(ulElements.get(1).isDisplayed());		
		assertFalse(ulElements.get(2).isDisplayed());
		assertFalse(ulElements.get(3).isDisplayed());
		
		jsExecutor.executeScript("arguments[0].scrollIntoView();",ulElements.get(4));
		wait.until(ExpectedConditions.visibilityOf(ulElements.get(4)));		
		assertTrue(ulElements.get(4).isDisplayed());
		//check for li tags
		List<WebElement> aboutUsInfoList = driver.findElements(By.className("oli"));
		assertEquals(4, aboutUsInfoList.size());
		assertEquals("Diversity and inclusion.", aboutUsInfoList.get(0).getText());
		assertEquals("Environment and energy.", aboutUsInfoList.get(1).getText());
		assertEquals("Governance.", aboutUsInfoList.get(2).getText());
		assertEquals("Community Outreach.", aboutUsInfoList.get(3).getText());
		highLight.highlightElement(driver, ulElements.get(4));
		
		//Footer
		footerHighlight.footerHighlightElement(driver, jsExecutor, wait);
	}

//	@Disabled
	@Test
	void localSportsPageTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		//Direct Url
//		driver.get("https://novoproso.com/localSports.html");
		
		//From home page
		driver.get("https://novoproso.com");
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait = new WebDriverWait(driver, Duration.ofMillis(3000));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(600));
		
		wait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		
		//click start now button
		elementWait.until(d -> startnow.isDisplayed());
		highLight.highlightElement(driver, startnow);
		startnow.click();

		//wait until about us section appears
		wait.until(d -> driver.findElement(By.xpath("//h1/a")).isDisplayed());
		wait.until(d -> driver.findElement(By.xpath("//section[contains(@id,'about-us')]//div[contains(@class,'about-info')]/h2")).isDisplayed());

		//click on nav element to open nav menu
		driver.findElement(By.xpath("//button[contains(@class,'navbar-toggle')]")).click();
		wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#home']")).isDisplayed());

		WebElement aboutUsLink = driver.findElement(By.xpath("//li/a[contains(@href,'#about-us')]"));
		WebElement aboutUsDropdownElement = driver.findElements(By.xpath("//li/ul")).get(0);
		WebElement localSportsElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'localSports.html')]"));
		
		elementWait.until(ExpectedConditions.visibilityOf(aboutUsLink));
		highLight.highlightElement(driver, aboutUsLink);
		hoverJS.mouseHoverJScript(aboutUsLink, driver);
		
		//wait until about us dropdown appears
		elementWait.until(d -> aboutUsDropdownElement.isDisplayed());

		//click localSports element to go that page
		highLight.highlightElement(driver, localSportsElement);
		localSportsElement.click();

		//wait until page loads (waiting until page main heading loads)
		wait.until(ExpectedConditions.urlToBe("https://novoproso.com/localSports.html"));
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
		highLight.highlightElement(driver, mainHeading);
		
		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/idea.png", imageElement.getDomAttribute("src"));
		highLight.highlightElement(driver, imageElement);

		//assert ul, li tags
		List<WebElement> ulElements = driver.findElements(By.cssSelector("ul"));
		assertEquals(5, ulElements.size());

		//whether ul tags are displayed or not
		assertFalse(ulElements.get(0).isDisplayed());
		assertFalse(ulElements.get(1).isDisplayed());		
		assertFalse(ulElements.get(2).isDisplayed());
		assertFalse(ulElements.get(3).isDisplayed());

		jsExecutor.executeScript("arguments[0].scrollIntoView();",ulElements.get(4));
		wait.until(ExpectedConditions.visibilityOf(ulElements.get(4)));			
		assertTrue(ulElements.get(4).isDisplayed());

		//Footer
		footerHighlight.footerHighlightElement(driver, jsExecutor, wait);
	}
}
