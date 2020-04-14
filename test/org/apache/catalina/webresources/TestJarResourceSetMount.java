
package org.apache.catalina.webresources;

import java.io.File;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.WebResourceSet;

public class TestJarResourceSetMount extends AbstractTestResourceSetMount {

    @Override
    public WebResourceRoot getWebResourceRoot() {
        File f = new File("test/webresources/dir1.jar");
        TesterWebResourceRoot root = new TesterWebResourceRoot();
        WebResourceSet webResourceSet =
                new JarResourceSet(root, getMount(), f.getAbsolutePath(), "/");
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
    protected String getNewDirName() {
        return "test-dir-10";
    }

    @Override
    protected String getNewFileNameNull() {
        return "test-null-10";
    }

    @Override
    protected String getNewFileName() {
        return "test-file-10";
    }
}
