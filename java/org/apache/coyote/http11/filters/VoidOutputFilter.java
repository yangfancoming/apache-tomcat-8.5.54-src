
package org.apache.coyote.http11.filters;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.apache.coyote.Response;
import org.apache.coyote.http11.HttpOutputBuffer;
import org.apache.coyote.http11.OutputFilter;
import org.apache.tomcat.util.buf.ByteChunk;

/**
 * Void output filter, which silently swallows bytes written. Used with a 204
 * status (no content) or a HEAD request.
 *
 * @author Remy Maucherat
 */
public class VoidOutputFilter implements OutputFilter {

    private HttpOutputBuffer buffer = null;


    // --------------------------------------------------- OutputBuffer Methods

    /**
     * @deprecated Unused. Will be removed in Tomcat 9. Use
     *             {@link #doWrite(ByteBuffer)}
     */
    @Deprecated
    @Override
    public int doWrite(ByteChunk chunk) throws IOException {
        return chunk.getLength();
    }


    @Override
    public int doWrite(ByteBuffer chunk) throws IOException {
        return chunk.remaining();
    }


    @Override
    public long getBytesWritten() {
        return 0;
    }


    // --------------------------------------------------- OutputFilter Methods

    @Override
    public void setResponse(Response response) {
        // NOOP: No need for parameters from response in this filter
    }


    @Override
    public void setBuffer(HttpOutputBuffer buffer) {
        this.buffer = buffer;
    }


    @Override
    public void flush() throws IOException {
        this.buffer.flush();
    }


    @Override
    public void recycle() {
        buffer = null;
    }


    @Override
    public void  end() throws IOException {
        buffer.end();
    }
}
