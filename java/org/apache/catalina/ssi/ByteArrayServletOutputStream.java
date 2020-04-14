

package org.apache.catalina.ssi;

import java.io.ByteArrayOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;


/**
 * Class that extends ServletOuputStream, used as a wrapper from within
 * <code>SsiInclude</code>
 *
 * @author Bip Thelin
 * @see ServletOutputStream and ByteArrayOutputStream
 */
public class ByteArrayServletOutputStream extends ServletOutputStream {
    /**
     * Our buffer to hold the stream.
     */
    protected final ByteArrayOutputStream buf;


    /**
     * Construct a new ServletOutputStream.
     */
    public ByteArrayServletOutputStream() {
        buf = new ByteArrayOutputStream();
    }


    /**
     * @return the byte array.
     */
    public byte[] toByteArray() {
        return buf.toByteArray();
    }


    /**
     * Write to our buffer.
     *
     * @param b The parameter to write
     */
    @Override
    public void write(int b) {
        buf.write(b);
    }

    /**
     * TODO SERVLET 3.1
     */
    @Override
    public boolean isReady() {
        // TODO Auto-generated method stub
        return false;
    }


    /**
     * TODO SERVLET 3.1
     */
    @Override
    public void setWriteListener(WriteListener listener) {
        // TODO Auto-generated method stub

    }


}
