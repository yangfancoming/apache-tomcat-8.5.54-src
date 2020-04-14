

package org.apache.tomcat.jni;

/** Stdlib
 *
 * @author Mladen Turk
 */
public class Stdlib {

    /**
     * Read from plain memory
     * @param dst Destination byte array
     * @param src Source memory address
     * @param sz Number of bytes to copy.
     * @return <code>true</code> if the operation was successful
     */
    public static native boolean memread(byte [] dst, long src, int sz);

    /**
     * Write to plain memory
     * @param dst Destination memory address
     * @param src Source byte array
     * @param sz Number of bytes to copy.
     * @return <code>true</code> if the operation was successful
     */
    public static native boolean memwrite(long dst, byte [] src, int sz);

    /**
     * Sets buffers to a specified character
     * @param dst Destination memory address
     * @param c Character to set.
     * @param sz Number of characters.
     * @return <code>true</code> if the operation was successful
     */
    public static native boolean memset(long dst, int c, int sz);

    /**
     * Allocates memory blocks.
     * @param sz Bytes to allocate.
     * @return a pointer
     */
    public static native long malloc(int sz);

    /**
     * Reallocate memory blocks.
     * @param mem Pointer to previously allocated memory block.
     * @param sz New size in bytes.
     * @return a pointer
     */
    public static native long realloc(long mem, int sz);

    /**
     * Allocates an array in memory with elements initialized to 0.
     * @param num Number of elements.
     * @param sz Length in bytes of each element.
     * @return a pointer
     */
    public static native long calloc(int num, int sz);

    /**
     * Deallocates or frees a memory block.
     * @param mem Previously allocated memory block to be freed.
     */
    public static native void free(long mem);

    /**
     * Get current process pid.
     * @return current pid or &lt; 1 in case of error.
     */
    public static native int getpid();

    /**
     * Get current process parent pid.
     * @return parent pid or &lt; 1 in case of error.
     */
    public static native int getppid();

}
