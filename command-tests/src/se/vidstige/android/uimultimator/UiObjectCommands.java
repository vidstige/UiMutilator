package se.vidstige.android.uimultimator;

import java.io.UnsupportedEncodingException;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;

public class UiObjectCommands extends UiCommandsTestCase {
	
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
