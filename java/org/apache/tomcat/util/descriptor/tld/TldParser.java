
package org.apache.tomcat.util.descriptor.tld;

import java.io.IOException;
import java.io.InputStream;
import java.security.AccessController;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.apache.tomcat.util.descriptor.Constants;
import org.apache.tomcat.util.descriptor.DigesterFactory;
import org.apache.tomcat.util.descriptor.XmlErrorHandler;
import org.apache.tomcat.util.digester.Digester;
import org.apache.tomcat.util.digester.RuleSet;
import org.apache.tomcat.util.security.PrivilegedGetTccl;
import org.apache.tomcat.util.security.PrivilegedSetTccl;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Parses a Tag Library Descriptor.
 */
public class TldParser {
    private final Log log = LogFactory.getLog(TldParser.class); // must not be static
    private final Digester digester;

    public TldParser(boolean namespaceAware, boolean validation,
            boolean blockExternal) {
        this(namespaceAware, validation, new TldRuleSet(), blockExternal);
    }

    public TldParser(boolean namespaceAware, boolean validation, RuleSet ruleSet,
            boolean blockExternal) {
        digester = DigesterFactory.newDigester(
                validation, namespaceAware, ruleSet, blockExternal);
    }

    public TaglibXml parse(TldResourcePath path) throws IOException, SAXException {
        ClassLoader original;
        if (Constants.IS_SECURITY_ENABLED) {
            PrivilegedGetTccl pa = new PrivilegedGetTccl();
            original = AccessController.doPrivileged(pa);
        } else {
            original = Thread.currentThread().getContextClassLoader();
        }
        try (InputStream is = path.openStream()) {
            if (Constants.IS_SECURITY_ENABLED) {
                PrivilegedSetTccl pa = new PrivilegedSetTccl(TldParser.class.getClassLoader());
                AccessController.doPrivileged(pa);
            } else {
                Thread.currentThread().setContextClassLoader(TldParser.class.getClassLoader());
            }
            XmlErrorHandler handler = new XmlErrorHandler();
            digester.setErrorHandler(handler);

            TaglibXml taglibXml = new TaglibXml();
            digester.push(taglibXml);

            InputSource source = new InputSource(path.toExternalForm());
            source.setByteStream(is);
            digester.parse(source);
            if (!handler.getWarnings().isEmpty() || !handler.getErrors().isEmpty()) {
                handler.logFindings(log, source.getSystemId());
                if (!handler.getErrors().isEmpty()) {
                    // throw the first to indicate there was an error during processing
                    throw handler.getErrors().iterator().next();
                }
            }
            return taglibXml;
        } finally {
            digester.reset();
            if (Constants.IS_SECURITY_ENABLED) {
                PrivilegedSetTccl pa = new PrivilegedSetTccl(original);
                AccessController.doPrivileged(pa);
            } else {
                Thread.currentThread().setContextClassLoader(original);
            }
        }
    }

    public void setClassLoader(ClassLoader classLoader) {
        digester.setClassLoader(classLoader);
    }
}
