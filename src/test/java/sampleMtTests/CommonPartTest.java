package sampleMtTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import sample.application.multitenant.pages.MtBaseTest;
import sample.framework.spring.baseTest.LoginBeforeMethod;

public class CommonPartTest extends MtBaseTest implements LoginBeforeMethod {
	
	@Test
	public void toggleCommonTextTest() {
		boolean displayed = mtPage.getCommonTextElement().isDisplayed();
		Assert.assertFalse(displayed);
		mtPage.toggleCommonText();
		String commonText = mtPage.getCommonText();
		Assert.assertEquals(commonText, "Common text");
		mtPage.toggleCommonText();
		displayed = mtPage.getCommonTextElement().isDisplayed();
		Assert.assertFalse(displayed);
		
	}

}
