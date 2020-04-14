

package org.apache.tomcat.jni;

import java.nio.ByteBuffer;

/** Buffer
 *
 * @author Mladen Turk
 */
public class Buffer {

    /**
     * Allocate a new ByteBuffer from memory
     * @param size The amount of memory to allocate
     * @return The ByteBuffer with allocated memory
     */
    public static native ByteBuffer malloc(int size);

    /**
     * Allocate a new ByteBuffer from memory and set all of the memory to 0
     * @param num Number of elements.
     * @param size Length in bytes of each element.
     * @return The ByteBuffer with allocated memory
     */
    public static native ByteBuffer calloc(int num, int size);

    /**
     * Allocate a new ByteBuffer from a pool
     * @param p The pool to allocate from
     * @param size The amount of memory to allocate
     * @return The ByteBuffer with allocated memory
     */
    public static native ByteBuffer palloc(long p, int size);

    /**
     * Allocate a new ByteBuffer from a pool and set all of the memory to 0
     * @param p The pool to allocate from
     * @param size The amount of memory to allocate
     * @return The ByteBuffer with allocated memory
     */
    public static native ByteBuffer pcalloc(long p, int size);

    /**
     * Allocate a new ByteBuffer from already allocated memory.
     * <br>Allocated memory must be provided from call to the
     * Stdlib.alloc or Stdlib.calloc methods.
     * @param mem The memory to use
     * @param size The amount of memory to use
     * @return The ByteBuffer with attached memory
     */
    public static native ByteBuffer create(long mem, int size);

    /**
     * Deallocates or frees a memory block used by ByteBuffer
     * <br><b>Warning :</b> Call this method only on ByteBuffers
     * that were created by calling Buffer.alloc or Buffer.calloc.
     * @param buf Previously allocated ByteBuffer to be freed.
     */
    public static native void free(ByteBuffer buf);

    /**
     * Returns the memory address of the ByteBuffer.
     * @param buf Previously allocated ByteBuffer.
     * @return the memory address
     */
    public static native long address(ByteBuffer buf);

    /**
     * Returns the allocated memory size of the ByteBuffer.
     * @param buf Previously allocated ByteBuffer.
     * @return the size
     */
    public static native long size(ByteBuffer buf);

}
