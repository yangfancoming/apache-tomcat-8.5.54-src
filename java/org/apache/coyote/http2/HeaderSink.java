
package org.apache.coyote.http2;

import org.apache.coyote.http2.HpackDecoder.HeaderEmitter;

/**
 * Purpose of this class is to silently swallow any headers. It is used once
 * the connection close process has started if headers for new streams are
 * received.
 */
public class HeaderSink implements HeaderEmitter {

    @Override
    public void emitHeader(String name, String value) {
        // NO-OP
    }

    @Override
    public void validateHeaders() throws StreamException {
        // NO-OP
    }

    @Override
    public void setHeaderException(StreamException streamException) {
        // NO-OP
        // The connection is already closing so no need to process additional
        // errors
    }
}
