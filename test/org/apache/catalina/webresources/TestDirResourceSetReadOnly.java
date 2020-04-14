
package org.apache.catalina.webresources;

import java.io.File;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.WebResourceSet;

public class TestDirResourceSetReadOnly extends AbstractTestResourceSet {

    @Override
    public WebResourceRoot getWebResourceRoot() {
        TesterWebResourceRoot root = new TesterWebResourceRoot();
        WebResourceSet webResourceSet =
                new DirResourceSet(root, "/", getBaseDir().getAbsolutePath(), "/");
        webResourceSet.setReadOnly(true);
        root.setMainResources(webResourceSet);
        return root;
    }

    @Override
    protected boolean isWriteable() {
        return false;
    }

    @Override
    public File getBaseDir() {
        return new File("test/webresources/dir1");
    }

    @Override
    public void testNoArgConstructor() {
        // NO-OP. Tested in TestDirResource
    }

    @Override
    protected String getNewDirName() {
        return "test-dir-04";
    }

    @Override
    protected String getNewFileNameNull() {
        return "test-null-04";
    }

    @Override
    protected String getNewFileName() {
        return "test-file-04";
    }
}
