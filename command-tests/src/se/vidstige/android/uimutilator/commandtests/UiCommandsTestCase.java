package se.vidstige.android.uimutilator.commandtests;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;

import android.os.Bundle;

import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class UiCommandsTestCase extends UiAutomatorTestCase {

	protected int getInt(String name)	{
		return Integer.parseInt(getParams().getString(name));	
	}

	protected UiSelector recreateSelector(String selectorId) throws UnsupportedEncodingException	{

		Bundle bundle = getParams();

		UiSelector selector = null;
		try {
			selector = UiSelectorFactory.getUiSelector(bundle, selectorId);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return selector;
	}
	
	protected void respond(String response)	{
		System.out.println("return:" + response);		
	}
	protected void respond(int response)	{
		respond(Integer.toString(response));
	}
	protected void respond(boolean response)	{
		respond(Boolean.toString(response));
	}
}
