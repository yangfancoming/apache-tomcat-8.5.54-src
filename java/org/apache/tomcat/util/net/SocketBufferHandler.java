
package org.apache.tomcat.util.net;

import java.nio.ByteBuffer;

import org.apache.tomcat.util.buf.ByteBufferUtils;

public class SocketBufferHandler {

    private volatile boolean readBufferConfiguredForWrite = true;
    private volatile ByteBuffer readBuffer;

    private volatile boolean writeBufferConfiguredForWrite = true;
    private volatile ByteBuffer writeBuffer;

    private final boolean direct;

    public SocketBufferHandler(int readBufferSize, int writeBufferSize,
            boolean direct) {
        this.direct = direct;
        if (direct) {
            readBuffer = ByteBuffer.allocateDirect(readBufferSize);
            writeBuffer = ByteBuffer.allocateDirect(writeBufferSize);
        } else {
            readBuffer = ByteBuffer.allocate(readBufferSize);
            writeBuffer = ByteBuffer.allocate(writeBufferSize);
        }
    }


    public void configureReadBufferForWrite() {
        setReadBufferConfiguredForWrite(true);
    }


    public void configureReadBufferForRead() {
        setReadBufferConfiguredForWrite(false);
    }


    private void setReadBufferConfiguredForWrite(boolean readBufferConFiguredForWrite) {
        // NO-OP if buffer is already in correct state
        if (this.readBufferConfiguredForWrite != readBufferConFiguredForWrite) {
            if (readBufferConFiguredForWrite) {
                // Switching to write
                int remaining = readBuffer.remaining();
                if (remaining == 0) {
                    readBuffer.clear();
                } else {
                    readBuffer.compact();
                }
            } else {
                // Switching to read
                readBuffer.flip();
            }
            this.readBufferConfiguredForWrite = readBufferConFiguredForWrite;
        }
    }


    public ByteBuffer getReadBuffer() {
        return readBuffer;
    }


    public boolean isReadBufferEmpty() {
        if (readBufferConfiguredForWrite) {
            return readBuffer.position() == 0;
        } else {
            return readBuffer.remaining() == 0;
        }
    }


    public void configureWriteBufferForWrite() {
        setWriteBufferConfiguredForWrite(true);
    }


    public void configureWriteBufferForRead() {
        setWriteBufferConfiguredForWrite(false);
    }


    private void setWriteBufferConfiguredForWrite(boolean writeBufferConfiguredForWrite) {
        // NO-OP if buffer is already in correct state
        if (this.writeBufferConfiguredForWrite != writeBufferConfiguredForWrite) {
            if (writeBufferConfiguredForWrite) {
                // Switching to write
                int remaining = writeBuffer.remaining();
                if (remaining == 0) {
                    writeBuffer.clear();
                } else {
                    writeBuffer.compact();
                    writeBuffer.position(remaining);
                    writeBuffer.limit(writeBuffer.capacity());
                }
            } else {
                // Switching to read
                writeBuffer.flip();
            }
            this.writeBufferConfiguredForWrite = writeBufferConfiguredForWrite;
        }
    }


    public boolean isWriteBufferWritable() {
        if (writeBufferConfiguredForWrite) {
            return writeBuffer.hasRemaining();
        } else {
            return writeBuffer.remaining() == 0;
        }
    }


    public ByteBuffer getWriteBuffer() {
        return writeBuffer;
    }


    public boolean isWriteBufferEmpty() {
        if (writeBufferConfiguredForWrite) {
            return writeBuffer.position() == 0;
        } else {
            return writeBuffer.remaining() == 0;
        }
    }


    public void reset() {
        readBuffer.clear();
        readBufferConfiguredForWrite = true;
        writeBuffer.clear();
        writeBufferConfiguredForWrite = true;
    }


    public void expand(int newSize) {
        configureReadBufferForWrite();
        readBuffer = ByteBufferUtils.expand(readBuffer, newSize);
        configureWriteBufferForWrite();
        writeBuffer = ByteBufferUtils.expand(writeBuffer, newSize);
    }

    public void free() {
        if (direct) {
            ByteBufferUtils.cleanDirectBuffer(readBuffer);
            ByteBufferUtils.cleanDirectBuffer(writeBuffer);
        }
    }

}
