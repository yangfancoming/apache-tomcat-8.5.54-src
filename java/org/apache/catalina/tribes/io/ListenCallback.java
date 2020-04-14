

package org.apache.catalina.tribes.io;

import org.apache.catalina.tribes.ChannelMessage;



/**
 * Internal interface, similar to the MessageListener but used
 * at the IO base
 * The listen callback interface is used by the replication system
 * when data has been received. The interface does not care about
 * objects and marshalling and just passes the bytes straight through.
 */
public interface ListenCallback
{
    /**
     * This method is invoked on the callback object to notify it that new data has
     * been received from one of the cluster nodes.
     * @param data - the message bytes received from the cluster/replication system
     */
     public void messageDataReceived(ChannelMessage data);

}