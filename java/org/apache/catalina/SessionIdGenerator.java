
package org.apache.catalina;

public interface SessionIdGenerator {

    /**
     * @return the node identifier associated with this node which will be
     * included in the generated session ID.
     */
    public String getJvmRoute();

    /**
     * Specify the node identifier associated with this node which will be
     * included in the generated session ID.
     *
     * @param jvmRoute  The node identifier
     */
    public void setJvmRoute(String jvmRoute);

    /**
     * @return the number of bytes for a session ID
     */
    public int getSessionIdLength();

    /**
     * Specify the number of bytes for a session ID
     *
     * @param sessionIdLength   Number of bytes
     */
    public void setSessionIdLength(int sessionIdLength);

    /**
     * Generate and return a new session identifier.
     *
     * @return the newly generated session id
     */
    public String generateSessionId();

    /**
     * Generate and return a new session identifier.
     *
     * @param route   node identifier to include in generated id
     * @return the newly generated session id
     */
    public String generateSessionId(String route);
}
