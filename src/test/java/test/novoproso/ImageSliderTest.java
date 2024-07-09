package test.novoproso;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
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

class ImageSliderTest {

	//chrome, msedge, firefox
	static String browser = "chrome";
	static RemoteWebDriver driver;
	
//	static WebDriver driver;
	static JavascriptExecutor jsExecutor;
	static WebDriverWait wait;
	static Actions action;
	static mouseHoverJS hoverJS;
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
	}

	@AfterAll
	static void tearDown() throws Exception {
		driver.quit();
	}


	
//	@Disabled
	@Test
	void sliderImagesLongTest() throws InterruptedException, IOException {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://novoproso.com");		
			
		wait = new WebDriverWait(driver, Duration.ofMillis(20000));

		System.out.println("bgImage elements: " + driver.findElements(By.className("item")).size());
		System.out.println("h1 elements: " + driver.findElements(By.cssSelector("h1")).size());
		System.out.println("h3 elements: " + driver.findElements(By.cssSelector("h3")).size());
		System.out.println("start now elements: " + driver.findElements(By.className("btn-start")).size());

		assertEquals("rgba(0, 0, 0, 0)", driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0).getCssValue("background-color"));
		assertEquals("rgb(110, 109, 108)", driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0).getCssValue("border-color"));
		highLightElementClass.highlightElement(driver, driver.findElements(By.cssSelector("h1")).get(0));
		highLightElementClass.highlightElement(driver, driver.findElements(By.cssSelector("h3")).get(0));
		highLightElementClass.highlightElement(driver, driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0));
		System.out.println(driver.findElements(By.className("item")).get(0).getCssValue("background-image"));
		System.out.println(driver.findElements(By.cssSelector("h1")).get(0).getText());
		System.out.println(driver.findElements(By.cssSelector("h3")).get(0).getText());
		System.out.println(driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0).getText());
