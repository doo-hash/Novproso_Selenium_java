package test.novoproso;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.manager.SeleniumManager;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

class homePage {

	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait wait, imageSliderWait, imageWait, elementWait, initialWait;
	RemoteWebDriver remotedriver;//it includes JavascriptExecutor, TakesScreenshot functionality
	Actions action;
	SeleniumManager seleniumManager;
	ChromeOptions chromeoptions;
	
	@BeforeEach
	void setUp() throws Exception {
		
		driver = new FirefoxDriver();
//		EdgeOptions edgeOptions = new EdgeOptions();		
//		edgeOptions.addArguments("start-maximized");
//		driver = new EdgeDriver(edgeOptions);

//		chromeoptions = new ChromeOptions();
//		driver = new RemoteWebDriver(new URL("http://localhost:4444"), chromeoptions);
//		chromeoptions.addArguments("start-maximized");
//		driver = new ChromeDriver(chromeoptions);
		
		jsExecutor = (JavascriptExecutor) driver;//casting webdriver to JavascriptExecutor
		action = new Actions(driver);
//		remotedriver = new ChromeDriver();
	}

	@AfterEach
	void tearDown() throws Exception {
		//has to do later
		driver.quit();
//		remotedriver.quit();
	}

	public void highlightElement(WebDriver driver, WebElement element) {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].setAttribute('style', 'background: yellow;border: 2px solid red;')", element);
	}
	
	@Disabled
	@Test
	void cookiePolicyTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://novoproso.com");

		elementWait = new WebDriverWait(driver, Duration.ofMillis(500));
		elementWait.until(d -> driver.findElement(By.className("cookie-container")).isDisplayed());
		assertEquals("rgb(47, 54, 64)", driver.findElement(By.className("cookie-container")).getCssValue("background-color"));
		assertEquals("rgb(245, 246, 250)", driver.findElement(By.className("cookie-container")).getCssValue("color"));
		System.out.println("P element in cookie policy: " + driver.findElements(By.xpath("//p")).get(0).getText());
		System.out.println("a element in cookie policy: " + driver.findElements(By.xpath("//p/a")).get(0).getText());
		action.moveToElement(driver.findElements(By.xpath("//p/a")).get(0)).perform();
		Thread.sleep(500);
		assertEquals("rgb(252, 208, 93)", driver.findElements(By.xpath("//p/a")).get(0).getCssValue("color"));		
		assertEquals("policy.html", driver.findElements(By.xpath("//p/a")).get(0).getDomAttribute("href"));		
		WebElement btnElement = driver.findElement(By.xpath("//p/button"));
		highlightElement(driver, driver.findElements(By.xpath("//p/a")).get(0));
		highlightElement(driver, btnElement);
		WebElement cookieLocation = driver.findElement(By.className("cookie-container"));
		System.out.println("before btn click: " + cookieLocation.getRect());
		System.out.println("before btn click: " + cookieLocation.getLocation());
		btnElement.click();
		WebElement cookieNewLocation = driver.findElement(By.className("cookie-container"));
		System.out.println("after btn click: " + cookieNewLocation.getRect());
		System.out.println("after btn click: " + cookieNewLocation.getLocation());
		assertFalse(cookieNewLocation.getLocation().equals(cookieLocation.getLocation()));
	}

	@Disabled
	@Test
	void navBarTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://novoproso.com");

		imageWait = new WebDriverWait(driver, Duration.ofMillis(3000));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(600));
		
		imageWait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		elementWait.until(d -> startnow.isDisplayed());
		highlightElement(driver, startnow);
		startnow.click();
		Thread.sleep(1000);

		WebElement homeLink = driver.findElement(By.xpath("//li/a[@href='#home']"));
		System.out.println("home nav element : " + homeLink.getText());
		highlightElement(driver, homeLink);

		WebElement aboutUsLink = driver.findElement(By.xpath("//li/a[@href='#about-us']"));
		WebElement aboutUsDropdownElement = driver.findElements(By.xpath("//li/ul")).get(0);
		elementWait.until(ExpectedConditions.visibilityOf(aboutUsLink));
		System.out.println("about us nav element : " + aboutUsLink.getText());
		assertEquals("rgb(252, 208, 93)", aboutUsLink.getCssValue("background-color"));
		highlightElement(driver, aboutUsLink);
		action.moveToElement(aboutUsLink).perform();
		elementWait.until(d -> aboutUsDropdownElement.isDisplayed());
		WebElement csrElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'csr.html')]"));
		WebElement localSportsElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'localSports.html')]"));
		action.moveToElement(csrElement).perform();
		Thread.sleep(500);
		assertEquals("rgb(32, 158, 72)", csrElement.getCssValue("color"));
		assertEquals("csr.html", csrElement.getDomAttribute("href"));
		highlightElement(driver, csrElement);
		action.moveToElement(localSportsElement).perform();
		Thread.sleep(500);
		assertEquals("rgb(32, 158, 72)", localSportsElement.getCssValue("color"));
		assertEquals("localSports.html", localSportsElement.getDomAttribute("href"));
		highlightElement(driver, localSportsElement);
		
		Thread.sleep(1000);
		WebElement productsLink = driver.findElement(By.xpath("//li/a[@href='#products']"));
		highlightElement(driver, productsLink);
		action.moveToElement(productsLink).perform();
		System.out.println("products nav element : " + productsLink.getText());
		WebElement productsDropdownElement = driver.findElements(By.xpath("//li/ul")).get(1);
		elementWait.until(d -> productsDropdownElement.isDisplayed());
		Thread.sleep(500);
		WebElement danElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'DAN.html')]"));
		WebElement bdIngensionElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'BDIngension.html')]"));
		action.moveToElement(danElement).perform();
		Thread.sleep(500);
		assertEquals("rgb(32, 158, 72)", danElement.getCssValue("color"));
		assertEquals("DAN.html", danElement.getDomAttribute("href"));
		highlightElement(driver, danElement);
		action.moveToElement(bdIngensionElement).perform();
		Thread.sleep(500);
		assertEquals("rgb(32, 158, 72)", bdIngensionElement.getCssValue("color"));
		assertEquals("BDIngension.html", bdIngensionElement.getDomAttribute("href"));
		highlightElement(driver, bdIngensionElement);
		
		Thread.sleep(1000);
		WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
		System.out.println("services nav element : " + servicesLink.getText());
		highlightElement(driver, servicesLink);
		action.moveToElement(servicesLink).perform();
		WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
		elementWait.until(d -> servicesDropdownElement.isDisplayed());
		Thread.sleep(500);
		WebElement ideaElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'idea.html')]"));
		WebElement sdElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'sd.html')]"));
		WebElement itstaffElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'itstaff.html')]"));
		WebElement cloudElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'cloud.html')]"));
		WebElement aiElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'ai.html')]"));
		WebElement bigDataElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'bigData.html')]"));
		WebElement hraElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'hra.html')]"));
		WebElement itpmElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'itpm.html')]"));
		WebElement networkElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'network.html')]"));
		
		action.moveToElement(ideaElement).perform();
		Thread.sleep(500);
		assertEquals("rgb(32, 158, 72)", ideaElement.getCssValue("color"));
		assertEquals("idea.html", ideaElement.getDomAttribute("href"));
		highlightElement(driver, ideaElement);
		action.moveToElement(sdElement).perform();
		Thread.sleep(500);
		assertEquals("rgb(32, 158, 72)", sdElement.getCssValue("color"));
		assertEquals("sd.html", sdElement.getDomAttribute("href"));
		highlightElement(driver, sdElement);
		action.moveToElement(itstaffElement).perform();
		Thread.sleep(500);
		assertEquals("rgb(32, 158, 72)", itstaffElement.getCssValue("color"));
		assertEquals("itstaff.html", itstaffElement.getDomAttribute("href"));
		highlightElement(driver, itstaffElement);
		action.moveToElement(cloudElement).perform();
		Thread.sleep(500);
		assertEquals("rgb(32, 158, 72)", cloudElement.getCssValue("color"));
		assertEquals("cloud.html", cloudElement.getDomAttribute("href"));
		highlightElement(driver, cloudElement);
		action.moveToElement(aiElement).perform();
		Thread.sleep(500);
		assertEquals("rgb(32, 158, 72)", aiElement.getCssValue("color"));
		assertEquals("ai.html", aiElement.getDomAttribute("href"));
		highlightElement(driver, aiElement);
		action.moveToElement(bigDataElement).perform();
		Thread.sleep(500);
		assertEquals("rgb(32, 158, 72)", bigDataElement.getCssValue("color"));
		assertEquals("bigData.html", bigDataElement.getDomAttribute("href"));
		highlightElement(driver, bigDataElement);
		action.moveToElement(hraElement).perform();
		Thread.sleep(500);
		assertEquals("rgb(32, 158, 72)", hraElement.getCssValue("color"));
		assertEquals("hra.html", hraElement.getDomAttribute("href"));
		highlightElement(driver, hraElement);
		action.moveToElement(itpmElement).perform();
		Thread.sleep(500);
		assertEquals("rgb(32, 158, 72)", itpmElement.getCssValue("color"));
		assertEquals("itpm.html", itpmElement.getDomAttribute("href"));
		highlightElement(driver, itpmElement);
		action.moveToElement(networkElement).perform();
		Thread.sleep(500);
		assertEquals("rgb(32, 158, 72)", networkElement.getCssValue("color"));
		assertEquals("network.html", networkElement.getDomAttribute("href"));
		highlightElement(driver, networkElement);
		
		Thread.sleep(1000);
		WebElement careersLink = driver.findElement(By.xpath("//li/a[@href='#career']"));
		System.out.println("careers nav element : " + careersLink.getText());
		highlightElement(driver, careersLink);
		action.moveToElement(careersLink).perform();
		Thread.sleep(500);
		
		WebElement contactLink = driver.findElement(By.xpath("//li/a[@href='#contact']"));
		highlightElement(driver, contactLink);
		System.out.println("contact us nav element : " + contactLink.getText());
		action.moveToElement(contactLink).perform();
		Thread.sleep(500);
	}


	@Disabled
	@Test
	void aboutUsSectionTest() throws InterruptedException {
		driver.manage().window().maximize();

		//
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://novoproso.com");

		//
		wait = new WebDriverWait(driver, Duration.ofMillis(600));
	
		//
	
		//fetch elements from image block
		WebElement startnow = driver.findElement(By.linkText("START NOW"));
		highlightElement(driver, startnow);
		
		//start now button upon image
		wait.until(ExpectedConditions.visibilityOf(startnow));
		startnow.click();

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("about-us"))));
		assertTrue(driver.findElement(By.className("main-nav")).isDisplayed());
		assertTrue(driver.findElement(By.className("cookie-container")).isDisplayed());
		
		
		assertEquals("url(\"https://novoproso.com/images/about-bg.jpg\")", driver.findElement(By.id("about-us")).getCssValue("background-image"));
		wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.cssSelector("h2")).get(0)));
		assertEquals("rgba(252, 208, 93, 1)", driver.findElement(By.linkText("ABOUT US")).getCssValue("background-color"));
