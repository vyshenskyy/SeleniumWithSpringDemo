package sampleMtTests;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import sample.application.multitenant.pages.MtBaseTest;
import sample.application.multitenant.pages.MtSecondPage;
import sample.framework.spring.baseTest.LoginBeforeMethod;

public class SecondTenantTest extends MtBaseTest implements LoginBeforeMethod {
	
	@Autowired
	private MtSecondPage secondPage;
	
	@Test
	public void toggleTextTest() {
		boolean displayed = secondPage.getTenantTextElement().isDisplayed();
		Assert.assertFalse(displayed);
		secondPage.toggleTenantText();
		displayed = secondPage.getTenantTextElement().isDisplayed();
		Assert.assertTrue(displayed);
		String tenantText = secondPage.getTenantText();
		Assert.assertEquals(tenantText, "Second text");
		secondPage.toggleTenantText();
		displayed = secondPage.getTenantTextElement().isDisplayed();
		Assert.assertFalse(displayed);
	}
	
	@Test
	public void toggleCommonTextTest() {
		boolean displayed = secondPage.getCommonTextElement().isDisplayed();
		Assert.assertFalse(displayed);
		secondPage.toggleCommonText();
		String commonText = secondPage.getCommonText();
		Assert.assertEquals(commonText, "Common text");
		secondPage.toggleCommonText();
		displayed = secondPage.getCommonTextElement().isDisplayed();
		Assert.assertFalse(displayed);
	}

}
