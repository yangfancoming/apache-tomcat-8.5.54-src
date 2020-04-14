
package org.apache.catalina.tribes;

/**
 *
 * <p>Title: MessageListener</p>
 *
 * <p>Description: The listener to be registered with the ChannelReceiver, internal Tribes component</p>
 *
 * @version 1.0
 */

public interface MessageListener {

    /**
     * Receive a message from the IO components in the Channel stack
     * @param msg ChannelMessage
     */
    public void messageReceived(ChannelMessage msg);

    public boolean accept(ChannelMessage msg);
}
