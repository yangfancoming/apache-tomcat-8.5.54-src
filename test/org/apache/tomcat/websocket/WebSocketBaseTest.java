
package org.apache.tomcat.websocket;

import org.junit.After;
import org.junit.Assert;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.servlets.DefaultServlet;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.TomcatBaseTest;
import org.apache.tomcat.websocket.server.WsContextListener;

public abstract class WebSocketBaseTest extends TomcatBaseTest {

    protected Tomcat startServer(
            final Class<? extends WsContextListener> configClass)
            throws LifecycleException {

        Tomcat tomcat = getTomcatInstance();
        // No file system docBase required
        Context ctx = tomcat.addContext("", null);
        ctx.addApplicationListener(configClass.getName());
        Tomcat.addServlet(ctx, "default", new DefaultServlet());
        ctx.addServletMappingDecoded("/", "default");

        tomcat.start();
        return tomcat;
    }


    @After
    public void checkBackgroundProcessHasStopped() throws Exception {
        // Need to stop Tomcat to ensure background processed have been stopped.
        getTomcatInstance().stop();

        // Make sure the background process has stopped. In some test
        // environments it will continue to run and break other tests that check
        // it has stopped.
        int count = 0;
        // 5s should be plenty here but Gump can be a lot slower so allow 60s.
        while (count < 600) {
            if (BackgroundProcessManager.getInstance().getProcessCount() == 0) {
                break;
            }
            Thread.sleep(100);
            count++;
        }

        try {
            Assert.assertEquals(0, BackgroundProcessManager.getInstance().getProcessCount());
        } finally {
            // Ensure the next test is not affected
            BackgroundProcessManager.getInstance().shutdown();
        }
    }
}
