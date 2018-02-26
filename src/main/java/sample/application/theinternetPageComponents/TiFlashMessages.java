package sample.application.theinternetPageComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import sample.framework.pages.PageComponent;

@Component
@Scope("prototype")
public class TiFlashMessages extends PageComponent {
	public TiFlashMessages() {
		super(null, "#flash-messages");
	}
	
	public WebElement getFlashMessagesElement() {
		return getPageComponentElement();
	}
	
	public WebElement getFlashElement() {
		return getPageComponentElement().findElement(By.id("flash"));
	}
	
	public WebElement getCloseFlashElement() {
		return getFlashElement().findElement(By.tagName("a"));
	}
	
	public String getFlashClasses() {
		return getFlashElement().getAttribute("class");
	}
	
	public boolean isSuccess() {
		String flashClasses = getFlashClasses();
		return flashClasses.contains("success");
	}
	
	public boolean isError() {
		String flashClasses = getFlashClasses();
		return flashClasses.contains("error");
	}
	
	public String getMessageText() {
		 String fullText = getFlashElement().getText();
		 fullText = fullText.trim();
		 String closeFlashElementText = getCloseFlashElement().getText();
		 String removedEnd = fullText.replace(closeFlashElementText, "").trim();
		 return removedEnd;
	}
	
	public void closeFlashMessage() {
		getCloseFlashElement().click();
		WebDriverWait wait = new WebDriverWait(driver, 5 );
		wait.until(ExpectedConditions.invisibilityOf(getFlashElement()));
	}
}
