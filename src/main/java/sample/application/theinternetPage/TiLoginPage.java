package sample.application.theinternetPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TiLoginPage extends TiAbstractPage {

	@Value(("${loginName}"))
	private String userName;
	
	@Value(("${password:aa}"))
	private String password;
	
	
	@Autowired
	private TiSecureAreaPage secureAreaPage;
	
	
	
	public TiAbstractPage doLogin(String userName, String password) {
		WebElement usernameElement = getContentElement().findElement(By.id("username"));
		WebElement passwordElement = getContentElement().findElement(By.id("password"));
		usernameElement.clear();
		usernameElement.sendKeys(userName);
		passwordElement.clear();
		passwordElement.sendKeys(password);
		WebElement loginButtonElement = getContentElement().findElement(By.tagName("button"));
		loginButtonElement.click();
		if(isLoginError()) {
			return this;
		}
		if(isLoginSuccess()) {
			return secureAreaPage;
		}
		return null;
	}
	
	
	public TiAbstractPage doLogin() {
		return doLogin(userName, password);
	}
	
	
	public boolean isLoginError() {
		return getTiFlashMessages().isError();
	}
	
	public boolean isLoginSuccess() {
		return getTiFlashMessages().isSuccess();
	}
	
	
}
