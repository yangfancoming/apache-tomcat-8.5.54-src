
package org.apache.tomcat.websocket;

import java.io.IOException;

import javax.websocket.CloseReason;

/**
 * Allows the WebSocket implementation to throw an {@link IOException} that
 * includes a {@link CloseReason} specific to the error that can be passed back
 * to the client.
 */
public class WsIOException extends IOException {

    private static final long serialVersionUID = 1L;

    private final CloseReason closeReason;

    public WsIOException(CloseReason closeReason) {
        this.closeReason = closeReason;
    }

    public CloseReason getCloseReason() {
        return closeReason;
    }
}
