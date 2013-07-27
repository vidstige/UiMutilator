package se.vidstige.android.uimultimator;

public class UiSelector {

	private String text;

	public UiSelector text(String text) {
		this.text = text;
		return this;
	}

	String getText() {
		return text;
	}
}
