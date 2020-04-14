
package org.apache.tomcat.util.bcel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import org.apache.tomcat.Jar;
import org.apache.tomcat.util.bcel.classfile.ClassParser;
import org.apache.tomcat.util.scan.JarFactory;

public class TesterPerformance {

    private static final String JAR_LOCATION = "/tmp/jira-libs";

    @Test
    public void testClassParserPerformance() throws IOException {
        File libDir = new File(JAR_LOCATION);
        String[] libs = libDir.list();

        Assert.assertNotNull(libs);

        Set<URL> jarURLs = new HashSet<>();

        for (String lib : libs) {
            if (!lib.toLowerCase(Locale.ENGLISH).endsWith(".jar")) {
                continue;
            }
            jarURLs.add(new URL("jar:" + new File (libDir, lib).toURI().toURL().toExternalForm() + "!/"));
        }

        long duration = 0;

        for (URL jarURL : jarURLs) {
            try (Jar jar = JarFactory.newInstance(jarURL)) {
                jar.nextEntry();
                String jarEntryName = jar.getEntryName();
                while (jarEntryName != null) {
                    if (jarEntryName.endsWith(".class")) {
                        InputStream is = jar.getEntryInputStream();
                        long start = System.nanoTime();
                        ClassParser cp = new ClassParser(is);
                        cp.parse();
                        duration += System.nanoTime() - start;
                    }
                    jar.nextEntry();
                    jarEntryName = jar.getEntryName();
                }
            }
        }

        System.out.println("ClassParser performance test took: " + duration + " ns");
    }
}
