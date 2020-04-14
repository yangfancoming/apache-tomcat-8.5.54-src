
package org.apache.jasper.compiler;

import javax.servlet.ServletContext;

import org.apache.tomcat.JarScanner;
import org.apache.tomcat.util.scan.StandardJarScanner;

/**
 * Provide a mechanism for Jasper to obtain a reference to the JarScanner
 * implementation.
 */
public class JarScannerFactory {

    private JarScannerFactory() {
        // Don't want any instances so hide the default constructor.
    }

    /**
     * Obtain the {@link JarScanner} associated with the specified {@link
     * ServletContext}. It is obtained via a context parameter.
     * @param ctxt The Servlet context
     * @return a scanner instance
     */
    public static JarScanner getJarScanner(ServletContext ctxt) {
        JarScanner jarScanner =
            (JarScanner) ctxt.getAttribute(JarScanner.class.getName());
        if (jarScanner == null) {
            ctxt.log(Localizer.getMessage("jsp.warning.noJarScanner"));
            jarScanner = new StandardJarScanner();
        }
        return jarScanner;
    }

}
