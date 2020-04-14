

package org.apache.tomcat.jni;

/**
 * Local socket.
 *
 * @author Mladen Turk
 */
public class Local {

    /**
     * Create a socket.
     * @param path The address of the new socket.
     * @param cont The parent pool to use
     * @return The new socket that has been set up.
     * @throws Exception If socket creation failed
     */
    public static native long create(String path, long cont)
        throws Exception;

    /**
     * Bind the socket to its associated port
     * @param sock The socket to bind
     * @param sa The socket address to bind to
     * This may be where we will find out if there is any other process
     *      using the selected port.
     * @return the operation status
     */
    public static native int bind(long sock, long sa);

    /**
     * Listen to a bound socket for connections.
     * @param sock The socket to listen on
     * @param backlog The number of outstanding connections allowed in the sockets
     *                listen queue.  If this value is less than zero, for NT pipes
     *                the number of instances is unlimited.
     * @return the operation status
     */
    public static native int listen(long sock, int backlog);

    /**
     * Accept a new connection request
     * @param sock The socket we are listening on.
     * @return  A copy of the socket that is connected to the socket that
     *          made the connection request.  This is the socket which should
     *          be used for all future communication.
     * @throws Exception If accept failed
     */
    public static native long accept(long sock)
        throws Exception;

    /**
     * Issue a connection request to a socket either on the same machine
     * or a different one.
     * @param sock The socket we wish to use for our side of the connection
     * @param sa The address of the machine we wish to connect to.
     *           Unused for NT Pipes.
     * @return the operation status
     */
    public static native int connect(long sock, long sa);

}
