package se.vidstige.android.uimutilator;


public class UiMutilator extends Exception {
	
	private static final long serialVersionUID = 6954101221203802578L;
	
	public UiMutilator(String message, Throwable inner) {
		super(message, inner);
	}

	public UiMutilator(String message) {
		super(message);
	}
}
