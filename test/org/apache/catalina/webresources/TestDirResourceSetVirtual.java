
package org.apache.catalina.webresources;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.WebResourceSet;
import org.apache.catalina.startup.ExpandWar;
import org.apache.catalina.startup.TomcatBaseTest;

public class TestDirResourceSetVirtual extends AbstractTestResourceSet {

    private static Path tempDir;
    private static File dir1;

    @BeforeClass
    public static void before() throws IOException {
        tempDir = Files.createTempDirectory("test", new FileAttribute[0]);
        dir1 = new File(tempDir.toFile(), "dir1");
        TomcatBaseTest.recursiveCopy(new File("test/webresources/dir1").toPath(), dir1.toPath());
    }

    @AfterClass
    public static void after() {
        ExpandWar.delete(tempDir.toFile());
    }


    @Override
    public WebResourceRoot getWebResourceRoot() {
        TesterWebResourceRoot root = new TesterWebResourceRoot();
        WebResourceSet webResourceSet =
                new DirResourceSet(new TesterWebResourceRoot(), "/",
                        getBaseDir().getAbsolutePath(), "/");
        root.setMainResources(webResourceSet);

        WebResourceSet f1 = new FileResourceSet(root, "/f1.txt",
                dir1.getAbsolutePath() + "/f1.txt", "/");
        root.addPreResources(f1);

        WebResourceSet f2 = new FileResourceSet(root, "/f2.txt",
                dir1.getAbsolutePath() + "/f2.txt", "/");
        root.addPreResources(f2);

        WebResourceSet d1 = new DirResourceSet(root, "/d1",
                dir1.getAbsolutePath() + "/d1", "/");
        root.addPreResources(d1);

        WebResourceSet d2 = new DirResourceSet(root, "/d2",
                dir1.getAbsolutePath() + "/d2", "/");
        root.addPreResources(d2);

        return root;
    }

    @Override
    protected boolean isWriteable() {
        return true;
    }

    @Override
    public File getBaseDir() {
        return new File("test/webresources/dir3");
    }

    @Override
    public void testNoArgConstructor() {
        // NO-OP. Tested in TestDirResource
    }

    @Override
    protected String getNewDirName() {
        return "test-dir-05";
    }

    @Override
    protected String getNewFileNameNull() {
        return "test-null-05";
    }

    @Override
    protected String getNewFileName() {
        return "test-file-05";
    }
}
