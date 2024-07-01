package test.novoproso;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

class ContactFormTest {

	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait wait, imageWait, elementWait;
	Actions action;
	ChromeOptions chromeoptions;
	String excelFilePath = "./testFiles/contactFormTestData.xls";
	Object[][] contacttestData;
	
	public Object[][] formTestData(){
		ReadExcelFile readFile = new ReadExcelFile(excelFilePath);
		int rows = readFile.getRowCount(0);
		Object[][] testData = new Object[rows][5];
		
		for(int i=0;i<rows;i++) {
			testData[i][0] = readFile.getData(0, i, 0);
			testData[i][1] = readFile.getData(0, i, 1);
			testData[i][2] = readFile.getData(0, i, 2);
			testData[i][3] = readFile.getData(0, i, 3);
		}
		return testData;
	}
	
	@BeforeEach
	void setUp() throws Exception {
		//Chrome Browser
//		chromeoptions = new ChromeOptions();
//		chromeoptions.addArguments("start-maximized");
//		driver = new ChromeDriver(chromeoptions);

		//Edge Browser
//		driver = new EdgeDriver();
		
		//Firefox Browser
//		driver = new FirefoxDriver();
		
		//actions
//		action = new Actions(driver);
		
		//JavaScriptExecutor
//		jsExecutor = (JavascriptExecutor) driver;
		
		contacttestData = formTestData();
	}

	@AfterEach
	void tearDown() throws Exception {
		//has to do later
//		driver.quit();
	}

	public void highlightElement(WebDriver driver, WebElement element) {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].setAttribute('style', 'background: yellow;border: 2px solid red;')", element);
	}

//	@Disabled
	@Test
	void ContactFormDataDrivenTest() throws InterruptedException {
		for(Object[] row: contacttestData) {
			for(Object cell: row) {
				System.out.println(cell.toString());
			}
		}

//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//		driver.get("https://novoproso.com");
//
//		wait = new WebDriverWait(driver, Duration.ofMillis(20000));
//		elementWait = new WebDriverWait(driver, Duration.ofMillis(1000));
//		
//		wait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
//		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
//		elementWait.until(d -> startnow.isDisplayed());
//		highlightElement(driver, startnow);
//		startnow.click();
//
//		//wait until about us link in the header appears
//		elementWait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());
//
//		//go to contact us link and click
//		WebElement contactLink = driver.findElement(By.xpath("//li/a[@href='#contact']"));
//		highlightElement(driver, contactLink);
//		action.moveToElement(contactLink).perform();
//		contactLink.click();
//
//		//From contact us section
//		WebElement contactUsHeading = driver.findElement(By.xpath("//div[contains(@id, 'contact-us')]/div/div/div/h2"));
//
//		//wait until contact us section is visible
//		wait.until(d-> driver.findElement(By.xpath("//section[contains(@id, 'contact')]")).isDisplayed());
//		wait.until(d -> contactUsHeading.isDisplayed());
//
////		action.moveToElement(contactUsHeading).perform();
//
//		//Form Handling
//		WebElement nameInput = driver.findElement(By.xpath("//input[contains(@name,'name')]"));
//		WebElement emailInput = driver.findElement(By.xpath("//input[contains(@name,'email')]"));
//		WebElement subjectElement = driver.findElement(By.xpath("//input[contains(@name,'subject')]"));
//		WebElement messageElement = driver.findElement(By.xpath("//textarea[contains(@name,'message')]"));
////		WebElement submitButton = driver.findElement(By.xpath("//button[contains(@class,'btn-submit')]"));
//		WebElement submitButton = driver.findElement(By.className("btn-submit"));
//
////		jsExecutor.executeScript("arguments[0].value='test name'", nameInput);
////		jsExecutor.executeScript("arguments[0].value='test@example.com'", emailInput);
////		jsExecutor.executeScript("arguments[0].value='test subject'", subjectElement);
////		jsExecutor.executeScript("arguments[0].value='test message'", messageElement);
//
//		nameInput.sendKeys("test name");
//		emailInput.sendKeys("test@example.com");
//		subjectElement.sendKeys("test subject");
//		messageElement.sendKeys("test message");
//
//		jsExecutor.executeScript("arguments[0].scrollIntoView();", submitButton);
//		wait.until(d -> submitButton.isDisplayed());
//		
//		action.moveToElement(submitButton).perform();
//		assertTrue(submitButton.getCssValue("background-color").equals("rgb(66, 148, 79)"));
//		submitButton.click();
//		highlightElement(driver, submitButton);
//
//		WebElement successMessage = driver.findElement(By.className("form_status"));
//		jsExecutor.executeScript("arguments[0].scrollIntoView();", successMessage);
//		wait.until(d -> successMessage.isDisplayed());
//		
//		assertTrue(successMessage.isDisplayed());
//		System.out.println(driver.findElement(By.className("text-success")).getText());
	}

	@Disabled
	@Test
	void ContactFormManualTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://novoproso.com");

		wait = new WebDriverWait(driver, Duration.ofMillis(20000));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(1000));
		
		wait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		elementWait.until(d -> startnow.isDisplayed());
		highlightElement(driver, startnow);
		startnow.click();

		//wait until about us link in the header appears
		elementWait.until(d -> driver.findElement(By.xpath("//li/a[@href='#about-us']")).isDisplayed());

		//go to contact us link and click
		WebElement contactLink = driver.findElement(By.xpath("//li/a[@href='#contact']"));
		highlightElement(driver, contactLink);
		action.moveToElement(contactLink).perform();
		contactLink.click();

		//From contact us section
		WebElement contactUsHeading = driver.findElement(By.xpath("//div[contains(@id, 'contact-us')]/div/div/div/h2"));

		//wait until contact us section is visible
		wait.until(d-> driver.findElement(By.xpath("//section[contains(@id, 'contact')]")).isDisplayed());
		wait.until(d -> contactUsHeading.isDisplayed());

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
		
		action.moveToElement(submitButton).perform();
		assertTrue(submitButton.getCssValue("background-color").equals("rgb(66, 148, 79)"));
		submitButton.click();
		highlightElement(driver, submitButton);

		WebElement successMessage = driver.findElement(By.className("form_status"));
		jsExecutor.executeScript("arguments[0].scrollIntoView();", successMessage);
		wait.until(d -> successMessage.isDisplayed());
		
		assertTrue(successMessage.isDisplayed());
		System.out.println(driver.findElement(By.className("text-success")).getText());
	}


}
