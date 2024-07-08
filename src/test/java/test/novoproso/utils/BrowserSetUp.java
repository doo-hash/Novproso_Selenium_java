package test.novoproso.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserSetUp {

	public BrowserSetUp() {
		// TODO Auto-generated constructor stub
	}
	
	public WebDriver getBrowserSetUp(String browserName) {
		WebDriver driver = null;
		switch (browserName) {
		case "chrome":
			ChromeOptions chromeoptions = new ChromeOptions();
			chromeoptions.addArguments("start-maximized");
			driver = new ChromeDriver(chromeoptions);
			break;
		case "msedge":
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("start-maximized");
			driver = new EdgeDriver(edgeOptions);
			break;
		case "firefox":
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			driver = new FirefoxDriver(firefoxOptions);
			driver.manage().window().maximize();
			break;
		default:
			break;
		}
		
		return driver;
	}

}
