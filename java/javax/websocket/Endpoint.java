
package javax.websocket;

public abstract class Endpoint {

    /**
     * Event that is triggered when a new session starts.
     * @param session   The new session.
     * @param config    The configuration with which the Endpoint was configured.
     */
    public abstract void onOpen(Session session, EndpointConfig config);

    /**
     * Event that is triggered when a session has closed.
     * @param session       The session
     * @param closeReason   Why the session was closed
     */
    public void onClose(Session session, CloseReason closeReason) {
        // NO-OP by default
    }

    /**
     * Event that is triggered when a protocol error occurs.
     * @param session   The session.
     * @param throwable The exception.
     */
    public void onError(Session session, Throwable throwable) {
        // NO-OP by default
    }
}
