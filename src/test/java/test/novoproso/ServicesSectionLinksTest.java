package test.novoproso;


import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import test.novoproso.utils.BrowserSetUp;
import test.novoproso.utils.highLightElement;
import test.novoproso.utils.mouseHoverJS;


class ServicesSectionLinksTest {

	//chrome, msedge, firefox
	static String browser = "msedge";
//	static RemoteWebDriver driver; 
//	static DesiredCapabilities capabilities = new DesiredCapabilities();

	static WebDriver driver;
	static JavascriptExecutor jsExecutor;
	static WebDriverWait wait, elementWait;
//	static RemoteWebDriver driver;//it includes JavascriptExecutor, TakesScreenshot functionality
	static Actions actions;
	static mouseHoverJS hoverJS;
	static highLightElement highLightElementClass;
	static BrowserSetUp browserSetUp = new BrowserSetUp();
	
	@BeforeAll
	static void setUp() throws Exception {
		driver = browserSetUp.getBrowserSetUp(browser);
		
//		capabilities.setBrowserName("chrome");
//		capabilities.setPlatform(Platform.WIN11);
//		driver = new RemoteWebDriver(new URL("http://localhost:4444/"), capabilities);
//		driver.manage().window().maximize();
		
		jsExecutor = (JavascriptExecutor) driver;//casting webdriver to JavascriptExecutor
		actions = new Actions(driver);
		
		hoverJS = new mouseHoverJS();
		highLightElementClass = new highLightElement();
	}

	@AfterAll
	static void tearDown() throws Exception {
		driver.quit();
	}

	@BeforeEach
	void openUrl() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://novoproso.com");

		wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(700));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1")));		
		wait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		hoverJS.mouseHoverJScript(startnow, driver);
		highLightElementClass.highlightElement(driver, startnow);
		startnow.click();

		//wait until about us link in the header appears
		wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());
		wait.until(d -> driver.findElement(By.xpath("//section[contains(@id,'about-us')]//div[contains(@class,'about-info')]/h2")).isDisplayed());

		//go to services link and click
		WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
		highLightElementClass.highlightElement(driver, servicesLink);
		System.out.println("services nav element : " + servicesLink.getText());

		driver.findElement(By.xpath("//li/a[contains(@href,'#services')]")).click();

		//From services section
		WebElement servicesHeading = driver.findElement(By.xpath("//div[contains(@class,'services-heading')]/h2"));
		//wait until services section is visible
		wait.until(d -> servicesHeading.isDisplayed());
	}

	
//	@Disabled
	@Test
	void loopLinkTest() throws InterruptedException {			
		//close cookie button
		driver.findElement(By.xpath("//div[contains(@class,'cookie-container')]/p/button")).click();
		
		//get services elements
		List<WebElement> services = driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]"));

//		elementWait.until(d -> services.get(0).isDisplayed());			

		for (WebElement service : services) {
			int index = services.indexOf(service);
			
			System.out.println("index: " + index);
			
			jsExecutor.executeScript("arguments[0].scrollIntoView();", driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(index));			
			wait.until(d -> driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(index).isDisplayed());		
			
			//hover over first service read more link
			hoverJS.mouseHoverJScript(driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(index), driver);
			highLightElementClass.highlightElement(driver, driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(index));
			driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(index).click();
			
//			wait.until(ExpectedConditions.urlToBe("https://novoproso.com/hra.html"));
			String firsturl = driver.getCurrentUrl();
			System.out.println("Current URL: " + firsturl);
			wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
			highLightElementClass.highlightElement(driver, driver.findElement(By.xpath("//h2")));

			driver.navigate().back();
			
			wait.until(d -> driver.findElement(By.xpath("//div[contains(@class,'services-heading')]/h2")).isDisplayed());			
		}
		
		
	}
	

//	@Disabled
	@Test
	void ideaLinkTest() throws InterruptedException {			
		//get services elements
		List<WebElement> services = driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]"));
		List<WebElement> serviceReadMoreLink = driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a"));		
		elementWait.until(d -> services.get(0).isDisplayed());			
		
		//hover over first service read more link
		hoverJS.mouseHoverJScript(serviceReadMoreLink.get(0), driver);
		highLightElementClass.highlightElement(driver, serviceReadMoreLink.get(0));
		serviceReadMoreLink.get(0).click();
		
		wait.until(ExpectedConditions.urlToBe("https://novoproso.com/idea.html"));
		String firsturl = driver.getCurrentUrl();
		System.out.println("Current URL: " + firsturl);
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		highLightElementClass.highlightElement(driver, driver.findElement(By.xpath("//h2")));

		driver.navigate().back();
		
		wait.until(d -> driver.findElement(By.xpath("//div[contains(@class,'services-heading')]/h2")).isDisplayed());
	}

	
