package test.novoproso;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class servicesSection {

	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait wait, imageWait, elementWait;
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

	@Disabled
	@Test
	void ideaPageTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		//Direct URL
//		driver.get("https://novoproso.com/idea.html");
		
		//From home page
		driver.get("https://novoproso.com");
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		imageWait = new WebDriverWait(driver, Duration.ofMillis(3000));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(600));
		
		imageWait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		
		//click start now button
		elementWait.until(d -> startnow.isDisplayed());
		highlightElement(driver, startnow);
		startnow.click();
		Thread.sleep(1000);

		WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
		WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
		WebElement ideaElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'idea.html')]"));
		
		//wait until Services link appears and hover over it
		elementWait.until(ExpectedConditions.visibilityOf(servicesLink));
		highlightElement(driver, servicesLink);
		action.moveToElement(servicesLink).perform();
		Thread.sleep(1000);
		
		//wait until Services dropdown appears
		elementWait.until(d -> servicesDropdownElement.isDisplayed());
		Thread.sleep(1000);

		//click idea element to go that page
		highlightElement(driver, ideaElement);
		ideaElement.click();

		//wait until page loads (waiting until page main heading loads)
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		
		//get Current URL
		String IdeaURL = driver.getCurrentUrl();
		System.out.println("Current URL: " + IdeaURL);

		//assert page title
		String pageTitle = driver.getTitle();
		assertEquals("Novo ProSo, Inc.", pageTitle);
		
		//assert main heading
		//did not include commented tag
		WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
		highlightElement(driver, mainHeading);
		assertEquals("Your Own Idea", mainHeading.getText());

		//sub heading
		//did not include commented tag
		WebElement subHeading = driver.findElement(By.cssSelector("h4"));
		assertEquals("Bring Your Vision to Life!", subHeading.getText());
		assertEquals("rgba(8, 117, 3, 0.8)", subHeading.getCssValue("color"));
		assertEquals("1.5px", subHeading.getCssValue("letter-spacing"));
		assertEquals("27px", subHeading.getCssValue("line-height"));
		assertEquals("600", subHeading.getCssValue("font-weight"));
		highlightElement(driver, subHeading);
		
		//hr
		assertTrue(driver.findElement(By.cssSelector("hr")).isDisplayed());

		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		highlightElement(driver, imageElement);
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/idea.png", imageElement.getDomAttribute("src"));
		
		//assert ul, li tags
		List<WebElement> ulElements = driver.findElements(By.cssSelector("ul"));
		assertEquals(6, ulElements.size());
		assertEquals(new Dimension(616,80), ulElements.get(0).getSize());
		assertEquals(new Dimension(746, 120), ulElements.get(4).getSize());
		assertEquals(new Dimension(124, 36), ulElements.get(5).getSize());

		//whether ul tags are displayed or not
		assertTrue(ulElements.get(0).isDisplayed());
		assertFalse(ulElements.get(1).isDisplayed());		
		assertFalse(ulElements.get(2).isDisplayed());
		assertFalse(ulElements.get(3).isDisplayed());
		assertTrue(ulElements.get(4).isDisplayed());

		//check for li tags
		
		Thread.sleep(1000);
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		Thread.sleep(1000);

		highlightElement(driver, ulElements.get(5));
		assertTrue(ulElements.get(5).isDisplayed());
		Thread.sleep(1000);

	}

	@Disabled
	@Test
	void softwarePageTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		//Direct URL
