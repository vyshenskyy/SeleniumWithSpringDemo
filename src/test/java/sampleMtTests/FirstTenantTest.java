package sampleMtTests;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import sample.application.multitenant.pages.MtBaseTest;
import sample.application.multitenant.pages.MtFirstPage;
import sample.framework.spring.baseTest.LoginBeforeMethod;

public class FirstTenantTest extends MtBaseTest implements LoginBeforeMethod {
	
	@Autowired
	private MtFirstPage firstPage;
	
	@Test
	public void toggleTextTest() {
		boolean displayed = firstPage.getTenantTextElement().isDisplayed();
		Assert.assertFalse(displayed);
		firstPage.toggleTenantText();
		displayed = firstPage.getTenantTextElement().isDisplayed();
		Assert.assertTrue(displayed);
		String tenantText = firstPage.getTenantText();
		Assert.assertEquals(tenantText, "First text");
		firstPage.toggleTenantText();
		displayed = firstPage.getTenantTextElement().isDisplayed();
		Assert.assertFalse(displayed);
	}
	
	@Test
	public void toggleCommonTextTest() {
		boolean displayed = firstPage.getCommonTextElement().isDisplayed();
		Assert.assertFalse(displayed);
		firstPage.toggleCommonText();
		String commonText = firstPage.getCommonText();
		Assert.assertEquals(commonText, "Common text");
		firstPage.toggleCommonText();
		displayed = firstPage.getCommonTextElement().isDisplayed();
		Assert.assertFalse(displayed);
	}

}
