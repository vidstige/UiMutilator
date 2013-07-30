package se.vidstige.android.uimultimator;

import android.os.RemoteException;

public class UiDeviceCommands extends UiCommandsTestCase {
	
	public void testClick()	{
		getUiDevice().click(getInt("x"), getInt("y"));				
	}
	
	public void testFreezeRotation() throws RemoteException {
		getUiDevice().freezeRotation();
	}
	
	public void testUnfreezeRotation() throws RemoteException
	{
		getUiDevice().unfreezeRotation();
	}

	public void testGetDisplayHeight() {
		respond(getUiDevice().getDisplayHeight());
	}
	
	public void testGetDisplayWidth() {
		respond(getUiDevice().getDisplayWidth());
	}
	
	public void testGetDisplayRotation()
	{
		respond(getUiDevice().getDisplayRotation());
	}
	
	public void testGetLastTraversedText()
	{
		respond(getUiDevice().getLastTraversedText());
	}
	
	public void testIsScreenOn() throws RemoteException
	{
		respond(getUiDevice().isScreenOn());
	}
	
	public void testPressBack()	{
		getUiDevice().pressBack();
	}
	
	public void testPressSearch()	{
		getUiDevice().pressSearch();
	}
		
	public void testPressHome()	{
		getUiDevice().pressHome();
	}
	
	public void testPressMenu()	{
		getUiDevice().pressMenu();
	}
	
	public void testSleep() throws RemoteException {
		getUiDevice().sleep();
	}

	public void testWakeUp() throws RemoteException {
		getUiDevice().wakeUp();	
	}
	
	public void testSwipe()	{
		getUiDevice().swipe(getInt("startX"), getInt("startY"), getInt("endX"), getInt("endY"), getInt("steps"));
	}
	
	public void testWaitForIdle()
	{
		getUiDevice().waitForIdle();
	}
}
