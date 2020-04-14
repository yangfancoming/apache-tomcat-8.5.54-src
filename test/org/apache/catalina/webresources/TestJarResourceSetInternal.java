
package org.apache.catalina.webresources;

import java.io.File;

import org.junit.Test;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.WebResourceSet;

public class TestJarResourceSetInternal extends AbstractTestResourceSet {

    @Override
    public WebResourceRoot getWebResourceRoot() {
        File f = new File("test/webresources/dir1-internal.jar");
        TesterWebResourceRoot root = new TesterWebResourceRoot();
        WebResourceSet webResourceSet =
                new JarResourceSet(root, "/", f.getAbsolutePath(), "/dir1");
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
        return "test-dir-09";
    }

    @Override
    protected String getNewFileNameNull() {
        return "test-null-09";
    }

    @Override
    protected String getNewFileName() {
        return "test-file-09";
    }
}
