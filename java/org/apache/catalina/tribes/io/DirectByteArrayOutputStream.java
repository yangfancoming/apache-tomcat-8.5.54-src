

package org.apache.catalina.tribes.io;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Byte array output stream that exposes the byte array directly
 *
 * @version 1.0
 */
public class DirectByteArrayOutputStream extends OutputStream {

    private final XByteBuffer buffer;

    public DirectByteArrayOutputStream(int size) {
        buffer = new XByteBuffer(size,false);
    }

    /**
     * Writes the specified byte to this output stream.
     *
     * @param b the <code>byte</code>.
     * @throws IOException if an I/O error occurs. In particular, an
     *   <code>IOException</code> may be thrown if the output stream has
     *   been closed.
     */
    @Override
    public void write(int b) throws IOException {
        buffer.append((byte)b);
    }

    public int size() {
        return buffer.getLength();
    }

    public byte[] getArrayDirect() {
        return buffer.getBytesDirect();
    }

    public byte[] getArray() {
        return buffer.getBytes();
    }


}