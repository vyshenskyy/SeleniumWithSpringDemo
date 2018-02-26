package sample.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class PageComponent {
	
	@Autowired
	protected WebDriver driver;
	
	private final String elementCssLocator;
	private final PageComponent parentComponent;
	
	public PageComponent(PageComponent parentComponent, String elementCssLocator) {
		this.elementCssLocator = elementCssLocator;
		this.parentComponent  = parentComponent;
	}
	
	public String getElementCssLocator() {
		return elementCssLocator;
	}
	
	public WebElement getPageComponentElement() {
		if(null == elementCssLocator) {
			return null;
		} else {
			return (null==parentComponent )? driver.findElement(By.cssSelector(elementCssLocator)) : parentComponent.getPageComponentElement().findElement(By.cssSelector(elementCssLocator));
		}
	}

	public PageComponent getParentComponent() {
		return parentComponent;
	} 
}
