
package org.apache.coyote.http2;

/**
 * Thrown when an HTTP/2 stream error occurs.
 */
public class StreamException extends Http2Exception {

    private static final long serialVersionUID = 1L;

    private final int streamId;

    public StreamException(String msg, Http2Error error, int streamId) {
        super(msg, error);
        this.streamId = streamId;
    }


    public int getStreamId() {
        return streamId;
    }
}
