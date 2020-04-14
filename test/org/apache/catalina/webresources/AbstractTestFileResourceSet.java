
package org.apache.catalina.webresources;

import java.io.File;

import org.junit.Test;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.WebResourceSet;

public abstract class AbstractTestFileResourceSet extends AbstractTestResourceSet {

    private final boolean readOnly;

    protected AbstractTestFileResourceSet(boolean readOnly) {
        this.readOnly = readOnly;
    }

    protected abstract File getDir2();

    @Override
    public WebResourceRoot getWebResourceRoot() {
        TesterWebResourceRoot root = new TesterWebResourceRoot();
        WebResourceSet webResourceSet = new DirResourceSet(root, "/", getBaseDir().getAbsolutePath(), "/");
        webResourceSet.setReadOnly(readOnly);
        root.setMainResources(webResourceSet);

        WebResourceSet f1 = new FileResourceSet(root, "/f1.txt",
                "test/webresources/dir1/f1.txt", "/");
        f1.setReadOnly(readOnly);
        root.addPreResources(f1);

        WebResourceSet f2 = new FileResourceSet(root, "/f2.txt",
                "test/webresources/dir1/f2.txt", "/");
        f2.setReadOnly(readOnly);
        root.addPreResources(f2);

        WebResourceSet d1f1 = new FileResourceSet(root, "/d1/d1-f1.txt",
                "test/webresources/dir1/d1/d1-f1.txt", "/");
        d1f1.setReadOnly(readOnly);
        root.addPreResources(d1f1);

        WebResourceSet d2f1 = new FileResourceSet(root, "/d2/d2-f1.txt",
                "test/webresources/dir1/d2/d2-f1.txt", "/");
        d2f1.setReadOnly(readOnly);
        root.addPreResources(d2f1);

        return root;
    }

    @Override
    protected boolean isWriteable() {
        return !readOnly;
    }

    @Override
    public File getBaseDir() {
        return getDir2();
    }

    @Override
    @Test
    public void testNoArgConstructor() {
        @SuppressWarnings("unused")
        Object obj = new FileResourceSet();
    }
}
