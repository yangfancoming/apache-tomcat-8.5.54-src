
package javax.websocket;

public interface MessageHandler {

    interface Partial<T> extends MessageHandler {

        /**
         * Called when part of a message is available to be processed.
         *
         * @param messagePart   The message part
         * @param last          <code>true</code> if this is the last part of
         *                      this message, else <code>false</code>
         */
        void onMessage(T messagePart, boolean last);
    }

    interface Whole<T> extends MessageHandler {

        /**
         * Called when a whole message is available to be processed.
         *
         * @param message   The message
         */
        void onMessage(T message);
    }
}
