package me.brandonyb.jazzsnazz.util;

/**
 * <p>Specifies the level of severity for a log entry.</p>
 * @author brandon
 */
public enum EntryType {
    /**
     * <p>Indicates that the message is severe.</p>
     */
    CRITICAL,
    /**
     * <p>Indicates that a non-fatal error has occurred.</p>
     */
    ERROR,
    /**
     * <p>Indicates a warning which is purely advisory.</p>
     */
    WARNING,
    /**
     * <p>Indicates an informational message where no further
     * action is required.</p>
     */
    INFORMATION,
    /**
     * <p>Indicates that something has succeeded.</p>
     */
    SUCCESS;
}
