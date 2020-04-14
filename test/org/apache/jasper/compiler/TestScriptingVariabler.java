

package org.apache.jasper.compiler;

import java.io.IOException;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.tagext.VariableInfo;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.startup.TomcatBaseTest;

public class TestScriptingVariabler extends TomcatBaseTest {

    @Test
    public void testBug42390() throws Exception {
        getTomcatInstanceTestWebapp(false, true);

        Exception e = null;
        try {
            getUrl("http://localhost:" + getPort() + "/test/bug42390.jsp");
        } catch (IOException ioe) {
            e = ioe;
        }

        // Should not fail
        Assert.assertNull(e);
    }

    public static class Bug48616aTag extends TagSupport {
        private static final long serialVersionUID = 1L;
    }

    public static class Bug48616bTag extends TagSupport {
        private static final long serialVersionUID = 1L;
    }

    public static class Bug48616bTei extends TagExtraInfo {
        /**
         * Return information about the scripting variables to be created.
         */
        @Override
        public VariableInfo[] getVariableInfo(TagData data) {
            return new VariableInfo[] {
                new VariableInfo("Test", "java.lang.String", true,
                    VariableInfo.AT_END)
            };
        }
    }

    @Test
    public void testBug48616() throws Exception {
        getTomcatInstanceTestWebapp(false, true);

        Exception e = null;
        try {
            getUrl("http://localhost:" + getPort() + "/test/bug48nnn/bug48616.jsp");
        } catch (IOException ioe) {
            e = ioe;
        }

        // Should not fail
        Assert.assertNull(e);
    }

    @Test
    public void testBug48616b() throws Exception {
        getTomcatInstanceTestWebapp(false, true);

        Exception e = null;
        try {
            getUrl("http://localhost:" + getPort() + "/test/bug48nnn/bug48616b.jsp");
        } catch (IOException ioe) {
            e = ioe;
        }

        // Should not fail
        Assert.assertNull(e);
    }
}
