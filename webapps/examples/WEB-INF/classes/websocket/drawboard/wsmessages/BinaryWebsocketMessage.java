
package websocket.drawboard.wsmessages;

import java.nio.ByteBuffer;

/**
 * Represents a binary websocket message.
 */
public final class BinaryWebsocketMessage extends AbstractWebsocketMessage {
    private final ByteBuffer bytes;

    public BinaryWebsocketMessage(ByteBuffer bytes) {
        this.bytes = bytes;
    }

    public ByteBuffer getBytes() {
        return bytes;
    }
}