//		driver.get("https://novoproso.com/sd.html");

		//From home page
		driver.get("https://novoproso.com");
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		imageWait = new WebDriverWait(driver, Duration.ofMillis(3000));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(600));
		
		imageWait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		
		//click start now button
		elementWait.until(d -> startnow.isDisplayed());
		highlightElement(driver, startnow);
		startnow.click();
		Thread.sleep(1000);

		WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
		WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
		WebElement sdElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'sd.html')]"));
		
		//wait until Services link appears and hover over it
		elementWait.until(ExpectedConditions.visibilityOf(servicesLink));
		highlightElement(driver, servicesLink);
		action.moveToElement(servicesLink).perform();
		Thread.sleep(1000);
		
		//wait until Services dropdown appears
		elementWait.until(d -> servicesDropdownElement.isDisplayed());
		Thread.sleep(1000);

		//click softwareDevelopment element to go that page
		highlightElement(driver, sdElement);
		sdElement.click();

		//wait until page loads (waiting until page main heading loads)
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		
		//get Current URL
		String sdURL = driver.getCurrentUrl();
		System.out.println("Current URL: " + sdURL);
		
		//assert page title
		String pageTitle = driver.getTitle();
		assertEquals("Novo ProSo, Inc.", pageTitle);
		
		//assert main heading
		WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
		WebElement subHeading = driver.findElement(By.cssSelector("h4"));
		assertEquals("Software Development", mainHeading.getText());
		highlightElement(driver, mainHeading);
		
		//sub heading
		assertEquals("We design custom software to boost your business using the latest tech and agile methods!", subHeading.getText());
		assertEquals("rgba(8, 117, 3, 0.8)", subHeading.getCssValue("color"));
		assertEquals("1.5px", subHeading.getCssValue("letter-spacing"));
		assertEquals("27px", subHeading.getCssValue("line-height"));
		assertEquals("600", subHeading.getCssValue("font-weight"));
		highlightElement(driver, subHeading);
		
		//hr
		assertTrue(driver.findElement(By.cssSelector("hr")).isDisplayed());

		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		highlightElement(driver, imageElement);
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/sd.png", imageElement.getDomAttribute("src"));
		
		//assert ul, li tags
		List<WebElement> ulElements = driver.findElements(By.cssSelector("ul"));
		assertEquals(5, ulElements.size());
		assertEquals(new Dimension(616,80), ulElements.get(0).getSize());
		assertEquals(new Dimension(124, 36), ulElements.get(4).getSize());

		//whether ul tags are displayed or not
		assertTrue(ulElements.get(0).isDisplayed());
		assertFalse(ulElements.get(1).isDisplayed());		
		assertFalse(ulElements.get(2).isDisplayed());
		assertFalse(ulElements.get(3).isDisplayed());
		
		Thread.sleep(1000);
		jsExecutor.executeScript("window.scrollBy(0,600)");
		Thread.sleep(1000);

		highlightElement(driver, ulElements.get(4));
		assertTrue(ulElements.get(4).isDisplayed());
		Thread.sleep(1000);
	}

	@Disabled
	@Test
	void ITStaffPageTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		//Direct URL
