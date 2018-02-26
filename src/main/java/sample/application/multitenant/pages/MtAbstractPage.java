package sample.application.multitenant.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

import sample.application.multitenant.components.MtCommonPart;
import sample.application.multitenant.components.MtFirstPart;
import sample.application.multitenant.components.MtLoginPart;
import sample.application.multitenant.components.MtSecondPart;

public abstract class MtAbstractPage   implements MtSitePage {
	
	@Autowired
	private MtLoginPart loginPart;
	@Autowired
	private MtCommonPart commonPart;
	@Autowired
	private  MtFirstPart firstPart;
	@Autowired
	private  MtSecondPart secondPart;
	
	@Autowired
	protected WebDriver driver;

	
	public boolean isLoggedIn() {
		boolean loginDisplayed = loginPart.getPageComponentElement().isDisplayed();
		return !loginDisplayed;
	}

	public MtLoginPart getLoginPart() {
		return loginPart;
	}

	public MtCommonPart getCommonPart() {
		return commonPart;
	}

	public MtFirstPart getFirstPart() {
		return firstPart;
	}

	public MtSecondPart getSecondPart() {
		return secondPart;
	}
	
	public boolean isCommonPartDisplayed() {
		return commonPart.getPageComponentElement().isDisplayed();
	}
	
	public void doLogout() {
		commonPart.doLogout();
	}
	
	@Override
	public WebElement getTenantTextElement() {
		return null;
	}
	@Override
	public void toggleTenantText() {
		// do nothing
	}
	
	public String getTenantText() {
		return getTenantTextElement().getText();
	}
	
	public WebElement getCommonTextElement() {
		return getCommonPart().getTextElement();
	}
	
	public String getCommonText() {
		return getCommonTextElement().getText();
	}
	
	public void toggleCommonText() {
		getCommonPart().toggleCommonText();
	}
	
}
