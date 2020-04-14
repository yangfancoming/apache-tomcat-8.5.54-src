
package org.apache.coyote.http2;

import java.io.IOException;

import org.junit.Test;

public class TestHttp2Section_3_5 extends Http2TestBase {

    @Test(expected=IOException.class)
    public void testNoConnectionPreface() throws Exception {
        enableHttp2();
        configureAndStartWebApplication();
        openClientConnection();
        doHttpUpgrade();
        // Should send client preface here
        sendPing();
        // Send several pings else server will block waiting for the client
        // preface which is longer than a single ping.
        sendPing();
        sendPing();
        validateHttp2InitialResponse();
    }
}
