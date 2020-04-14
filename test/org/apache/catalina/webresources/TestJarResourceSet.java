
package org.apache.catalina.webresources;

import java.io.File;

import org.junit.Test;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.WebResourceSet;

public class TestJarResourceSet extends AbstractTestResourceSet {

    @Override
    public WebResourceRoot getWebResourceRoot() {
        File f = new File("test/webresources/dir1.jar");
        TesterWebResourceRoot root = new TesterWebResourceRoot();
        WebResourceSet webResourceSet =
                new JarResourceSet(root, "/", f.getAbsolutePath(), "/");
        root.setMainResources(webResourceSet);
        return root;
    }

    @Override
    protected boolean isWriteable() {
        return false;
    }

    @Override
    public File getBaseDir() {
        return new File("test/webresources");
    }

    @Override
    @Test
    public void testNoArgConstructor() {
        @SuppressWarnings("unused")
        Object obj = new JarResourceSet();
    }

    @Override
    protected String getNewDirName() {
        return "test-dir-08";
    }

    @Override
    protected String getNewFileNameNull() {
        return "test-null-08";
    }

    @Override
    protected String getNewFileName() {
        return "test-file-08";
    }
}
