
package org.apache.tomcat.dbcp.dbcp2;

/**
 * Exception thrown when a connection's maximum lifetime has been exceeded.
 *
 * @since 2.1
 */
class LifetimeExceededException extends Exception {

    private static final long serialVersionUID = -3783783104516492659L;

    /**
     * Create a LifetimeExceededException.
     */
    public LifetimeExceededException() {
        super();
    }

    /**
     * Create a LifetimeExceededException with the given message.
     *
     * @param message
     *            The message with which to create the exception
     */
    public LifetimeExceededException(final String message) {
        super(message);
    }
}