//		assertEquals("About us", driver.findElement(By.xpath("//h2[.='About us']")).getText());
		assertEquals("About us", driver.findElements(By.cssSelector("h2")).get(0).getText());
		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h2")).get(0).getCssValue("color"));
		assertEquals("Core Values", driver.findElements(By.cssSelector("h2")).get(1).getText());
		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h2")).get(1).getCssValue("color"));
		assertEquals("Certifications", driver.findElements(By.cssSelector("h2")).get(2).getText());
		assertEquals("rgba(255, 255, 255, 1)", driver.findElements(By.cssSelector("h2")).get(2).getCssValue("color"));
		assertEquals(24, driver.findElements(By.cssSelector("ul")).size());		
		assertEquals(55, driver.findElements(By.cssSelector("li")).size());
	}

	@Disabled
	@Test
	void productsSectionTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://novoproso.com");

		imageWait = new WebDriverWait(driver, Duration.ofMillis(3000));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(600));
		
		imageWait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		elementWait.until(d -> startnow.isDisplayed());
		highlightElement(driver, startnow);
		startnow.click();

		//wait until about us link in the header appears
		elementWait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());

		//go to products link and click
		WebElement productsLink = driver.findElement(By.xpath("//li/a[@href='#products']"));
		highlightElement(driver, productsLink);
		System.out.println("products nav element : " + productsLink.getText());
		action.moveToElement(productsLink).perform();
