
package org.apache.catalina.webresources;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.WebResource;
import org.apache.catalina.WebResourceSet;

public class TestResourceJars {

    @Test
    public void testNonStaticResources() {
        File empty = new File("test/webresources/dir3");
        File jar = new File("test/webresources/non-static-resources.jar");

        TesterWebResourceRoot root = new TesterWebResourceRoot();

        // Use empty dir for root of web app.
        WebResourceSet webResourceSet = new DirResourceSet(root, "/", empty.getAbsolutePath(), "/");
        root.setMainResources(webResourceSet);

        // If this JAR was in a web application, this is equivalent to how it
        // would be added
        JarResourceSet test =
                new JarResourceSet(root, "/", jar.getAbsolutePath(), "/META-INF/resources");
        test.setStaticOnly(true);
        root.addJarResources(test);

        WebResource resource = root.getClassLoaderResource("/org/apache/tomcat/unittest/foo.txt");

        Assert.assertFalse(resource.exists());
    }
}
