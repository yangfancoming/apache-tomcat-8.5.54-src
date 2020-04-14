
package org.apache.coyote.http2;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for Section 6.5 of
 * <a href="https://tools.ietf.org/html/rfc7540">RFC 7540</a>.
 * <br>
 * The order of tests in this class is aligned with the order of the
 * requirements in the RFC.
 */
public class TestHttp2Section_6_5 extends Http2TestBase {


    @Test
    public void testSettingsFrameNonEmptAck() throws Exception {
        // HTTP2 upgrade
        http2Connect();

        sendSettings(0, true, new SettingValue(1,1));

        handleGoAwayResponse(1, Http2Error.FRAME_SIZE_ERROR);
    }


    @Test
    public void testSettingsFrameNonZeroStream() throws Exception {
        // HTTP2 upgrade
        http2Connect();

        sendPriority(3, 0, 15);
        sendSettings(3, true, new SettingValue(1,1));

        handleGoAwayResponse(1);
    }


    @Test
    public void testSettingsFrameWrongLength() throws Exception {
        // HTTP2 upgrade
        http2Connect();

        byte[] resetFrame = new byte[10];
        // length
        ByteUtil.setThreeBytes(resetFrame, 0, 1);
        // type
        resetFrame[3] = FrameType.SETTINGS.getIdByte();
        // No flags
        // Stream ID 0

        // Payload - left as zero

        os.write(resetFrame);
        os.flush();

        handleGoAwayResponse(1, Http2Error.FRAME_SIZE_ERROR);
    }


    // Need to test sending push promise when push promise support is disabled

    @Test
    public void testSettingsFrameInvalidPushSetting() throws Exception {
        // HTTP2 upgrade
        http2Connect();

        sendSettings(0, false, new SettingValue(0x2,0x2));

        handleGoAwayResponse(1);
    }


    @Test
    public void testSettingsFrameInvalidWindowSizeSetting() throws Exception {
        // HTTP2 upgrade
        http2Connect();

        sendSettings(0, false, new SettingValue(0x4,1 << 31));

        handleGoAwayResponse(1, Http2Error.FLOW_CONTROL_ERROR);
    }


    @Test
    public void testSettingsFrameInvalidMaxFrameSizeSetting() throws Exception {
        // HTTP2 upgrade
        http2Connect();

        sendSettings(0, false, new SettingValue(0x5,1 << 31));

        handleGoAwayResponse(1);
    }


    @Test
    public void testSettingsUnknownSetting() throws Exception {
        // HTTP2 upgrade
        http2Connect();

        sendSettings(0, false, new SettingValue(0xFF,0xFF));

        // Ack
        parser.readFrame(true);

        Assert.assertTrue(output.getTrace(), output.getTrace().startsWith(
                "0-Settings-Ack"));
    }

    // delayed ACKs. Requires an API (TBD) for applications to send settings.
}
