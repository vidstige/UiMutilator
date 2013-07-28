package se.vidstige.android.uimultimator;

import java.io.UnsupportedEncodingException;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;

public class UiObjectCommands extends UiCommandsTestCase {

	private UiObject uiObject() throws UnsupportedEncodingException {
		return new UiObject(recreateSelector());
	}	
	
	public void testSetText() throws UiObjectNotFoundException, UnsupportedEncodingException
	{
		uiObject().setText(getParams().getString("set_text"));	
	}

	public void testClearTextField() throws UiObjectNotFoundException, UnsupportedEncodingException
	{
		uiObject().clearTextField();
	}
	
	public void testClickAndWaitForNewWindow() throws UiObjectNotFoundException, UnsupportedEncodingException
	{		
		uiObject().clickAndWaitForNewWindow();
	}
	
	public void testGetText() throws UiObjectNotFoundException, UnsupportedEncodingException
	{		
		String text = uiObject().getText();
		respond(text);
	}
}
