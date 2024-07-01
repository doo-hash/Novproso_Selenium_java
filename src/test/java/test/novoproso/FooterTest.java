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
import org.openqa.selenium.interactions.Actions;

class FooterTest {

	WebDriver driver;
	JavascriptExecutor js;
	Actions action;

	@BeforeEach
	void setUp() throws Exception {
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		action = new Actions(driver);
	}

	@AfterEach
	void tearDown() throws Exception {
		driver.quit();
	}


	@Disabled
	@Test
	void footerTestMethod() throws InterruptedException {
		driver.manage().window().maximize();
		driver.get("https://novoproso.com/csr.html");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		//it scrolls to bottom -- method 1
//		js.executeScript("window.scrollBy(0, 550)", "");

		List<WebElement> websiteLinks = driver.findElements(By.linkText("Novo ProSo, Inc."));
		assertEquals(2, websiteLinks.size());
		assertEquals("index.html", websiteLinks.get(1).getDomAttribute("href"));
		String currentHandle = driver.getWindowHandle(); 
		assertNotNull(currentHandle);
		driver.findElement(By.linkText("Novo ProSo, Inc")).click();
		
		Thread.sleep(1000);
		Object[] windowHandles = driver.getWindowHandles().toArray(); 
		assertEquals(1, windowHandles.length);
		assertEquals("https://novoproso.com/index.html", driver.getCurrentUrl());
		
		Thread.sleep(1000);
		driver.navigate().back();
		assertEquals("https://novoproso.com/csr.html", driver.getCurrentUrl());

		Thread.sleep(1000);
		WebElement twitterElement = driver.findElement(By.className("twitter"));
		WebElement facebookElement = driver.findElement(By.className("facebook"));
		WebElement linkedinElement = driver.findElement(By.className("linkedin"));

		assertEquals("https://mobile.twitter.com/novoproso", twitterElement.getDomAttribute("href"));
		assertEquals("https://www.facebook.com/novoproso", facebookElement.getDomAttribute("href"));
		assertEquals("https://linkedin.com/company/novo-proso-inc", linkedinElement.getDomAttribute("href"));
		
		twitterElement.click();
		String twittterHandle = driver.getWindowHandle();
		assertNotNull(twittterHandle);
		Thread.sleep(2000);
		driver.switchTo().window(currentHandle);
		Thread.sleep(2000);
		
		facebookElement.click();
		String facebookHandle = driver.getWindowHandle();
		assertNotNull(facebookHandle);
		Thread.sleep(3000);
		driver.switchTo().window((String) currentHandle);
		Thread.sleep(3000);
		
		linkedinElement.click();
		Thread.sleep(1000);
		driver.navigate().back();
		Thread.sleep(1000);
		driver.switchTo().window(twittterHandle);

		System.out.println(twittterHandle);
		System.out.println(facebookHandle);
		System.out.println(currentHandle);
		assertEquals(currentHandle, twittterHandle);
		Object[] currentWindowHandles = driver.getWindowHandles().toArray(); 
		assertEquals(3, currentWindowHandles.length);

		Thread.sleep(1000);
		driver.switchTo().window((String) currentWindowHandles[2]);
		Thread.sleep(1000);
		driver.close();
		assertEquals(2, driver.getWindowHandles().toArray().length);
		Thread.sleep(1000);
		
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
		Thread.sleep(1000);
		driver.close();
		assertEquals(1, driver.getWindowHandles().toArray().length);
		Thread.sleep(1000);

		driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
		assertEquals("https://novoproso.com/csr.html", driver.getCurrentUrl());
		WebElement policyElement = driver.findElement(By.linkText("Disclaimer & PrivacyPolicy"));
		policyElement.click();
		Thread.sleep(1000);
		assertEquals("https://novoproso.com/policy.html", driver.getCurrentUrl());
		assertEquals("Novo ProSo, Inc.", driver.getTitle());
		driver.navigate().back();
		Thread.sleep(1000);
		
		WebElement copyrightElement = driver.findElement(By.id("md"));
		assertEquals("© 2024 Novo ProSo, Inc.", copyrightElement.getText());
		websiteLinks.get(1).click();
		Thread.sleep(1000);
		assertEquals("https://novoproso.com/index.html", driver.getCurrentUrl());
		driver.navigate().back();
		
		Thread.sleep(1000);
		WebElement designedByElement = driver.findElement(By.className("pull-right"));
		assertEquals("Designed by Novo ProSo", designedByElement.getText());
		WebElement designedByLinkElement = driver.findElement(By.linkText("Novo ProSo"));
		designedByLinkElement.click();
		Thread.sleep(1000);
		assertEquals("https://novoproso.com/", driver.getCurrentUrl());
		driver.navigate().back();
		
		
		
	}

