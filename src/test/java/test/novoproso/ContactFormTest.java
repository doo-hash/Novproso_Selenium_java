package test.novoproso;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URL;
import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import test.novoproso.utilities.BrowserSetUp;
import test.novoproso.utilities.HighLight;
import test.novoproso.utilities.MouseHoverScript;

@TestInstance(Lifecycle.PER_CLASS)
class ContactFormTest {

	//chrome, msedge, firefox
	String browser = "msedge";
	RemoteWebDriver driver;
//	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait wait, imageWait, elementWait;
	Actions action;
	MouseHoverScript hoverJS;
	HighLight highLightElementClass;
	BrowserSetUp browserSetUp = new BrowserSetUp();
	
	@BeforeAll
	void setUp() throws Exception {
//		driver = browserSetUp.getBrowserSetUp(browser);

		driver = browserSetUp.getBrowserGridSetUp(browser);

		//actions
		action = new Actions(driver);
		
		//JavaScriptExecutor
		jsExecutor = (JavascriptExecutor) driver;
		hoverJS = new MouseHoverScript();
		highLightElementClass = new HighLight();

	}

	@AfterAll
	void tearDown() throws Exception {
		driver.quit();
	}
	
	
//	@Disabled
	@Test
	void ContactFormManualTest() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://novoproso.com");

		wait = new WebDriverWait(driver, Duration.ofMillis(8000));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(1000));
		
		wait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		elementWait.until(d -> startnow.isDisplayed());
		hoverJS.mouseHoverJScript(startnow, driver);
		highLightElementClass.highlightElement(driver, startnow);
		startnow.click();

		wait.until(d -> driver.findElement(By.xpath("//li/a[contains(@href,'#about-us')]")));
		wait.until(d -> driver.findElement(By.xpath("//section[contains(@id,'about-us')]//div[contains(@class,'about-info')]/h2")).isDisplayed());

		//go to contact us link and click
		WebElement contactLink = driver.findElement(By.xpath("//li/a[@href='#contact']"));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li/a[@href='#contact']")));
		highLightElementClass.highlightElement(driver, contactLink);
		hoverJS.mouseHoverJScript(contactLink, driver);
//		action.moveToElement(contactLink).perform();
//		action.moveToElement(contactLink).perform();
//		action.moveToElement(contactLink).perform();
		driver.findElement(By.xpath("//li/a[@href='#contact']")).click();

		//wait until contact us section is visible
		wait.until(d-> driver.findElement(By.xpath("//section[contains(@id, 'contact')]")).isDisplayed());
		wait.until(d -> driver.findElement(By.xpath("//div[contains(@id, 'contact-us')]/div/div/div/h2")).isDisplayed());
		//From contact us section
		WebElement contactUsHeading = driver.findElement(By.xpath("//div[contains(@id, 'contact-us')]/div/div/div/h2"));

		hoverJS.mouseHoverJScript(contactUsHeading, driver);
//		action.moveToElement(contactUsHeading).perform();

		//Form Handling
		WebElement nameInput = driver.findElement(By.xpath("//input[contains(@name,'name')]"));
		WebElement emailInput = driver.findElement(By.xpath("//input[contains(@name,'email')]"));
		WebElement subjectElement = driver.findElement(By.xpath("//input[contains(@name,'subject')]"));
		WebElement messageElement = driver.findElement(By.xpath("//textarea[contains(@name,'message')]"));
//		WebElement submitButton = driver.findElement(By.xpath("//button[contains(@class,'btn-submit')]"));
		WebElement submitButton = driver.findElement(By.className("btn-submit"));

//		jsExecutor.executeScript("arguments[0].value='test name'", nameInput);
//		jsExecutor.executeScript("arguments[0].value='test@example.com'", emailInput);
//		jsExecutor.executeScript("arguments[0].value='test subject'", subjectElement);
//		jsExecutor.executeScript("arguments[0].value='test message'", messageElement);

		nameInput.sendKeys("test name");
		emailInput.sendKeys("test@example.com");
		subjectElement.sendKeys("test subject");
		messageElement.sendKeys("test message");

		jsExecutor.executeScript("arguments[0].scrollIntoView();", submitButton);
		wait.until(d -> submitButton.isDisplayed());

		hoverJS.mouseHoverJScript(submitButton, driver);
//		action.moveToElement(submitButton).perform();
//		assertTrue(submitButton.getCssValue("background-color").equals("rgb(66, 148, 79)"));
		submitButton.click();
		highLightElementClass.highlightElement(driver, submitButton);

		WebElement successMessage = driver.findElement(By.className("form_status"));
		jsExecutor.executeScript("arguments[0].scrollIntoView();", successMessage);
		jsExecutor.executeScript("window.scrollBy(0,150)");
		wait.until(d -> successMessage.isDisplayed());		
		assertTrue(successMessage.isDisplayed());
	}

}
