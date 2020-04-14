
package org.apache.catalina.loader;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.TomcatBaseTest;
import org.apache.catalina.webresources.StandardRoot;

public class TestVirtualWebappLoader extends TomcatBaseTest {

    @Test
    public void testModified() throws Exception {
        WebappLoader loader = new WebappLoader();
        Assert.assertNull(loader.getClassLoader());
        Assert.assertFalse(loader.modified());
    }

    @Test
    public void testStartInternal() throws Exception {
        Tomcat tomcat = getTomcatInstance();

        File appDir = new File("test/webapp");
        StandardContext ctx = (StandardContext) tomcat.addContext("",
                appDir.getAbsolutePath());


        WebappLoader loader = new WebappLoader();

        loader.setContext(ctx);
        ctx.setLoader(loader);

        ctx.setResources(new StandardRoot(ctx));
        ctx.resourcesStart();

        File f1 = new File("test/webapp-fragments/WEB-INF/lib");
        ctx.getResources().createWebResourceSet(
                WebResourceRoot.ResourceSetType.POST, "/WEB-INF/lib",
                f1.getAbsolutePath(), null, "/");

        loader.start();
        String[] repos = loader.getLoaderRepositories();
        Assert.assertEquals(4,repos.length);
        loader.stop();

        repos = loader.getLoaderRepositories();
        Assert.assertEquals(0, repos.length);

        // no leak
        loader.start();
        repos = loader.getLoaderRepositories();
        Assert.assertEquals(4,repos.length);

        // clear loader
        ctx.setLoader(null);
        // see tearDown()!
        tomcat.start();
    }
}
