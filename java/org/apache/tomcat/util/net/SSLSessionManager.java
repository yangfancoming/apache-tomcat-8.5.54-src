

package org.apache.tomcat.util.net;

/**
 * Defines an interface used to manage SSL sessions. The manager operates on a
 * single session.
 */
public interface SSLSessionManager {
    /**
     * Invalidate the SSL session
     */
    public void invalidateSession();
}
