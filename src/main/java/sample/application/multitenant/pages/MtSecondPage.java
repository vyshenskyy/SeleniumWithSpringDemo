package sample.application.multitenant.pages;

import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class MtSecondPage extends MtAbstractPage {

	@Override
	public void toggleTenantText() {
		getSecondPart().toggleText();
	}
	
	@Override
	public  WebElement getTenantTextElement() {
		return getSecondPart().getTextElement();
	}
	
}
