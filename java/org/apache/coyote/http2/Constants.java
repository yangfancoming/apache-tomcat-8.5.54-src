
package org.apache.coyote.http2;

public class Constants {

    // Prioritisation
    public static final int DEFAULT_WEIGHT = 16;

    // Parsing
    static final int DEFAULT_HEADER_READ_BUFFER_SIZE = 1024;

    // Header frame size
    // TODO: Is 1k the optimal value?
    static final int DEFAULT_HEADERS_FRAME_SIZE = 1024;
    // TODO: Is 64 too big? Just the status header with compression
    static final int DEFAULT_HEADERS_ACK_FRAME_SIZE = 64;

    // Limits
    static final int DEFAULT_MAX_COOKIE_COUNT = 200;
    static final int DEFAULT_MAX_HEADER_COUNT = 100;
    static final int DEFAULT_MAX_HEADER_SIZE = 8 * 1024;
    static final int DEFAULT_MAX_TRAILER_COUNT = 100;
    static final int DEFAULT_MAX_TRAILER_SIZE = 8 * 1024;
}
