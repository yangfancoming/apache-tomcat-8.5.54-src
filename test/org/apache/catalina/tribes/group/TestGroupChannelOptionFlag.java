
package org.apache.catalina.tribes.group;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.apache.catalina.tribes.Channel;
import org.apache.catalina.tribes.ChannelException;
import org.apache.catalina.tribes.ChannelInterceptor;

public class TestGroupChannelOptionFlag {
    private GroupChannel channel = null;

    @Before
    public void setUp() throws Exception {
        channel = new GroupChannel();
    }

    @After
    public void tearDown() throws Exception {
        if (channel != null) {
            try {
                channel.stop(Channel.DEFAULT);
            } catch (Exception ignore) {
                // Ignore
            }
        }
        channel = null;
    }

    @Test
    public void testOptionConflict() throws Exception {
        boolean error = false;
        channel.setOptionCheck(true);
        ChannelInterceptor i = new TestInterceptor();
        i.setOptionFlag(128);
        channel.addInterceptor(i);
        i = new TestInterceptor();
        i.setOptionFlag(128);
        channel.addInterceptor(i);
        try {
            channel.start(Channel.DEFAULT);
        }catch ( ChannelException x ) {
            if ( x.getMessage().indexOf("option flag conflict") >= 0 ) error = true;
        }
        Assert.assertTrue(error);
    }

    @Test
    public void testOptionNoConflict() throws Exception {
        boolean error = false;
        channel.setOptionCheck(true);
        ChannelInterceptor i = new TestInterceptor();
        i.setOptionFlag(128);
        channel.addInterceptor(i);
        i = new TestInterceptor();
        i.setOptionFlag(64);
        channel.addInterceptor(i);
        i = new TestInterceptor();
        i.setOptionFlag(256);
        channel.addInterceptor(i);
        try {
            channel.start(Channel.DEFAULT);
        }catch ( ChannelException x ) {
            if ( x.getMessage().indexOf("option flag conflict") >= 0 ) error = true;
        }
        Assert.assertFalse(error);
    }

    public static class TestInterceptor extends ChannelInterceptorBase {
        // Just use base class
    }


}