//	@Disabled
	@Test
	void softwareLinkTest() throws InterruptedException {			
		//get services elements
		List<WebElement> services = driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]"));
		List<WebElement> serviceReadMoreLink = driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a"));		
		elementWait.until(d -> services.get(1).isDisplayed());			
		
		//hover over first service read more link
		hoverJS.mouseHoverJScript(serviceReadMoreLink.get(1), driver);
		highLightElementClass.highlightElement(driver, serviceReadMoreLink.get(1));
		serviceReadMoreLink.get(1).click();
		
		wait.until(ExpectedConditions.urlToBe("https://novoproso.com/sd.html"));
		String firsturl = driver.getCurrentUrl();
		System.out.println("Current URL: " + firsturl);
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		highLightElementClass.highlightElement(driver, driver.findElement(By.xpath("//h2")));

		driver.navigate().back();
		
		wait.until(d -> driver.findElement(By.xpath("//div[contains(@class,'services-heading')]/h2")).isDisplayed());
	}

	
//	@Disabled
	@Test
	void itStaffLinkTest() throws InterruptedException {			
		//get services elements
		List<WebElement> services = driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]"));
		List<WebElement> serviceReadMoreLink = driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a"));		
		elementWait.until(d -> services.get(2).isDisplayed());			
		
		//hover over first service read more link
		hoverJS.mouseHoverJScript(serviceReadMoreLink.get(2), driver);
		highLightElementClass.highlightElement(driver, serviceReadMoreLink.get(2));
		serviceReadMoreLink.get(2).click();
		
		wait.until(ExpectedConditions.urlToBe("https://novoproso.com/itstaff.html"));
		String firsturl = driver.getCurrentUrl();
		System.out.println("Current URL: " + firsturl);
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		highLightElementClass.highlightElement(driver, driver.findElement(By.xpath("//h2")));

		driver.navigate().back();
		
		wait.until(d -> driver.findElement(By.xpath("//div[contains(@class,'services-heading')]/h2")).isDisplayed());
	}
	
	
//	@Disabled
	@Test
	void cloudLinkTest() throws InterruptedException {			
		//close cookie button
		driver.findElement(By.xpath("//div[contains(@class,'cookie-container')]/p/button")).click();
		
		//get services elements
		List<WebElement> services = driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]"));
		List<WebElement> serviceReadMoreLink = driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a"));		
		elementWait.until(d -> services.get(0).isDisplayed());			

		jsExecutor.executeScript("arguments[0].scrollIntoView();", driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(3));			
		wait.until(d -> driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(3).isDisplayed());		
		
		//hover over first service read more link
		hoverJS.mouseHoverJScript(serviceReadMoreLink.get(3), driver);
		highLightElementClass.highlightElement(driver, serviceReadMoreLink.get(3));
		serviceReadMoreLink.get(3).click();
		
		wait.until(ExpectedConditions.urlToBe("https://novoproso.com/cloud.html"));
		String firsturl = driver.getCurrentUrl();
		System.out.println("Current URL: " + firsturl);
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		highLightElementClass.highlightElement(driver, driver.findElement(By.xpath("//h2")));

		driver.navigate().back();
		
		wait.until(d -> driver.findElement(By.xpath("//div[contains(@class,'services-heading')]/h2")).isDisplayed());
	}
	
	
//	@Disabled
	@Test
	void aiLinkTest() throws InterruptedException {			
		//close cookie button
		driver.findElement(By.xpath("//div[contains(@class,'cookie-container')]/p/button")).click();
		
		//get services elements
		List<WebElement> services = driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]"));
		List<WebElement> serviceReadMoreLink = driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a"));		
		elementWait.until(d -> services.get(0).isDisplayed());			

		jsExecutor.executeScript("arguments[0].scrollIntoView();", driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(4));			
		wait.until(d -> driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(4).isDisplayed());		
		
		//hover over first service read more link
		hoverJS.mouseHoverJScript(serviceReadMoreLink.get(4), driver);
		highLightElementClass.highlightElement(driver, serviceReadMoreLink.get(4));
		serviceReadMoreLink.get(4).click();
		
		wait.until(ExpectedConditions.urlToBe("https://novoproso.com/ai.html"));
		String firsturl = driver.getCurrentUrl();
		System.out.println("Current URL: " + firsturl);
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		highLightElementClass.highlightElement(driver, driver.findElement(By.xpath("//h2")));

		driver.navigate().back();
		
		wait.until(d -> driver.findElement(By.xpath("//div[contains(@class,'services-heading')]/h2")).isDisplayed());
	}

