package sample.application.theinternetPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

import sample.application.theinternetPageComponents.TiContent;
import sample.application.theinternetPageComponents.TiFlashMessages;
import sample.application.theinternetPageComponents.TiFooter;

public abstract class TiAbstractPage {
	
	@Autowired
	protected WebDriver driver;
	
	@Autowired
	private TiFooter footer;
	
	@Autowired
	private TiContent content;
	
	@Autowired
	private TiFlashMessages flashMessages;
	
	public void goToElementalSeleniumSite() {
		footer.goToElementalSeleniumSite();
	}
	
	public WebElement getContentElement() {
		return content.getPageComponentElement();
	}
	
	public TiContent getTiContent() {
		return content;
	}
	
	public String getFlashClasses() {
		return flashMessages.getFlashClasses();
	}
	
	public TiFlashMessages getTiFlashMessages() {
		return flashMessages;
	}

}
