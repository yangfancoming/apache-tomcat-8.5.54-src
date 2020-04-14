

package org.apache.coyote.http11.filters;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.apache.coyote.InputBuffer;
import org.apache.coyote.http11.InputFilter;
import org.apache.tomcat.util.buf.ByteChunk;
import org.apache.tomcat.util.net.ApplicationBufferHandler;

/**
 * Input filter responsible for replaying the request body when restoring the
 * saved request after FORM authentication.
 */
public class SavedRequestInputFilter implements InputFilter {

    /**
     * The original request body.
     */
    protected ByteChunk input = null;

    /**
     * Create a new SavedRequestInputFilter.
     *
     * @param input The saved request body to be replayed.
     */
    public SavedRequestInputFilter(ByteChunk input) {
        this.input = input;
    }

    /**
     * @deprecated Unused. Will be removed in Tomcat 9. Use
     *             {@link #doRead(ApplicationBufferHandler)}
     */
    @Deprecated
    @Override
    public int doRead(ByteChunk chunk) throws IOException {
        if(input.getOffset()>= input.getEnd())
            return -1;

        int writeLength = 0;

        if (chunk.getLimit() > 0 && chunk.getLimit() < input.getLength()) {
            writeLength = chunk.getLimit();
        } else {
            writeLength = input.getLength();
        }

        input.substract(chunk.getBuffer(), 0, writeLength);
        chunk.setOffset(0);
        chunk.setEnd(writeLength);

        return writeLength;
    }

    @Override
    public int doRead(ApplicationBufferHandler handler) throws IOException {
        if(input.getOffset()>= input.getEnd())
            return -1;

        ByteBuffer byteBuffer = handler.getByteBuffer();
        byteBuffer.position(byteBuffer.limit()).limit(byteBuffer.capacity());
        input.substract(byteBuffer);

        return byteBuffer.remaining();
    }

    /**
     * Set the content length on the request.
     */
    @Override
    public void setRequest(org.apache.coyote.Request request) {
        request.setContentLength(input.getLength());
    }

    /**
     * Make the filter ready to process the next request.
     */
    @Override
    public void recycle() {
        input = null;
    }

    /**
     * Return the name of the associated encoding; here, the value is null.
     */
    @Override
    public ByteChunk getEncodingName() {
        return null;
    }

    /**
     * Set the next buffer in the filter pipeline (has no effect).
     */
    @Override
    public void setBuffer(InputBuffer buffer) {
        // NOOP since this filter will be providing the request body
    }

    /**
     * Amount of bytes still available in a buffer.
     */
    @Override
    public int available() {
        return input.getLength();
    }

    /**
     * End the current request (has no effect).
     */
    @Override
    public long end() throws IOException {
        return 0;
    }

    @Override
    public boolean isFinished() {
        return input.getOffset() >= input.getEnd();
    }
}
