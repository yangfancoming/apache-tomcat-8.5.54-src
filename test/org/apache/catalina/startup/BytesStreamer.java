
package org.apache.catalina.startup;

/**
 *
 * Used by {@link TomcatBaseTest}
 *
 *
 */
public interface BytesStreamer {
    /**
     * Get the length of the content about to be streamed.
     *
     * @return the length if known, else -1 and chucked encoding should be used
     */
    int getLength();

    /**
     * @return  the number of bytes available in next chunk
     */
    int available();

    /**
     * @return  returns the next byte to write if {@link #available()} returns
     *          > 0
     */
    byte[] next();
}
