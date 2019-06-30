package me.brandonyb.jazzsnazz.exceptions;

/**
 * <p>This exception is thrown whenever the application attempts to initialize
 * a payload that is already active, and vice versa.</p>
 * @author brandon
 */
@SuppressWarnings("serial")
public class PayloadActivityException extends Exception {
	/**
	 * <p>Throws an exception without any detail.</p>
	 */
	public PayloadActivityException() {
		super();
	}
	
	/**
	 * <p>Throws an exception with details specified.</p>
	 */
	public PayloadActivityException(final String message) {
		super(message);
	}
}
