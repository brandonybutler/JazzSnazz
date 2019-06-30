package me.brandonyb.jazzsnazz.payload;

import me.brandonyb.jazzsnazz.exceptions.PayloadActivityException;
import me.brandonyb.jazzsnazz.payload.norton.NortonNotification;

/**
 * <p>Provides a facility to manage payloads.</p>
 * @author brandon
 */
public final class PayloadManager {
	/**
	 * <p>Initialize a payload from the list.</p>
	 * @param payload The specified payload
	 * @throws PayloadActivityException Thrown when a payload is already active
	 */
	public void initialize(final PayloadList payload) throws PayloadActivityException {
		switch (payload) {
			case NORTON_NOTIFICATION:
				NortonNotification norton = new NortonNotification();
				
				if (norton.isActive()) {
					throw new PayloadActivityException("The NORTON_NOTIFICATION payload is already active.");
				} else {
					norton.initialize();
				}
				break;
			default:
				// If for some reason an invalid parameter was specified, it will throw an
	            // IllegalArgumentException.
				throw new IllegalArgumentException();
		}
	}
	
	/**
	 * <p>Terminate a payload from the list.</p>
	 * @param payload The specified payload
	 * @throws PayloadActivityException Thrown when a payload is already terminated
	 */
	public void cease(final PayloadList payload) throws PayloadActivityException {
		switch (payload) {
			case NORTON_NOTIFICATION:
				NortonNotification norton = new NortonNotification();
				
				if (!norton.isActive()) {
					throw new PayloadActivityException("The NORTON_NOTIFICATION payload is already terminated.");
				} else {
					norton.cease();
				}
				break;
			default:
				// If for some reason an invalid parameter was specified, it will throw an
	            // IllegalArgumentException.
				throw new IllegalArgumentException();
		}
	}
	
	/**
	 * <p>Returns whether or not the payload from the list is presently active.</p>
	 * @param payload The specified payload
	 * @return The activity status of the payload
	 */
	public boolean isActive(final PayloadList payload) {
		switch (payload) {
			case NORTON_NOTIFICATION:
				NortonNotification norton = new NortonNotification();
				return norton.isActive();
			default:
				// If for some reason an invalid parameter was specified, it will throw an
	            // IllegalArgumentException.
				throw new IllegalArgumentException();
		}
	}
}
