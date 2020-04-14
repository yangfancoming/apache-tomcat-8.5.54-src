
package org.apache.tomcat.util.net;

public abstract class SendfileDataBase {

    /**
     * Is the current request being processed on a keep-alive connection? This
     * determines if the socket is closed once the send file completes or if
     * processing continues with the next request on the connection or waiting
     * for that next request to arrive.
     */
    public SendfileKeepAliveState keepAliveState = SendfileKeepAliveState.NONE;

    /**
     * The full path to the file that contains the data to be written to the
     * socket.
     */
    public final String fileName;

    /**
     * The position of the next byte in the file to be written to the socket.
     * This is initialised to the start point and then updated as the file is
     * written.
     */
    public long pos;

    /**
     * The number of bytes remaining to be written from the file (from the
     * current {@link #pos}. This is initialised to the end point - the start
     * point and then updated as the file is written.
     */
    public long length;

    public SendfileDataBase(String filename, long pos, long length) {
        this.fileName = filename;
        this.pos = pos;
        this.length = length;
    }
}