//		driver.get("https://novoproso.com/itstaff.html");

		//From home page
		driver.get("https://novoproso.com");
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		imageWait = new WebDriverWait(driver, Duration.ofMillis(3000));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(600));
		
		imageWait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		
		//click start now button
		elementWait.until(d -> startnow.isDisplayed());
		highlightElement(driver, startnow);
		startnow.click();
		Thread.sleep(1000);

		WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
		WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
		WebElement itstaffElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'itstaff.html')]"));
		
		//wait until Services link appears and hover over it
		elementWait.until(ExpectedConditions.visibilityOf(servicesLink));
		highlightElement(driver, servicesLink);
		action.moveToElement(servicesLink).perform();
		Thread.sleep(1000);
		
		//wait until Services dropdown appears
		elementWait.until(d -> servicesDropdownElement.isDisplayed());
		Thread.sleep(1000);

		//click ITStaff element to go that page
		highlightElement(driver, itstaffElement);
		itstaffElement.click();

		//wait until page loads (waiting until page main heading loads)
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		
		//get Current URL
		String itStaffURL = driver.getCurrentUrl();
		System.out.println("Current URL: " + itStaffURL);
		
		//assert page title
		String pageTitle = driver.getTitle();
		assertEquals("Novo ProSo, Inc.", pageTitle);
		
		//assert main heading
		WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
		WebElement subHeading = driver.findElement(By.cssSelector("h4"));
		highlightElement(driver, mainHeading);
		assertEquals("IT Staffing", mainHeading.getText());

		//sub heading
		assertEquals("Unlock your IT project potential with our "
				+ "tailored staffing "
				+ "solutions—let's build your winning team!", 
				subHeading.getText());
		assertEquals("rgba(8, 117, 3, 0.8)", subHeading.getCssValue("color"));
		assertEquals("1.5px", subHeading.getCssValue("letter-spacing"));
		assertEquals("27px", subHeading.getCssValue("line-height"));
		assertEquals("600", subHeading.getCssValue("font-weight"));
		highlightElement(driver, subHeading);
		
		//hr
		assertTrue(driver.findElement(By.cssSelector("hr")).isDisplayed());

		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		highlightElement(driver, imageElement);
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/idea.png", imageElement.getDomAttribute("src"));
		
		//assert ul, li tags
		List<WebElement> ulElements = driver.findElements(By.cssSelector("ul"));
		assertEquals(5, ulElements.size());
		assertEquals(new Dimension(616,80), ulElements.get(0).getSize());
		assertEquals(new Dimension(124, 36), ulElements.get(4).getSize());

		//whether ul tags are displayed or not
		assertTrue(ulElements.get(0).isDisplayed());
		assertFalse(ulElements.get(1).isDisplayed());		
		assertFalse(ulElements.get(2).isDisplayed());
		assertFalse(ulElements.get(3).isDisplayed());
		
		assertTrue(driver.findElement(By.linkText("Novo ProSo")).isDisplayed());
		
		Thread.sleep(1000);
		jsExecutor.executeScript("window.scrollBy(0,600)");

		wait.until(ExpectedConditions.visibilityOf(ulElements.get(4)));
		highlightElement(driver, ulElements.get(4));
		assertTrue(ulElements.get(4).isDisplayed());
		Thread.sleep(1000);
	}
	
	@Disabled
	@Test
	void cloudPageTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		//Direct URL
//		driver.get("https://novoproso.com/cloud.html");

		//From home page
		driver.get("https://novoproso.com");
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		imageWait = new WebDriverWait(driver, Duration.ofMillis(3000));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(600));
		
		imageWait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		
		//click start now button
		elementWait.until(d -> startnow.isDisplayed());
		highlightElement(driver, startnow);
		startnow.click();
		Thread.sleep(1000);

		WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
		WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
		WebElement cloudElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'cloud.html')]"));
		
		//wait until Services link appears and hover over it
		elementWait.until(ExpectedConditions.visibilityOf(servicesLink));
		highlightElement(driver, servicesLink);
		action.moveToElement(servicesLink).perform();
		Thread.sleep(1000);
		
		//wait until Services dropdown appears
		elementWait.until(d -> servicesDropdownElement.isDisplayed());
		Thread.sleep(1000);

		//click cloud element to go that page
		highlightElement(driver, cloudElement);
		cloudElement.click();

		//wait until page loads (waiting until page main heading loads)
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		
		//get Current URL
		String cloudURL = driver.getCurrentUrl();
		System.out.println("Current URL: " + cloudURL);
		
		//assert page title
		String pageTitle = driver.getTitle();
		assertEquals("Novo ProSo, Inc.", pageTitle);
		
		//assert main heading
		WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
		WebElement subHeading = driver.findElement(By.cssSelector("h4"));
		assertEquals("Cloud Computing", mainHeading.getText());
		highlightElement(driver, mainHeading);
		
		//sub heading
		assertEquals("Take your business to new heights with our cloud computing services!", subHeading.getText());
		assertEquals("rgba(8, 117, 3, 0.8)", subHeading.getCssValue("color"));
		assertEquals("1.5px", subHeading.getCssValue("letter-spacing"));
		assertEquals("27px", subHeading.getCssValue("line-height"));
		assertEquals("600", subHeading.getCssValue("font-weight"));
		highlightElement(driver, subHeading);
		
		//hr
		assertTrue(driver.findElement(By.cssSelector("hr")).isDisplayed());

		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		highlightElement(driver, imageElement);
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/idea.png", imageElement.getDomAttribute("src"));
		
		//assert ul, li tags
		List<WebElement> ulElements = driver.findElements(By.cssSelector("ul"));
		assertEquals(6, ulElements.size());
		assertEquals(new Dimension(616,80), ulElements.get(0).getSize());
		assertEquals(new Dimension(750, 120), ulElements.get(4).getSize());
		assertEquals(new Dimension(124, 36), ulElements.get(5).getSize());

		//whether ul tags are displayed or not
		assertTrue(ulElements.get(0).isDisplayed());
		assertFalse(ulElements.get(1).isDisplayed());		
		assertFalse(ulElements.get(2).isDisplayed());
		assertFalse(ulElements.get(3).isDisplayed());
		assertTrue(ulElements.get(4).isDisplayed());
		
		Thread.sleep(1000);
		jsExecutor.executeScript("window.scrollBy(0,600)");

		wait.until(d-> ulElements.get(5).isDisplayed());
		highlightElement(driver, ulElements.get(5));
		assertTrue(ulElements.get(5).isDisplayed());
		Thread.sleep(1000);
	}

	@Disabled
	@Test
	void AIMLPageTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		//Direct URL
