package se.vidstige.android.uimutilator;


public class UiMutilatorException extends Exception {
	
	private static final long serialVersionUID = 6954101221203802578L;
	
	public UiMutilatorException(String message, Throwable inner) {
		super(message, inner);
	}

	public UiMutilatorException(String message) {
		super(message);
	}
}
