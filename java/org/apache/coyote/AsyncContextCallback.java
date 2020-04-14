
package org.apache.coyote;

/**
 * Provides a mechanism for the Coyote connectors to communicate with the
 * {@link javax.servlet.AsyncContext}. It is implemented in this manner so that
 * the org.apache.coyote package does not have a dependency on the
 * org.apache.catalina package.
 */
public interface AsyncContextCallback {
    public void fireOnComplete();

    /**
     * Reports if the web application associated with this async request is
     * available.
     *
     * @return {@code true} if the associated web application is available,
     *         otherwise {@code false}
     */
    public boolean isAvailable();
}
