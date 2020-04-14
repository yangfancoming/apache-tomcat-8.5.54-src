
package org.apache.catalina.session;

/**
 * An exception that indicates the maximum number of active sessions has been
 * reached and the server is refusing to create any new sessions.
 */
public class TooManyActiveSessionsException extends IllegalStateException {

    private static final long serialVersionUID = 1L;

    /**
     * The maximum number of active sessions the server will tolerate.
     */
    private final int maxActiveSessions;

    /**
     * Creates a new TooManyActiveSessionsException.
     *
     * @param message A description for the exception.
     * @param maxActive The maximum number of active sessions allowed by the
     *                  session manager.
     */
    public TooManyActiveSessionsException(String message, int maxActive) {
        super(message);
        maxActiveSessions = maxActive;
    }

    /**
     * Gets the maximum number of sessions allowed by the session manager.
     *
     * @return The maximum number of sessions allowed by the session manager.
     */
    public int getMaxActiveSessions() {
        return maxActiveSessions;
    }
}
