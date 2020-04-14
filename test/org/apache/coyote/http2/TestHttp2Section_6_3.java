
package org.apache.coyote.http2;

import java.nio.ByteBuffer;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for Section 6.3 of
 * <a href="https://tools.ietf.org/html/rfc7540">RFC 7540</a>.
 * <br>
 * The order of tests in this class is aligned with the order of the
 * requirements in the RFC.
 */
public class TestHttp2Section_6_3 extends Http2TestBase {

    @Test
    public void testPriorityFrameOnStreamZero() throws Exception {
        // HTTP2 upgrade
        http2Connect();

        sendPriority(0, 1, 15);

        handleGoAwayResponse(1);
    }


    @Test
    public void testPriorityFrameBetweenHeaderFrames() throws Exception {
        // HTTP2 upgrade
        http2Connect();

        // Part 1
        byte[] frameHeader = new byte[9];
        ByteBuffer headersPayload = ByteBuffer.allocate(128);
        buildSimpleGetRequestPart1(frameHeader, headersPayload, 3);
        writeFrame(frameHeader, headersPayload);

        sendPriority(5, 3, 15);

        handleGoAwayResponse(1, Http2Error.COMPRESSION_ERROR);
    }


    @Test
    public void testPriorityFrameWrongLength() throws Exception {
        // HTTP2 upgrade
        http2Connect();

        byte[] priorityFrame = new byte[10];
        // length
        ByteUtil.setThreeBytes(priorityFrame, 0, 1);
        // type
        priorityFrame[3] = FrameType.PRIORITY.getIdByte();
        // No flags
        // Stream ID
        ByteUtil.set31Bits(priorityFrame, 5, 3);

        // Payload - left as zero

        os.write(priorityFrame);
        os.flush();

        // Read reset frame
        parser.readFrame(true);

        Assert.assertEquals("3-RST-[" + Http2Error.FRAME_SIZE_ERROR.getCode() + "]\n",
                output.getTrace());
    }
}
