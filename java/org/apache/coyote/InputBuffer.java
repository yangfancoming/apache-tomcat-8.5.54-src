
package org.apache.coyote;

import java.io.IOException;

import org.apache.tomcat.util.buf.ByteChunk;
import org.apache.tomcat.util.net.ApplicationBufferHandler;

/**
 * This class is only for internal use in the protocol implementation. All
 * reading from Tomcat (or adapter) should be done using Request.doRead().
 */
public interface InputBuffer {

    /**
     * Read from the input stream into the given buffer.
     * IMPORTANT: the current model assumes that the protocol will 'own' the
     * buffer and return a pointer to it in ByteChunk (i.e. the param will
     * have chunk.getBytes()==null before call, and the result after the call).
     *
     * @param chunk The buffer to read data into.
     *
     * @return The number of bytes that have been added to the buffer or -1 for
     *         end of stream
     *
     * @throws IOException If an I/O error occurs reading from the input stream
     *
     * @deprecated Unused. Will be removed in Tomcat 9. Use
     *             {@link #doRead(ApplicationBufferHandler)}
     */
    @Deprecated
    public int doRead(ByteChunk chunk) throws IOException;

    /**
     * Read from the input stream into the ByteBuffer provided by the
     * ApplicationBufferHandler.
     * IMPORTANT: the current model assumes that the protocol will 'own' the
     * ByteBuffer and return a pointer to it.
     *
     * @param handler ApplicationBufferHandler that provides the buffer to read
     *                data into.
     *
     * @return The number of bytes that have been added to the buffer or -1 for
     *         end of stream
     *
     * @throws IOException If an I/O error occurs reading from the input stream
     */
    public int doRead(ApplicationBufferHandler handler) throws IOException;
}
