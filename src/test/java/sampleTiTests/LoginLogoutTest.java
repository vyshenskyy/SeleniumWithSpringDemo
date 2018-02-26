package sampleTiTests;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import sample.application.theinternetPage.TiBaseTest;
import sample.application.theinternetPage.TiLoginPage;
import sample.application.theinternetPage.TiSecureAreaPage;

public class LoginLogoutTest extends TiBaseTest {
	
	@Autowired
	private TiLoginPage loginPage;
	
	@Autowired
	private TiSecureAreaPage securePage;

	@Test
	public void loginLogOutTest() {
		goToLoginPage();
		loginPage.doLogin();
		boolean success = securePage.getTiFlashMessages().isSuccess();
		Assert.assertTrue(success, "We must be logged in");
		String messageText = securePage.getTiFlashMessages().getMessageText();
		Assert.assertEquals(messageText, "You logged into a secure area!");
		securePage.doLogout();
		String messageText2 = loginPage.getTiFlashMessages().getMessageText();
		Assert.assertEquals(messageText2, "You logged out of the secure area!");
	}

	
	@Test
	public void loginAfterLogOutTest() {
		goToLoginPage();
		loginPage.doLogin();
		securePage.doLogout();
		TiSecureAreaPage nextPage = (TiSecureAreaPage) loginPage.doLogin();
		boolean success2 = nextPage.getTiFlashMessages().isSuccess();
		Assert.assertTrue(success2, "We must be logged in");
	}
}
