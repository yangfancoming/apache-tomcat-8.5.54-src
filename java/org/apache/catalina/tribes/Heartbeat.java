
package org.apache.catalina.tribes;

/**
 * Can be implemented by the ChannelListener and Membership listeners to receive heartbeat
 * notifications from the Channel
 * @version 1.0
 * @see Channel
 * @see Channel#heartbeat()
 */
public interface Heartbeat {

    /**
     * Heartbeat invocation for resources cleanup etc
     */
    public void heartbeat();

}