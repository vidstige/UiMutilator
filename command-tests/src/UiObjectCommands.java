package se.vidstige.android.uimultimator;

import java.net.URLDecoder;
import java.io.UnsupportedEncodingException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class UiObjectCommands extends UiAutomatorTestCase {
	
	public void testClickAndWaitForNewWindow() throws UiObjectNotFoundException, UnsupportedEncodingException
	{
		String text = getParams().getString("text_selector");
		text = URLDecoder.decode(text, "utf-8");
		
		UiDevice uiDevice = getUiDevice();		
		UiObject uiObject = new UiObject(new UiSelector().text(text));
		uiObject.clickAndWaitForNewWindow();
	}
}
