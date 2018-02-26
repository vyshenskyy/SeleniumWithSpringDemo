package sample.application.multitenant.pages;

import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public interface MtSitePage {

	void toggleTenantText();
	WebElement getTenantTextElement();
	boolean isLoggedIn();
	void doLogout();

}
