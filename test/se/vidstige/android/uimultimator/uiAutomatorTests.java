package se.vidstige.android.uimultimator;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.testng.annotations.Test;

public class uiAutomatorTests extends UiMultimatorTestCase {  
	@Test(expectedExceptions=UiMultimatorException.class, expectedExceptionsMessageRegExp="UiObject not found: UiSelector\\[TEXT=notFound\\]")
	public void testUiSelectorThatWillThrow() throws Exception {
		UiDevice uiDevice = getUiDevice().any();
		uiDevice.pressHome();
		uiDevice.pressMenu();
		
		UiObject notFound = uiDevice.newUiObject(new UiSelector().text("notFound"));
		notFound.clickAndWaitForNewWindow();
	}
	
	@Test
	public void testGetText() throws Exception {
		UiDevice uiDevice = getUiDevice().withSerial("emulator-5554");
		uiDevice.pressHome();
		uiDevice.pressMenu();
		
		UiObject settingsOption = uiDevice.newUiObject(new UiSelector().text("System settings"));
		settingsOption.clickAndWaitForNewWindow();
		
		UiObject sound = uiDevice.newUiObject(new UiSelector().text("Sound"));
		Assert.assertEquals("Sound", sound.getText());
	}
	
	@Test
	public void testClearText() throws Exception
	{
		UiDevice uiDevice = getUiDevice().any();
		uiDevice.pressHome();
		
		UiObject messagingButton = uiDevice.newUiObject(new UiSelector().text("Messaging"));
		messagingButton.clickAndWaitForNewWindow();
		
		UiObject newMessageButton = uiDevice.newUiObject(new UiSelector().text("New message"));
		newMessageButton.clickAndWaitForNewWindow();
		
		UiObject toText = uiDevice.newUiObject(new UiSelector().className("android.widget.MultiAutoCompleteTextView"));
		toText.setText("lollipop");
	
		toText.clearTextField();		
	}
	
	@Test
	public void testScreencapture() throws UiMultimatorException, IOException
	{
		UiDevice uiDevice = getUiDevice().any();
		File file = File.createTempFile("sceenshot", ".png");
		uiDevice.takeScreenshot(file);
		System.out.println("Screen capture saved to: " + file.getAbsolutePath());
	}
}
