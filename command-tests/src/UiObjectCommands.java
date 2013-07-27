package se.vidstige.android.uimultimator;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class UiObjectCommands extends UiAutomatorTestCase {
	
	public void testClickAndWaitForNewWindow() throws UiObjectNotFoundException
	{
		UiDevice uiDevice = getUiDevice();		
		UiObject uiObject = new UiObject(new UiSelector().text("fff"));
		uiObject.clickAndWaitForNewWindow();
	}
}
