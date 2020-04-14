
package javax.servlet.resources;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import org.apache.tomcat.util.descriptor.DigesterFactory;
import org.apache.tomcat.util.descriptor.XmlErrorHandler;
import org.apache.tomcat.util.descriptor.XmlIdentifiers;
import org.apache.tomcat.util.descriptor.web.WebRuleSet;
import org.apache.tomcat.util.descriptor.web.WebXml;
import org.apache.tomcat.util.digester.Digester;

public class TestSchemaValidation {

    @Test
    public void testWebapp() throws Exception {
        XmlErrorHandler handler = new XmlErrorHandler();
        Digester digester = DigesterFactory.newDigester(
                true, true, new WebRuleSet(false), true);
        digester.setErrorHandler(handler);
        digester.push(new WebXml());
        WebXml desc = (WebXml) digester.parse(
                new File("test/webapp/WEB-INF/web.xml"));
        Assert.assertEquals("3.1", desc.getVersion());
        Assert.assertEquals(0, handler.getErrors().size());
        Assert.assertEquals(0, handler.getWarnings().size());
    }

    @Test
    public void testWebapp_2_2() throws Exception {
        XmlErrorHandler handler = new XmlErrorHandler();
        Digester digester = DigesterFactory.newDigester(
                true, true, new WebRuleSet(false), true);
        digester.setErrorHandler(handler);
        digester.push(new WebXml());
        WebXml desc = (WebXml) digester.parse(
                new File("test/webapp-2.2/WEB-INF/web.xml"));
        Assert.assertEquals("2.2", desc.getVersion());
        Assert.assertEquals(XmlIdentifiers.WEB_22_PUBLIC, desc.getPublicId());
        Assert.assertEquals(0, handler.getErrors().size());
        Assert.assertEquals(0, handler.getWarnings().size());
    }

    @Test
    public void testWebapp_2_3() throws Exception {
        XmlErrorHandler handler = new XmlErrorHandler();
        Digester digester = DigesterFactory.newDigester(
                true, true, new WebRuleSet(false), true);
        digester.setErrorHandler(handler);
        digester.push(new WebXml());
        WebXml desc = (WebXml) digester.parse(
                new File("test/webapp-2.3/WEB-INF/web.xml"));
        Assert.assertEquals("2.3", desc.getVersion());
        Assert.assertEquals(XmlIdentifiers.WEB_23_PUBLIC, desc.getPublicId());
        Assert.assertEquals(0, handler.getErrors().size());
        Assert.assertEquals(0, handler.getWarnings().size());
    }

    @Test
    public void testWebapp_2_4() throws Exception {
        XmlErrorHandler handler = new XmlErrorHandler();
        Digester digester = DigesterFactory.newDigester(
                true, true, new WebRuleSet(false), true);
        digester.setErrorHandler(handler);
        digester.push(new WebXml());
        WebXml desc = (WebXml) digester.parse(
                new File("test/webapp-2.4/WEB-INF/web.xml"));
        Assert.assertEquals("2.4", desc.getVersion());
        Assert.assertEquals(0, handler.getErrors().size());
        Assert.assertEquals(0, handler.getWarnings().size());
    }

    @Test
    public void testWebapp_2_5() throws Exception {
        XmlErrorHandler handler = new XmlErrorHandler();
        Digester digester = DigesterFactory.newDigester(
                true, true, new WebRuleSet(false), true);
        digester.setErrorHandler(handler);
        digester.push(new WebXml());
        WebXml desc = (WebXml) digester.parse(
                new File("test/webapp-2.5/WEB-INF/web.xml"));
        Assert.assertEquals("2.5", desc.getVersion());
        Assert.assertEquals(0, handler.getErrors().size());
        Assert.assertEquals(0, handler.getWarnings().size());
    }

    @Test
    public void testWebapp_3_0() throws Exception {
        XmlErrorHandler handler = new XmlErrorHandler();
        Digester digester = DigesterFactory.newDigester(
                true, true, new WebRuleSet(false), true);
        digester.setErrorHandler(handler);
        digester.push(new WebXml());
        WebXml desc = (WebXml) digester.parse(
                new File("test/webapp-3.0/WEB-INF/web.xml"));
        Assert.assertEquals("3.0", desc.getVersion());
        Assert.assertEquals(0, handler.getErrors().size());
        Assert.assertEquals(0, handler.getWarnings().size());
    }

    @Test
    public void testWebapp_3_1() throws Exception {
        XmlErrorHandler handler = new XmlErrorHandler();
        Digester digester = DigesterFactory.newDigester(
                true, true, new WebRuleSet(false), true);
        digester.setErrorHandler(handler);
        digester.push(new WebXml());
        WebXml desc = (WebXml) digester.parse(
                new File("test/webapp-3.1/WEB-INF/web.xml"));
        Assert.assertEquals("3.1", desc.getVersion());
        Assert.assertEquals(0, handler.getErrors().size());
        Assert.assertEquals(0, handler.getWarnings().size());
    }
}
