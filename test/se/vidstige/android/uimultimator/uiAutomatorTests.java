package se.vidstige.android.uimultimator;

import junit.framework.Assert;

import org.testng.annotations.Test;

public class uiAutomatorTests extends UiMultimatorTestCase {  
		
	@Test(expectedExceptions=UiMultimatorException.class)
	public void testUiSelectorThatWillThrow() throws Exception {
		UiDevice uiDevice = getUiDevice().any();
		uiDevice.pressHome();
		uiDevice.pressMenu();
		
		UiObject notFound = uiDevice.newUiObject(new UiSelector().text("notFound"));
		notFound.clickAndWaitForNewWindow();
	}
	
	@Test
	public void testGetText() throws Exception {
		UiDevice uiDevice = getUiDevice().any();
		uiDevice.pressHome();
		uiDevice.pressMenu();
		
		UiObject settingsOption = uiDevice.newUiObject(new UiSelector().text("System settings"));
		settingsOption.clickAndWaitForNewWindow();
		
		UiObject sound = uiDevice.newUiObject(new UiSelector().text("Sound"));
		Assert.assertEquals("Sound", sound.getText());
	}
}
