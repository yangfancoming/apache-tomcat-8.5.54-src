
package org.apache.coyote.http2;

import java.nio.ByteBuffer;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

public class TestHttp2UpgradeHandler extends Http2TestBase {

    // https://bz.apache.org/bugzilla/show_bug.cgi?id=60970
    @Test
    public void testLargeHeader() throws Exception {
        enableHttp2();

        Tomcat tomcat = getTomcatInstance();

        Context ctxt = tomcat.addContext("", null);
        Tomcat.addServlet(ctxt, "simple", new SimpleServlet());
        ctxt.addServletMappingDecoded("/simple", "simple");
        Tomcat.addServlet(ctxt, "large", new LargeHeaderServlet());
        ctxt.addServletMappingDecoded("/large", "large");

        tomcat.start();

        openClientConnection();
        doHttpUpgrade();
        sendClientPreface();
        validateHttp2InitialResponse();

        byte[] frameHeader = new byte[9];
        ByteBuffer headersPayload = ByteBuffer.allocate(128);
        buildGetRequest(frameHeader, headersPayload, null, 3, "/large");
        writeFrame(frameHeader, headersPayload);

        // Headers
        parser.readFrame(true);
        parser.readFrame(true);
        // Body
        parser.readFrame(true);

        Assert.assertEquals(
                "3-HeadersStart\n" +
                "3-Header-[:status]-[200]\n" +
                "3-Header-[x-ignore]-[...]\n" +
                "3-Header-[content-type]-[text/plain;charset=UTF-8]\n" +
                "3-Header-[content-length]-[2]\n" +
                "3-Header-[date]-[Wed, 11 Nov 2015 19:18:42 GMT]\n" +
                "3-HeadersEnd\n" +
                "3-Body-2\n" +
                "3-EndOfStream\n", output.getTrace());
    }

}
