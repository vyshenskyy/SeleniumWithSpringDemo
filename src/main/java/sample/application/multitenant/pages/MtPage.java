package sample.application.multitenant.pages;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class MtPage extends MtAbstractPage {

}