//		driver.get("https://novoproso.com/ai.html");

		//From home page
		driver.get("https://novoproso.com");
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		imageWait = new WebDriverWait(driver, Duration.ofMillis(3000));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(600));
		
		imageWait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		
		//click start now button
		elementWait.until(d -> startnow.isDisplayed());
		highlightElement(driver, startnow);
		startnow.click();
		Thread.sleep(1000);

		WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
		WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
		WebElement AIMlElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'ai.html')]"));
		
		//wait until Services link appears and hover over it
		elementWait.until(ExpectedConditions.visibilityOf(servicesLink));
		highlightElement(driver, servicesLink);
		action.moveToElement(servicesLink).perform();
		Thread.sleep(1000);
		
		//wait until Services dropdown appears
		elementWait.until(d -> servicesDropdownElement.isDisplayed());
		Thread.sleep(1000);

		//click AI/ML element to go that page
		highlightElement(driver, AIMlElement);
		AIMlElement.click();

		//wait until page loads (waiting until page main heading loads)
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		
		//get Current URL
		String AIMLURL = driver.getCurrentUrl();
		System.out.println("Current URL: " + AIMLURL);
		
		//assert page title
		String pageTitle = driver.getTitle();
		assertEquals("Novo ProSo, Inc.", pageTitle);
		
		//assert main heading
		WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
		WebElement subHeading = driver.findElement(By.cssSelector("h4"));
		highlightElement(driver, mainHeading);
		assertEquals("Artificial Intelligence/ Machine Learning", mainHeading.getText());

		//sub heading
		assertEquals("Unlock the power of Artificial Intelligence and Machine Learning to transform your business!", subHeading.getText());
		assertEquals("rgba(8, 117, 3, 0.8)", subHeading.getCssValue("color"));
		assertEquals("1.5px", subHeading.getCssValue("letter-spacing"));
		assertEquals("27px", subHeading.getCssValue("line-height"));
		assertEquals("600", subHeading.getCssValue("font-weight"));
		highlightElement(driver, subHeading);
		
		//hr
		assertTrue(driver.findElement(By.cssSelector("hr")).isDisplayed());

		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		highlightElement(driver, imageElement);
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/idea.png", imageElement.getDomAttribute("src"));
		
		//assert ul, li tags
		List<WebElement> ulElements = driver.findElements(By.cssSelector("ul"));
		assertEquals(6, ulElements.size());
		assertEquals(new Dimension(616,80), ulElements.get(0).getSize());
		assertEquals(new Dimension(746, 144), ulElements.get(4).getSize());
		assertEquals(new Dimension(124, 36), ulElements.get(5).getSize());

		//whether ul tags are displayed or not
		assertTrue(ulElements.get(0).isDisplayed());
		assertFalse(ulElements.get(1).isDisplayed());		
		assertFalse(ulElements.get(2).isDisplayed());
		assertFalse(ulElements.get(3).isDisplayed());
		
		Thread.sleep(1000);
		jsExecutor.executeScript("window.scrollBy(0,600)");

		wait.until(ExpectedConditions.visibilityOf(ulElements.get(5)));
		highlightElement(driver, ulElements.get(5));
		assertTrue(ulElements.get(5).isDisplayed());
		Thread.sleep(1000);
	}

	@Disabled
	@Test
	void BDanalyticsPageTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		//Direct URL
