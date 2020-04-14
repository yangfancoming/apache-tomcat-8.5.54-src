
package org.apache.tomcat.util.descriptor.tld;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class TestImplicitTldParser {
    private TldParser parser;

    @Before
    public void init() {
        parser = new TldParser(true, true, new ImplicitTldRuleSet(), true);
    }

    @Test
    public void testImplicitTldGood() throws Exception {
        TaglibXml xml = parse("test/tld/implicit-good.tld");
        Assert.assertEquals("1.0", xml.getTlibVersion());
        Assert.assertEquals("2.1", xml.getJspVersion());
        Assert.assertEquals("Ignored", xml.getShortName());
    }

    @Test(expected=SAXParseException.class)
    public void testImplicitTldBad() throws Exception {
        TaglibXml xml = parse("test/tld/implicit-bad.tld");
        Assert.assertEquals("1.0", xml.getTlibVersion());
        Assert.assertEquals("2.1", xml.getJspVersion());
        Assert.assertEquals("Ignored", xml.getShortName());
    }

    private TaglibXml parse(String pathname) throws IOException, SAXException {
        File file = new File(pathname);
        TldResourcePath path = new TldResourcePath(file.toURI().toURL(), null);
        return parser.parse(path);
    }

}
