

package org.apache.tomcat.jni;

/** Open SSL BIO Callback Interface
 *
 * @author Mladen Turk
 */
public interface BIOCallback {

    /**
     * Write data
     * @param buf containing the bytes to write.
     * @return Number of characters written.
     */
    public int write(byte [] buf);

    /**
     * Read data
     * @param buf buffer to store the read bytes.
     * @return number of bytes read.
     */
    public int read(byte [] buf);

    /**
     * Puts string
     * @param data String to write
     * @return Number of characters written
     */
    public int puts(String data);

    /**
     * Read string up to the len or CLRLF
     * @param len Maximum number of characters to read
     * @return String with up to len bytes read
     */
    public String gets(int len);

}
