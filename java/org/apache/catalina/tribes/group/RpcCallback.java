
package org.apache.catalina.tribes.group;

import java.io.Serializable;

import org.apache.catalina.tribes.Member;

/**
 * The RpcCallback interface is an interface for the Tribes channel to request a
 * response object to a request that came in.
 * @version 1.0
 */
public interface RpcCallback {

    /**
     * Allows sending a response to a received message.
     * @param msg The message
     * @param sender Member
     * @return Serializable object, <code>null</code> if no reply should be sent
     */
    public Serializable replyRequest(Serializable msg, Member sender);

    /**
     * If the reply has already been sent to the requesting thread,
     * the rpc callback can handle any data that comes in after the fact.
     * @param msg The message
     * @param sender Member
     */
    public void leftOver(Serializable msg, Member sender);

}