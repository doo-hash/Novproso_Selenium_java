package test.novoproso;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URL;
import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class FooterTest {

	static RemoteWebDriver driver;
	static DesiredCapabilities capabilities = new DesiredCapabilities();
//	static WebDriver driver;
	static JavascriptExecutor jsExecutor;
	static Actions action;
	static WebDriverWait wait, elementWait;
	static ChromeOptions chromeoptions;
	static mouseHoverJS hoverJS;
	static highLightElement highLightElementClass;
	
	@BeforeAll
	static void setUp() throws Exception {
		capabilities.setBrowserName("chrome");
		capabilities.setPlatform(Platform.WIN11);
		driver = new RemoteWebDriver(new URL("http://localhost:4444/"), capabilities);
		driver.manage().window().maximize();
		
		//Chrome Browser
//		chromeoptions = new ChromeOptions();
//		chromeoptions.addArguments("start-maximized");
//		driver = new ChromeDriver(chromeoptions);
		
		//Edge Browser
//		driver = new EdgeDriver();
		
		//Firefox Browser
//		driver = new FirefoxDriver();
		
		//actions
		action = new Actions(driver);
		
		//JavaScriptExecutor
		jsExecutor = (JavascriptExecutor) driver;
		
		hoverJS = new mouseHoverJS();
		highLightElementClass = new highLightElement();
	}

	@AfterAll
	static void tearDown() throws Exception {
		driver.quit();
	}


//	@Disabled
	@Test
	void footerSocialLinksTest() throws InterruptedException {
		driver.get("https://novoproso.com/csr.html");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		//it scrolls to bottom -- method 1
//		js.executeScript("window.scrollBy(0, 550)", "");
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight);");

		wait.until(d -> driver.findElement(By.xpath("//div[contains(@class,'footer-logo')]")).isDisplayed());
		WebElement footerLogoLink = driver.findElement(By.xpath("//div[contains(@class,'footer-logo')]/a"));

		String currentHandle = driver.getWindowHandle(); 
		assertNotNull(currentHandle);
		
		//click footer logo link
		hoverJS.mouseHoverJScript(footerLogoLink, driver);
		highLightElementClass.highlightElement(driver, footerLogoLink);
		footerLogoLink.click();

		//wait until home page opens and check heading
		wait.until(d -> driver.findElement(By.xpath("//h1")).isDisplayed());
		highLightElementClass.highlightElement(driver, driver.findElement(By.xpath("//h1")));
		
		//check windowhandles and url
		Object[] windowHandles = driver.getWindowHandles().toArray(); 
		assertEquals(1, windowHandles.length);
		assertEquals("https://novoproso.com/index.html", driver.getCurrentUrl());
		System.out.println("Navigated to URL : " + driver.getCurrentUrl());

		//switch to back
		driver.navigate().back();
		
		//wait until csr page is loaded
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		assertEquals("https://novoproso.com/csr.html", driver.getCurrentUrl());
		System.out.println("Back to Url : " + driver.getCurrentUrl());

		//hover to twitter link and click it 
		WebElement twitterElement = driver.findElement(By.className("twitter"));
		hoverJS.mouseHoverJScript(twitterElement, driver);
		highLightElementClass.highlightElement(driver, twitterElement);
		twitterElement.click();

		//go to twitter page
		Object[] twitterwindowHandles = driver.getWindowHandles().toArray(); 
		assertNotNull(twitterwindowHandles);
		driver.switchTo().window((String) twitterwindowHandles[1]);
		System.out.println("Switched to Url: " + driver.getCurrentUrl());
		wait.until(d -> driver.getCurrentUrl().equals("https://twitter.com/novoproso"));
		assertTrue(driver.getCurrentUrl().equals("https://twitter.com/novoproso"));
		
		//switch back to csr page
		driver.switchTo().window((String) twitterwindowHandles[0]);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("facebook"))));
		System.out.println("Back to Url: " + driver.getCurrentUrl());
		WebElement facebookElement = driver.findElement(By.className("facebook"));

		//Go to facebook page
		hoverJS.mouseHoverJScript(facebookElement, driver);
		highLightElementClass.highlightElement(driver, facebookElement);
		facebookElement.click();
		Object[] facebookwindowHandles = driver.getWindowHandles().toArray(); 
		assertNotNull(facebookwindowHandles);
		driver.switchTo().window((String) facebookwindowHandles[2]);
		System.out.println("Switched to Url: " + driver.getCurrentUrl());
		wait.until(d -> driver.getCurrentUrl().equals("https://www.facebook.com/novoproso"));
		assertTrue(driver.getCurrentUrl().equals("https://www.facebook.com/novoproso"));

		//switch back to csr page
		driver.switchTo().window((String) facebookwindowHandles[0]);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("linkedin")));
		System.out.println("Back to Url: " + driver.getCurrentUrl());
		WebElement linkedinElement = driver.findElement(By.className("linkedin"));

		//Go to linkedIn page
		hoverJS.mouseHoverJScript(linkedinElement, driver);
		highLightElementClass.highlightElement(driver, linkedinElement);
		linkedinElement.click();
		Object[] linkedinwindowHandles = driver.getWindowHandles().toArray(); 
		assertNotNull(linkedinwindowHandles);
		wait.until(d -> driver.getCurrentUrl().equals("https://www.linkedin.com/company/novo-proso-inc"));
		System.out.println("Switched to Url: " + driver.getCurrentUrl());
		assertTrue(driver.getCurrentUrl().equals("https://www.linkedin.com/company/novo-proso-inc"));

		Object[] currentWindowHandles = driver.getWindowHandles().toArray(); 
		assertEquals(3, currentWindowHandles.length);

		//Go back to csr page
		driver.navigate().back();
		wait.until(d -> driver.findElement(By.xpath("//div[contains(@class,'footer-logo')]")).isDisplayed());
		System.out.println("Back to Url: " + driver.getCurrentUrl());		

		driver.switchTo().window((String) currentWindowHandles[2]);
		driver.close();
		assertEquals(2, driver.getWindowHandles().toArray().length);
		
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
		driver.close();
		assertEquals(1, driver.getWindowHandles().toArray().length);

		driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
		assertEquals("https://novoproso.com/csr.html", driver.getCurrentUrl());
		
		//click on policy link
		WebElement policyLink = driver.findElement(By.xpath("//div/p/a[contains(@href,'policy.html')]"));
		hoverJS.mouseHoverJScript(policyLink, driver);
		highLightElementClass.highlightElement(driver, policyLink);
		policyLink.click();
		wait.until(d -> driver.findElement(By.xpath("//h1")).isDisplayed());		
		assertEquals("https://novoproso.com/policy.html", driver.getCurrentUrl());
		assertEquals("Novo ProSo, Inc.", driver.getTitle());
		System.out.println("navigated to Url: " + driver.getCurrentUrl());

		driver.navigate().back();
		wait.until(d -> driver.findElement(By.xpath("//div[contains(@id,'md')]/p/a")).isDisplayed());
		System.out.println("Back to Url: " + driver.getCurrentUrl());		

		WebElement footerbottomCopyrightLink = driver.findElement(By.xpath("//div[contains(@id,'md')]/p/a"));
		hoverJS.mouseHoverJScript(footerbottomCopyrightLink, driver);
		highLightElementClass.highlightElement(driver, footerbottomCopyrightLink);
		footerbottomCopyrightLink.click();
		wait.until(d -> driver.findElement(By.xpath("//h1/a")).isDisplayed());
		highLightElementClass.highlightElement(driver, driver.findElement(By.xpath("//h1")));
		assertEquals("https://novoproso.com/index.html", driver.getCurrentUrl());
		assertEquals("Novo ProSo, Inc.", driver.getTitle());
		System.out.println("navigated to Url: " + driver.getCurrentUrl());		

		driver.navigate().back();
		wait.until(d -> driver.findElement(By.xpath("//div[contains(@id,'md')]/p/a")).isDisplayed());
		System.out.println("Back to Url: " + driver.getCurrentUrl());		
		
		WebElement designedByElement = driver.findElement(By.xpath("//p[contains(@class,'pull-right')]"));
		assertEquals("Designed by Novo ProSo", designedByElement.getText());
		WebElement designedByLinkElement = driver.findElement(By.xpath("//p[contains(@class,'pull-right')]/a"));
		hoverJS.mouseHoverJScript(footerbottomCopyrightLink, driver);
		highLightElementClass.highlightElement(driver, footerbottomCopyrightLink);
		designedByLinkElement.click();
		wait.until(d -> driver.findElement(By.xpath("//h1")).isDisplayed());
		highLightElementClass.highlightElement(driver, driver.findElement(By.xpath("//h1")));		
		assertEquals("https://novoproso.com/", driver.getCurrentUrl());
		System.out.println("navigated to Url: " + driver.getCurrentUrl());		

		driver.navigate().back();
		wait.until(d -> driver.findElement(By.xpath("//div[contains(@id,'md')]/p/a")).isDisplayed());
		System.out.println("Back to Url: " + driver.getCurrentUrl());				
		
	}
	
	
