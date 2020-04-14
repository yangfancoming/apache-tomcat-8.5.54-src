
package javax.websocket;

import java.nio.ByteBuffer;

/**
 * Represents a WebSocket Pong message and used by message handlers to enable
 * applications to process the response to any Pings they send.
 */
public interface PongMessage {
    /**
     * Get the payload of the Pong message.
     *
     * @return  The payload of the Pong message.
     */
    ByteBuffer getApplicationData();
}
