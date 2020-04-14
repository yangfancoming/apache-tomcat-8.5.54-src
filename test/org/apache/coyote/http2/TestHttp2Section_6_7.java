
package org.apache.coyote.http2;

import java.nio.charset.StandardCharsets;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for Section 6.7 of
 * <a href="https://tools.ietf.org/html/rfc7540">RFC 7540</a>.
 * <br>
 * The order of tests in this class is aligned with the order of the
 * requirements in the RFC.
 */
public class TestHttp2Section_6_7 extends Http2TestBase {


    @Test
    public void testPingFrame() throws Exception {
        // HTTP2 upgrade
        http2Connect();

        sendPing(0, false, "01234567".getBytes(StandardCharsets.ISO_8859_1));

        // Ping ack
        parser.readFrame(true);

        Assert.assertEquals("0-Ping-Ack-[48,49,50,51,52,53,54,55]\n", output.getTrace());
    }


    @Test
    public void testPingFrameUnexpectedAck() throws Exception {
        // HTTP2 upgrade
        http2Connect();

        sendPing(0, true, "01234567".getBytes(StandardCharsets.ISO_8859_1));
        sendPing(0, false, "76543210".getBytes(StandardCharsets.ISO_8859_1));

        // Ping ack (only for second ping)
        parser.readFrame(true);

        Assert.assertEquals("0-Ping-Ack-[55,54,53,52,51,50,49,48]\n", output.getTrace());
    }


    @Test
    public void testPingFrameNonZeroStream() throws Exception {
        // HTTP2 upgrade
        http2Connect();

        sendPing(1, false, "76543210".getBytes(StandardCharsets.ISO_8859_1));

        handleGoAwayResponse(1);
    }


    @Test
    public void testPingFrameWrongPayloadSize() throws Exception {
        // HTTP2 upgrade
        http2Connect();

        sendPing(0, false, "6543210".getBytes(StandardCharsets.ISO_8859_1));

        handleGoAwayResponse(1, Http2Error.FRAME_SIZE_ERROR);
    }
}