//		Thread.sleep(500);
		productsLink.click();

		//From products section
		WebElement productsHeading = driver.findElement(By.xpath("//div[contains(@class,'products-heading')]/h2"));
		//wait until products section is visible
		elementWait.until(d -> productsHeading.isDisplayed());

		//assert main heading
		highlightElement(driver, productsHeading);
		System.out.println("Main Heading in Products: " + productsHeading);
		assertEquals("PRODUCTS", productsHeading.getText());

		//sub heading
		WebElement subHeading = driver.findElement(By.xpath("//div[contains(@class,'products-heading')]/h4"));
		System.out.println("Sub Heading in Products: " + subHeading);
		assertEquals("Projects Under Development", subHeading.getText());
		assertEquals("rgb(128, 199, 131)", subHeading.getCssValue("color"));
		highlightElement(driver, subHeading);

		//get product elements
		List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class,'products-content')]/div[contains(@class,'product')]"));
		List<WebElement> productName = driver.findElements(By.xpath("//div[contains(@class,'product')]/div[contains(@class,'product-heading')]/h4"));
		List<WebElement> productImage = driver.findElements(By.xpath("//div[contains(@class,'product')]/div[contains(@class,'product-image')]/img"));
		List<WebElement> productContent = driver.findElements(By.xpath("//div[contains(@class,'product')]/div[contains(@class,'product-content')]/p"));
		List<WebElement> productReadMoreLink = driver.findElements(By.xpath("//div[contains(@class,'product-button')]/a"));
		
		for(WebElement product: products) {
			int index = products.indexOf(product);
			elementWait.until(d -> product.isDisplayed());
			System.out.println("Product Name: " + productName.get(index).getText());
			System.out.println("Product Image: " + productImage.get(index).getDomAttribute("src"));
			System.out.println("Product Content: " + productContent.get(index).getText());
			System.out.println("Product Readmore Link: " + productReadMoreLink.get(index).getText());
			System.out.println("Product Readmore Link: " + productReadMoreLink.get(index).getDomAttribute("href"));
			
			//Checking Styles
			assertEquals("rgb(57, 144, 30)", productName.get(index).getCssValue("color"));
			assertEquals("1.6px solid rgb(128, 199, 131)", driver.findElements(By.xpath("//div[contains(@class,'product')]/div[contains(@class,'product-content')]")).get(index).getCssValue("border"));
			assertEquals("rgb(220, 245, 214)", driver.findElements(By.xpath("//div[contains(@class,'product')]/div[contains(@class,'product-content')]")).get(index).getCssValue("background-color"));
			assertEquals("rgba(0, 0, 0, 0.6)", productContent.get(index).getCssValue("color"));
			assertEquals("rgb(128, 128, 128)", productReadMoreLink.get(index).getCssValue("color"));
			
			highlightElement(driver, productName.get(index));
			highlightElement(driver, productImage.get(index));
			highlightElement(driver, productContent.get(index));

			//hover over readmore link
			action.moveToElement(productReadMoreLink.get(index)).perform();
			Thread.sleep(600);
			assertEquals("rgb(57, 144, 30)", productReadMoreLink.get(index).getCssValue("color"));
			assertEquals("2.4px solid rgb(128, 199, 131)", productReadMoreLink.get(index).getCssValue("border"));
			highlightElement(driver, productReadMoreLink.get(index));
			Thread.sleep(2000);
		}
	}

	@Disabled
	@Test
	void productsSectionLinksTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://novoproso.com");

		wait = new WebDriverWait(driver, Duration.ofMillis(3000));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(600));
		
		wait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		elementWait.until(d -> startnow.isDisplayed());
		highlightElement(driver, startnow);
		startnow.click();

		//wait until about us link in the header appears
		elementWait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());

		//go to products link and click
		WebElement productsLink = driver.findElement(By.xpath("//li/a[@href='#products']"));
		highlightElement(driver, productsLink);
		System.out.println("products nav element : " + productsLink.getText());
		action.moveToElement(productsLink).perform();
