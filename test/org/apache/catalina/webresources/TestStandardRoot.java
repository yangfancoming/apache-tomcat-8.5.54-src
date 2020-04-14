
package org.apache.catalina.webresources;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.webresources.StandardRoot.BaseLocation;

public class TestStandardRoot {

    private static final File file;
    private static final String fileUrl;

    static {
        file = new File("/foo");
        String url = null;
        try {
            url = file.toURI().toURL().toExternalForm();
        } catch (MalformedURLException e) {
            // Ignore
        }
        fileUrl = url;
    }

    @Test
    public void testBaseLocation01() throws Exception {
        doTestBaseLocation(new URL (fileUrl),
                file.getAbsolutePath(), null);
    }

    @Test
    public void testBaseLocation02() throws Exception {
        doTestBaseLocation(new URL ("jar:" + fileUrl + "!/"),
                file.getAbsolutePath(), null);
    }

    @Test
    public void testBaseLocation03() throws Exception {
        doTestBaseLocation(new URL ("jar:" + fileUrl + "!/bar"),
                file.getAbsolutePath(), "bar");
    }

    @Test
    public void testBaseLocation04() throws Exception {
        doTestBaseLocation(new URL ("jar:" + fileUrl + "!/bar/bar"),
                file.getAbsolutePath(), "bar/bar");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testBaseLocation05() throws Exception {
        doTestBaseLocation(new URL ("http://localhost:8080/foo"),
                null, null);
    }

    private void doTestBaseLocation(URL url, String expectedBasePath,
            String expectedArchivePath) {
        BaseLocation baseLocation = new BaseLocation(url);
        Assert.assertEquals(expectedBasePath, baseLocation.getBasePath());
        Assert.assertEquals(expectedArchivePath, baseLocation.getArchivePath());
    }
}
