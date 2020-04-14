
package org.apache.catalina.tribes.group;

import java.io.Serializable;

import org.apache.catalina.tribes.Member;
/**
 * Extension to the {@link RpcCallback} interface. Allows a RPC messenger to get a confirmation if the reply
 * was sent successfully to the original sender.
 *
 */
public interface ExtendedRpcCallback extends RpcCallback {

    /**
     * The reply failed.
     * @param request - the original message that requested the reply
     * @param response - the reply message to the original message
     * @param sender - the sender requested that reply
     * @param reason - the reason the reply failed
     */
    public void replyFailed(Serializable request, Serializable response, Member sender, Exception reason);

    /**
     * The reply succeeded
     * @param request - the original message that requested the reply
     * @param response - the reply message to the original message
     * @param sender - the sender requested that reply
     */
    public void replySucceeded(Serializable request, Serializable response, Member sender);
}
