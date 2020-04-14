

package org.apache.coyote.http11;

import java.io.IOException;

import org.apache.coyote.InputBuffer;
import org.apache.coyote.Request;
import org.apache.tomcat.util.buf.ByteChunk;

/**
 * Input filter interface.
 *
 * @author Remy Maucherat
 */
public interface InputFilter extends InputBuffer {

    /**
     * Some filters need additional parameters from the request.
     *
     * @param request The request to be associated with this filter
     */
    public void setRequest(Request request);


    /**
     * Make the filter ready to process the next request.
     */
    public void recycle();


    /**
     * Get the name of the encoding handled by this filter.
     *
     * @return The encoding name as a byte chunk to facilitate comparison with
     *         the value read from the HTTP headers which will also be a
     *         ByteChunk
     */
    public ByteChunk getEncodingName();


    /**
     * Set the next buffer in the filter pipeline.
     *
     * @param buffer The next buffer
     */
    public void setBuffer(InputBuffer buffer);


    /**
     * End the current request.
     *
     * @return 0 is the expected return value. A positive value indicates that
     * too many bytes were read. This method is allowed to use buffer.doRead
     * to consume extra bytes. The result of this method can't be negative (if
     * an error happens, an IOException should be thrown instead).
     *
     * @throws IOException If an error happens
     */
    public long end() throws IOException;


    /**
     * Amount of bytes still available in a buffer.
     *
     * @return The number of bytes in the buffer
     */
    public int available();


    /**
     * Has the request body been read fully?
     *
     * @return {@code true} if the request body has been fully read, otherwise
     *         {@code false}
     */
    public boolean isFinished();
}
