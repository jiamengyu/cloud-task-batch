package ca.itm.batch.recurring.exception;

public class NotSufficientFundsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3107124996591545514L;
	
	public NotSufficientFundsException(String message) {
		super(message);
		
	}
	public NotSufficientFundsException(String message, Throwable cause) {
		super(message,cause);
	}
}
