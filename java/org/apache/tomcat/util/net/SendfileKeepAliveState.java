
package org.apache.tomcat.util.net;

public enum SendfileKeepAliveState {

    /**
     * Keep-alive is not in use. The socket can be closed when the response has
     * been written.
     */
    NONE,

    /**
     * Keep-alive is in use and there is pipelined data in the input buffer to
     * be read as soon as the current response has been written.
     */
    PIPELINED,

    /**
     * Keep-alive is in use. The socket should be added to the poller (or
     * equivalent) to await more data as soon as the current response has been
     * written.
     */
    OPEN
}
