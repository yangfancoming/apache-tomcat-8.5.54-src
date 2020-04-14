
package org.apache.tomcat.util.buf;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.webresources.TomcatURLStreamHandlerFactory;

public abstract class TesterUriUtilBase {

    private final String separator;

    protected TesterUriUtilBase(String separator) {
        this.separator = separator;
        TomcatURLStreamHandlerFactory.register();
        System.setProperty("org.apache.tomcat.util.buf.UriUtil.WAR_SEPARATOR", separator);
    }


    @Test
    public void testBuildJarUrl01() throws MalformedURLException {
        File jarFile = new File("/patha/pathb!/pathc");
        String result = UriUtil.buildJarUrl(jarFile).toString();

        int index = result.indexOf("!/");
        Assert.assertEquals(result, result.length() - 2, index);
    }


    @Test
    public void testBuildJarUrl02() throws MalformedURLException {
        File jarFile = new File("/patha/pathb*/pathc");
        String result = UriUtil.buildJarUrl(jarFile).toString();

        int index = result.indexOf("!/");
        Assert.assertEquals(result,  result.length() - 2, index);

        index = result.indexOf("*/");
        Assert.assertEquals(result, -1, index);
    }


    @Test
    public void testBuildJarUrl03() throws MalformedURLException {
        File jarFile = new File("/patha/pathb^/pathc");
        String result = UriUtil.buildJarUrl(jarFile).toString();

        int index = result.indexOf("!/");
        Assert.assertEquals(result, result.length() - 2, index);

        index = result.indexOf("^/");
        Assert.assertEquals(result, -1, index);
    }


    @Test
    public void testBuildJarUrl04() throws MalformedURLException {
        File jarFile = new File("/patha/pathb" + separator + "/pathc");
        String result = UriUtil.buildJarUrl(jarFile).toString();

        int index = result.indexOf("!/");
        Assert.assertEquals(result, result.length() - 2, index);

        index = result.indexOf(separator + "/");
        Assert.assertEquals(result, -1, index);
    }


    @Test
    public void testWarToJar01() throws MalformedURLException {
        doTestWarToJar("^");
    }


    @Test
    public void testWarToJar02() throws MalformedURLException {
        doTestWarToJar("*");
    }


    @Test
    public void testWarToJar03() throws MalformedURLException {
        doTestWarToJar(separator);
    }


    private void doTestWarToJar(String separator) throws MalformedURLException {
        URL warUrl = new URL("war:file:/external/path" + separator + "/internal/path");
        URL jarUrl = UriUtil.warToJar(warUrl);
        Assert.assertEquals("jar:file:/external/path!/internal/path", jarUrl.toString());
    }


    // @Test /* Uncomment to test performance for different implementations. */
    public void performanceTestBuildJarUrl() throws MalformedURLException {
        File jarFile = new File("/patha/pathb^/pathc");

        URL url = null;

        int count = 1000000;

        // Warm up
        for (int i = 0; i < count / 10; i++) {
            url = UriUtil.buildJarUrl(jarFile);
        }

        // Test
        long start = System.nanoTime();
        for (int i = 0; i < count / 10; i++) {
            url = UriUtil.buildJarUrl(jarFile);
        }
        long duration = System.nanoTime() - start;

        System.out.println("[" + count + "] iterations took [" +
                duration + "] ns for [" + url + "]");
    }
}
