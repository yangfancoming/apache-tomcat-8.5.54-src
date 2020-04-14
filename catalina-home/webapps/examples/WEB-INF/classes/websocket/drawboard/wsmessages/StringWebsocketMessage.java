
package websocket.drawboard.wsmessages;

/**
 * Represents a string websocket message.
 *
 */
public final class StringWebsocketMessage extends AbstractWebsocketMessage {
    private final String string;

    public StringWebsocketMessage(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

}
