package sample.application.multitenant.pages;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import sample.framework.spring.baseTest.BaseTest;

public abstract class MtBaseTest extends BaseTest {

	@Value(("${loginName}"))
	private String loginName;
	
	@Value(("${password}"))
	private String password;
	
	@Value(("${tenant}"))
	private String tenantName;
	
	@Autowired
	protected MtPage mtPage;
	
	
	@Autowired
	private MtLoginPage loginPage;
	
	@Override
	public void loginToTestUrl() {
		goToLoginPage();
		loginPage.doLogin(loginName, password, tenantName);
	}

	protected void goToLoginPage() {
		if(mtPage.isLoggedIn()) {
			mtPage.doLogout(); 
		}
	}
	
	@Override
	public void openBaseUrl() {
		if(isAbsoluteUrl(baseUrl)) {
			driver.get(baseUrl);
		} else {
			URL resource = getClass().getClassLoader().getResource(baseUrl);
			String urlString = resource.toString();
			driver.get(urlString);	
		}
		
	}
	 private boolean isAbsoluteUrl(String relativeOrAbsoluteUrl) {
		    return relativeOrAbsoluteUrl.toLowerCase().startsWith("http:") ||
		        relativeOrAbsoluteUrl.toLowerCase().startsWith("https:") ||
		        isLocalFile(relativeOrAbsoluteUrl);
		  }
		  
	  private boolean isLocalFile(String url) {
		    return url.toLowerCase().startsWith("file:");
		  }


	
}
