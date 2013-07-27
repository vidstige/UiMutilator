package se.vidstige.android.uimultimator;

import junit.framework.Assert;

import org.testng.annotations.Test;

public class uiAutomatorTests extends UiMultimatorTestCase {  
		
	@Test
	public void f() throws Exception {
		UiDevice uiDevice = getUiDevice(0);
		uiDevice.pressHome();
		uiDevice.pressMenu();
		
		UiObject settingsOption = new UiObject(new UiSelector().text("System settings"));
		settingsOption.clickAndWaitForNewWindow();
		
		UiObject sound = new UiObject(new UiSelector().text("Sound"));
		Assert.assertEquals("Sound", sound.getText());
	}
}
