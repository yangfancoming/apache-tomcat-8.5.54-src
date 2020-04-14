
package org.apache.catalina.tribes.transport;

import javax.management.ObjectName;

import org.apache.catalina.tribes.Channel;
import org.apache.catalina.tribes.ChannelException;
import org.apache.catalina.tribes.ChannelMessage;
import org.apache.catalina.tribes.ChannelSender;
import org.apache.catalina.tribes.Member;
import org.apache.catalina.tribes.jmx.JmxRegistry;
import org.apache.catalina.tribes.transport.nio.PooledParallelSender;

/**
 * Transmit message to other cluster members
 * Actual senders are created based on the replicationMode
 * type
 */
public class ReplicationTransmitter implements ChannelSender {

    private Channel channel;

    /**
     * the ObjectName of this Sender.
     */
    private ObjectName oname = null;

    public ReplicationTransmitter() {
    }

    private MultiPointSender transport = new PooledParallelSender();

    public MultiPointSender getTransport() {
        return transport;
    }

    public void setTransport(MultiPointSender transport) {
        this.transport = transport;
    }

    // ------------------------------------------------------------- public

    /**
     * Send data to one member
     * @see org.apache.catalina.tribes.ChannelSender#sendMessage(org.apache.catalina.tribes.ChannelMessage, org.apache.catalina.tribes.Member[])
     */
    @Override
    public void sendMessage(ChannelMessage message, Member[] destination) throws ChannelException {
        MultiPointSender sender = getTransport();
        sender.sendMessage(destination,message);
    }


    /**
     * start the sender and register transmitter mbean
     *
     * @see org.apache.catalina.tribes.ChannelSender#start()
     */
    @Override
    public void start() throws java.io.IOException {
        getTransport().connect();
        // register jmx
        JmxRegistry jmxRegistry = JmxRegistry.getRegistry(channel);
        if (jmxRegistry != null) this.oname = jmxRegistry.registerJmx(",component=Sender", transport);
    }

    /**
     * stop the sender and deregister mbeans (transmitter, senders)
     *
     * @see org.apache.catalina.tribes.ChannelSender#stop()
     */
    @Override
    public synchronized void stop() {
        getTransport().disconnect();
        if (oname != null) {
            JmxRegistry.getRegistry(channel).unregisterJmx(oname);
            oname = null;
        }
        channel = null;
    }

    /**
     * Call transmitter to check for sender socket status
     *
     * @see org.apache.catalina.ha.tcp.SimpleTcpCluster#backgroundProcess()
     */
    @Override
    public void heartbeat() {
        if (getTransport()!=null) getTransport().keepalive();
    }

    /**
     * add new cluster member and create sender ( s. replicationMode) transfer
     * current properties to sender
     *
     * @see org.apache.catalina.tribes.ChannelSender#add(org.apache.catalina.tribes.Member)
     */
    @Override
    public synchronized void add(Member member) {
        getTransport().add(member);
    }

    /**
     * remove sender from transmitter. ( deregister mbean and disconnect sender )
     *
     * @see org.apache.catalina.tribes.ChannelSender#remove(org.apache.catalina.tribes.Member)
     */
    @Override
    public synchronized void remove(Member member) {
        getTransport().remove(member);
    }

    @Override
    public Channel getChannel() {
        return channel;
    }

    @Override
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

}
