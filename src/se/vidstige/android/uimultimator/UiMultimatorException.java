package se.vidstige.android.uimultimator;


public class UiMultimatorException extends Exception {
	
	private static final long serialVersionUID = 6954101221203802578L;
	
	public UiMultimatorException(String message, Throwable inner) {
		super(message, inner);
	}

	public UiMultimatorException(String message) {
		super(message);
	}
}
