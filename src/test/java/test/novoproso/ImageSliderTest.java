package test.novoproso;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class ImageSliderTest {

	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait wait, imageSliderWait, imageWait, elementWait, initialWait;
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
				highlightElement(driver, h1spanElement);
				highlightElement(driver, h1Element);
				highlightElement(driver, h3Element);
				highlightElement(driver, h1spanElement);

				System.out.println("\n");
			}			
		}
		
		Thread.sleep(2000);
	}
	
	
	@Disabled
	@Test
	void sliderImagesLongTest() throws InterruptedException, IOException {
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://novoproso.com");		
			
		wait = new WebDriverWait(driver, Duration.ofMillis(20000));

		System.out.println("bgImage elements: " + driver.findElements(By.className("item")).size());
		System.out.println("h1 elements: " + driver.findElements(By.cssSelector("h1")).size());
		System.out.println("h3 elements: " + driver.findElements(By.cssSelector("h3")).size());
		System.out.println("start now elements: " + driver.findElements(By.className("btn-start")).size());

		assertEquals("rgba(0, 0, 0, 0)", driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0).getCssValue("background-color"));
		assertEquals("rgb(110, 109, 108)", driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0).getCssValue("border-color"));
		highlightElement(driver, driver.findElements(By.cssSelector("h1")).get(0));
		highlightElement(driver, driver.findElements(By.cssSelector("h3")).get(0));
		highlightElement(driver, driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0));
		System.out.println(driver.findElements(By.className("item")).get(0).getCssValue("background-image"));
		System.out.println(driver.findElements(By.cssSelector("h1")).get(0).getText());
		System.out.println(driver.findElements(By.cssSelector("h3")).get(0).getText());
		System.out.println(driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0).getText());
		assertEquals("rgb(255, 235, 59)", driver.findElements(By.xpath("//h1/span")).get(0).getCssValue("color"));
		assertEquals("rgb(255, 255, 255)", driver.findElements(By.cssSelector("h1")).get(0).getCssValue("color"));
		assertEquals("rgb(255, 255, 255)", driver.findElements(By.cssSelector("h3")).get(0).getCssValue("color"));
		assertEquals("rgb(255, 255, 255)", driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0).getCssValue("color"));
		System.out.println("\n");
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.cssSelector("h1")).get(1)));
		assertEquals("rgba(0, 0, 0, 0)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[2]/div/a")).getCssValue("background-color"));
		assertEquals("rgb(110, 109, 108)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[2]/div/a")).getCssValue("border-color"));
		highlightElement(driver, driver.findElements(By.cssSelector("h1")).get(1));
		highlightElement(driver, driver.findElements(By.cssSelector("h3")).get(1));
		highlightElement(driver, driver.findElements(By.xpath("//*[@id=\"home-slider\"]/div/div[2]/div/a")).get(0));
		System.out.println(driver.findElements(By.className("item")).get(1).getCssValue("background-image"));
		System.out.println(driver.findElements(By.cssSelector("h1")).get(1).getText());
		System.out.println(driver.findElements(By.cssSelector("h3")).get(1).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[2]/div/a")).getText());
		assertEquals("rgb(255, 235, 59)", driver.findElements(By.xpath("//h1/span")).get(0).getCssValue("color"));
		assertEquals("rgb(255, 255, 255)", driver.findElements(By.cssSelector("h1")).get(1).getCssValue("color"));
		assertEquals("rgb(255, 255, 255)", driver.findElements(By.cssSelector("h3")).get(1).getCssValue("color"));
		assertEquals("rgb(255, 255, 255)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[2]/div/a")).getCssValue("color"));
		System.out.println("\n");

		wait.until(d->driver.findElements(By.cssSelector("h1")).get(2).isDisplayed());
		assertEquals("rgba(0, 0, 0, 0)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[3]/div/a")).getCssValue("background-color"));
		assertEquals("rgb(110, 109, 108)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[3]/div/a")).getCssValue("border-color"));
		highlightElement(driver, driver.findElements(By.cssSelector("h1")).get(2));
		highlightElement(driver, driver.findElements(By.cssSelector("h3")).get(2));
		highlightElement(driver, driver.findElements(By.xpath("//*[@id=\"home-slider\"]/div/div[3]/div/a")).get(0));
		System.out.println(driver.findElements(By.className("item")).get(2).getCssValue("background-image"));
		System.out.println(driver.findElements(By.cssSelector("h1")).get(2).getText());
		System.out.println(driver.findElements(By.cssSelector("h3")).get(2).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[3]/div/a")).getText());
		assertEquals("rgb(255, 235, 59)", driver.findElements(By.xpath("//h1/span")).get(0).getCssValue("color"));
		assertEquals("rgb(255, 255, 255)", driver.findElements(By.cssSelector("h1")).get(2).getCssValue("color"));
		assertEquals("rgb(255, 255, 255)", driver.findElements(By.cssSelector("h3")).get(2).getCssValue("color"));
		assertEquals("rgb(255, 255, 255)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[3]/div/a")).getCssValue("color"));
		System.out.println("\n");		
		
		wait.until(d->driver.findElements(By.cssSelector("h1")).get(3).isDisplayed());
		assertEquals("rgba(0, 0, 0, 0)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[4]/div/a")).getCssValue("background-color"));
		assertEquals("rgb(110, 109, 108)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[4]/div/a")).getCssValue("border-color"));
		highlightElement(driver, driver.findElements(By.cssSelector("h1")).get(3));
		highlightElement(driver, driver.findElements(By.cssSelector("h3")).get(3));
		highlightElement(driver, driver.findElements(By.xpath("//*[@id=\"home-slider\"]/div/div[4]/div/a")).get(0));
		System.out.println(driver.findElements(By.className("item")).get(3).getCssValue("background-image"));
		System.out.println(driver.findElements(By.cssSelector("h1")).get(3).getText());
		System.out.println(driver.findElements(By.cssSelector("h3")).get(3).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[4]/div/a")).getText());
		assertEquals("rgb(255, 235, 59)", driver.findElements(By.xpath("//h1/span")).get(0).getCssValue("color"));
		assertEquals("rgb(255, 255, 255)", driver.findElements(By.cssSelector("h1")).get(3).getCssValue("color"));
		assertEquals("rgb(255, 255, 255)", driver.findElements(By.cssSelector("h3")).get(3).getCssValue("color"));
		assertEquals("rgb(255, 255, 255)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[4]/div/a")).getCssValue("color"));
		System.out.println("\n");
		
		wait.until(d->driver.findElements(By.cssSelector("h1")).get(4).isDisplayed());
		assertEquals("rgba(0, 0, 0, 0)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[5]/div/a")).getCssValue("background-color"));
		assertEquals("rgb(110, 109, 108)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[5]/div/a")).getCssValue("border-color"));
		highlightElement(driver, driver.findElements(By.cssSelector("h1")).get(4));
		highlightElement(driver, driver.findElements(By.cssSelector("h3")).get(4));
		highlightElement(driver, driver.findElements(By.xpath("//*[@id=\"home-slider\"]/div/div[5]/div/a")).get(0));
		System.out.println(driver.findElements(By.className("item")).get(4).getCssValue("background-image"));
		System.out.println(driver.findElements(By.cssSelector("h1")).get(4).getText());
		System.out.println(driver.findElements(By.cssSelector("h3")).get(4).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[5]/div/a")).getText());
		assertEquals("rgb(255, 235, 59)", driver.findElements(By.xpath("//h1/span")).get(0).getCssValue("color"));
		assertEquals("rgb(255, 255, 255)", driver.findElements(By.cssSelector("h1")).get(4).getCssValue("color"));
		assertEquals("rgb(255, 255, 255)", driver.findElements(By.cssSelector("h3")).get(4).getCssValue("color"));
		assertEquals("rgb(255, 255, 255)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[5]/div/a")).getCssValue("color"));
		System.out.println("\n");
		
		wait.until(d->driver.findElements(By.cssSelector("h1")).get(5).isDisplayed());
		assertEquals("rgba(0, 0, 0, 0)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[6]/div/a")).getCssValue("background-color"));
		assertEquals("rgb(110, 109, 108)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[6]/div/a")).getCssValue("border-color"));
		highlightElement(driver, driver.findElements(By.cssSelector("h1")).get(5));
		highlightElement(driver, driver.findElements(By.cssSelector("h3")).get(5));
		highlightElement(driver, driver.findElements(By.xpath("//*[@id=\"home-slider\"]/div/div[6]/div/a")).get(0));
		System.out.println(driver.findElements(By.className("item")).get(5).getCssValue("background-image"));
		System.out.println(driver.findElements(By.cssSelector("h1")).get(5).getText());
		System.out.println(driver.findElements(By.cssSelector("h3")).get(5).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[6]/div/a")).getText());
		assertEquals("rgb(255, 235, 59)", driver.findElements(By.xpath("//h1/span")).get(0).getCssValue("color"));
		assertEquals("rgb(255, 255, 255)", driver.findElements(By.cssSelector("h1")).get(5).getCssValue("color"));
		assertEquals("rgb(255, 255, 255)", driver.findElements(By.cssSelector("h3")).get(5).getCssValue("color"));
		assertEquals("rgb(255, 255, 255)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[6]/div/a")).getCssValue("color"));
		System.out.println("\n");
		
		Thread.sleep(2000);
	}

	@Disabled
	@Test
	void Startnowbtn1Test() throws InterruptedException {
		driver.manage().window().maximize();
		//
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
		highlightElement(driver, driver.findElement(By.xpath("//a[contains(text(), 'About Us')]")));
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
		highlightElement(driver, driver.findElement(By.xpath("//a[contains(text(), 'About Us')]")));
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
		highlightElement(driver, driver.findElement(By.xpath("//a[contains(text(), 'About Us')]")));
		Thread.sleep(1000);
	}


}
