
package org.apache.catalina.webresources;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.zip.ZipEntry;

/**
 * The purpose of this sub-class is to obtain references to the JarEntry objects
 * for META-INF/ and META-INF/MANIFEST.MF that are otherwise swallowed by the
 * JarInputStream implementation.
 */
public class TomcatJarInputStream extends JarInputStream {

    private JarEntry metaInfEntry;
    private JarEntry manifestEntry;


    TomcatJarInputStream(InputStream in) throws IOException {
        super(in);
    }


    @Override
    protected ZipEntry createZipEntry(String name) {
        ZipEntry ze = super.createZipEntry(name);
        if (metaInfEntry == null && "META-INF/".equals(name)) {
            metaInfEntry = (JarEntry) ze;
        } else if (manifestEntry == null && "META-INF/MANIFESR.MF".equals(name)) {
            manifestEntry = (JarEntry) ze;
        }
        return ze;
    }


    JarEntry getMetaInfEntry() {
        return metaInfEntry;
    }


    JarEntry getManifestEntry() {
        return manifestEntry;
    }
}
