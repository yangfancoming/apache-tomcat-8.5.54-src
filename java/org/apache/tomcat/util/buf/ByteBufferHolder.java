
package org.apache.tomcat.util.buf;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Simple wrapper for a {@link ByteBuffer} that remembers if the buffer has been
 * flipped or not.
 */
public class ByteBufferHolder {

    private final ByteBuffer buf;
    private final AtomicBoolean flipped;

    public ByteBufferHolder(ByteBuffer buf, boolean flipped) {
       this.buf = buf;
       this.flipped = new AtomicBoolean(flipped);
    }


    public ByteBuffer getBuf() {
        return buf;
    }


    public boolean isFlipped() {
        return flipped.get();
    }


    public boolean flip() {
        if (flipped.compareAndSet(false, true)) {
            buf.flip();
            return true;
        } else {
            return false;
        }
    }
}