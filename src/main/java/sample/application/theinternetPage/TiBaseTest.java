package sample.application.theinternetPage;

import org.springframework.beans.factory.annotation.Autowired;
import sample.framework.spring.baseTest.BaseTest;

public abstract class TiBaseTest extends BaseTest {

	@Autowired
	private TiLoginPage loginPage;
	
	protected void goToLoginPage() {
		String loginPageUrl = baseUrl + "login";
		driver.get(loginPageUrl);
	}
	
	@Override
	public void loginToTestUrl() {
		goToLoginPage();
		loginPage.doLogin();
	}
	

}
