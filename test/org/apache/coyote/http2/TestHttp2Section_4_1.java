
package org.apache.coyote.http2;

import java.nio.ByteBuffer;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for Section 4.1 of
 * <a href="https://tools.ietf.org/html/rfc7540">RFC 7540</a>.
 * <br>
 * The order of tests in this class is aligned with the order of the
 * requirements in the RFC.
 */
public class TestHttp2Section_4_1 extends Http2TestBase {

    private static final byte[] UNKNOWN_FRAME = new byte[] {
        0x00, 0x00, 0x00, 0x7F, 0x00, 0x00, 0x00, 0x00, 0x00 };

    // TODO: Tests for over-sized frames. Better located in tests for section 6?


    @Test
    public void testUnknownFrameType() throws Exception {
        http2Connect();
        os.write(UNKNOWN_FRAME);
        os.flush();
        sendSimpleGetRequest(3);
        readSimpleGetResponse();
        Assert.assertEquals(getSimpleResponseTrace(3), output.getTrace());
    }


    // TODO: Tests for unexpected flags. Better located in tests for section 6?


    @Test
    public void testReservedBitIgnored() throws Exception {
        // HTTP2 upgrade
        http2Connect();

        // Build the simple request
        byte[] frameHeader = new byte[9];
        ByteBuffer headersPayload = ByteBuffer.allocate(128);
        buildSimpleGetRequest(frameHeader, headersPayload, null, 3);

        // Tweak the header to set the reserved bit
        frameHeader[5] = (byte) (frameHeader[5] | 0x80);

        // Process the request
        writeFrame(frameHeader, headersPayload);

        readSimpleGetResponse();
        Assert.assertEquals(getSimpleResponseTrace(3), output.getTrace());
    }
}
