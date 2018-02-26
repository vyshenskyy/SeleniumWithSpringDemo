package sample.framework.spring.baseTest;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

public class ScreenshotOnFailure {
	
	  private static final String SCREENSHOT_SUBFOLDER = "/screenshots";
	
	  /**
     * This method can be directly used from @AfterMethod. Sample
     * 
     * <pre>
     * &#064;AfterMethod(alwaysRun = true)
     * public void takeScreenshot(ITestResult result) {
     *     getScreenshotForFailedMethod(result, driver);
     * }
     * </pre>
     * 
     * @param result
     * @param driver
     * @return true if the screenshot is written
     */
    public static boolean getScreenshotForFailedMethod(ITestResult result, WebDriver driver) {
        String className = getClassName(result);
        String methodName = result.getMethod().getMethodName();
        String testName = className + "." + methodName ;
        if (result.isSuccess()) {
            return false;
        }

        ITestContext testContext = result.getTestContext();
        String testOutputDirectory = testContext.getOutputDirectory();
        String screenshotDir = testOutputDirectory + "/.." + SCREENSHOT_SUBFOLDER;

        try {

            String prefix = testName + "_";
            ScreenshotTaker.takeScreenshot(driver, prefix, screenshotDir);
            return true;
        } catch (Exception e) {
            return false;
        }
        
        
    }
    
    private static String getClassName(ITestResult result) {
        ITestNGMethod method = result.getMethod();
        return method.getTestClass().getRealClass().getSimpleName();
    }
}
