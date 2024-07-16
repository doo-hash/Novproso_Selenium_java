package test.novoproso.utilities;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.http.UrlPath;

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

	public RemoteWebDriver getBrowserGridSetUp(String browserName) throws MalformedURLException {
		RemoteWebDriver driver = null;
		DesiredCapabilities capabilities = new DesiredCapabilities();

		switch (browserName) {
		case "chrome":
			capabilities.setBrowserName("chrome");
			capabilities.setPlatform(Platform.WIN11);
			driver = new RemoteWebDriver(new URL("http://localhost:4444/"), capabilities);
			driver.manage().window().maximize();
			break;
		case "msedge":
			capabilities.setBrowserName("MicrosoftEdge");
			capabilities.setPlatform(Platform.WIN11);
			driver = new RemoteWebDriver(new URL("http://localhost:4444/"), capabilities);
			driver.manage().window().maximize();
			break;
		case "firefox":
			break;
		default:
			break;
		}
		
		return driver;
	}
}
