
package org.apache.coyote.http11.filters;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.apache.coyote.Response;
import org.apache.coyote.http11.HttpOutputBuffer;
import org.apache.coyote.http11.OutputFilter;
import org.apache.tomcat.util.buf.ByteChunk;

/**
 * Identity output filter.
 *
 * @author Remy Maucherat
 */
public class IdentityOutputFilter implements OutputFilter {

    // ----------------------------------------------------- Instance Variables

    /**
     * Content length.
     */
    protected long contentLength = -1;


    /**
     * Remaining bytes.
     */
    protected long remaining = 0;


    /**
     * Next buffer in the pipeline.
     */
    protected HttpOutputBuffer buffer;


    // --------------------------------------------------- OutputBuffer Methods

    /**
     * @deprecated Unused. Will be removed in Tomcat 9. Use
     *             {@link #doWrite(ByteBuffer)}
     */
    @Deprecated
    @Override
    public int doWrite(ByteChunk chunk) throws IOException {

        int result = -1;

        if (contentLength >= 0) {
            if (remaining > 0) {
                result = chunk.getLength();
                if (result > remaining) {
                    // The chunk is longer than the number of bytes remaining
                    // in the body; changing the chunk length to the number
                    // of bytes remaining
                    chunk.setBytes(chunk.getBytes(), chunk.getStart(),
                                   (int) remaining);
                    result = (int) remaining;
                    remaining = 0;
                } else {
                    remaining = remaining - result;
                }
                buffer.doWrite(chunk);
            } else {
                // No more bytes left to be written : return -1 and clear the
                // buffer
                chunk.recycle();
                result = -1;
            }
        } else {
            // If no content length was set, just write the bytes
            buffer.doWrite(chunk);
            result = chunk.getLength();
        }

        return result;

    }


    @Override
    public int doWrite(ByteBuffer chunk) throws IOException {

        int result = -1;

        if (contentLength >= 0) {
            if (remaining > 0) {
                result = chunk.remaining();
                if (result > remaining) {
                    // The chunk is longer than the number of bytes remaining
                    // in the body; changing the chunk length to the number
                    // of bytes remaining
                    chunk.limit(chunk.position() + (int) remaining);
                    result = (int) remaining;
                    remaining = 0;
                } else {
                    remaining = remaining - result;
                }
                buffer.doWrite(chunk);
            } else {
                // No more bytes left to be written : return -1 and clear the
                // buffer
                chunk.position(0);
                chunk.limit(0);
                result = -1;
            }
        } else {
            // If no content length was set, just write the bytes
            result = chunk.remaining();
            buffer.doWrite(chunk);
            result -= chunk.remaining();
        }

        return result;

    }


    @Override
    public long getBytesWritten() {
        return buffer.getBytesWritten();
    }


    // --------------------------------------------------- OutputFilter Methods

    @Override
    public void setResponse(Response response) {
        contentLength = response.getContentLengthLong();
        remaining = contentLength;
    }


    @Override
    public void setBuffer(HttpOutputBuffer buffer) {
        this.buffer = buffer;
    }


    @Override
    public void flush() throws IOException {
        // No data buffered in this filter. Flush next buffer.
        buffer.flush();
    }


    @Override
    public void end() throws IOException {
        buffer.end();
    }


    @Override
    public void recycle() {
        contentLength = -1;
        remaining = 0;
    }
}
