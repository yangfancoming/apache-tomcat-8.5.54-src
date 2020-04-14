

package org.apache.coyote.http11.filters;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import org.apache.coyote.InputBuffer;
import org.apache.coyote.Request;
import org.apache.coyote.http11.InputFilter;
import org.apache.tomcat.util.buf.ByteChunk;
import org.apache.tomcat.util.net.ApplicationBufferHandler;

/**
 * Input filter responsible for reading and buffering the request body, so that
 * it does not interfere with client SSL handshake messages.
 */
public class BufferedInputFilter implements InputFilter, ApplicationBufferHandler {

    // -------------------------------------------------------------- Constants

    private static final String ENCODING_NAME = "buffered";
    private static final ByteChunk ENCODING = new ByteChunk();


    // ----------------------------------------------------- Instance Variables

    private ByteBuffer buffered;
    private ByteBuffer tempRead;
    private InputBuffer buffer;
    private boolean hasRead = false;


    // ----------------------------------------------------- Static Initializer

    static {
        ENCODING.setBytes(ENCODING_NAME.getBytes(StandardCharsets.ISO_8859_1),
                0, ENCODING_NAME.length());
    }


    // --------------------------------------------------------- Public Methods


    /**
     * Set the buffering limit. This should be reset every time the buffer is
     * used.
     *
     * @param limit The maximum number of bytes that will be buffered
     */
    public void setLimit(int limit) {
        if (buffered == null) {
            buffered = ByteBuffer.allocate(limit);
            buffered.flip();
        }
    }


    // ---------------------------------------------------- InputBuffer Methods


    /**
     * Reads the request body and buffers it.
     */
    @Override
    public void setRequest(Request request) {
        // save off the Request body
        try {
            while (buffer.doRead(this) >= 0) {
                buffered.mark().position(buffered.limit()).limit(buffered.capacity());
                buffered.put(tempRead);
                buffered.limit(buffered.position()).reset();
                tempRead = null;
            }
        } catch(IOException | BufferOverflowException ioe) {
            // No need for i18n - this isn't going to get logged anywhere
            throw new IllegalStateException(
                    "Request body too large for buffer");
        }
    }

    /**
     * Fills the given ByteChunk with the buffered request body.
     *
     * @deprecated Unused. Will be removed in Tomcat 9. Use
     *             {@link #doRead(ApplicationBufferHandler)}
     */
    @Deprecated
    @Override
    public int doRead(ByteChunk chunk) throws IOException {
        if (isFinished()) {
            return -1;
        }

        chunk.setBytes(buffered.array(), buffered.arrayOffset() + buffered.position(),
                buffered.remaining());
        hasRead = true;
        return chunk.getLength();
    }

    /**
     * Fills the given ByteBuffer with the buffered request body.
     */
    @Override
    public int doRead(ApplicationBufferHandler handler) throws IOException {
        if (isFinished()) {
            return -1;
        }

        handler.setByteBuffer(buffered);
        hasRead = true;
        return buffered.remaining();
    }

    @Override
    public void setBuffer(InputBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void recycle() {
        if (buffered != null) {
            if (buffered.capacity() > 65536) {
                buffered = null;
            } else {
                buffered.position(0).limit(0);
            }
        }
        hasRead = false;
        buffer = null;
    }

    @Override
    public ByteChunk getEncodingName() {
        return ENCODING;
    }

    @Override
    public long end() throws IOException {
        return 0;
    }

    @Override
    public int available() {
        return buffered.remaining();
    }


    @Override
    public boolean isFinished() {
        return hasRead || buffered.remaining() <= 0;
    }


    @Override
    public void setByteBuffer(ByteBuffer buffer) {
        tempRead = buffer;
    }


    @Override
    public ByteBuffer getByteBuffer() {
        return tempRead;
    }


    @Override
    public void expand(int size) {
        // no-op
    }
}
