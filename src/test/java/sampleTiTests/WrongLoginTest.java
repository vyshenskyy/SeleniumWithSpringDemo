package sampleTiTests;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import sample.application.theinternetPage.TiBaseTest;
import sample.application.theinternetPage.TiLoginPage;
import sample.application.theinternetPage.TiSecureAreaPage;

public class WrongLoginTest extends TiBaseTest {
	
	@Autowired
	private TiLoginPage loginPage;
	
	@Autowired
	private TiSecureAreaPage securePage;

	@Test
	public void wrongLoginTest() {
		goToLoginPage();
		loginPage.doLogin("abcd","1234");
		boolean success = securePage.getTiFlashMessages().isSuccess();
		Assert.assertFalse(success, "We must not be logged in");
		boolean error = loginPage.getTiFlashMessages().isError();
		Assert.assertTrue(error, "We must see error");
		String messageText = loginPage.getTiFlashMessages().getMessageText();
		Assert.assertEquals(messageText, "Your username is invalid!");
		loginPage.doLogin("tomsmith","1234");
		String messageText2 = loginPage.getTiFlashMessages().getMessageText();
		Assert.assertEquals(messageText2, "Your password is invalid!");
	}
	
	@Test
	public void correctAfterWrongLoginTest() {
		goToLoginPage();
		loginPage.doLogin("abcd","1234");
		loginPage.doLogin();
		boolean success = securePage.getTiFlashMessages().isSuccess();
		Assert.assertTrue(success, "We must be logged in");
		String messageText = securePage.getTiFlashMessages().getMessageText();
		Assert.assertEquals(messageText, "You logged into a secure area!");
		securePage.doLogout();
	}
}