//		Thread.sleep(500);
		productsLink.click();

		//From products section
		WebElement productsHeading = driver.findElement(By.xpath("//div[contains(@class,'products-heading')]/h2"));
		//wait until products section is visible
		elementWait.until(d -> productsHeading.isDisplayed());

		//get product elements
		List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class,'products-content')]/div[contains(@class,'product')]"));
		List<WebElement> productReadMoreLink = driver.findElements(By.xpath("//div[contains(@class,'product-button')]/a"));
		
		elementWait.until(d -> products.get(0).isDisplayed());			
		
		//hover over first product read more link
		action.moveToElement(productReadMoreLink.get(0)).perform();
		Thread.sleep(500);
		productReadMoreLink.get(0).click();
		String url = driver.getCurrentUrl();
		System.out.println("Current URL: " + url);
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		highlightElement(driver, driver.findElement(By.xpath("//h2")));
		driver.navigate().back();
		wait.until(d -> driver.findElement(By.xpath("//div[contains(@class,'products-heading')]/h2")).isDisplayed());
		
		//hover over second product read more link
		action.moveToElement(driver.findElements(By.xpath("//div[contains(@class,'product-button')]/a")).get(1)).perform();
		Thread.sleep(500);
		driver.findElements(By.xpath("//div[contains(@class,'product-button')]/a")).get(1).click();
		String prdcturl = driver.getCurrentUrl();
		System.out.println("Current URL: " + prdcturl);
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		highlightElement(driver, driver.findElement(By.xpath("//h2")));
		driver.navigate().back();
		wait.until(d -> driver.findElement(By.xpath("//div[contains(@class,'products-heading')]/h2")).isDisplayed());
	}
	
	@Disabled
	@Test
	void ServicesSectionTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://novoproso.com");

		imageWait = new WebDriverWait(driver, Duration.ofMillis(3000));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(1000));
		
		imageWait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		elementWait.until(d -> startnow.isDisplayed());
		highlightElement(driver, startnow);
		startnow.click();

		//wait until about us link in the header appears
		elementWait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());

		//go to services link and click
		WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
		highlightElement(driver, servicesLink);
		System.out.println("services nav element : " + servicesLink.getText());
		action.moveToElement(servicesLink).perform();
//		Thread.sleep(500);
		servicesLink.click();

		//From services section
		WebElement servicesHeading = driver.findElement(By.xpath("//div[contains(@class,'services-heading')]/h2"));
		//wait until products section is visible
		elementWait.until(d -> servicesHeading.isDisplayed());
		
		//assert main heading
		highlightElement(driver, servicesHeading);
		System.out.println("Main Heading in Services: " + servicesHeading);
		assertEquals("SERVICES", servicesHeading.getText());

		//get services elements
		List<WebElement> services = driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]"));
		List<WebElement> serviceImage = driver.findElements(By.xpath("//div[contains(@class,'service')]/div[contains(@class,'image')]/img"));
		List<WebElement> serviceName = driver.findElements(By.xpath("//div[contains(@class,'service')]/div[contains(@class,'content')]/h4"));
		List<WebElement> serviceContent = driver.findElements(By.xpath("//div[contains(@class,'service')]/div[contains(@class,'content')]/p"));
		List<WebElement> serviceReadMoreLink = driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a"));
		
		for(WebElement service: services) {
			int index = services.indexOf(service);
			if(!service.isDisplayed()) {
				System.out.println("index: " + index);
				jsExecutor.executeScript("arguments[0].scrollIntoView();",service);
			}
			
			elementWait.until(d -> service.isDisplayed());
			System.out.println("Service Name: " + serviceName.get(index).getText());
			System.out.println("Service Image: " + serviceImage.get(index).getDomAttribute("src"));
			System.out.println("Service Content: " + serviceContent.get(index).getText());
			System.out.println("Service Readmore Link: " + serviceReadMoreLink.get(index).getText());
			System.out.println("Service Readmore Link: " + serviceReadMoreLink.get(index).getDomAttribute("href"));
			
			//Checking Styles
			assertEquals("rgb(57, 144, 30)", serviceName.get(index).getCssValue("color"));
			assertEquals("0.8px solid rgb(128, 199, 131)", driver.findElements(By.xpath("//div[contains(@class,'service')]/div[contains(@class,'content')]")).get(index).getCssValue("border"));
			assertEquals("rgb(220, 245, 214)", driver.findElements(By.xpath("//div[contains(@class,'service')]/div[contains(@class,'content')]")).get(index).getCssValue("background-color"));
			assertEquals("rgb(128, 128, 128)", serviceContent.get(index).getCssValue("color"));
			assertEquals("rgb(128, 128, 128)", serviceReadMoreLink.get(index).getCssValue("color"));
			
			highlightElement(driver, serviceName.get(index));
			highlightElement(driver, serviceImage.get(index));
			highlightElement(driver, serviceContent.get(index));

			//hover over readmore link
			action.moveToElement(serviceReadMoreLink.get(index)).perform();
			Thread.sleep(800);
			assertEquals("rgb(57, 144, 30)", serviceReadMoreLink.get(index).getCssValue("color"));
			assertEquals("2.4px solid rgb(128, 199, 131)", serviceReadMoreLink.get(index).getCssValue("border"));
			highlightElement(driver, serviceReadMoreLink.get(index));
			Thread.sleep(2000);
		}
	}

	@Disabled
	@Test
	void servicesSectionLinksTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://novoproso.com");

		wait = new WebDriverWait(driver, Duration.ofMillis(3000));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(600));
		
		wait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		elementWait.until(d -> startnow.isDisplayed());
		highlightElement(driver, startnow);
		startnow.click();

		//wait until about us link in the header appears
		elementWait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());

		//go to services link and click
		WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
		highlightElement(driver, servicesLink);
		System.out.println("services nav element : " + servicesLink.getText());
		action.moveToElement(servicesLink).perform();
