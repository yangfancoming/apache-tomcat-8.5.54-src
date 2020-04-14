
package org.apache.juli;

import java.io.File;
import java.net.URLDecoder;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import org.apache.catalina.startup.LoggingBaseTest;

public class TestFileHandlerNonRotatable extends LoggingBaseTest {
    private FileHandler testHandler;

    @BeforeClass
    public static void setUpPerTestClass() throws Exception {
        LoggingBaseTest.setUpPerTestClass();

        System.setProperty("java.util.logging.manager",
                "org.apache.juli.ClassLoaderLogManager");
        String configLoggingPath = TestFileHandlerNonRotatable.class
                .getResource("logging-non-rotatable.properties")
                .getFile();
        System.setProperty("java.util.logging.config.file",
                URLDecoder.decode(configLoggingPath, java.nio.charset.StandardCharsets.UTF_8.toString()));
    }

    @Override
    @After
    public void tearDown() throws Exception {
        if (testHandler != null) {
            testHandler.close();
        }
        super.tearDown();
    }

    @Test
    public void testBug61232() throws Exception {
        testHandler = new FileHandler(this.getTemporaryDirectory().toString(),
                "juli.", ".log");

        File logFile = new File(this.getTemporaryDirectory(), "juli.log");
        Assert.assertTrue(logFile.exists());
    }

    @Test
    public void testCustomSuffixWithoutSeparator() throws Exception {
        testHandler = new FileHandler(this.getTemporaryDirectory().toString(),
                "juli.", "log");

        File logFile = new File(this.getTemporaryDirectory(), "juli.log");
        Assert.assertTrue(logFile.exists());
    }

    @Test
    public void testCustomPrefixWithoutSeparator() throws Exception {
        testHandler = new FileHandler(this.getTemporaryDirectory().toString(),
                "juli", ".log");

        File logFile = new File(this.getTemporaryDirectory(), "juli.log");
        Assert.assertTrue(logFile.exists());
    }
}