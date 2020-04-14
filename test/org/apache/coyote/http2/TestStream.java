
package org.apache.coyote.http2;

import java.io.IOException;
import java.nio.ByteBuffer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

public class TestStream extends Http2TestBase {

    /*
     * https://bz.apache.org/bugzilla/show_bug.cgi?id=61120
     */
    @Test
    public void testPathParam() throws Exception {

        enableHttp2();

        Tomcat tomcat = getTomcatInstance();

        Context ctxt = tomcat.addContext("", null);
        Tomcat.addServlet(ctxt, "simple", new SimpleServlet());
        ctxt.addServletMappingDecoded("/simple", "simple");
        Tomcat.addServlet(ctxt, "pathparam", new PathParam());
        ctxt.addServletMappingDecoded("/pathparam", "pathparam");

        tomcat.start();

        openClientConnection();
        doHttpUpgrade();
        sendClientPreface();
        validateHttp2InitialResponse();

        byte[] frameHeader = new byte[9];
        ByteBuffer headersPayload = ByteBuffer.allocate(128);
        buildGetRequest(frameHeader, headersPayload, null, 3,
                "/pathparam;jsessionid=" + PathParam.EXPECTED_SESSION_ID);
        writeFrame(frameHeader, headersPayload);

        readSimpleGetResponse();

        Assert.assertEquals(
                "3-HeadersStart\n" +
                "3-Header-[:status]-[200]\n" +
                "3-Header-[content-type]-[text/plain;charset=UTF-8]\n" +
                "3-Header-[content-length]-[2]\n" +
                "3-Header-[date]-[Wed, 11 Nov 2015 19:18:42 GMT]\n" +
                "3-HeadersEnd\n" +
                "3-Body-2\n" +
                "3-EndOfStream\n", output.getTrace());
    }


    private static final class PathParam extends HttpServlet {

        private static final long serialVersionUID = 1L;

        public static final String EXPECTED_SESSION_ID = "0123456789ABCDEF";

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");

            if (EXPECTED_SESSION_ID.equals(request.getRequestedSessionId())) {
                response.getWriter().write("OK");
            } else {
                response.getWriter().write("FAIL");
            }
        }
    }
}
