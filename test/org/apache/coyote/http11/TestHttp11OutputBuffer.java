
package org.apache.coyote.http11;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.Context;
import org.apache.catalina.startup.SimpleHttpClient;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.TomcatBaseTest;

public class TestHttp11OutputBuffer extends TomcatBaseTest {

    @Test
    public void testSendAck() throws Exception {
        Tomcat tomcat = getTomcatInstance();

        // No file system docBase required
        Context ctx = tomcat.addContext("", null);

        Tomcat.addServlet(ctx, "echo", new EchoBodyServlet());
        ctx.addServletMappingDecoded("/echo", "echo");

        tomcat.start();

        ExpectationClient client = new ExpectationClient();

        client.setPort(tomcat.getConnector().getLocalPort());
        // Expected content doesn't end with a CR-LF so if it isn't chunked make
        // sure the content length is used as reading it line-by-line will fail
        // since there is no "line".
        client.setUseContentLength(true);

        client.connect();

        client.doRequestHeaders();
        Assert.assertTrue(client.isResponse100());

        client.doRequestBody();
        Assert.assertTrue(client.isResponse200());
        Assert.assertTrue(client.isResponseBodyOK());
    }

    private static class ExpectationClient extends SimpleHttpClient {

        private static final String BODY = "foo=bar";

        public void doRequestHeaders() throws Exception {
            StringBuilder requestHeaders = new StringBuilder();
            requestHeaders.append("POST /echo HTTP/1.1").append(CRLF);
            requestHeaders.append("Host: localhost").append(CRLF);
            requestHeaders.append("Expect: 100-continue").append(CRLF);
            requestHeaders.append("Content-Type: application/x-www-form-urlencoded").append(CRLF);
            String len = Integer.toString(BODY.length());
            requestHeaders.append("Content-length: ").append(len).append(CRLF);
            requestHeaders.append(CRLF);

            setRequest(new String[] {requestHeaders.toString()});

            processRequest(false);
        }

        public void doRequestBody() throws Exception {
            setRequest(new String[] { BODY });

            processRequest(true);
        }

        @Override
        public boolean isResponseBodyOK() {
            return BODY.equals(getResponseBody());
        }
    }
}
