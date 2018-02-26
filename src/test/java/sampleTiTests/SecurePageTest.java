package sampleTiTests;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import sample.application.theinternetPage.TiBaseTest;
import sample.application.theinternetPage.TiLoginPage;
import sample.application.theinternetPage.TiSecureAreaPage;
import sample.framework.spring.baseTest.LoginBeforeMethod;

public class SecurePageTest extends TiBaseTest implements LoginBeforeMethod{

	@Autowired
	private TiSecureAreaPage securePage;
	
	@Autowired
	private TiLoginPage loginPage;
	
	@Test
	public void logOutTest() {
		securePage.doLogout();
		String messageText = loginPage.getTiFlashMessages().getMessageText();
		Assert.assertEquals(messageText, "You logged out of the secure area!");
		
	}
	
	@Test
	public void closeMessageTest() {
		securePage.getTiFlashMessages().closeFlashMessage();
		String text = securePage.getTiFlashMessages().getPageComponentElement().getText();
		Assert.assertEquals(text, "");
	}
}
