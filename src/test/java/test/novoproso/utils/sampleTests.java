package test.novoproso.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class sampleTests {

	static WebDriver driver;
	static JavascriptExecutor jsExecutor;
	static WebDriverWait wait, elementWait;
	static Actions action;
	static ChromeOptions chromeoptions;
	static mouseHoverJS hoverJS;
	static highLightElement highLightElementClass;
	
	private void elementWait(WebElement element) {
		synchronized (element) {
			try {
				element.wait(800);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}	
		}
	}
	
	@Disabled
	@Test
	void Startnowbtn1Test() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://novoproso.com");

		//assert page title
		String pageTitle = driver.getTitle();
		assertEquals("Novo ProSo, Inc.", pageTitle);

		//explicit wait for first h1 element in home-slider
		wait = new WebDriverWait(driver, Duration.ofMillis(600));
		wait.until(d -> driver.findElements(By.xpath("//h1")).get(0).isDisplayed());
		System.out.println("first h1 element: " + driver.findElements(By.xpath("//h1")).get(0).getText());
		
		//fetch start now from home-slider
		WebElement startnow = driver.findElement(By.xpath("//a[contains(text(), 'Start now')]"));
//		highlightElement(driver, startnow);
		Thread.sleep(1000);

		action.moveToElement(startnow).build().perform();
		Thread.sleep(500);
		assertEquals("rgb(2, 143, 204)", startnow.getCssValue("border-color"));
		assertEquals("rgb(53, 60, 54)", startnow.getCssValue("background-color"));
		assertEquals("rgb(255, 255, 255)", startnow.getCssValue("color"));

		startnow.click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[contains(text(), 'About Us')]"))));
		wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//h2/b")).get(0)));
		assertTrue(driver.findElement(By.xpath("//a[contains(text(), 'About Us')]")).isDisplayed());
//		System.out.println(driver.findElement(By.xpath("//a[contains(text(), 'About Us')]")).getText());
		assertEquals("rgb(252, 208, 93)", driver.findElement(By.xpath("//a[contains(text(), 'About Us')]")).getCssValue("background-color"));
		highLightElementClass.highlightElement(driver, driver.findElement(By.xpath("//a[contains(text(), 'About Us')]")));
		Thread.sleep(1000);


	}
	
	@Disabled
	@Test
	void Startnowbtn2Test() throws InterruptedException {
		driver.manage().window().maximize();
		//
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://novoproso.com");

		//assert page title
		String pageTitle = driver.getTitle();
		assertEquals("Novo ProSo, Inc.", pageTitle);

		//explicit wait for first h1 element in home-slider
		wait = new WebDriverWait(driver, Duration.ofMillis(6000));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(500));
		wait.until(d -> driver.findElements(By.xpath("//h1")).get(1).isDisplayed());
		System.out.println("second h1 element: " + driver.findElements(By.xpath("//h1")).get(1).getText());
		
		//fetch start now from home-slider
		WebElement startnow = driver.findElements(By.xpath("//a[contains(text(), 'Start now')]")).get(1);
		elementWait.until(d -> driver.findElements(By.xpath("//a[contains(text(), 'Start now')]")).get(1).isDisplayed());
		System.out.println(driver.findElements(By.xpath("//a[contains(text(), 'Start now')]")).get(1).getText());
		Thread.sleep(500);
		action.moveToElement(startnow).build().perform();
		Thread.sleep(1000);

		assertEquals("rgb(2, 143, 204)", startnow.getCssValue("border-color"));
		assertEquals("rgb(53, 60, 54)", startnow.getCssValue("background-color"));
		assertEquals("rgb(255, 255, 255)", startnow.getCssValue("color"));

		startnow.click();
		elementWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[contains(text(), 'About Us')]"))));
		elementWait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//h2/b")).get(0)));
		assertTrue(driver.findElement(By.xpath("//a[contains(text(), 'About Us')]")).isDisplayed());
		System.out.println(driver.findElements(By.xpath("//h2/b")).get(0).getText());
		System.out.println(driver.findElements(By.xpath("//h2/b")).size());
		assertEquals("rgb(252, 208, 93)", driver.findElement(By.xpath("//a[contains(text(), 'About Us')]")).getCssValue("background-color"));
		highLightElementClass.highlightElement(driver, driver.findElement(By.xpath("//a[contains(text(), 'About Us')]")));
		Thread.sleep(1000);
	}
	
	@Disabled
	@Test
	void Startnowbtn3Test() throws InterruptedException {
		driver.manage().window().maximize();
		//
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://novoproso.com");

		//assert page title
		String pageTitle = driver.getTitle();
		assertEquals("Novo ProSo, Inc.", pageTitle);

		//explicit wait for first h1 element in home-slider
		wait = new WebDriverWait(driver, Duration.ofMillis(10000));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(500));
		wait.until(d -> driver.findElements(By.xpath("//h1")).get(2).isDisplayed());
		System.out.println("second h1 element: " + driver.findElements(By.xpath("//h1")).get(2).getText());
		
		//fetch start now from home-slider
		WebElement startnow = driver.findElements(By.xpath("//a[contains(text(), 'Start now')]")).get(2);
		elementWait.until(d -> driver.findElements(By.xpath("//a[contains(text(), 'Start now')]")).get(2).isDisplayed());
		System.out.println(driver.findElements(By.xpath("//a[contains(text(), 'Start now')]")).get(2).getText());
		Thread.sleep(500);
		action.moveToElement(startnow).build().perform();
		Thread.sleep(1000);

		assertEquals("rgb(2, 143, 204)", startnow.getCssValue("border-color"));
		assertEquals("rgb(53, 60, 54)", startnow.getCssValue("background-color"));
		assertEquals("rgb(255, 255, 255)", startnow.getCssValue("color"));

		startnow.click();
		elementWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[contains(text(), 'About Us')]"))));
		elementWait.until(ExpectedConditions.visibilityOf(driver.findElements(By.xpath("//h2/b")).get(0)));
		assertTrue(driver.findElement(By.xpath("//a[contains(text(), 'About Us')]")).isDisplayed());
		System.out.println(driver.findElements(By.xpath("//h2/b")).get(0).getText());
		System.out.println(driver.findElements(By.xpath("//h2/b")).size());
		assertEquals("rgb(252, 208, 93)", driver.findElement(By.xpath("//a[contains(text(), 'About Us')]")).getCssValue("background-color"));
		highLightElementClass.highlightElement(driver, driver.findElement(By.xpath("//a[contains(text(), 'About Us')]")));
		Thread.sleep(1000);
	}


	
	//still not working
	@Disabled
	@Test
	void sliderImagesShortTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		driver.get("https://novoproso.com");		
		