//		driver.get("https://novoproso.com/bigData.html");

		//From home page
		driver.get("https://novoproso.com");
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		imageWait = new WebDriverWait(driver, Duration.ofMillis(3000));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(600));
		
		imageWait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		
		//click start now button
		elementWait.until(d -> startnow.isDisplayed());
		highlightElement(driver, startnow);
		startnow.click();
		Thread.sleep(1000);

		WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
		WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
		WebElement bigDataElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'bigData.html')]"));
		
		//wait until Services link appears and hover over it
		elementWait.until(ExpectedConditions.visibilityOf(servicesLink));
		highlightElement(driver, servicesLink);
		action.moveToElement(servicesLink).perform();
		Thread.sleep(1000);
		
		//wait until Services dropdown appears
		elementWait.until(d -> servicesDropdownElement.isDisplayed());
		Thread.sleep(1000);

		//click BigData Analytics element to go that page
		highlightElement(driver, bigDataElement);
		bigDataElement.click();

		//wait until page loads (waiting until page main heading loads)
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		
		//get Current URL
		String bigDataURL = driver.getCurrentUrl();
		System.out.println("Current URL: " + bigDataURL);
		
		//assert page title
		String pageTitle = driver.getTitle();
		assertEquals("Novo ProSo, Inc.", pageTitle);
		
		//assert main heading
		List<WebElement> mainHeading = driver.findElements(By.cssSelector("h2"));
		WebElement subHeading = driver.findElement(By.cssSelector("h4"));
		highlightElement(driver, mainHeading.getLast());
		assertEquals("BigData Analytics", mainHeading.getLast().getText());

		//sub heading
		assertEquals("Unlock the hidden insights in your data and drive business success with our Big Data Analytics services!", subHeading.getText());
		assertEquals("rgba(8, 117, 3, 0.8)", subHeading.getCssValue("color"));
		assertEquals("1.5px", subHeading.getCssValue("letter-spacing"));
		assertEquals("27px", subHeading.getCssValue("line-height"));
		assertEquals("600", subHeading.getCssValue("font-weight"));
		highlightElement(driver, subHeading);
		
		//hr
		assertTrue(driver.findElement(By.cssSelector("hr")).isDisplayed());

		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		highlightElement(driver, imageElement);
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/idea.png", imageElement.getDomAttribute("src"));
		
		
		//assert ul, li tags
		List<WebElement> ulElements = driver.findElements(By.cssSelector("ul"));
		assertEquals(7, ulElements.size());
		assertEquals(new Dimension(616,80), ulElements.get(0).getSize());
		assertEquals(new Dimension(750, 120), ulElements.get(4).getSize());
		assertEquals(new Dimension(750, 120), ulElements.get(5).getSize());
		assertEquals(new Dimension(124, 36), ulElements.get(6).getSize());

		//whether ul tags are displayed or not
		assertTrue(ulElements.get(0).isDisplayed());
		assertFalse(ulElements.get(1).isDisplayed());		
		assertFalse(ulElements.get(2).isDisplayed());
		assertFalse(ulElements.get(3).isDisplayed());
		assertTrue(ulElements.get(4).isDisplayed());
		assertTrue(ulElements.get(5).isDisplayed());
		
		Thread.sleep(1000);
		jsExecutor.executeScript("window.scrollBy(0,600)");

		wait.until(d->ulElements.get(6).isDisplayed());
		highlightElement(driver, ulElements.get(6));
		assertTrue(ulElements.get(6).isDisplayed());
		Thread.sleep(1000);
	}

	@Disabled
	@Test
	void HRAPageTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		//Direct URL
