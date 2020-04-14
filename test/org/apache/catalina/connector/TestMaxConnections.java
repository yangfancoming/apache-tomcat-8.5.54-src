
package org.apache.catalina.connector;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;

import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.SimpleHttpClient;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.TomcatBaseTest;

public class TestMaxConnections extends TomcatBaseTest {
    private static final int MAX_CONNECTIONS = 3;
    public static final int soTimeout = 5000;
    public static final int connectTimeout = 1000;

    @Test
    public void testConnector() throws Exception {
        init();
        Assume.assumeFalse("This feature is not available for NIO2 (BZ58103)",
                getTomcatInstance().getConnector().getProtocolHandlerClassName().contains("Nio2"));
        ConnectThread[] t = new ConnectThread[10];
        for (int i=0; i<t.length; i++) {
            t[i] = new ConnectThread();
            t[i].setName("ConnectThread["+i+"]");
        }
        for (int i=0; i<t.length; i++) {
            t[i].start();
            Thread.sleep(50);
        }
        for (int i=0; i<t.length; i++) {
            t[i].join();
        }

        Assert.assertEquals(MAX_CONNECTIONS, SimpleServlet.getMaxConnections());
    }

    private class ConnectThread extends Thread {
        @Override
        public void run() {
            try {
                TestClient client = new TestClient();
                client.doHttp10Request();
            } catch (Exception x) {
                // NO-OP. Some connections are expected to fail.
            }
        }
    }


    private synchronized void init() throws Exception {
        Tomcat tomcat = getTomcatInstance();
        StandardContext root = (StandardContext) tomcat.addContext("", SimpleHttpClient.TEMP_DIR);
        root.setUnloadDelay(soTimeout);
        Tomcat.addServlet(root, "Simple", new SimpleServlet());
        root.addServletMappingDecoded("/test", "Simple");
        Assert.assertTrue(tomcat.getConnector().setProperty("maxKeepAliveRequests", "1"));
        Assert.assertTrue(tomcat.getConnector().setProperty("maxThreads", "10"));
        Assert.assertTrue(tomcat.getConnector().setProperty("connectionTimeout", "20000"));
        Assert.assertTrue(tomcat.getConnector().setProperty("keepAliveTimeout", "50000"));
        Assert.assertTrue(tomcat.getConnector().setProperty("maxConnections", Integer.toString(MAX_CONNECTIONS)));
        Assert.assertTrue(tomcat.getConnector().setProperty("acceptCount", "1"));
        tomcat.start();
    }

    private class TestClient extends SimpleHttpClient {

        private void doHttp10Request() throws Exception {
            setPort(getPort());

            long start = System.currentTimeMillis();
            // Open connection
            connect(connectTimeout,soTimeout);

            // Send request in two parts
            String[] request = new String[1];
            request[0] =
                "GET /test HTTP/1.0" + CRLF + CRLF;
            setRequest(request);
            boolean passed = false;
            processRequest(false); // blocks until response has been read
            long stop = System.currentTimeMillis();
            log.info(Thread.currentThread().getName()+" Request complete:"+(stop-start)+" ms.");
            passed = (this.readLine()==null);
            // Close the connection
            disconnect();
            reset();
            Assert.assertTrue(passed);
        }

        @Override
        public boolean isResponseBodyOK() {
            return true;
        }
    }


    private static class SimpleServlet extends HttpServlet {

        private static final long serialVersionUID = 1L;

        private static int currentConnections = 0;
        private static int maxConnections = 0;

        @Override
        protected void service(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException {

            increment();

            System.out.println("Processing thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(TestMaxConnections.soTimeout*4/5);
            } catch (InterruptedException x) {

            }
            resp.setContentLength(0);
            resp.flushBuffer();

            decrement();
        }

        private static synchronized void increment() {
            currentConnections++;
            if (currentConnections > maxConnections) {
                maxConnections = currentConnections;
            }
        }

        private static synchronized void decrement() {
            currentConnections--;
        }


        public static synchronized int getMaxConnections() {
            return maxConnections;
        }
    }
}
