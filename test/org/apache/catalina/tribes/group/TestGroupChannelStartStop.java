
package org.apache.catalina.tribes.group;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.apache.catalina.tribes.Channel;
import org.apache.catalina.tribes.transport.ReceiverBase;

/**
 * @version 1.0
 */
public class TestGroupChannelStartStop {
    private GroupChannel channel = null;
    private int udpPort = 45543;

    @Before
    public void setUp() throws Exception {
        channel = new GroupChannel();
    }

    @After
    public void tearDown() throws Exception {
        try {
            channel.stop(Channel.DEFAULT);
        } catch (Exception ignore) {
            // Ignore
        }
    }

    @Test
    public void testDoubleFullStart() throws Exception {
        int count = 0;
        try {
            channel.start(Channel.DEFAULT);
            count++;
        } catch ( Exception x){x.printStackTrace();}
        try {
            channel.start(Channel.DEFAULT);
            count++;
        } catch ( Exception x){x.printStackTrace();}
        Assert.assertEquals(count,2);
        channel.stop(Channel.DEFAULT);
    }

    @Test
    public void testScrap() throws Exception {
        System.out.println(channel.getChannelReceiver().getClass());
        ((ReceiverBase)channel.getChannelReceiver()).setMaxThreads(1);
    }

    @Test
    public void testDoublePartialStart() throws Exception {
        //try to double start the RX
        int count = 0;
        try {
            channel.start(Channel.SND_RX_SEQ);
            channel.start(Channel.MBR_RX_SEQ);
            count++;
        } catch ( Exception x){x.printStackTrace();}
        try {
            channel.start(Channel.MBR_RX_SEQ);
            count++;
        } catch ( Exception x){
            // expected
        }
        Assert.assertEquals(count,1);
        channel.stop(Channel.DEFAULT);
        //double the membership sender
        count = 0;
        try {
            channel.start(Channel.SND_RX_SEQ);
            channel.start(Channel.MBR_TX_SEQ);
            count++;
        } catch ( Exception x){x.printStackTrace();}
        try {
            channel.start(Channel.MBR_TX_SEQ);
            count++;
        } catch ( Exception x){
            // expected
        }
        Assert.assertEquals(count,1);
        channel.stop(Channel.DEFAULT);

        count = 0;
        try {
            channel.start(Channel.SND_RX_SEQ);
            count++;
        } catch ( Exception x){x.printStackTrace();}
        try {
            channel.start(Channel.SND_RX_SEQ);
            count++;
        } catch ( Exception x){
            // expected
        }
        Assert.assertEquals(count,1);
        channel.stop(Channel.DEFAULT);

        count = 0;
        try {
            channel.start(Channel.SND_TX_SEQ);
            count++;
        } catch ( Exception x){x.printStackTrace();}
        try {
            channel.start(Channel.SND_TX_SEQ);
            count++;
        } catch ( Exception x){
            // expected
        }
        Assert.assertEquals(count,1);
        channel.stop(Channel.DEFAULT);
    }

    @Test
    public void testFalseOption() throws Exception {
        int flag = 0xFFF0;//should get ignored by the underlying components
        int count = 0;
        try {
            channel.start(flag);
            count++;
        } catch ( Exception x){x.printStackTrace();}
        try {
            channel.start(flag);
            count++;
        } catch ( Exception x){
            // expected
        }
        Assert.assertEquals(count,2);
        channel.stop(Channel.DEFAULT);
    }

    @Test
    public void testUdpReceiverStart() throws Exception {
        ReceiverBase rb = (ReceiverBase)channel.getChannelReceiver();
        rb.setUdpPort(udpPort);
        channel.start(Channel.DEFAULT);
        Thread.sleep(1000);
        channel.stop(Channel.DEFAULT);
    }
}
