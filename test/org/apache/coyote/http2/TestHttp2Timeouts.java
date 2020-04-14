
package org.apache.coyote.http2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestHttp2Timeouts extends Http2TestBase {

    @Override
    @Before
    public void http2Connect() throws Exception {
        super.http2Connect();
    }


    /*
     * Simple request won't fill buffer so timeout will occur in Tomcat internal
     * code during response completion.
     */
    @Test
    public void testClientWithEmptyWindow() throws Exception {
        sendSettings(0, false, new SettingValue(Setting.INITIAL_WINDOW_SIZE.getId(), 0));
        sendSimpleGetRequest(3);

        // Settings
        parser.readFrame(false);
        // Headers
        parser.readFrame(false);

        output.clearTrace();

        parser.readFrame(false);
        Assert.assertEquals("3-RST-[11]\n", output.getTrace());
    }


    /*
     * Large request will fill buffer so timeout will occur in application code
     * during response write (when Tomcat commits the response and flushes the
     * buffer as a result of the buffer filling).
     */
    @Test
    public void testClientWithEmptyWindowLargeResponse() throws Exception {
        sendSettings(0, false, new SettingValue(Setting.INITIAL_WINDOW_SIZE.getId(), 0));
        sendLargeGetRequest(3);

        // Settings
        parser.readFrame(false);
        // Headers
        parser.readFrame(false);

        output.clearTrace();

        parser.readFrame(false);
        Assert.assertEquals("3-RST-[11]\n", output.getTrace());
    }


    /*
     * Timeout with app reading request body directly.
     */
    @Test
    public void testClientPostsNoBody() throws Exception {
        sendSimplePostRequest(3,  null,  false);

        // Headers
        parser.readFrame(false);
        output.clearTrace();

        parser.readFrame(false);

        Assert.assertEquals("3-RST-[11]\n", output.getTrace());
    }


    /*
     * Timeout with app processing parameters.
     */
    @Test
    public void testClientPostsNoParameters() throws Exception {
        sendParameterPostRequest(3, null, null, 10, false);

        // Headers
        parser.readFrame(false);
        output.clearTrace();

        parser.readFrame(false);

        Assert.assertEquals("3-RST-[11]\n", output.getTrace());
    }
}