//	@Disabled
	@Test
	void bigDataLinkTest() throws InterruptedException {			
		//close cookie button
		driver.findElement(By.xpath("//div[contains(@class,'cookie-container')]/p/button")).click();
		
		//get services elements
		List<WebElement> services = driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]"));
		List<WebElement> serviceReadMoreLink = driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a"));		
		elementWait.until(d -> services.get(0).isDisplayed());			

		jsExecutor.executeScript("arguments[0].scrollIntoView();", driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(5));			
		wait.until(d -> driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(5).isDisplayed());		
		
		//hover over first service read more link
		hoverJS.mouseHoverJScript(serviceReadMoreLink.get(5), driver);
		highLightElementClass.highlightElement(driver, serviceReadMoreLink.get(5));
		serviceReadMoreLink.get(5).click();
		
		wait.until(ExpectedConditions.urlToBe("https://novoproso.com/bigData.html"));
		String firsturl = driver.getCurrentUrl();
		System.out.println("Current URL: " + firsturl);
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		highLightElementClass.highlightElement(driver, driver.findElement(By.xpath("//h2")));

		driver.navigate().back();
		
		wait.until(d -> driver.findElement(By.xpath("//div[contains(@class,'services-heading')]/h2")).isDisplayed());
	}

	
//	@Disabled
	@Test
	void hraLinkTest() throws InterruptedException {			
		//close cookie button
		driver.findElement(By.xpath("//div[contains(@class,'cookie-container')]/p/button")).click();
		
		//get services elements
		List<WebElement> services = driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]"));
		List<WebElement> serviceReadMoreLink = driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a"));		
		elementWait.until(d -> services.get(0).isDisplayed());			

		jsExecutor.executeScript("arguments[0].scrollIntoView();", driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(6));			
		wait.until(d -> driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(6).isDisplayed());		
		
		//hover over first service read more link
		hoverJS.mouseHoverJScript(serviceReadMoreLink.get(6), driver);
		highLightElementClass.highlightElement(driver, serviceReadMoreLink.get(6));
		serviceReadMoreLink.get(6).click();
		
		wait.until(ExpectedConditions.urlToBe("https://novoproso.com/hra.html"));
		String firsturl = driver.getCurrentUrl();
		System.out.println("Current URL: " + firsturl);
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		highLightElementClass.highlightElement(driver, driver.findElement(By.xpath("//h2")));

		driver.navigate().back();
		
		wait.until(ExpectedConditions.urlToBe("https://novoproso.com/"));
		wait.until(d -> driver.findElement(By.xpath("//div[contains(@class,'services-heading')]/h2")).isDisplayed());
	}

//	@Disabled
	@Test
	void itpmLinkTest() throws InterruptedException {			
		//close cookie button
		driver.findElement(By.xpath("//div[contains(@class,'cookie-container')]/p/button")).click();
		
		//get services elements
		List<WebElement> services = driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]"));
		List<WebElement> serviceReadMoreLink = driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a"));		
		elementWait.until(d -> services.get(0).isDisplayed());			

		jsExecutor.executeScript("arguments[0].scrollIntoView();", driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(7));			
		wait.until(d -> driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(7).isDisplayed());		
		
		//hover over first service read more link
		hoverJS.mouseHoverJScript(serviceReadMoreLink.get(7), driver);
		highLightElementClass.highlightElement(driver, serviceReadMoreLink.get(7));
		serviceReadMoreLink.get(7).click();
		
		wait.until(ExpectedConditions.urlToBe("https://novoproso.com/itpm.html"));
		String firsturl = driver.getCurrentUrl();
		System.out.println("Current URL: " + firsturl);
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		highLightElementClass.highlightElement(driver, driver.findElement(By.xpath("//h2")));

		driver.navigate().back();
		
		wait.until(d -> driver.findElement(By.xpath("//div[contains(@class,'services-heading')]/h2")).isDisplayed());
	}

//	@Disabled
	@Test
	void networkLinkTest() throws InterruptedException {			
		//close cookie button
		driver.findElement(By.xpath("//div[contains(@class,'cookie-container')]/p/button")).click();
		
		//get services elements
		List<WebElement> services = driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]"));
		List<WebElement> serviceReadMoreLink = driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a"));		
		elementWait.until(d -> services.get(0).isDisplayed());			

		jsExecutor.executeScript("arguments[0].scrollIntoView();", driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(8));			
		wait.until(d -> driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(8).isDisplayed());		
		
		//hover over first service read more link
		hoverJS.mouseHoverJScript(serviceReadMoreLink.get(8), driver);
		highLightElementClass.highlightElement(driver, serviceReadMoreLink.get(8));
		serviceReadMoreLink.get(8).click();
		
		wait.until(ExpectedConditions.urlToBe("https://novoproso.com/network.html"));
		String firsturl = driver.getCurrentUrl();
		System.out.println("Current URL: " + firsturl);
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		highLightElementClass.highlightElement(driver, driver.findElement(By.xpath("//h2")));

		driver.navigate().back();
		
		wait.until(d -> driver.findElement(By.xpath("//div[contains(@class,'services-heading')]/h2")).isDisplayed());
	}
	
	
}
