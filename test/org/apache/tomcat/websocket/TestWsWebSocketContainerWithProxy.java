
package org.apache.tomcat.websocket;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;

@Ignore // Additional infrastructure is required to run this test
public class TestWsWebSocketContainerWithProxy extends TestWsWebSocketContainer {

    @BeforeClass
    public static void init() {
        // Set the system properties for an HTTP proxy on 192.168.0.100:80
        // I used an httpd instance configured as an open forward proxy for this
        // Update the IP/hostname as required
        System.setProperty("http.proxyHost", "192.168.0.100");
        System.setProperty("http.proxyPort", "80");
        System.setProperty("http.nonProxyHosts", "");
    }

    @Before
    public void setPort() {
        // With httpd 2.2, AllowCONNECT requires fixed ports. From 2.4, a range
        // can be used.
        getTomcatInstance().getConnector().setPort(8080);
        Assert.assertTrue(getTomcatInstance().getConnector().setProperty("address","0.0.0.0"));
    }

    @Override
    protected String getHostName() {
        // The IP/hostname where the tests are running. The proxy will connect
        // back to this expecting to find the Tomcat instance created by the
        // unit test.
        return "192.168.0.200";
    }
}