//		driver.get("https://novoproso.com/hra.html");

		//From home page
		driver.get("https://novoproso.com");
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		imageWait = new WebDriverWait(driver, Duration.ofMillis(3000));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(600));
		
		imageWait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		
		//click start now button
		elementWait.until(d -> startnow.isDisplayed());
		highlightElement(driver, startnow);
		startnow.click();
		Thread.sleep(1000);

		WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
		WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
		WebElement hraElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'hra.html')]"));
		
		//wait until Services link appears and hover over it
		elementWait.until(ExpectedConditions.visibilityOf(servicesLink));
		highlightElement(driver, servicesLink);
		action.moveToElement(servicesLink).perform();
		Thread.sleep(1000);
		
		//wait until Services dropdown appears
		elementWait.until(d -> servicesDropdownElement.isDisplayed());
		Thread.sleep(1000);

		//click AI/ML element to go that page
		highlightElement(driver, hraElement);
		hraElement.click();

		//wait until page loads (waiting until page main heading loads)
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		
		//get Current URL
		String hraURL = driver.getCurrentUrl();
		System.out.println("Current URL: " + hraURL);
		
		//assert page title
		String pageTitle = driver.getTitle();
		assertEquals("Novo ProSo, Inc.", pageTitle);
		
		//assert main heading
		WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
		WebElement subHeading = driver.findElement(By.cssSelector("h4"));
		highlightElement(driver, mainHeading);
		assertEquals("Healthcare Research & Analysis", mainHeading.getText());

		//sub heading
		assertEquals("Transform healthcare outcomes with data-driven insights!", subHeading.getText());
		assertEquals("rgba(8, 117, 3, 0.8)", subHeading.getCssValue("color"));
		assertEquals("1.5px", subHeading.getCssValue("letter-spacing"));
		assertEquals("27px", subHeading.getCssValue("line-height"));
		assertEquals("600", subHeading.getCssValue("font-weight"));
		highlightElement(driver, subHeading);
		
		//hr
		assertTrue(driver.findElement(By.cssSelector("hr")).isDisplayed());

		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		highlightElement(driver, imageElement);
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/idea.png", imageElement.getDomAttribute("src"));
		
		//assert ul, li tags
		List<WebElement> ulElements = driver.findElements(By.cssSelector("ul"));
		assertEquals(7, ulElements.size());
		assertEquals(new Dimension(616,80), ulElements.get(0).getSize());
		assertEquals(new Dimension(750, 120), ulElements.get(4).getSize());
		assertEquals(new Dimension(750, 120), ulElements.get(5).getSize());
		assertEquals(new Dimension(124, 36), ulElements.get(6).getSize());

		//whether ul tags are displayed or not
		assertTrue(ulElements.get(0).isDisplayed());
		assertFalse(ulElements.get(1).isDisplayed());		
		assertFalse(ulElements.get(2).isDisplayed());
		assertFalse(ulElements.get(3).isDisplayed());
		assertTrue(ulElements.get(4).isDisplayed());
		assertTrue(ulElements.get(5).isDisplayed());
		
		Thread.sleep(1000);
		jsExecutor.executeScript("window.scrollBy(0,600)");

		wait.until(d-> ulElements.get(6).isDisplayed());
		highlightElement(driver, ulElements.get(6));
		assertTrue(ulElements.get(6).isDisplayed());
		Thread.sleep(1000);
	}

	@Disabled
	@Test
	void ITPMPageTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		//Direct URL
