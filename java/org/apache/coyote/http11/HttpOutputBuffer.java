
package org.apache.coyote.http11;

import java.io.IOException;

import org.apache.coyote.OutputBuffer;

public interface HttpOutputBuffer extends OutputBuffer {

    /**
     * Finish writing the current response. It is acceptable to write extra
     * bytes using {@link #doWrite(java.nio.ByteBuffer)} during the execution of
     * this method.
     *
     * @throws IOException If an I/O error occurs while writing to the client
     */
    public void end() throws IOException;

    /**
     * Flushes any unwritten data to the client.
     *
     * @throws IOException If an I/O error occurs while flushing
     */
    public void flush() throws IOException;
}
