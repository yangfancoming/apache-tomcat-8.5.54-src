
package org.apache.coyote.http2;

import java.nio.ByteBuffer;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for Section 6.2 of
 * <a href="https://tools.ietf.org/html/rfc7540">RFC 7540</a>.
 * <br>
 * The order of tests in this class is aligned with the order of the
 * requirements in the RFC.
 */
public class TestHttp2Section_6_2 extends Http2TestBase {

    @Test
    public void testHeaderFrameOnStreamZero() throws Exception {
        // HTTP2 upgrade
        http2Connect();

        // Part 1
        byte[] frameHeader = new byte[9];
        ByteBuffer headersPayload = ByteBuffer.allocate(128);
        buildSimpleGetRequestPart1(frameHeader, headersPayload, 0);
        writeFrame(frameHeader, headersPayload);

        handleGoAwayResponse(1);
    }


    @Test
    public void testHeaderFrameWithPadding() throws Exception {
        http2Connect();

        byte[] padding= new byte[8];

        sendSimpleGetRequest(3, padding);
        readSimpleGetResponse();
        Assert.assertEquals(getSimpleResponseTrace(3), output.getTrace());
    }


    @Test
    public void testHeaderFrameWithNonZeroPadding() throws Exception {
        http2Connect();

        byte[] padding= new byte[8];
        padding[4] = 1;

        sendSimpleGetRequest(3, padding);

        handleGoAwayResponse(1);
    }


    @Test
    public void testHeaderFrameTooMuchPadding() throws Exception {
        http2Connect();

        byte[] headerFrame = new byte[10];

        // Header
        // length
        ByteUtil.setThreeBytes(headerFrame, 0, 1);
        headerFrame[3] = FrameType.HEADERS.getIdByte();
        // flags 8 (padded)
        headerFrame[4] = 0x08;
        // stream 3
        ByteUtil.set31Bits(headerFrame, 5, 3);
        // payload (pad length of 1)
        headerFrame[9] = 1;

        os.write(headerFrame);
        os.flush();

        handleGoAwayResponse(1);
    }


    @Test
    public void testHeaderFrameWithZeroLengthPadding() throws Exception {
        http2Connect();

        byte[] padding= new byte[0];

        sendSimpleGetRequest(3, padding);
        readSimpleGetResponse();
        Assert.assertEquals(getSimpleResponseTrace(3), output.getTrace());
    }
}