//		driver.get("https://novoproso.com/itpm.html");

		//From home page
		driver.get("https://novoproso.com");
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		imageWait = new WebDriverWait(driver, Duration.ofMillis(3000));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(600));
		
		imageWait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		
		//click start now button
		elementWait.until(d -> startnow.isDisplayed());
		highlightElement(driver, startnow);
		startnow.click();
		Thread.sleep(1000);

		WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
		WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
		WebElement itpmElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'itpm.html')]"));
		
		//wait until Services link appears and hover over it
		elementWait.until(ExpectedConditions.visibilityOf(servicesLink));
		highlightElement(driver, servicesLink);
		action.moveToElement(servicesLink).perform();
		Thread.sleep(1000);
		
		//wait until Services dropdown appears
		elementWait.until(d -> servicesDropdownElement.isDisplayed());
		Thread.sleep(1000);

		//click AI/ML element to go that page
		highlightElement(driver, itpmElement);
		itpmElement.click();

		//wait until page loads (waiting until page main heading loads)
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		
		//get Current URL
		String itpmURL = driver.getCurrentUrl();
		System.out.println("Current URL: " + itpmURL);		
		
		//assert page title
		String pageTitle = driver.getTitle();
		assertEquals("Novo ProSo, Inc.", pageTitle);
		
		//assert main heading
		WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
		WebElement subHeading = driver.findElement(By.cssSelector("h4"));
		wait.until(d->subHeading.isDisplayed());
		highlightElement(driver, mainHeading);
		assertEquals("IT Project/Program Management", mainHeading.getText());

		//sub heading
		assertEquals("Deliver your IT projects on time, within budget, and with exceptional quality!", subHeading.getText());
		assertEquals("rgba(8, 117, 3, 0.8)", subHeading.getCssValue("color"));
		assertEquals("1.5px", subHeading.getCssValue("letter-spacing"));
		assertEquals("27px", subHeading.getCssValue("line-height"));
		assertEquals("600", subHeading.getCssValue("font-weight"));
		highlightElement(driver, subHeading);
		
		//hr
		assertTrue(driver.findElement(By.cssSelector("hr")).isDisplayed());

		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		highlightElement(driver, imageElement);
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/idea.png", imageElement.getDomAttribute("src"));
		
		//assert ul, li tags
		List<WebElement> ulElements = driver.findElements(By.cssSelector("ul"));
		assertEquals(6, ulElements.size());
		assertEquals(new Dimension(616,80), ulElements.get(0).getSize());
		assertEquals(new Dimension(750, 168), ulElements.get(4).getSize());
		assertEquals(new Dimension(124, 36), ulElements.get(5).getSize());

		//whether ul tags are displayed or not
		assertTrue(ulElements.get(0).isDisplayed());
		assertFalse(ulElements.get(1).isDisplayed());		
		assertFalse(ulElements.get(2).isDisplayed());
		assertFalse(ulElements.get(3).isDisplayed());
		assertTrue(ulElements.get(4).isDisplayed());
		
		Thread.sleep(1000);
		jsExecutor.executeScript("window.scrollBy(0,600)");

		wait.until(ExpectedConditions.visibilityOf(ulElements.get(5)));
		highlightElement(driver, ulElements.get(5));
		assertTrue(ulElements.get(5).isDisplayed());
		Thread.sleep(1000);
	}

	@Disabled
	@Test
	void networkPageTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		//Direct URL
