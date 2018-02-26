package sample.application.theinternetPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TiSecureAreaPage extends TiAbstractPage {

	public void doLogout() {
		WebElement logoutButtonElement = getContentElement().findElement(By.cssSelector("a.button"));
		logoutButtonElement.click();
	}
	
}