//		assertEquals("rgb(255, 235, 59)", driver.findElements(By.xpath("//h1/span")).get(0).getCssValue("color"));
//		assertEquals("rgb(255, 255, 255)", driver.findElements(By.cssSelector("h1")).get(0).getCssValue("color"));
//		assertEquals("rgb(255, 255, 255)", driver.findElements(By.cssSelector("h3")).get(0).getCssValue("color"));
//		assertEquals("rgb(255, 255, 255)", driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0).getCssValue("color"));
		System.out.println("\n");
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.cssSelector("h1")).get(1)));
//		assertEquals("rgba(0, 0, 0, 0)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[2]/div/a")).getCssValue("background-color"));
//		assertEquals("rgb(110, 109, 108)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[2]/div/a")).getCssValue("border-color"));
		highLightElementClass.highlightElement(driver, driver.findElements(By.cssSelector("h1")).get(1));
		highLightElementClass.highlightElement(driver, driver.findElements(By.cssSelector("h3")).get(1));
		highLightElementClass.highlightElement(driver, driver.findElements(By.xpath("//*[@id=\"home-slider\"]/div/div[2]/div/a")).get(0));
		System.out.println(driver.findElements(By.className("item")).get(1).getCssValue("background-image"));
		System.out.println(driver.findElements(By.cssSelector("h1")).get(1).getText());
		System.out.println(driver.findElements(By.cssSelector("h3")).get(1).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[2]/div/a")).getText());
//		assertEquals("rgb(255, 235, 59)", driver.findElements(By.xpath("//h1/span")).get(0).getCssValue("color"));
//		assertEquals("rgb(255, 255, 255)", driver.findElements(By.cssSelector("h1")).get(1).getCssValue("color"));
//		assertEquals("rgb(255, 255, 255)", driver.findElements(By.cssSelector("h3")).get(1).getCssValue("color"));
//		assertEquals("rgb(255, 255, 255)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[2]/div/a")).getCssValue("color"));
		System.out.println("\n");

		wait.until(d->driver.findElements(By.cssSelector("h1")).get(2).isDisplayed());
//		assertEquals("rgba(0, 0, 0, 0)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[3]/div/a")).getCssValue("background-color"));
//		assertEquals("rgb(110, 109, 108)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[3]/div/a")).getCssValue("border-color"));
		highLightElementClass.highlightElement(driver, driver.findElements(By.cssSelector("h1")).get(2));
		highLightElementClass.highlightElement(driver, driver.findElements(By.cssSelector("h3")).get(2));
		highLightElementClass.highlightElement(driver, driver.findElements(By.xpath("//*[@id=\"home-slider\"]/div/div[3]/div/a")).get(0));
		System.out.println(driver.findElements(By.className("item")).get(2).getCssValue("background-image"));
		System.out.println(driver.findElements(By.cssSelector("h1")).get(2).getText());
		System.out.println(driver.findElements(By.cssSelector("h3")).get(2).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[3]/div/a")).getText());
//		assertEquals("rgb(255, 235, 59)", driver.findElements(By.xpath("//h1/span")).get(0).getCssValue("color"));
//		assertEquals("rgb(255, 255, 255)", driver.findElements(By.cssSelector("h1")).get(2).getCssValue("color"));
//		assertEquals("rgb(255, 255, 255)", driver.findElements(By.cssSelector("h3")).get(2).getCssValue("color"));
//		assertEquals("rgb(255, 255, 255)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[3]/div/a")).getCssValue("color"));
		System.out.println("\n");		
		
		wait.until(d->driver.findElements(By.cssSelector("h1")).get(3).isDisplayed());
//		assertEquals("rgba(0, 0, 0, 0)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[4]/div/a")).getCssValue("background-color"));
//		assertEquals("rgb(110, 109, 108)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[4]/div/a")).getCssValue("border-color"));
		highLightElementClass.highlightElement(driver, driver.findElements(By.cssSelector("h1")).get(3));
		highLightElementClass.highlightElement(driver, driver.findElements(By.cssSelector("h3")).get(3));
		highLightElementClass.highlightElement(driver, driver.findElements(By.xpath("//*[@id=\"home-slider\"]/div/div[4]/div/a")).get(0));
		System.out.println(driver.findElements(By.className("item")).get(3).getCssValue("background-image"));
		System.out.println(driver.findElements(By.cssSelector("h1")).get(3).getText());
		System.out.println(driver.findElements(By.cssSelector("h3")).get(3).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[4]/div/a")).getText());
//		assertEquals("rgb(255, 235, 59)", driver.findElements(By.xpath("//h1/span")).get(0).getCssValue("color"));
//		assertEquals("rgb(255, 255, 255)", driver.findElements(By.cssSelector("h1")).get(3).getCssValue("color"));
//		assertEquals("rgb(255, 255, 255)", driver.findElements(By.cssSelector("h3")).get(3).getCssValue("color"));
//		assertEquals("rgb(255, 255, 255)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[4]/div/a")).getCssValue("color"));
		System.out.println("\n");
		
		wait.until(d->driver.findElements(By.cssSelector("h1")).get(4).isDisplayed());
//		assertEquals("rgba(0, 0, 0, 0)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[5]/div/a")).getCssValue("background-color"));
//		assertEquals("rgb(110, 109, 108)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[5]/div/a")).getCssValue("border-color"));
		highLightElementClass.highlightElement(driver, driver.findElements(By.cssSelector("h1")).get(4));
		highLightElementClass.highlightElement(driver, driver.findElements(By.cssSelector("h3")).get(4));
		highLightElementClass.highlightElement(driver, driver.findElements(By.xpath("//*[@id=\"home-slider\"]/div/div[5]/div/a")).get(0));
		System.out.println(driver.findElements(By.className("item")).get(4).getCssValue("background-image"));
		System.out.println(driver.findElements(By.cssSelector("h1")).get(4).getText());
		System.out.println(driver.findElements(By.cssSelector("h3")).get(4).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[5]/div/a")).getText());
//		assertEquals("rgb(255, 235, 59)", driver.findElements(By.xpath("//h1/span")).get(0).getCssValue("color"));
//		assertEquals("rgb(255, 255, 255)", driver.findElements(By.cssSelector("h1")).get(4).getCssValue("color"));
//		assertEquals("rgb(255, 255, 255)", driver.findElements(By.cssSelector("h3")).get(4).getCssValue("color"));
//		assertEquals("rgb(255, 255, 255)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[5]/div/a")).getCssValue("color"));
		System.out.println("\n");
		
		wait.until(d->driver.findElements(By.cssSelector("h1")).get(5).isDisplayed());
//		assertEquals("rgba(0, 0, 0, 0)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[6]/div/a")).getCssValue("background-color"));
//		assertEquals("rgb(110, 109, 108)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[6]/div/a")).getCssValue("border-color"));
		highLightElementClass.highlightElement(driver, driver.findElements(By.cssSelector("h1")).get(5));
		highLightElementClass.highlightElement(driver, driver.findElements(By.cssSelector("h3")).get(5));
		highLightElementClass.highlightElement(driver, driver.findElements(By.xpath("//*[@id=\"home-slider\"]/div/div[6]/div/a")).get(0));
		System.out.println(driver.findElements(By.className("item")).get(5).getCssValue("background-image"));
		System.out.println(driver.findElements(By.cssSelector("h1")).get(5).getText());
		System.out.println(driver.findElements(By.cssSelector("h3")).get(5).getText());
		System.out.println(driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[6]/div/a")).getText());
//		assertEquals("rgb(255, 235, 59)", driver.findElements(By.xpath("//h1/span")).get(0).getCssValue("color"));
//		assertEquals("rgb(255, 255, 255)", driver.findElements(By.cssSelector("h1")).get(5).getCssValue("color"));
//		assertEquals("rgb(255, 255, 255)", driver.findElements(By.cssSelector("h3")).get(5).getCssValue("color"));
//		assertEquals("rgb(255, 255, 255)", driver.findElement(By.xpath("//*[@id=\"home-slider\"]/div/div[6]/div/a")).getCssValue("color"));
//		System.out.println("\n");		
	}

}
