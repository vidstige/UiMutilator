package se.vidstige.android.uimultimator;

class AdbException extends Exception {

	private static final long serialVersionUID = 3979090287953441057L;
	
	public AdbException(String message, Throwable cause) {
		super(message, cause);
	}

	public AdbException(String message) {
		super(message);
	}
}
