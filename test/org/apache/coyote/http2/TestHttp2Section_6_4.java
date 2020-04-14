
package org.apache.coyote.http2;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for Section 6.4 of
 * <a href="https://tools.ietf.org/html/rfc7540">RFC 7540</a>.
 * <br>
 * The order of tests in this class is aligned with the order of the
 * requirements in the RFC.
 */
public class TestHttp2Section_6_4 extends Http2TestBase {

    @Test
    public void testResetFrameOnStreamZero() throws Exception {
        // HTTP2 upgrade
        http2Connect();

        sendRst(0, Http2Error.NO_ERROR.getCode());

        handleGoAwayResponse(1);
    }


    @Test
    public void testResetFrameOnIdleStream() throws Exception {
        // HTTP2 upgrade
        http2Connect();

        sendPriority(3, 0, 15);
        sendRst(3, Http2Error.NO_ERROR.getCode());

        handleGoAwayResponse(1);
    }


    @Test
    public void testResetFrameWrongLength() throws Exception {
        // HTTP2 upgrade
        http2Connect();

        byte[] resetFrame = new byte[10];
        // length
        ByteUtil.setThreeBytes(resetFrame, 0, 1);
        // type
        resetFrame[3] = FrameType.RST.getIdByte();
        // No flags
        // Stream ID
        ByteUtil.set31Bits(resetFrame, 5, 3);

        // Payload - left as zero

        os.write(resetFrame);
        os.flush();

        // Read reset frame
        parser.readFrame(true);

        Assert.assertEquals("3-RST-[" + Http2Error.FRAME_SIZE_ERROR.getCode() + "]\n",
                output.getTrace());
    }
}
