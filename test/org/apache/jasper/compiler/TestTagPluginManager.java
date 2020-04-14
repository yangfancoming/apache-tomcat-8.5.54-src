
package org.apache.jasper.compiler;

import javax.servlet.ServletContext;
import javax.servlet.jsp.tagext.TagFileInfo;
import javax.servlet.jsp.tagext.TagInfo;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.TomcatBaseTest;

/**
 * Test case for {@link TagPluginManager}.
 */
public class TestTagPluginManager extends TomcatBaseTest {

    private static TagInfo tagInfo = new TagInfo("ATag",
            "org.apache.jasper.compiler.ATagSupport", "", "", null, null, null);

    @Test
    public void testBug54240() throws Exception {
        Tomcat tomcat = getTomcatInstanceTestWebapp(false, true);



        ServletContext context = ((Context) tomcat.getHost().findChildren()[0]).getServletContext();

        TagPluginManager manager = new TagPluginManager(context);

        Node.Nodes nodes = new Node.Nodes();
        Node.CustomTag c = new Node.CustomTag("test:ATag", "test", "ATag",
                "http://tomcat.apache.org/jasper", null, null, null, null, null,
                new TagFileInfo("ATag", "http://tomcat.apache.org/jasper",
                        tagInfo));
        c.setTagHandlerClass(TesterTag.class);
        nodes.add(c);
        manager.apply(nodes, null, null);

        Node n = nodes.getNode(0);
        Assert.assertNotNull(n);
        Assert.assertTrue(n instanceof Node.CustomTag);

        Node.CustomTag t = (Node.CustomTag)n;
        Assert.assertNotNull(t.getAtSTag());

        Node.Nodes sTag = c.getAtSTag();
        Node scriptlet = sTag.getNode(0);
        Assert.assertNotNull(scriptlet);
        Assert.assertTrue(scriptlet instanceof Node.Scriptlet);
        Node.Scriptlet s = (Node.Scriptlet)scriptlet;
        Assert.assertEquals("//Just a comment", s.getText());
    }
}
