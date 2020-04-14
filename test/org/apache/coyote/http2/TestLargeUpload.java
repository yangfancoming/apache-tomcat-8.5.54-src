
package org.apache.coyote.http2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.coyote.http11.AbstractHttp11Protocol;

public class TestLargeUpload extends Http2TestBase {

    int bodySize = 13107;
    int bodyCount = 5;

    volatile int read = 0;
    CountDownLatch done = new CountDownLatch(1);

    @Test
    public void testLargePostRequest() throws Exception {

        http2Connect(true);

        ((AbstractHttp11Protocol<?>) http2Protocol.getHttp11Protocol()).setAllowedTrailerHeaders(TRAILER_HEADER_NAME);

        byte[] headersFrameHeader = new byte[9];
        ByteBuffer headersPayload = ByteBuffer.allocate(128);
        byte[] dataFrameHeader = new byte[9];
        ByteBuffer dataPayload = ByteBuffer.allocate(bodySize);
        byte[] trailerFrameHeader = new byte[9];
        ByteBuffer trailerPayload = ByteBuffer.allocate(256);

        buildPostRequest(headersFrameHeader, headersPayload, false, dataFrameHeader, dataPayload,
                null, trailerFrameHeader, trailerPayload, 3);

        // Write the headers
        writeFrame(headersFrameHeader, headersPayload);
        // Body
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (int i = 0; i < bodyCount; i++) {
            baos.write(dataFrameHeader);
            baos.write(dataPayload.array(), dataPayload.arrayOffset(), dataPayload.limit());
        }
        os.write(baos.toByteArray());
        os.flush();

        // Trailers
        writeFrame(trailerFrameHeader, trailerPayload);

        done.await();
        Assert.assertEquals(Integer.valueOf(bodySize * bodyCount), Integer.valueOf(read));

    }


    @Override
    protected void configureAndStartWebApplication() throws LifecycleException {
        Tomcat tomcat = getTomcatInstance();

        // Retain '/simple' url-pattern since it enables code re-use
        Context ctxt = tomcat.addContext("", null);
        Tomcat.addServlet(ctxt, "read", new DataReadServlet());
        ctxt.addServletMappingDecoded("/simple", "read");

        tomcat.start();
    }

    private class DataReadServlet extends SimpleServlet {

        private static final long serialVersionUID = 1L;

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            byte[] buf = new byte[8192];
            InputStream is = req.getInputStream();
            int n = is.read(buf);
            try {
                while (n > 0) {
                    read += n;
                    n = is.read(buf);
                }
            } finally {
                done.countDown();
            }
            if (read != bodySize * bodyCount) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } else {
                resp.setStatus(HttpServletResponse.SC_OK);
            }
        }
    }
}
