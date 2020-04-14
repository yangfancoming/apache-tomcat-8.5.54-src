
package org.apache.tomcat.websocket;

public enum TransformationResult {
    /**
     * The end of the available data was reached before the WebSocket frame was
     * completely read.
     */
    UNDERFLOW,

    /**
     * The provided destination buffer was filled before all of the available
     * data from the WebSocket frame could be processed.
     */
    OVERFLOW,

    /**
     * The end of the WebSocket frame was reached and all the data from that
     * frame processed into the provided destination buffer.
     */
    END_OF_FRAME
}
