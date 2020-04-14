
package org.apache.catalina.webresources;

import java.io.File;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.TomcatBaseTest;

public class TestCachedResource extends TomcatBaseTest {

    // https://bz.apache.org/bugzilla/show_bug.cgi?id=63872
    @Test
    public void testUrlFileFromDirectory() throws Exception {

        Tomcat tomcat = getTomcatInstance();
        File docBase = new File("test/webresources/dir1");
        Context ctx = tomcat.addWebapp("/test", docBase.getAbsolutePath());
        tomcat.start();

        WebResourceRoot root = ctx.getResources();

        URL d1 = root.getResource("/d1").getURL();

        URL d1f1 = new URL(d1, "d1-f1.txt");

        try (InputStream is = d1f1.openStream()) {
            Assert.assertNotNull(is);
        }
    }


    // https://bz.apache.org/bugzilla/show_bug.cgi?id=63970
    @Test
    public void testCachedJarUrlConnection() throws Exception {

        Tomcat tomcat = getTomcatInstance();
        File docBase = new File("test/webresources/war-url-connection.war");
        Context ctx = tomcat.addWebapp("/test", docBase.getAbsolutePath());
        tomcat.start();

        WebResourceRoot root = ctx.getResources();

        // WAR contains a resources JAR so this should return a JAR URL
        URL webinf = root.getResource("/index.html").getURL();

        Assert.assertEquals("jar", webinf.getProtocol());
        JarURLConnection jarConn = null;
        try {
            jarConn = (JarURLConnection) webinf.openConnection();
        } catch (ClassCastException e) {
            // Ignore
        }
        Assert.assertNotNull(jarConn);
    }


    @Test
    public void testDirectoryListingsPackedWar() throws Exception {

        Tomcat tomcat = getTomcatInstance();
        File docBase = new File("test/webresources/war-url-connection.war");
        Context ctx = tomcat.addWebapp("/test", docBase.getAbsolutePath());
        ((StandardHost) tomcat.getHost()).setUnpackWARs(false);
        tomcat.start();

        WebResourceRoot root = ctx.getResources();

        URL d1 = root.getResource("/").getURL();

        try (InputStream is = d1.openStream()) {
            Assert.assertNotNull(is);
        }
    }


    @Test
    public void testDirectoryListingsWar() throws Exception {

        Tomcat tomcat = getTomcatInstance();
        File docBase = new File("test/webresources/war-url-connection.war");
        Context ctx = tomcat.addWebapp("/test", docBase.getAbsolutePath());
        tomcat.start();

        WebResourceRoot root = ctx.getResources();

        URL d1 = root.getResource("/").getURL();

        try (InputStream is = d1.openStream()) {
            Assert.assertNotNull(is);
        }
    }


    @Test
    public void testDirectoryListingsDir() throws Exception {

        Tomcat tomcat = getTomcatInstance();
        File docBase = new File("test/webresources/dir1");
        Context ctx = tomcat.addWebapp("/test", docBase.getAbsolutePath());
        tomcat.start();

        WebResourceRoot root = ctx.getResources();

        URL d1 = root.getResource("/d1").getURL();

        try (InputStream is = d1.openStream()) {
            Assert.assertNotNull(is);
        }
    }
}
