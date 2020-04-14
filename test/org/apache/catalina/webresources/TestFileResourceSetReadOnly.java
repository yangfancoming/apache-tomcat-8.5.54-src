
package org.apache.catalina.webresources;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import org.apache.catalina.startup.ExpandWar;
import org.apache.catalina.startup.TomcatBaseTest;

public class TestFileResourceSetReadOnly extends AbstractTestFileResourceSet {

    private static Path tempDir;
    private static File dir2;

    @BeforeClass
    public static void before() throws IOException {
        tempDir = Files.createTempDirectory("test", new FileAttribute[0]);
        dir2 = new File(tempDir.toFile(), "dir2");
        TomcatBaseTest.recursiveCopy(new File("test/webresources/dir2").toPath(), dir2.toPath());
    }

    @AfterClass
    public static void after() {
        ExpandWar.delete(tempDir.toFile());
    }


    public TestFileResourceSetReadOnly() {
        super(true);
    }

    @Override
    protected File getDir2() {
        return dir2;
    }

    @Override
    protected String getNewDirName() {
        return "test-dir-07";
    }

    @Override
    protected String getNewFileNameNull() {
        return "test-null-07";
    }

    @Override
    protected String getNewFileName() {
        return "test-file-07";
    }
}