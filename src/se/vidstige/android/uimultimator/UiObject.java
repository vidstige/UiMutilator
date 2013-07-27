package se.vidstige.android.uimultimator;

import java.io.IOException;
import java.net.URLEncoder;

public class UiObject {
	private UiSelector selector;
	private final AdbDevice adb = new AdbDevice();
	
	public UiObject(UiSelector selector) {
		this.selector = selector;
	}

	public void clickAndWaitForNewWindow() throws IOException, InterruptedException
	{
		adb.sendAdbCommand("shell", "uiautomator", "runtest", "command-tests.jar",
				"-c", "se.vidstige.android.uimultimator.UiObjectCommands#testClickAndWaitForNewWindow",
				"-e", "text_selector", URLEncoder.encode(selector.getText(), "utf-8"));
	}	
}
