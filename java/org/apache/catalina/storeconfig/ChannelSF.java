

package org.apache.catalina.storeconfig;

import java.io.PrintWriter;
import java.util.Iterator;

import org.apache.catalina.tribes.Channel;
import org.apache.catalina.tribes.ChannelInterceptor;
import org.apache.catalina.tribes.ChannelReceiver;
import org.apache.catalina.tribes.ChannelSender;
import org.apache.catalina.tribes.ManagedChannel;
import org.apache.catalina.tribes.MembershipService;

/**
 * Generate Channel Element
 */
public class ChannelSF extends StoreFactoryBase {

    /**
     * Store the specified Channel children.
     *
     * @param aWriter
     *            PrintWriter to which we are storing
     * @param indent
     *            Number of spaces to indent this element
     * @param aChannel
     *            Channel whose properties are being stored
     *
     * @exception Exception
     *                if an exception occurs while storing
     */
    @Override
    public void storeChildren(PrintWriter aWriter, int indent, Object aChannel,
            StoreDescription parentDesc) throws Exception {
        if (aChannel instanceof Channel) {
            Channel channel = (Channel) aChannel;
            if (channel instanceof ManagedChannel) {
                ManagedChannel managedChannel = (ManagedChannel) channel;
                // Store nested <Membership> element
                MembershipService service = managedChannel.getMembershipService();
                if (service != null) {
                    storeElement(aWriter, indent, service);
                }
                // Store nested <Sender> element
                ChannelSender sender = managedChannel.getChannelSender();
                if (sender != null) {
                    storeElement(aWriter, indent, sender);
                }
                // Store nested <Receiver> element
                ChannelReceiver receiver = managedChannel.getChannelReceiver();
                if (receiver != null) {
                    storeElement(aWriter, indent, receiver);
                }
                Iterator<ChannelInterceptor> interceptors = managedChannel.getInterceptors();
                while (interceptors.hasNext()) {
                    ChannelInterceptor interceptor = interceptors.next();
                    storeElement(aWriter, indent, interceptor);
                }
            }
       }
    }
}