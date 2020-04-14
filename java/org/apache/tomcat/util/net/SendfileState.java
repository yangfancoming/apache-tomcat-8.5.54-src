
package org.apache.tomcat.util.net;

public enum SendfileState {

    /**
     * The sending of the file has started but has not completed. Sendfile is
     * still using the socket.
     */
    PENDING,

    /**
     * The file has been fully sent. Sendfile is no longer using the socket.
     */
    DONE,

    /**
     * Something went wrong. The file may or may not have been sent. The socket
     * is in an unknown state.
     */
    ERROR
}
