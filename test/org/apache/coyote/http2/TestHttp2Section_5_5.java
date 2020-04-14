
package org.apache.coyote.http2;

import java.nio.ByteBuffer;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for Section 5.5 of
 * <a href="https://tools.ietf.org/html/rfc7540">RFC 7540</a>.
 * <br>
 * The order of tests in this class is aligned with the order of the
 * requirements in the RFC.
 */
public class TestHttp2Section_5_5 extends Http2TestBase {

    private static final byte[] UNKNOWN_FRAME;

    static {
        // Unknown frame type
        UNKNOWN_FRAME = new byte[29];
        // Frame header
        ByteUtil.setThreeBytes(UNKNOWN_FRAME, 0, 20);
        // Type
        UNKNOWN_FRAME[3] = (byte) 0x80;
        // No flags
        // Stream
        ByteUtil.set31Bits(UNKNOWN_FRAME, 5, 5);
        // zero payload
    }


    // Section 5.5

    @Test
    public void testUnknownSetting() throws Exception {
        http2Connect();

        // Unknown setting (should be ack'd)
        sendSettings(0, false, new SettingValue(1 << 15, 0));

        parser.readFrame(true);

        Assert.assertEquals("0-Settings-Ack\n",  output.getTrace());
    }


    @Test
    public void testUnknownFrame() throws Exception {
        http2Connect();

        os.write(UNKNOWN_FRAME);
        os.flush();

        // Ping
        sendPing();

        parser.readFrame(true);

        Assert.assertEquals("0-Ping-Ack-[0,0,0,0,0,0,0,0]\n", output.getTrace());
    }


    @Test
    public void testNonContiguousHeaderWithUnknownFrame() throws Exception {
        // HTTP2 upgrade
        http2Connect();

        // Part 1
        byte[] frameHeader = new byte[9];
        ByteBuffer headersPayload = ByteBuffer.allocate(128);
        buildSimpleGetRequestPart1(frameHeader, headersPayload, 3);
        writeFrame(frameHeader, headersPayload);

        os.write(UNKNOWN_FRAME);
        os.flush();

        handleGoAwayResponse(1, Http2Error.COMPRESSION_ERROR);
    }
}
