
package org.apache.tomcat.util.net;

import java.nio.ByteBuffer;

/**
 * Callback interface to be able to expand buffers when buffer overflow
 * exceptions happen or to replace buffers
 */
public interface ApplicationBufferHandler {

    public void setByteBuffer(ByteBuffer buffer);

    public ByteBuffer getByteBuffer();

    public void expand(int size);

}
