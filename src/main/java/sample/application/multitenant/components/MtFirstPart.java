package sample.application.multitenant.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import sample.framework.pages.PageComponent;

@Component
@Scope("prototype")
public class MtFirstPart extends PageComponent {
	
	public MtFirstPart() {
		super(null,  "#first");
	}

	public WebElement getButtonElement() {
		return getPageComponentElement().findElement(By.cssSelector(".button"));
	}
	
	public void toggleText() {
		getButtonElement().click();
	}
	
	public WebElement getTextElement() {
		return getPageComponentElement().findElement(By.cssSelector(".tenant_text"));
	}
	
}
