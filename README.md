# UiMultimator #
Introducing the *UiMultimator* - Android UI Testing on several devices that required interaction. Don't like jUnit? No problems, with *UiMultimator* you can use any test runner such as TestNG to drive the UI tests.

The *UiMultimator* exposes a similar interface as the Android UiAutomator. However, this *UiMultimator* is capable of running on multiple devices.

## Usage ##
The methods exposed by *UiMultimator* differs in two major ways: The `getUiDevice()` method and the `newUiObject()` method.

Instead of calling `getUiDevice()` like in the Android UiAutomator, you can `getUiDevice().any()` to connect to any device connected to your computer. You can also use other methods such as `getUiDevice().number(1)` or `getUiDevice().withSerial("emulator-554")` to select which device to run the test on.

This has the advantage of beeing to interleave ui commands and assertions between two devices.

Instead of calling `new UiObject()` like in the Android UiAutomator, you have to call `uiDevice.newUiObject()`. This is because *UiMultimator* needs to know on which device you intend to act, since a test may be connected to several devices.

## Example ##
This is an example of a *UiMultimator *test

    @Test
	public void testGetText() throws Exception {
		UiDevice uiDevice = getUiDevice().any();
		uiDevice.pressHome();
		uiDevice.pressMenu();
		
		UiObject settingsOption = uiDevice.newUiObject(new UiSelector().text("System settings"));
		settingsOption.clickAndWaitForNewWindow();
		
		UiObject sound = uiDevice.newUiObject(new UiSelector().text("Sound"));
		Assert.assertEquals("Sound", sound.getText());
	}

## Author ##
Created by Samuel Carlsson <samuel.carlsson@gmail.com>