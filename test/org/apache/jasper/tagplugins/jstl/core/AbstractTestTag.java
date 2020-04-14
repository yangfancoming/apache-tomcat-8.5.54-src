
package org.apache.jasper.tagplugins.jstl.core;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;

import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.TomcatBaseTest;
import org.apache.catalina.webresources.StandardRoot;

public abstract class AbstractTestTag extends TomcatBaseTest {

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        Tomcat tomcat = getTomcatInstance();

        File appDir = new File("test/webapp");
        Context ctx = tomcat.addWebapp(null, "/test", appDir.getAbsolutePath());

        ctx.setResources(new StandardRoot(ctx));

        // Add the JSTL (we need the TLD)
        File lib = new File("webapps/examples/WEB-INF/lib");
        ctx.getResources().createWebResourceSet(
                WebResourceRoot.ResourceSetType.POST, "/WEB-INF/lib",
                lib.getAbsolutePath(), null, "/");

        // Configure the use of the plug-in rather than the standard impl
        File plugin = new File(
                "java/org/apache/jasper/tagplugins/jstl/tagPlugins.xml");
        Assert.assertTrue(plugin.isFile());
        ctx.getResources().createWebResourceSet(
                WebResourceRoot.ResourceSetType.POST, "/WEB-INF/tagPlugins.xml",
                plugin.getAbsolutePath(), null, "/");

        tomcat.start();
    }
}