	@Test
	void footerMobileWindowMethod() throws InterruptedException {
		driver.manage().window().setSize(new Dimension(673,690));
		driver.get("https://novoproso.com/csr.html");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//it scrolls to bottom of the page-- method 2
		js.executeScript("window.scrollBy(0, document.body.scrollHeight)");

		
		//it scrolls down the page till the element is found - method 3
//		WebElement footerBottomElement = driver.findElement(By.className("pull-right"));
//		js.executeScript("arguments[0].scrollIntoView()", footerBottomElement);
		
		
		List<WebElement> websiteLinks = driver.findElements(By.linkText("Novo ProSo, Inc."));
		WebElement footerWebsiteLink = driver.findElement(By.linkText("Novo ProSo, Inc"));
		WebElement twitterElement = driver.findElement(By.className("twitter"));
		WebElement facebookElement = driver.findElement(By.className("facebook"));
		WebElement linkedinElement = driver.findElement(By.className("linkedin"));
		WebElement policyElement = driver.findElement(By.linkText("Disclaimer & PrivacyPolicy"));
		List<WebElement> spanLinkElements = driver.findElements(By.cssSelector("span"));
		Thread.sleep(1000);
		action.moveToElement(footerWebsiteLink).build().perform();
		Thread.sleep(1000);
		assertEquals("rgba(252, 208, 93, 1)", footerWebsiteLink.getCssValue("color"));
		
		
		Thread.sleep(1000);
		action.moveToElement(twitterElement).build().perform();
		Thread.sleep(1000);
		assertEquals("rgba(0, 0, 0, 1)", driver.findElement(By.className("twitter")).getCssValue("background-color"));
		assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("twitter")).getCssValue("color"));
		
		Thread.sleep(1000);
		action.moveToElement(facebookElement).build().perform();
		Thread.sleep(1000);
		assertEquals("rgba(59, 89, 153, 1)", facebookElement.getCssValue("background-color"));
		assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("facebook")).getCssValue("color"));		
		
		Thread.sleep(1000);
		action.moveToElement(linkedinElement).build().perform();
		Thread.sleep(1000);
		assertEquals("rgba(3, 109, 192, 1)", linkedinElement.getCssValue("background-color"));
		assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("linkedin")).getCssValue("color"));

		Thread.sleep(1000);
		action.moveToElement(policyElement).build().perform();
		Thread.sleep(500);
		assertEquals("rgba(252, 208, 93, 1)", policyElement.getCssValue("color"));
		
		Thread.sleep(1000);
		action.moveToElement(websiteLinks.get(1)).build().perform();
		Thread.sleep(1000);
		assertEquals("rgba(8, 8, 8, 1)", websiteLinks.get(1).getCssValue("color"));
		assertEquals("underline solid rgb(8, 8, 8)", websiteLinks.get(1).getCssValue("text-decoration"));
		
		Thread.sleep(1000);
		action.moveToElement(spanLinkElements.get(4)).build().perform();
		Thread.sleep(1000);
		assertEquals("rgba(0, 221, 0, 1)", spanLinkElements.get(4).getCssValue("color"));

		Thread.sleep(1000);
		action.moveToElement(driver.findElement(By.linkText("Novo ProSo"))).build().perform();
//		assertEquals("underline solid rgb(8, 8, 8)", driver.findElement(By.linkText("Novo ProSo")).getCssValue("text-decoration"));
		Thread.sleep(1000);
	}
}
