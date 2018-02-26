package sample.application.theinternetPageComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import sample.framework.pages.PageComponent;

@Component
@Scope("prototype")
public class TiFooter extends PageComponent {
		
	public TiFooter() {
		super(null,"#page-footer");
	}

	public void goToElementalSeleniumSite() {
		WebElement linkElement = getPageComponentElement().findElement(By.tagName("a"));
		linkElement.click();
	}
}
