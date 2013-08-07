package se.vidstige.android.uimultimator;

import java.io.UnsupportedEncodingException;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;

public class UiScrollableCommands extends UiCommandsTestCase {
	
	public void testScrollIntoView() throws UnsupportedEncodingException, UiObjectNotFoundException
	{
		UiSelector s0 = recreateSelector("0");
		UiScrollable scrollable = new UiScrollable(s0);
		UiSelector s1 = recreateSelector("1");
		scrollable.scrollIntoView(s1);
	}
}
