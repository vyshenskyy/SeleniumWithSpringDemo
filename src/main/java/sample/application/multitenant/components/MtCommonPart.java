package sample.application.multitenant.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import sample.framework.pages.PageComponent;

@Component
@Scope("prototype")
public class MtCommonPart extends PageComponent {
	
	public MtCommonPart() {
		super(null, "#common");
	}

	public WebElement getButtonElement() {
		return getPageComponentElement().findElement(By.cssSelector("#common_button"));
	}
	
	public void toggleCommonText() {
		getButtonElement().click();
	}	
	public WebElement getTextElement() {
		return getPageComponentElement().findElement(By.cssSelector("#common_text"));
	}
	
	public WebElement getLogoutElement() {
		return getPageComponentElement().findElement(By.cssSelector(".logout"));
	}
	
	public void doLogout() {
		 getLogoutElement().click();
	}
}
