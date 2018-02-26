package sample.framework.spring.selenium;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class BrowserConfiguration {
	
	
	@Value("${selenium.browser:chrome}")
	private  String browser;
	
	@Value("${browserPath.geckodriver:}")
	private String geckodriverPath;
	
	@Value("${browserPath.chromedriver:}")
	private String chromeDriverPath;
	
	@Value("${selenium.grid:false}")
	private boolean useGrid;

	@Value("${selenium.grid.url:}")
	private String gridUrl;
	
	@Value("${headless.browser:false}")
	private boolean useHeadlessBrowser;
	
	 @Bean(destroyMethod = "quit")
	 @Scope("thread") 
	 public WebDriver webDriver()  {	 
		 switch(browser) {
			case "chrome":
				ChromeOptions chromeOptions = setChromeOptions();
				if(useGrid) {
					return remoteWebDriver(chromeOptions);
				} else {
					 if(chromeDriverPath != null && !chromeDriverPath.isEmpty()) {
						  System.setProperty("webdriver.chrome.driver", chromeDriverPath);
					  }
					return new ChromeDriver(chromeOptions);
				}
		 	case "firefox":
			default:
				 FirefoxOptions firefoxOptions = setFirefoxOptions();
				 if(useGrid) {
						return remoteWebDriver(firefoxOptions);
					} else {
						 if(geckodriverPath != null && !geckodriverPath.isEmpty()) {
							  System.setProperty("webdriver.gecko.driver", geckodriverPath);
						  }
						return new FirefoxDriver(firefoxOptions);
					}
		 }
	 }
	
	private WebDriver remoteWebDriver(Capabilities capabilities) {
		try {
			return  new RemoteWebDriver(new URL(gridUrl), capabilities);
		} catch (MalformedURLException e) {
		} 
		return null;
	}
	
	private ChromeOptions setChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-infobars"); // Disable 'Chrome is being controlled by automated test software'
		if(useHeadlessBrowser) {
			options.setHeadless(true); // Headless browser
		}
		return options;
	}
	
	private FirefoxOptions setFirefoxOptions() {
		FirefoxOptions options = new FirefoxOptions();
		if(useHeadlessBrowser) {
			options.setHeadless(true);
		}
		return options;
	}
}


