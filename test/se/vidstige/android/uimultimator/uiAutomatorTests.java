package se.vidstige.android.uimultimator;

import org.testng.annotations.Test;

public class uiAutomatorTests extends UiMultimatorTestCase {  
		
	@Test
	public void f() throws Exception {
		UiDevice uiDevice = getUiDevice(0);
		uiDevice.pressHome();
		uiDevice.pressMenu();
		
		new UiObject(new UiSelector().text("System settings")).clickAndWaitForNewWindow();
	}
}
