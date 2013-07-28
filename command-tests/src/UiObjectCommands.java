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
	
	private UiSelector recreateSelector() throws UnsupportedEncodingException
	{
		String selector_type = getParams().getString("selector_type");
		String p = URLDecoder.decode(getParams().getString("selector_parameter"), "utf-8");
		if ("text".equals(selector_type))
		{
			return new UiSelector().text(p);
		}
		else if ("classNameMatches".equals(selector_type))
		{
			return new UiSelector().classNameMatches(p);
		}
		else if ("className".equals(selector_type))
		{
			return new UiSelector().className(p);
		}
		throw new IllegalStateException("Could not deserialize UiSelector of type " + selector_type);
	}
	
	private void respond(String response)
	{
		System.out.println("return:" + response);		
	}
	
	public void testSetText() throws UiObjectNotFoundException, UnsupportedEncodingException
	{
		UiObject uiObject = new UiObject(recreateSelector());
		uiObject.setText(getParams().getString("set_text"));	
	}
	
	public void testClearTextField() throws UiObjectNotFoundException, UnsupportedEncodingException
	{
		UiObject uiObject = new UiObject(recreateSelector());
		uiObject.clearTextField();
	}
	
	public void testClickAndWaitForNewWindow() throws UiObjectNotFoundException, UnsupportedEncodingException
	{		
		UiObject uiObject = new UiObject(recreateSelector());
		uiObject.clickAndWaitForNewWindow();
	}
	
	public void testGetText() throws UiObjectNotFoundException, UnsupportedEncodingException
	{		
		UiObject uiObject = new UiObject(recreateSelector());
		String text = uiObject.getText();
		respond(text);
	}	
}
