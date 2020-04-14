
package javax.servlet.http;

import java.util.EventListener;

/**
 * Objects that are bound to a session may listen to container events notifying
 * them that sessions will be passivated and that session will be activated. A
 * container that migrates session between VMs or persists sessions is required
 * to notify all attributes bound to sessions implementing
 * HttpSessionActivationListener.
 *
 * @since 2.3
 */
public interface HttpSessionActivationListener extends EventListener {

    /**
     * Notification that the session is about to be passivated.
     *
     * @param se Information about the session this is about to be passivated
     */
    public void sessionWillPassivate(HttpSessionEvent se);

    /**
     * Notification that the session has just been activated.
     *
     * @param se Information about the session this has just been activated
     */
    public void sessionDidActivate(HttpSessionEvent se);
}

