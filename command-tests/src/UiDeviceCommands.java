package se.vidstige.android.uimultimator;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class UiDeviceCommands extends UiAutomatorTestCase {
	
	public void testPressHome()
	{
		getUiDevice().pressHome();
	}
	
	public void testPressMenu()
	{
		getUiDevice().pressMenu();
	}
}
