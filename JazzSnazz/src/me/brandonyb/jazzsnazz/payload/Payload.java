package me.brandonyb.jazzsnazz.payload;

/**
 * <p>Any classes that extend this interface indicate that it is a payload.</p>
 * @author brandon
 */
public interface Payload {
	/**
	 * <p>Called when the payload is started.</p>
	 */
	public abstract void initialize();
	/**
	 * <p>Called when the payload is terminated.</p>
	 */
	public abstract void cease();
	/**
	 * <p>Returns whether or not the payload is active.</p>
	 * @return If the payload is presently active.
	 */
	public abstract boolean isActive();
}
