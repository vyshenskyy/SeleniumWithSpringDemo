package sample.application.multitenant.components;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import sample.framework.pages.PageComponent;

@Component
@Scope("prototype")
public class MtLoginPart extends PageComponent {
	
	public MtLoginPart() {
		super(null,  "#login");
	}

}