//	@Disabled
	@Test
	void footerMobileWindowMethod() throws InterruptedException {
		driver.manage().window().setSize(new Dimension(673,690));
		driver.get("https://novoproso.com/csr.html");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//it scrolls to bottom of the page-- method 2
		jsExecutor.executeScript("window.scrollBy(0, document.body.scrollHeight)");

		
		//it scrolls down the page till the element is found - method 3
//		WebElement footerBottomElement = driver.findElement(By.className("pull-right"));
//		js.executeScript("arguments[0].scrollIntoView()", footerBottomElement);
		
		
		wait.until(d -> driver.findElement(By.xpath("//div[contains(@class,'footer-logo')]")).isDisplayed());
		WebElement footerLogoLink = driver.findElement(By.xpath("//div[contains(@class,'footer-logo')]/a"));

		String currentHandle = driver.getWindowHandle(); 
		assertNotNull(currentHandle);
		
		//click footer logo link
		hoverJS.mouseHoverJScript(footerLogoLink, driver);
		highLightElementClass.highlightElement(driver, footerLogoLink);
		footerLogoLink.click();

		//wait until home page opens and check heading
		wait.until(d -> driver.findElement(By.xpath("//h1")).isDisplayed());
		highLightElementClass.highlightElement(driver, driver.findElement(By.xpath("//h1")));
		
		//check windowhandles and url
		Object[] windowHandles = driver.getWindowHandles().toArray(); 
		assertEquals(1, windowHandles.length);
		assertEquals("https://novoproso.com/index.html", driver.getCurrentUrl());
		System.out.println("Navigated to URL : " + driver.getCurrentUrl());

		//switch to back
		driver.navigate().back();
		
		//wait until csr page is loaded
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("twitter")));
		assertEquals("https://novoproso.com/csr.html", driver.getCurrentUrl());
		System.out.println("Back to Url : " + driver.getCurrentUrl());
		WebElement twitterElement = driver.findElement(By.className("twitter"));

		//hover to twitter link and click it 
		hoverJS.mouseHoverJScript(twitterElement, driver);
		highLightElementClass.highlightElement(driver, twitterElement);
		twitterElement.click();

		//go to twitter page
		Object[] twitterwindowHandles = driver.getWindowHandles().toArray(); 
		assertNotNull(twitterwindowHandles);
		driver.switchTo().window((String) twitterwindowHandles[1]);
		System.out.println("Switched to Url: " + driver.getCurrentUrl());
		wait.until(d -> driver.getCurrentUrl().equals("https://twitter.com/novoproso"));
		assertTrue(driver.getCurrentUrl().equals("https://twitter.com/novoproso"));
		
		//switch back to csr page
		driver.switchTo().window((String) twitterwindowHandles[0]);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("facebook")));
		System.out.println("Back to Url: " + driver.getCurrentUrl());
		WebElement facebookElement = driver.findElement(By.className("facebook"));

		//Go to facebook page
		hoverJS.mouseHoverJScript(facebookElement, driver);
		highLightElementClass.highlightElement(driver, facebookElement);
		facebookElement.click();
		Object[] facebookwindowHandles = driver.getWindowHandles().toArray(); 
		assertNotNull(facebookwindowHandles);
		driver.switchTo().window((String) facebookwindowHandles[2]);
		System.out.println("Switched to Url: " + driver.getCurrentUrl());
		wait.until(d -> driver.getCurrentUrl().equals("https://www.facebook.com/novoproso"));
		assertTrue(driver.getCurrentUrl().equals("https://www.facebook.com/novoproso"));

		//switch back to csr page
		driver.switchTo().window((String) twitterwindowHandles[0]);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("linkedin")));
		System.out.println("Back to Url: " + driver.getCurrentUrl());
		WebElement linkedinElement = driver.findElement(By.className("linkedin"));

		//Go to linkedIn page
		hoverJS.mouseHoverJScript(linkedinElement, driver);
		highLightElementClass.highlightElement(driver, linkedinElement);
		linkedinElement.click();
		Object[] linkedinwindowHandles = driver.getWindowHandles().toArray(); 
		assertNotNull(linkedinwindowHandles);
		wait.until(d -> driver.getCurrentUrl().equals("https://www.linkedin.com/company/novo-proso-inc"));
		System.out.println("Switched to Url: " + driver.getCurrentUrl());
		assertTrue(driver.getCurrentUrl().equals("https://www.linkedin.com/company/novo-proso-inc"));

		Object[] currentWindowHandles = driver.getWindowHandles().toArray(); 
		assertEquals(3, currentWindowHandles.length);

		//Go back to csr page
		driver.navigate().back();
		wait.until(d -> driver.findElement(By.xpath("//div[contains(@class,'footer-logo')]")).isDisplayed());
		System.out.println("Back to Url: " + driver.getCurrentUrl());		

		driver.switchTo().window((String) currentWindowHandles[2]);
		driver.close();
		assertEquals(2, driver.getWindowHandles().toArray().length);
		
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
		driver.close();
		assertEquals(1, driver.getWindowHandles().toArray().length);

		driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
		assertEquals("https://novoproso.com/csr.html", driver.getCurrentUrl());
		
		//click on policy link
		WebElement policyLink = driver.findElement(By.xpath("//div/p/a[contains(@href,'policy.html')]"));
		hoverJS.mouseHoverJScript(policyLink, driver);
		highLightElementClass.highlightElement(driver, policyLink);
		policyLink.click();
		wait.until(d -> driver.findElement(By.xpath("//h1")).isDisplayed());		
		assertEquals("https://novoproso.com/policy.html", driver.getCurrentUrl());
		assertEquals("Novo ProSo, Inc.", driver.getTitle());
		System.out.println("navigated to Url: " + driver.getCurrentUrl());

		driver.navigate().back();
		wait.until(d -> driver.findElement(By.xpath("//div[contains(@id,'md')]/p/a")).isDisplayed());
		System.out.println("Back to Url: " + driver.getCurrentUrl());		

		WebElement footerbottomCopyrightLink = driver.findElement(By.xpath("//div[contains(@id,'md')]/p/a"));
		hoverJS.mouseHoverJScript(footerbottomCopyrightLink, driver);
		highLightElementClass.highlightElement(driver, footerbottomCopyrightLink);
		footerbottomCopyrightLink.click();
		wait.until(d -> driver.findElement(By.xpath("//h1/a")).isDisplayed());
		highLightElementClass.highlightElement(driver, driver.findElement(By.xpath("//h1")));
		assertEquals("https://novoproso.com/index.html", driver.getCurrentUrl());
		assertEquals("Novo ProSo, Inc.", driver.getTitle());
		System.out.println("navigated to Url: " + driver.getCurrentUrl());		

		driver.navigate().back();
		wait.until(d -> driver.findElement(By.xpath("//div[contains(@id,'md')]/p/a")).isDisplayed());
		System.out.println("Back to Url: " + driver.getCurrentUrl());		
		
		WebElement designedByElement = driver.findElement(By.xpath("//p[contains(@class,'pull-right')]"));
		jsExecutor.executeScript("arguments[0].scrollIntoView();", designedByElement);
		assertEquals("Designed by Novo ProSo", designedByElement.getText());
		highLightElementClass.highlightElement(driver, designedByElement);

//		WebElement designedByLinkElement = driver.findElement(By.xpath("//p[contains(@class,'pull-right')]/a"));
//		hoverJS.mouseHoverJScript(designedByLinkElement, driver);
//		highLightElementClass.highlightElement(driver, designedByLinkElement);
//		wait.until(ExpectedConditions.elementToBeClickable(designedByLinkElement));
//		designedByLinkElement.click();
//		wait.until(ExpectedConditions.urlToBe("https://novoproso.com/"));
//		wait.until(d -> driver.findElement(By.xpath("//h1")).isDisplayed());
//		highLightElementClass.highlightElement(driver, driver.findElement(By.xpath("//h1")));		
//		assertEquals("https://novoproso.com/", driver.getCurrentUrl());
//		System.out.println("navigated to Url: " + driver.getCurrentUrl());		
//
//		driver.navigate().back();
//		wait.until(d -> driver.findElement(By.xpath("//div[contains(@id,'md')]/p/a")).isDisplayed());
//		System.out.println("Back to Url: " + driver.getCurrentUrl());				
	}
}
