
package org.apache.tomcat.util.net;

import java.io.File;
import java.io.ObjectOutputStream;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.TomcatBaseTest;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.apache.tomcat.websocket.server.WsContextListener;

public class TestSSLHostConfigIntegration extends TomcatBaseTest {

    @Test
    public void testSslHostConfigIsSerializable() throws Exception {
        Tomcat tomcat = getTomcatInstance();

        File appDir = new File(getBuildDirectory(), "webapps/examples");
        org.apache.catalina.Context ctxt  = tomcat.addWebapp(
                null, "/examples", appDir.getAbsolutePath());
        ctxt.addApplicationListener(WsContextListener.class.getName());

        TesterSupport.initSsl(tomcat);

        tomcat.start();

        SSLHostConfig[] sslHostConfigs =
                tomcat.getConnector().getProtocolHandler().findSslHostConfigs();

        boolean written = false;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            for (SSLHostConfig sslHostConfig : sslHostConfigs) {
                oos.writeObject(sslHostConfig);
                written = true;
            }
        }

        Assert.assertTrue(written);
    }
}