//		Thread.sleep(500);
		servicesLink.click();

		//From services section
		WebElement servicesHeading = driver.findElement(By.xpath("//div[contains(@class,'services-heading')]/h2"));
		//wait until products section is visible
		wait.until(d -> servicesHeading.isDisplayed());
		
		//get services elements
		List<WebElement> services = driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]"));
		List<WebElement> serviceReadMoreLink = driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a"));		
		elementWait.until(d -> services.get(0).isDisplayed());			
		
		//hover over first service read more link
		action.moveToElement(serviceReadMoreLink.get(0)).perform();
		Thread.sleep(500);
		serviceReadMoreLink.get(0).click();
		String firsturl = driver.getCurrentUrl();
		System.out.println("Current URL: " + firsturl);
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		highlightElement(driver, driver.findElement(By.xpath("//h2")));
		driver.navigate().back();
		wait.until(d -> driver.findElement(By.xpath("//div[contains(@class,'services-heading')]/h2")).isDisplayed());
		
		//hover over second service read more link
		elementWait.until(d -> driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(1).isDisplayed());			
		action.moveToElement(driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(1)).perform();
		Thread.sleep(500);
		driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(1).click();
		String secondUrl = driver.getCurrentUrl();
		System.out.println("Current URL: " + secondUrl);
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		highlightElement(driver, driver.findElement(By.xpath("//h2")));
		driver.navigate().back();
		wait.until(d -> driver.findElement(By.xpath("//div[contains(@class,'services-heading')]/h2")).isDisplayed());
		
		//hover over third service read more link
		elementWait.until(d -> driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(2).isDisplayed());			
		action.moveToElement(driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(2)).perform();
		Thread.sleep(500);
		driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(2).click();
		String thirdUrl = driver.getCurrentUrl();
		System.out.println("Current URL: " + thirdUrl);
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		highlightElement(driver, driver.findElement(By.xpath("//h2")));
		driver.navigate().back();
		wait.until(d -> driver.findElement(By.xpath("//div[contains(@class,'services-heading')]/h2")).isDisplayed());
		
		//hover over fourth service read more link
		jsExecutor.executeScript("arguments[0].scrollIntoView();", driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(3));
		
		Thread.sleep(2000);
		wait.until(d -> driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(3).isDisplayed());		
		action.moveToElement(driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(3)).perform();
		Thread.sleep(500);
		driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(3).click();
		String fourthUrl = driver.getCurrentUrl();
		System.out.println("Current URL: " + fourthUrl);
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		highlightElement(driver, driver.findElement(By.xpath("//h2")));
		driver.navigate().back();
		wait.until(d -> driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(3).isDisplayed());
		
		//hover over fifth service read more link
		action.moveToElement(driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(4)).perform();
		Thread.sleep(500);
		driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(4).click();
		String fifthUrl = driver.getCurrentUrl();
		System.out.println("Current URL: " + fifthUrl);
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		highlightElement(driver, driver.findElement(By.xpath("//h2")));
		driver.navigate().back();
		wait.until(d -> driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(4).isDisplayed());			

		
		//hover over sixth service read more link
		action.moveToElement(driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(5)).perform();
		Thread.sleep(500);
		driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(5).click();
		String sixthUrl = driver.getCurrentUrl();
		System.out.println("Current URL: " + sixthUrl);
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		highlightElement(driver, driver.findElement(By.xpath("//h2")));
		driver.navigate().back();
		wait.until(d -> driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(5).isDisplayed());			
		
		//hover over seventh service read more link
		jsExecutor.executeScript("arguments[0].scrollIntoView();", driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(6));
		
		Thread.sleep(500);
		wait.until(d -> driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(6).isDisplayed());
		action.moveToElement(driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(6)).perform();
		Thread.sleep(500);
		driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(6).click();
		String seventhUrl = driver.getCurrentUrl();
		System.out.println("Current URL: " + seventhUrl);
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		highlightElement(driver, driver.findElement(By.xpath("//h2")));
		driver.navigate().back();
		wait.until(d -> driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(6).isDisplayed());			
		
		//hover over eighth service read more link
		wait.until(d -> driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(7).isDisplayed());			
		action.moveToElement(driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(7)).perform();
		Thread.sleep(500);
		driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(7).click();
		String eighthUrl = driver.getCurrentUrl();
		System.out.println("Current URL: " + eighthUrl);
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		highlightElement(driver, driver.findElement(By.xpath("//h2")));
		driver.navigate().back();
		wait.until(d -> driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(7).isDisplayed());			
		
		//hover over ninth service read more link
		wait.until(d -> driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(8).isDisplayed());			
		action.moveToElement(driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(8)).perform();
		Thread.sleep(500);
		driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(8).click();
		String ninthUrl = driver.getCurrentUrl();
		System.out.println("Current URL: " + ninthUrl);
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		highlightElement(driver, driver.findElement(By.xpath("//h2")));
		driver.navigate().back();
		wait.until(d -> driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(8).isDisplayed());			

	}
	
	@Disabled
	@Test
	void careersSectionTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://novoproso.com");

		imageWait = new WebDriverWait(driver, Duration.ofMillis(3000));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(1000));
		
		imageWait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		elementWait.until(d -> startnow.isDisplayed());
		highlightElement(driver, startnow);
		startnow.click();

		//wait until about us link in the header appears
		elementWait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());

		//go to careers link and click
		WebElement careersLink = driver.findElement(By.xpath("//li/a[@href='#career']"));
		highlightElement(driver, careersLink);
		System.out.println("careers nav element : " + careersLink.getText());
		action.moveToElement(careersLink).perform();
		Thread.sleep(500);
		careersLink.click();

		//From careers section
		WebElement careersHeading = driver.findElement(By.xpath("//div[contains(@class,'careers-heading')]/h2"));

		//wait until careers section is visible
		elementWait.until(d -> careersHeading.isDisplayed());
		
		//close cookie
		driver.findElement(By.xpath("//button[contains(@class,'cookie-btn')]")).click();
		Thread.sleep(500);

		//heading block background color
		assertEquals("rgb(220, 245, 214)", driver.findElement(By.xpath("//div[contains(@class,'careers-heading-info')]")).getCssValue("background-color"));
		
		//assert main heading
		highlightElement(driver, careersHeading);
		System.out.println("Main Heading in Careers: " + careersHeading.getText());
		assertEquals("CAREERS", careersHeading.getText());

		//sub heading info
		WebElement subHeading = driver.findElement(By.xpath("//div[contains(@class,'careers-heading')]/h4"));
		assertEquals("rgb(57, 144, 30)", subHeading.getCssValue("color"));
		highlightElement(driver, subHeading);
		System.out.println("SubHeading in Careers: " + subHeading.getText());

		//resume contact
		WebElement contactMailElement = driver.findElement(By.xpath("//div[contains(@class,'career-mail-info')]/h4"));
		assertEquals("rgb(128, 128, 128)", contactMailElement.getCssValue("color"));
		assertEquals("rgb(57, 144, 30)", driver.findElement(By.xpath("//div[contains(@class,'career-mail-info')]/h4/span")).getCssValue("color"));
		assertEquals("600", driver.findElement(By.xpath("//div[contains(@class,'career-mail-info')]/h4/span")).getCssValue("font-weight"));
		highlightElement(driver, contactMailElement);
		System.out.println("Resume contact mail: " + contactMailElement.getText());

		//get career elements
		List<WebElement> careers = driver.findElements(By.xpath("//div[contains(@class,'career-card')]"));
		List<WebElement> jobName = driver.findElements(By.xpath("//div[contains(@class,'career-card')]/h4"));
		List<WebElement> jobPositionheading = driver.findElements(By.xpath("//div[contains(@class,'card-body')]/div/span[contains(text(),'Position ID')]"));
		List<WebElement> jobPosition = driver.findElements(By.xpath("//div[contains(@class,'card-body')]/div/p"));
		List<WebElement> jobDurationHeading = driver.findElements(By.xpath("//div[contains(@class,'card-body')]/div/span[contains(text(),'Length')]"));
		List<WebElement> jobDuration = driver.findElements(By.xpath("//div[contains(@class,'card-body')]/div/p"));
		List<WebElement> jobLocationHeading = driver.findElements(By.xpath("//div[contains(@class,'card-body')]/div/span[contains(text(),'Location')]"));
		List<WebElement> jobLocation = driver.findElements(By.xpath("//div[contains(@class,'card-body')]/div/p"));
		List<WebElement> jobEducationHeading = driver.findElements(By.xpath("//div[contains(@class,'card-body')]/div/span[contains(text(),'Education')]"));
		List<WebElement> jobEducation = driver.findElements(By.xpath("//div[contains(@class,'card-body')]/div/p"));
		List<WebElement> jobDetailsLink = driver.findElements(By.xpath("//div[contains(@class,'card-footer')]/span"));
		
		for(WebElement career: careers) {
			int index = careers.indexOf(career);
			if(!career.isDisplayed()) {
				Thread.sleep(1000);
				System.out.println("index: " + index);
				jsExecutor.executeScript("arguments[0].scrollIntoView();",career);
				Thread.sleep(3000);
			}
			
			elementWait.until(d -> career.isDisplayed());
			System.out.println("job Name: " + jobName.get(index).getText());
			Thread.sleep(500);
			System.out.println(jobPositionheading.get(index).getText() + " : " + jobPosition.get(index*4).getText());
			Thread.sleep(500);
			System.out.println(jobDurationHeading.get(index).getText() + " : " + jobDuration.get((index*4) + 1).getText());
			Thread.sleep(500);
			System.out.println(jobLocationHeading.get(index).getText() + " : " + jobLocation.get((index*4) + 2).getText());
			Thread.sleep(500);
			System.out.println(jobEducationHeading.get(index).getText() + " : " + jobEducation.get((index*4) + 3).getText());
			Thread.sleep(500);
			System.out.println("job Details Link: " + jobDetailsLink.get(index).getText());
			System.out.println("\n");
			
			//Checking Styles
			assertEquals("rgb(1, 110, 14)", jobName.get(index).getCssValue("color"));
			assertEquals("2.4px solid rgb(128, 199, 131)", driver.findElements(By.xpath("//div[contains(@class,'career-card')]")).get(index).getCssValue("border"));
			assertEquals("rgb(255, 255, 255)", driver.findElements(By.xpath("//div[contains(@class,'career-card')]")).get(index).getCssValue("background-color"));
			assertEquals("rgb(0, 0, 0)", jobPositionheading.get(index).getCssValue("color"));
			assertEquals("rgb(128, 128, 128)", jobPosition.get((index*4)).getCssValue("color"));
			assertEquals("rgb(0, 0, 0)", jobDurationHeading.get(index).getCssValue("color"));
			assertEquals("rgb(128, 128, 128)", jobDuration.get((index*4) + 1).getCssValue("color"));			
			assertEquals("rgb(0, 0, 0)", jobLocationHeading.get(index).getCssValue("color"));
			assertEquals("rgb(128, 128, 128)", jobLocation.get((index*4) + 2).getCssValue("color"));
			assertEquals("rgb(0, 0, 0)", jobEducationHeading.get(index).getCssValue("color"));
			assertEquals("rgb(128, 128, 128)", jobEducation.get((index*4) + 3).getCssValue("color"));
			
			assertEquals("0.8px solid rgb(128, 199, 131)", jobDetailsLink.get(index).getCssValue("border"));
			assertEquals("rgb(220, 245, 214)", jobDetailsLink.get(index).getCssValue("background-color"));
			assertEquals("rgb(128, 128, 128)", jobDetailsLink.get(index).getCssValue("color"));
			
			highlightElement(driver, jobName.get(index));
			highlightElement(driver, jobPositionheading.get(index));
			highlightElement(driver, jobPosition.get((index*4)));
			Thread.sleep(500);
			highlightElement(driver, jobDurationHeading.get(index));
			highlightElement(driver, jobDuration.get((index*4) + 1));
			Thread.sleep(500);
			highlightElement(driver, jobLocationHeading.get(index));
			highlightElement(driver, jobLocation.get((index*4) + 2));
			Thread.sleep(500);
			highlightElement(driver, jobEducationHeading.get(index));
			highlightElement(driver, jobEducation.get((index*4) + 3));
			
			//hover over jobDetails link
			action.moveToElement(jobDetailsLink.get(index)).perform();
			Thread.sleep(800);
			assertEquals("rgb(57, 144, 30)", jobDetailsLink.get(index).getCssValue("color"));
			assertEquals("rgb(220, 245, 214)", jobDetailsLink.get(index).getCssValue("background-color"));
			assertEquals("2.4px solid rgb(128, 199, 131)", jobDetailsLink.get(index).getCssValue("border"));
			highlightElement(driver, jobDetailsLink.get(index));
			Thread.sleep(2000);
		}
	}

	@Disabled
	@Test
	void careersSectionDetailsTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://novoproso.com");

		wait = new WebDriverWait(driver, Duration.ofMillis(3000));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(1000));
		
		wait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		elementWait.until(d -> startnow.isDisplayed());
		highlightElement(driver, startnow);
		startnow.click();

		//wait until about us link in the header appears
		elementWait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());

		//go to careers link and click
		WebElement careersLink = driver.findElement(By.xpath("//li/a[@href='#career']"));
		highlightElement(driver, careersLink);
		System.out.println("careers nav element : " + careersLink.getText());
		action.moveToElement(careersLink).perform();
		Thread.sleep(500);
		careersLink.click();

		//From careers section
		WebElement careersHeading = driver.findElement(By.xpath("//div[contains(@class,'careers-heading')]/h2"));

		//wait until careers section is visible
		wait.until(d -> careersHeading.isDisplayed());
		
		//close cookie
		driver.findElement(By.xpath("//button[contains(@class,'cookie-btn')]")).click();
		Thread.sleep(500);
		
		//get career elements
		List<WebElement> careers = driver.findElements(By.xpath("//div[contains(@class,'career-card')]"));
		List<WebElement> jobDetailsLink = driver.findElements(By.xpath("//div[contains(@class,'card-footer')]/span"));

		for(WebElement career: careers) {
			int index = careers.indexOf(career);
			jsExecutor.executeScript("arguments[0].scrollIntoView();", career);
			System.out.println(careers.indexOf(career));
			elementWait.until(d -> career.isDisplayed());
			Thread.sleep(1000);
			//hover over jobDetails link
			action.moveToElement(jobDetailsLink.get(index)).perform();
			Thread.sleep(500);
			jobDetailsLink.get(index).click();
			String[] jobIDArray = {"AIML", "PEGA5","QASEL7","SD241","SD242","NSE18"};
			action.moveToElement(driver.findElement(By.xpath("//div[contains(@id,'"+jobIDArray[index]+"')]"))).perform();
			Thread.sleep(500);
			WebElement jobName = driver.findElement(By.xpath("//div[contains(@id,'"+jobIDArray[index]+"')]/div/div/h2"));
			Thread.sleep(500);
			WebElement jobBasicInfo = driver.findElements(By.xpath("//div[contains(@class,'job-basic-info')]")).get((index));
			Thread.sleep(500);
			WebElement jobMessage = driver.findElement(By.xpath("//div[contains(@id,'"+jobIDArray[index]+"')]/div/div/p"));
			Thread.sleep(500);
			WebElement jobQualification = driver.findElements(By.xpath("//div[contains(@class,'job-qualification')]")).get(index);
			Thread.sleep(500);
			WebElement jobApplyInfo = driver.findElements(By.xpath("//div[contains(@class,'job-apply-info')]")).get(index);
			
			System.out.println("Job Name : "+ jobName.getText());
			highlightElement(driver, jobName);
			Thread.sleep(500);
			System.out.println("Job Basic Info : "+ jobBasicInfo.getText());
			highlightElement(driver, jobBasicInfo);
			Thread.sleep(500);
			System.out.println("Job Qualifications heading: "+ jobQualification.getText());
			highlightElement(driver, jobQualification);
			Thread.sleep(500);
			System.out.println("Job ApplyInfo : "+ jobApplyInfo.getText());
			highlightElement(driver, jobApplyInfo);
			Thread.sleep(500);
			jsExecutor.executeScript("arguments[0].scrollIntoView();",jobMessage);
			System.out.println("Job message : "+ jobMessage.getText());
			highlightElement(driver, jobMessage);
			Thread.sleep(500);
			action.moveToElement(driver.findElements(By.xpath("//div[contains(@class,'overlay')]")).get(index*2)).perform();
			driver.findElements(By.xpath("//div[contains(@class,'overlay')]")).get(index*2).click();
			Thread.sleep(500);			
		}				
	}
	
	@Disabled
	@Test
	void ContactSectionTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://novoproso.com");

		imageWait = new WebDriverWait(driver, Duration.ofMillis(3000));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(1000));
		
		imageWait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		elementWait.until(d -> startnow.isDisplayed());
		highlightElement(driver, startnow);
		startnow.click();

		//wait until about us link in the header appears
		elementWait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());

		//go to contact us link and click
		WebElement contactLink = driver.findElement(By.xpath("//li/a[@href='#contact']"));
		highlightElement(driver, contactLink);
		System.out.println("contact nav element : " + contactLink.getText());
		action.moveToElement(contactLink).perform();
