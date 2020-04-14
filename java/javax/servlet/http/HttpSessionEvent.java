
package javax.servlet.http;

/**
 * This is the class representing event notifications for changes to sessions
 * within a web application.
 *
 * @since v 2.3
 */
public class HttpSessionEvent extends java.util.EventObject {
    private static final long serialVersionUID = 1L;

    /**
     * Construct a session event from the given source.
     *
     * @param source    The HTTP session where the change took place
     */
    public HttpSessionEvent(HttpSession source) {
        super(source);
    }

    /**
     * Get the session that changed.
     *
     * @return The session that changed
     */
    public HttpSession getSession() {
        return (HttpSession) super.getSource();
    }
}
