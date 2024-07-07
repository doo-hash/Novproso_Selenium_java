package test.novoproso;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class footerHighlight {

	highLightElement highLight = new highLightElement();
	
	public footerHighlight() {
		// TODO Auto-generated constructor stub
	}

	public void footerHighlightElement(WebDriver driver, JavascriptExecutor jsExecutor, WebDriverWait wait) {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight);");

		wait.until(d -> driver.findElement(By.xpath("//div[contains(@class,'footer-logo')]")).isDisplayed());
		WebElement footerLogoLink = driver.findElement(By.xpath("//div[contains(@class,'footer-logo')]"));
		WebElement footerSocialIcons = driver.findElement(By.xpath("//div[contains(@class,'social-icons')]/ul"));
		WebElement policyLink = driver.findElement(By.xpath("//div/p/a[contains(@href,'policy.html')]"));
		WebElement footerbottomCopyright = driver.findElement(By.xpath("//div[contains(@id,'md')]/p"));
		WebElement footerbottomDesignedBy = driver.findElement(By.xpath("//p[contains(@class,'pull-right')]"));

		highLight.highlightElement(driver, footerLogoLink);
		highLight.highlightElement(driver, footerSocialIcons);
		highLight.highlightElement(driver, policyLink);
		highLight.highlightElement(driver, footerbottomCopyright);
		highLight.highlightElement(driver, footerbottomDesignedBy);
	}
}