//		Thread.sleep(500);
		contactLink.click();

		//From contact us section
		WebElement contactUsHeading = driver.findElement(By.xpath("//div[contains(@id, 'contact-us')]/div/div/div/h2"));
		WebElement subHeading = driver.findElement(By.xpath("//div[contains(@id, 'contact-us')]/div/div/div/p"));

		//wait until contact us section is visible
		elementWait.until(d -> contactUsHeading.isDisplayed());

		//assert image
		assertEquals("url(\"https://novoproso.com/images/contact-bg.jpg\")", driver.findElement(By.xpath("//div[contains(@id, 'contact-us')]")).getCssValue("background-image"));
		
		//assert main heading
		highlightElement(driver, contactUsHeading);
		System.out.println("Main Heading in Contact us: " + contactUsHeading.getText());
		assertEquals("Contact Us", contactUsHeading.getText());
		
		//assert main heading
		highlightElement(driver, subHeading);
		System.out.println("SubHeading in Contact us: " + subHeading.getText());

		WebElement conatctFormBlock = driver.findElements(By.xpath("//div[contains(@class, 'contact-form')]/div/div")).get(0);
		WebElement addressBlock = driver.findElements(By.xpath("//div[contains(@class, 'contact-form')]/div/div")).get(1);

		System.out.println("contact us location: " + conatctFormBlock.getLocation());
		System.out.println("Address block location" + addressBlock.getLocation());

		//address
		WebElement address = driver.findElements(By.xpath("//div[contains(@class, 'contact-info')]/ul/li")).get(0);
		WebElement phone = driver.findElements(By.xpath("//div[contains(@class, 'contact-info')]/ul/li")).get(1);
		WebElement emailAddress = driver.findElements(By.xpath("//div[contains(@class, 'contact-info')]/ul/li")).get(2);
		highlightElement(driver, address);
		System.out.println("Address given: " + address.getText());
		highlightElement(driver, phone);
		System.out.println("Phone number : " + phone.getText());
		highlightElement(driver, emailAddress);
		System.out.println("Email address : " + emailAddress.getText());
		
		Thread.sleep(3000);

		//map
		WebElement mapElement = driver.findElement(By.xpath("//div[contains(@class, 'contact-info')]/ul/li/iframe"));
		driver.findElement(By.xpath("//div[contains(@class, 'contact-info')]/ul/li/iframe"));
		driver.switchTo().frame(mapElement);
		/*
		 * Map<String, Object> mockLocation = new HashMap<String, Object>();
		 * mockLocation.put("latitude", 39.03565);
		 * mockLocation.put("longitude",-93.73178); mockLocation.put("accuracy",1);
		 * 
		 * ((ChromiumDriver)
		 * driver).executeCdpCommand("Page.setGeolocationOverride",mockLocation);
		 * driver.navigate().refresh(); driver.navigate().to("http://novoproso.com/");
		 */

			
		}
		
}
