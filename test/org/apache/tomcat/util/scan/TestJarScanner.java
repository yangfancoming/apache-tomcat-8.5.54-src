
package org.apache.tomcat.util.scan;

import java.util.StringTokenizer;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.startup.TomcatBaseTest;

public class TestJarScanner extends TomcatBaseTest {

    @Test
    public void testJarsToSkipFormat() {

        String jarList = System.getProperty(Constants.SKIP_JARS_PROPERTY);
        Assert.assertNotNull("Jar skip list is null", jarList);
        Assert.assertFalse("Jar skip list is empty", jarList.isEmpty());
        StringTokenizer tokenizer = new StringTokenizer(jarList, ",");
        String token;
        while (tokenizer.hasMoreElements()) {
            token = tokenizer.nextToken();
            Assert.assertTrue("Token \"" + token + "\" does not end with \".jar\"",
                    token.endsWith(".jar"));
            Assert.assertEquals("Token \"" + token + "\" contains sub string \".jar\"" +
                    " or separator \",\" is missing",
                    token.length() - ".jar".length(),
                    token.indexOf(".jar"));
        }
    }
}
