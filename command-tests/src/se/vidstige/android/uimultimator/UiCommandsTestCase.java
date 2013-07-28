package se.vidstige.android.uimultimator;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class UiCommandsTestCase extends UiAutomatorTestCase {
	
	protected int getInt(String name)	{
		return Integer.parseInt(getParams().getString(name));	
	}
	
	protected UiSelector recreateSelector() throws UnsupportedEncodingException	{
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
