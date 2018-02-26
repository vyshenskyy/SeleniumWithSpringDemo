package sampleMtTests;

import org.testng.annotations.Test;

import sample.application.multitenant.pages.MtBaseTest;
import sample.framework.spring.baseTest.LoginBeforeMethod;


public class DoNothingWithLoginTest extends MtBaseTest implements LoginBeforeMethod {
	@Test
	public void doNothingTest() {
		
	}
	
	@Test
	public void doNothingSecondTest() {
		
	}

}
