
package org.apache.tomcat.websocket;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class TestWsFrame {

    @Test
    public void testByteArrayToLong() throws IOException {
        Assert.assertEquals(0L, WsFrameBase.byteArrayToLong(new byte[] { 0 }, 0, 1));
        Assert.assertEquals(1L, WsFrameBase.byteArrayToLong(new byte[] { 1 }, 0, 1));
        Assert.assertEquals(0xFF, WsFrameBase.byteArrayToLong(new byte[] { -1 }, 0, 1));
        Assert.assertEquals(0xFFFF,
                WsFrameBase.byteArrayToLong(new byte[] { -1, -1 }, 0, 2));
        Assert.assertEquals(0xFFFFFF,
                WsFrameBase.byteArrayToLong(new byte[] { -1, -1, -1 }, 0, 3));
    }


    @Test
    public void testByteArrayToLongOffset() throws IOException {
        Assert.assertEquals(0L, WsFrameBase.byteArrayToLong(new byte[] { 20, 0 }, 1, 1));
        Assert.assertEquals(1L, WsFrameBase.byteArrayToLong(new byte[] { 20, 1 }, 1, 1));
        Assert.assertEquals(0xFF, WsFrameBase.byteArrayToLong(new byte[] { 20, -1 }, 1, 1));
        Assert.assertEquals(0xFFFF,
                WsFrameBase.byteArrayToLong(new byte[] { 20, -1, -1 }, 1, 2));
        Assert.assertEquals(0xFFFFFF,
                WsFrameBase.byteArrayToLong(new byte[] { 20, -1, -1, -1 }, 1, 3));
    }

}