//		driver.get("https://novoproso.com/network.html");

		//From home page
		driver.get("https://novoproso.com");
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		imageWait = new WebDriverWait(driver, Duration.ofMillis(3000));
		elementWait = new WebDriverWait(driver, Duration.ofMillis(600));
		
		imageWait.until(d-> driver.findElement(By.xpath("//a[contains(@class, 'btn-start')]")).isDisplayed());
		WebElement startnow = driver.findElements(By.xpath("//div[contains(@class,'caption')]/a")).get(0);
		
		//click start now button
		elementWait.until(d -> startnow.isDisplayed());
		highlightElement(driver, startnow);
		startnow.click();
		Thread.sleep(1000);

		WebElement servicesLink = driver.findElement(By.xpath("//li/a[@href='#services']"));
		WebElement servicesDropdownElement = driver.findElements(By.xpath("//li/ul")).get(2);
		WebElement networkElement = driver.findElement(By.xpath("//li/ul/li/a[contains(@href, 'network.html')]"));
		
		//wait until Services link appears and hover over it
		elementWait.until(ExpectedConditions.visibilityOf(servicesLink));
		highlightElement(driver, servicesLink);
		action.moveToElement(servicesLink).perform();
		Thread.sleep(1000);
		
		//wait until Services dropdown appears
		elementWait.until(d -> servicesDropdownElement.isDisplayed());
		Thread.sleep(1000);

		//click AI/ML element to go that page
		highlightElement(driver, networkElement);
		networkElement.click();

		//wait until page loads (waiting until page main heading loads)
		wait.until(d -> driver.findElement(By.xpath("//h2")).isDisplayed());
		
		//get Current URL
		String networkURL = driver.getCurrentUrl();
		System.out.println("Current URL: " + networkURL);		
		
		//assert page title
		String pageTitle = driver.getTitle();
		assertEquals("Novo ProSo, Inc.", pageTitle);
		
		//assert main heading
		WebElement mainHeading = driver.findElement(By.cssSelector("h2"));
		WebElement subHeading = driver.findElement(By.cssSelector("h4"));
		wait.until(d->subHeading.isDisplayed());
		highlightElement(driver, mainHeading);
		assertEquals("IT Network & Security", mainHeading.getText());

		//sub heading
		assertEquals("Protect your business from cyber threats and optimize your network performance with our comprehensive IT network and security services!", subHeading.getText());
		assertEquals("rgba(8, 117, 3, 0.8)", subHeading.getCssValue("color"));
		assertEquals("1.5px", subHeading.getCssValue("letter-spacing"));
		assertEquals("27px", subHeading.getCssValue("line-height"));
		assertEquals("600", subHeading.getCssValue("font-weight"));
		highlightElement(driver, subHeading);
		
		//hr
		assertTrue(driver.findElement(By.cssSelector("hr")).isDisplayed());

		//assert image
		WebElement imageElement = driver.findElement(By.cssSelector("img"));
		highlightElement(driver, imageElement);
		assertTrue(imageElement.isDisplayed());
		assertEquals("images/idea.png", imageElement.getDomAttribute("src"));
		
		//assert ul, li tags
		List<WebElement> ulElements = driver.findElements(By.cssSelector("ul"));
		assertEquals(6, ulElements.size());
		assertEquals(new Dimension(616,80), ulElements.get(0).getSize());
		assertEquals(new Dimension(750, 144), ulElements.get(4).getSize());
		assertEquals(new Dimension(124, 36), ulElements.get(5).getSize());

		//whether ul tags are displayed or not
		assertTrue(ulElements.get(0).isDisplayed());
		assertFalse(ulElements.get(1).isDisplayed());		
		assertFalse(ulElements.get(2).isDisplayed());
		assertFalse(ulElements.get(3).isDisplayed());
		assertTrue(ulElements.get(4).isDisplayed());
		
		Thread.sleep(1000);
		jsExecutor.executeScript("window.scrollBy(0,600)");

		wait.until(ExpectedConditions.visibilityOf(ulElements.get(5)));
		highlightElement(driver, ulElements.get(5));
		assertTrue(ulElements.get(5).isDisplayed());
		Thread.sleep(1000);
	}

}