//		List<WebElement> bgImageElement = driver.findElements(By.className("item"));
//		WebElement h1Element = driver.findElement(By.cssSelector("h1"));
//		WebElement h1spanElement = driver.findElement(By.cssSelector("span"));
//		WebElement h3Element = driver.findElement(By.cssSelector("h3"));
//		WebElement startNowElement = driver.findElement(By.linkText("Start now"));

		//using JavascriptExecutor for web elements
		@SuppressWarnings("unchecked")
		List<WebElement> bgImageElement = (List<WebElement>) jsExecutor.executeScript("return document.getElementsByClassName('item');");
		WebElement h1Element = (WebElement) jsExecutor.executeScript("return document.querySelector('h1');");
		WebElement h1spanElement = (WebElement) jsExecutor.executeScript("return document.getElementsByTagName('span')[0];");
		WebElement h3Element = (WebElement) jsExecutor.executeScript("return document.getElementsByTagName('h3')[0];");
		WebElement startNowElement = (WebElement) jsExecutor.executeScript("return document.evaluate(\"//a[.='Start now']\",document, null, XPathResult.FIRST_ORDERED_NODE_TYPE,null).singleNodeValue;");	
		
		String[] imageArray = {"url(\"https://novoproso.com/images/slider/npsLogo2023.jpg\")","url(\"https://novoproso.com/images/slider/11.jpg\")","url(\"https://novoproso.com/images/slider/14.jpg\")","url(\"https://novoproso.com/images/slider/13.jpg\")","url(\"https://novoproso.com/images/slider/12.jpg\")","url(\"https://novoproso.com/images/slider/15.jpg\")"};

		wait = new WebDriverWait(driver, Duration.ofMillis(15000));
		for(WebElement element : bgImageElement) {
			wait.until(d->h1Element.isDisplayed());
			String imageUrl = element.getCssValue("background-image");
			int index = bgImageElement.indexOf(element);

			System.out.println("element index: " + index);
			System.out.println("image url: " + imageUrl);
			
			if(imageUrl.equals(imageArray[index])) {
				assertEquals(imageArray[index], imageUrl);

				//for h1 heading
				System.out.println("h1 element: " + h1Element.getText());
				System.out.println("h1 element color: " + h1Element.getCssValue("color"));			
				System.out.println("h1 span element color: " + h1spanElement.getCssValue("color"));			

				//for h3 heading
				System.out.println("h3 element: " + h3Element.getText());
				System.out.println("h3 element color: " + h3Element.getCssValue("color"));			

				//for start now
				System.out.println("start now element: " + startNowElement.getText());
				System.out.println("start now element href: " + startNowElement.getDomAttribute("href"));
				System.out.println("start now element color: " + startNowElement.getCssValue("color"));
				System.out.println("start now element border: " + startNowElement.getCssValue("border"));
				highLightElementClass.highlightElement(driver, h1spanElement);
				highLightElementClass.highlightElement(driver, h1Element);
				highLightElementClass.highlightElement(driver, h3Element);
				highLightElementClass.highlightElement(driver, h1spanElement);

				System.out.println("\n");
			}			
		}
		
		Thread.sleep(2000);
	}
	

	
	@Disabled
	@Test
	void servicesSectionLinksTest() throws InterruptedException {
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

//		try {
			driver.findElement(By.xpath("//li/a[contains(@href,'#services')]")).click();

			//From services section
			WebElement servicesHeading = driver.findElement(By.xpath("//div[contains(@class,'services-heading')]/h2"));
			//wait until services section is visible
			wait.until(d -> servicesHeading.isDisplayed());
			
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
			
			//hover over second service read more link
			elementWait.until(d -> driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(1).isDisplayed());			
			hoverJS.mouseHoverJScript(driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(1), driver);
			highLightElementClass.highlightElement(driver, driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(1));
			driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(1).click();

			wait.until(ExpectedConditions.urlToBe("https://novoproso.com/sd.html"));
			String secondUrl = driver.getCurrentUrl();
			System.out.println("Current URL: " + secondUrl);
			wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
			highLightElementClass.highlightElement(driver, driver.findElement(By.xpath("//h2")));

			driver.navigate().back();
			
			wait.until(d -> driver.findElement(By.xpath("//div[contains(@class,'services-heading')]/h2")).isDisplayed());
			
			//hover over third service read more link
			elementWait.until(d -> driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(2).isDisplayed());			
			hoverJS.mouseHoverJScript(driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(2), driver);
			driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(2).click();

			wait.until(ExpectedConditions.urlToBe("https://novoproso.com/itstaff.html"));		
			String thirdUrl = driver.getCurrentUrl();
			System.out.println("Current URL: " + thirdUrl);
			wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
			highLightElementClass.highlightElement(driver, driver.findElement(By.xpath("//h2")));

			driver.navigate().back();
			
			wait.until(d -> driver.findElement(By.xpath("//div[contains(@class,'services-heading')]/h2")).isDisplayed());
			
			//hover over fourth service read more link
			jsExecutor.executeScript("arguments[0].scrollIntoView();", driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(3));			
			wait.until(d -> driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(3).isDisplayed());		

			//close cookie button
			driver.findElement(By.xpath("//div[contains(@class,'cookie-container')]/p/button")).click();
			
			hoverJS.mouseHoverJScript(driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(3), driver);
			driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(3).click();

			wait.until(ExpectedConditions.urlToBe("https://novoproso.com/cloud.html"));		
			String fourthUrl = driver.getCurrentUrl();
			System.out.println("Current URL: " + fourthUrl);
			wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
			highLightElementClass.highlightElement(driver, driver.findElement(By.xpath("//h2")));

			driver.navigate().back();
			elementWait(driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(4));
//			wait.until(d -> driver.findElement(By.xpath("//div[contains(@class,'services-heading')]/h2")).isDisplayed());

			//hover over fifth service read more link
			jsExecutor.executeScript("arguments[0].scrollIntoView();", driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(4));
			wait.until(d -> driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(4).isDisplayed());
			
			//close cookie button
//			driver.findElement(By.xpath("//div[contains(@class,'cookie-container')]/p/button")).click();
			
			hoverJS.mouseHoverJScript(driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(4), driver);		
			driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(4).click();
			
			wait.until(ExpectedConditions.urlToBe("https://novoproso.com/ai.html"));
			String fifthUrl = driver.getCurrentUrl();
			System.out.println("Current URL: " + fifthUrl);
			wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
			highLightElementClass.highlightElement(driver, driver.findElement(By.xpath("//h2")));

			driver.navigate().back();
			Thread.sleep(2000);

			wait.until(d -> driver.findElement(By.xpath("//div[contains(@class,'services-heading')]/h2")).isDisplayed());

			//hover over sixth service read more link
			jsExecutor.executeScript("arguments[0].scrollIntoView();", driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(5));
			wait.until(d -> driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(5).isDisplayed());

			//close cookie button
			driver.findElement(By.xpath("//div[contains(@class,'cookie-container')]/p/button")).click();

			hoverJS.mouseHoverJScript(driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(5), driver);
			driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(5).click();

			wait.until(ExpectedConditions.urlToBe("https://novoproso.com/bigData.html"));
			String sixthUrl = driver.getCurrentUrl();
			System.out.println("Current URL: " + sixthUrl);
			wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
			highLightElementClass.highlightElement(driver, driver.findElement(By.xpath("//h2")));

			driver.navigate().back();

			wait.until(d -> driver.findElement(By.xpath("//div[contains(@class,'services-heading')]/h2")).isDisplayed());
			
			//hover over seventh service read more link
			jsExecutor.executeScript("arguments[0].scrollIntoView();", driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(6));
			wait.until(d -> driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(6).isDisplayed());			

			//close cookie button
			driver.findElement(By.xpath("//div[contains(@class,'cookie-container')]/p/button")).click();

			wait.until(d -> driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(6).isDisplayed());
			hoverJS.mouseHoverJScript(driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(6), driver);
			driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(6).click();

			wait.until(ExpectedConditions.urlToBe("https://novoproso.com/hra.html"));
			String seventhUrl = driver.getCurrentUrl();
			System.out.println("Current URL: " + seventhUrl);
			wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
			highLightElementClass.highlightElement(driver, driver.findElement(By.xpath("//h2")));

			driver.navigate().back();
			
			wait.until(d -> driver.findElement(By.xpath("//div[contains(@class,'services-heading')]/h2")).isDisplayed());
			
			//hover over eighth service read more link
			jsExecutor.executeScript("arguments[0].scrollIntoView();", driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(7));
			wait.until(d -> driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(7).isDisplayed());			

			//close cookie button
			driver.findElement(By.xpath("//div[contains(@class,'cookie-container')]/p/button")).click();

			wait.until(d -> driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(7).isDisplayed());
			hoverJS.mouseHoverJScript(driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(7), driver);
			driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(7).click();

			wait.until(ExpectedConditions.urlToBe("https://novoproso.com/itpm.html"));
			String eighthUrl = driver.getCurrentUrl();
			System.out.println("Current URL: " + eighthUrl);
			wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
			highLightElementClass.highlightElement(driver, driver.findElement(By.xpath("//h2")));

			driver.navigate().back();
			
			wait.until(d -> driver.findElement(By.xpath("//div[contains(@class,'services-heading')]/h2")).isDisplayed());
			
			//hover over ninth service read more link
			jsExecutor.executeScript("arguments[0].scrollIntoView();", driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(8));
			wait.until(d -> driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(8).isDisplayed());			

			//close cookie button
			driver.findElement(By.xpath("//div[contains(@class,'cookie-container')]/p/button")).click();

			wait.until(d -> driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(8).isDisplayed());			
			hoverJS.mouseHoverJScript(driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(8), driver);		
			driver.findElements(By.xpath("//div[contains(@class,'service-button')]/a")).get(8).click();
			
			wait.until(ExpectedConditions.urlToBe("https://novoproso.com/network.html"));
			String ninthUrl = driver.getCurrentUrl();
			System.out.println("Current URL: " + ninthUrl);
			wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
			highLightElementClass.highlightElement(driver, driver.findElement(By.xpath("//h2")));
			
			driver.navigate().back();
			
			wait.until(d -> driver.findElements(By.xpath("//div[contains(@class,'services-content')]/div[contains(@class,'service')]")).get(8).isDisplayed());			
			
//		} catch (ElementClickInterceptedException e) {
//			System.out.println("To check each service links: Services link is not clickable!");
//		}
	}

	
	@Disabled
	@Test
	void productsSectionLinksTest() throws InterruptedException {
		wait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		hoverJS.mouseHoverJScript(startnow, driver);
		highLightElementClass.highlightElement(driver, startnow);
		startnow.click();

		//wait until about us link in the header appears
		wait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());
		wait.until(d -> driver.findElement(By.xpath("//section[contains(@id,'about-us')]//div[contains(@class,'about-info')]/h2")).isDisplayed());

		//go to products link and click
		WebElement productsLink = driver.findElement(By.xpath("//li/a[@href='#products']"));
		hoverJS.mouseHoverJScript(productsLink, driver);
		highLightElementClass.highlightElement(driver, productsLink);
		System.out.println("products nav element : " + productsLink.getText());

		try {
			productsLink.click();

			//From products section
			WebElement productsHeading = driver.findElement(By.xpath("//div[contains(@class,'products-heading')]/h2"));
			//wait until products section is visible
			wait.until(d -> productsHeading.isDisplayed());

			//get product elements
			List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class,'products-content')]/div[contains(@class,'product')]"));
			List<WebElement> productReadMoreLink = driver.findElements(By.xpath("//div[contains(@class,'product-button')]/a"));
			
			elementWait.until(d -> products.get(0).isDisplayed());			
			
			//hover over first product read more link
			hoverJS.mouseHoverJScript(productReadMoreLink.get(0), driver);
			productReadMoreLink.get(0).click();

			wait.until(ExpectedConditions.urlToBe("https://novoproso.com/BDIngension.html"));
			String url = driver.getCurrentUrl();
			System.out.println("Current URL: " + url);
			wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
			highLightElementClass.highlightElement(driver, driver.findElement(By.xpath("//h2")));

			driver.navigate().back();

			wait.until(d -> driver.findElement(By.xpath("//div[contains(@class,'products-heading')]/h2")).isDisplayed());
			
			//hover over second product read more link		
			hoverJS.mouseHoverJScript(driver.findElements(By.xpath("//div[contains(@class,'product-button')]/a")).get(1), driver);
			driver.findElements(By.xpath("//div[contains(@class,'product-button')]/a")).get(1).click();

			wait.until(ExpectedConditions.urlToBe("https://novoproso.com/DAN.html"));
			String prdcturl = driver.getCurrentUrl();
			System.out.println("Current URL: " + prdcturl);
			wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
			highLightElementClass.highlightElement(driver, driver.findElement(By.xpath("//h2")));

			driver.navigate().back();
			
			wait.until(d -> driver.findElement(By.xpath("//div[contains(@class,'products-heading')]/h2")).isDisplayed());
			
		} catch (ElementClickInterceptedException e) {
			System.out.println("Check Sub-Products Links: Products link is not clickable at the moment!");
		}
		
	}
	

}
