package se.vidstige.android.uimultimator;

import java.net.URLDecoder;
import java.io.UnsupportedEncodingException;

import android.os.Bundle;

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
	
	public void testGetText() throws UiObjectNotFoundException, UnsupportedEncodingException
	{
		String text = getParams().getString("text_selector");
		text = URLDecoder.decode(text, "utf-8");
		
		UiDevice uiDevice = getUiDevice();		
		UiObject uiObject = new UiObject(new UiSelector().text(text));
		String text2 = uiObject.getText();
		System.out.println("return:" + text2);
		
//		Bundle bundle = new Bundle();
//		bundle.putString("getText", text2);
//		getAutomationSupport().sendStatus(1234, bundle);
	}	
}
