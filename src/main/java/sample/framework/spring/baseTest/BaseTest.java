package sample.framework.spring.baseTest;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import sample.framework.spring.selenium.SeleniumTestConfiguration;

@ContextConfiguration(classes = { SeleniumTestConfiguration.class })
public abstract class BaseTest extends AbstractTestNGSpringContextTests {

	@Autowired
	protected WebDriver driver;
	
	@Value("${implicit.timeout:10}")
	private long implicitlyWaitTimeout;

	@Value("${base.url}")
	protected String baseUrl;
	
	public void openBaseUrl() {
		driver.get(baseUrl);
	}
	
	@BeforeClass
	public void setUp()  {
		driver.manage().timeouts().implicitlyWait(implicitlyWaitTimeout, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		openBaseUrl();
		if (this instanceof LoginBeforeClass) {
			loginToTestUrl();
		}
	}

	@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		logger.info(methodName + " finished"); // Using logger from AbstractTestNGSpringContextTests
		ScreenshotOnFailure.getScreenshotForFailedMethod(result, driver);
	}

	@BeforeMethod
	public void loginToApplication(Method testMethod) {
		if (this instanceof LoginBeforeMethod) {
			loginToTestUrl();
		}
	}

	public abstract void loginToTestUrl();
	

	
}
