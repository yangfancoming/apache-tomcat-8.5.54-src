

package org.apache.catalina.tribes;



/**
 * The <code>ErrorHandler</code> class is used when sending messages
 * that are sent asynchronously and the application still needs to get
 * confirmation when the message was sent successfully or when a message errored out.
 * @version 1.0
 */
public interface ErrorHandler {

    /**
     * Invoked if the message is dispatched async, and an error occurs
     * @param x ChannelException - the error that happened
     * @param id - the unique id for the message
     * @see Channel#send(Member[], java.io.Serializable, int, ErrorHandler)
     */
    public void handleError(ChannelException x, UniqueId id);

    /**
     * Invoked when the message has been sent successfully.
     * @param id - the unique id for the message
     * @see Channel#send(Member[], java.io.Serializable, int, ErrorHandler)
     */
    public void handleCompletion(UniqueId id);

}