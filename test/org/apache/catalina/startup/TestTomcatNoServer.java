
package org.apache.catalina.startup;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.core.StandardContext;
import org.apache.tomcat.util.descriptor.DigesterFactory;
import org.apache.tomcat.util.descriptor.XmlErrorHandler;
import org.apache.tomcat.util.descriptor.web.WebRuleSet;
import org.apache.tomcat.util.descriptor.web.WebXml;
import org.apache.tomcat.util.digester.Digester;
import org.xml.sax.InputSource;


/**
 * Tests that do not require a Tomcat instance to be started.
 */
public class TestTomcatNoServer {

    @Test
    public void testDefaultMimeTypeMappings() throws Exception {
        StandardContext ctx = new StandardContext();

        Tomcat.initWebappDefaults(ctx);

        InputSource globalWebXml = new InputSource(new File("conf/web.xml").getAbsoluteFile().toURI().toString());

        WebXml webXmlDefaultFragment = new WebXml();
        webXmlDefaultFragment.setOverridable(true);
        webXmlDefaultFragment.setDistributable(true);
        webXmlDefaultFragment.setAlwaysAddWelcomeFiles(false);

        Digester digester = DigesterFactory.newDigester(true, true, new WebRuleSet(), true);
        XmlErrorHandler handler = new XmlErrorHandler();
        digester.setErrorHandler(handler);
        digester.push(webXmlDefaultFragment);
        digester.parse(globalWebXml);
        Assert.assertEquals(0, handler.getErrors().size());
        Assert.assertEquals(0, handler.getWarnings().size());

        Map<String,String> webXmlMimeMappings = webXmlDefaultFragment.getMimeMappings();

        Set<String> embeddedExtensions = new HashSet<>();
        embeddedExtensions.addAll(Arrays.asList(ctx.findMimeMappings()));

        // Find entries present in conf/web.xml that are missing in embedded
        Set<String> missingInEmbedded = new HashSet<>();
        missingInEmbedded.addAll(webXmlMimeMappings.keySet());
        missingInEmbedded.removeAll(embeddedExtensions);
        if (missingInEmbedded.size() > 0) {
            for (String missingExtension : missingInEmbedded) {
                System.out.println("Missing in embedded: [" + missingExtension +
                        "]-[" + webXmlMimeMappings.get(missingExtension) + "]");
            }
            Assert.fail("Embedded is missing [" + missingInEmbedded.size() + "] entires compared to conf/web.xml");
        }

        // Find entries present in embedded that are missing in conf/web.xml
        Set<String> missingInWebXml = new HashSet<>();
        missingInWebXml.addAll(embeddedExtensions);
        missingInWebXml.removeAll(webXmlMimeMappings.keySet());
        if (missingInWebXml.size() > 0) {
            for (String missingExtension : missingInWebXml) {
                System.out.println("Missing in embedded: [" + missingExtension +
                        "]-[" + ctx.findMimeMapping(missingExtension) + "]");
            }
            Assert.fail("Embedded is missing [" + missingInWebXml.size() + "] entires compared to conf/web.xml");
        }
    }
}
