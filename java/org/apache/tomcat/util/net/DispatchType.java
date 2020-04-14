
package org.apache.tomcat.util.net;

/**
 * This enumeration lists the different types of dispatches that request
 * processing can trigger. In this instance, dispatch means re-process this
 * request using the given socket status.
 */
public enum DispatchType {

    NON_BLOCKING_READ(SocketEvent.OPEN_READ),
    NON_BLOCKING_WRITE(SocketEvent.OPEN_WRITE);

    private final SocketEvent status;

    private DispatchType(SocketEvent status) {
        this.status = status;
    }

    public SocketEvent getSocketStatus() {
        return status;
    }
}
