package sampleMtTests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;
import org.testng.annotations.Test;

import sample.application.multitenant.pages.MtBaseTest;
import sample.application.multitenant.pages.MtLoginPage;
import sample.application.multitenant.pages.MtPage;
import sample.framework.spring.baseTest.LoginBeforeMethod;

public class LoginLogoutTest extends MtBaseTest implements LoginBeforeMethod {
	
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
	public void loginLogoutTest() {
		goToLoginPage();
		loginPage.doLogin(loginName, password, tenantName);
		boolean commonPartDisplayed = homePage.isCommonPartDisplayed();
		Assert.assertTrue(commonPartDisplayed);
		boolean loggedIn = homePage.isLoggedIn();
		Assert.assertTrue(loggedIn);
		homePage.doLogout();
		commonPartDisplayed = homePage.isCommonPartDisplayed();
		Assert.assertFalse(commonPartDisplayed);
	}
	
	@Test
	public void loginAndLogoutTest() {
		goToLoginPage();
		loginPage.doLogin(loginName, password, tenantName);
		homePage.doLogout();
		loginPage.doLogin(loginName, password, tenantName);
		boolean commonPartDisplayed = homePage.isCommonPartDisplayed();
		Assert.assertTrue(commonPartDisplayed);
		boolean loggedIn = homePage.isLoggedIn();
		Assert.assertTrue(loggedIn);
	}

}
