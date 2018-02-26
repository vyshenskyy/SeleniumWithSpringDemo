package sampleTiTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import sample.application.theinternetPage.TiBaseTest;
import sample.framework.spring.baseTest.LoginBeforeClass;

public class SaveScreenshotTest extends TiBaseTest implements LoginBeforeClass {

	@Test
	public void saveScreenshotTest() {
		Assert.fail("We want to save a screen shot");
	}
}
