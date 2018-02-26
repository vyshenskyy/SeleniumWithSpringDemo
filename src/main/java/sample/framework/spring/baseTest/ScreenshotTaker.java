package sample.framework.spring.baseTest;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.google.common.io.Files;



public class ScreenshotTaker {
	
    private static final String SCREENSHOTS_DIR = "./target/screenshots";
    
    /**
     * Takes a screenshot and writes it to the specified folder
     * 
     * @param driver
     *            WebDriver for which a screen shot should be taken
     * @param prefix
     *            the name of screen shot will be started with this prefix
     * @param outputDir
     *            the folder to which the screenshot will be written
     * @return File with saved screenshot
     * @throws IOException
     */
    public static File takeScreenshot(WebDriver driver, String prefix, String outputDir) {
        File screenshotFile = null;
        try {
            File screenshotDir = new File(outputDir);
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }
            screenshotFile = File.createTempFile(prefix, ".png", new File(outputDir));
            try {
                if (driver == null) {
                    throw new IllegalArgumentException(" The WebDriver is null ");
                }
                if (!(driver instanceof TakesScreenshot) ) {
//                	String message = "This driver does not support screenshots";
                  return screenshotFile;
                }
                    TakesScreenshot screenshoter = (TakesScreenshot) driver;
                    Files.copy(screenshoter.getScreenshotAs(OutputType.FILE), screenshotFile);
            } catch (Exception e) {
                /* Cannot take a screenshot */
            }
        } catch (IOException e1) {
        }
        return screenshotFile;
    }
    
    /**
     * Writes the screenshot to the default folder
     * 
     * @param driver
     * @param prefix
     * @return
     */
    public static File takeScreenshot(WebDriver driver, String prefix) {
        return takeScreenshot(driver, prefix, SCREENSHOTS_DIR);
    }
 
    
  }
