package sampleMtTests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;
import org.testng.annotations.Test;

import sample.application.multitenant.pages.MtBaseTest;
import sample.application.multitenant.pages.MtLoginPage;
import sample.application.multitenant.pages.MtPage;

public class WrongLoginTest extends MtBaseTest {
	
	@Autowired
	private MtLoginPage loginPage;
	
	@Autowired
	private MtPage homePage;
	
	@Value(("${loginName}"))
	private String loginName;
	
	@Value(("${password}"))
	private String password;
	
	@Value(("${tenant}"))
	private String tenantName;
	
	@Test
	public void wrongLoginTest() {
		goToLoginPage();
		loginPage.doLogin("asdf", "qwerty", tenantName);
		boolean displayedError = loginPage.getLoginErrorElement().isDisplayed();
		Assert.assertTrue(displayedError);
		boolean loggedIn = homePage.isLoggedIn();
		Assert.assertFalse(loggedIn);
	}
	
	@Test
	public void correctAfterWrongLoginTest() {
		goToLoginPage();
		loginPage.doLogin("aaa", "bbb", tenantName);
		loginPage.doLogin(loginName, password, tenantName);
		boolean commonPartDisplayed = homePage.isCommonPartDisplayed();
		Assert.assertTrue(commonPartDisplayed);
		boolean loggedIn = homePage.isLoggedIn();
		Assert.assertTrue(loggedIn);
	}
}
