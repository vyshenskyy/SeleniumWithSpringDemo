package sample.application.multitenant.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class MtLoginPage extends MtAbstractPage {
	
	@Autowired
	private MtPage homePage;
	
	@Autowired
	private MtFirstPage firstPage;
	
	@Autowired
	private MtSecondPage secondPage;
	
	
	public MtSitePage doLogin(String loginName, String password, String tenantName ) {
		MtTenant tenant = MtTenant.fromName(tenantName);
		return doLogin(loginName, password,  tenant);
	}
	
	
	
	public MtSitePage doLogin(String loginName, String password, MtTenant tenant ) {
		WebElement mailElement = driver.findElement(By.cssSelector("#login_name"));
		WebElement passwordElement = driver.findElement(By.cssSelector("#password"));
		mailElement.clear();
		mailElement.sendKeys(loginName);
		passwordElement.clear();
		passwordElement.sendKeys(password);
		WebElement loginButton = driver.findElement(By.cssSelector("#login_button"));
		loginButton.click();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(isLoginFailed()) {
			return this;
		} else  if (null== tenant) {
			return homePage;
		} 
		else {
			switch(tenant) {
			case FIRST:
				return firstPage;
			case SECOND:	
				return secondPage;
			default: 
				return null;	
			}
		}	
	}

	public WebElement getLoginErrorElement() {
		return driver.findElement(By.cssSelector("#error"));
	}
	
	public boolean isLoginFailed() {
		return getLoginErrorElement().isDisplayed();
	}
	
}
