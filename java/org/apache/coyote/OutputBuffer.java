
package org.apache.coyote;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.apache.tomcat.util.buf.ByteChunk;

/**
 * Output buffer.
 *
 * This class is used internally by the protocol implementation. All writes from
 * higher level code should happen via Response.doWrite().
 *
 * @author Remy Maucherat
 */
public interface OutputBuffer {

    /**
     * Write the given data to the response. The caller owns the chunks.
     *
     * @param chunk data to write
     *
     * @return The number of bytes written which may be less than available in
     *         the input chunk
     *
     * @throws IOException an underlying I/O error occurred
     *
     * @deprecated Unused. Will be removed in Tomcat 9. Use
     *             {@link #doWrite(ByteBuffer)}
     */
    @Deprecated
    public int doWrite(ByteChunk chunk) throws IOException;


    /**
     * Write the given data to the response. The caller owns the chunks.
     *
     * @param chunk data to write
     *
     * @return The number of bytes written which may be less than available in
     *         the input chunk
     *
     * @throws IOException an underlying I/O error occurred
     */
    public int doWrite(ByteBuffer chunk) throws IOException;


    /**
     * Bytes written to the underlying socket. This includes the effects of
     * chunking, compression, etc.
     *
     * @return  Bytes written for the current request
     */
    public long getBytesWritten();
}
