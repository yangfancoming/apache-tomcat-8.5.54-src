
package javax.servlet.http;

import java.util.EventListener;

/**
 * Implementations of this interface are notified when an {@link HttpSession}'s
 * ID changes. To receive notification events, the implementation class must be
 * configured in the deployment descriptor for the web application, annotated
 * with {@link javax.servlet.annotation.WebListener} or registered by calling an
 * addListener method on the {@link javax.servlet.ServletContext}.
 *
 * @see HttpSessionEvent
 * @see HttpServletRequest#changeSessionId()
 * @since Servlet 3.1
 */
public interface HttpSessionIdListener extends EventListener {

    /**
     * Notification that a session ID has been changed.
     *
     * @param se the notification event
     * @param oldSessionId the old session ID
     */
    public void sessionIdChanged(HttpSessionEvent se, String oldSessionId);
}
