package se.vidstige.android.uimultimator;

import java.util.HashMap;
import java.util.Map;

public class UiScrollable {
	private UiAutomatorRunner runner;
	private UiSelector selector;

	UiScrollable(UiAutomatorRunner runner, UiSelector selector) {
		this.runner = runner;
		this.selector = selector;
	}
	
	public void scrollIntoView(UiSelector selector) throws UiMultimatorException {
		Map<String, String> parameters = new HashMap<String, String>(); 
		this.selector.serializeTo(parameters, "s0_");
		selector.serializeTo(parameters, "s1_");
		runner.run("se.vidstige.android.uimultimator.UiScrollableCommands", "testScrollIntoView", parameters);
	}

	public UiObject getChild(UiSelector selector) {
		return null;
	}
}
