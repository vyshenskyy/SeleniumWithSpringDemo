package sample.application.theinternetPageComponents;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import sample.framework.pages.PageComponent;

@Component
@Scope("prototype")
public class TiContent extends PageComponent {
		
	public TiContent() {
		super(null,"#content");
	}
	
	
}
