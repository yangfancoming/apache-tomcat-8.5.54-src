

package org.apache.catalina.storeconfig;

import java.io.PrintWriter;

import org.apache.tomcat.JarScanFilter;
import org.apache.tomcat.JarScanner;

/**
 * Store server.xml Element JarScanner
 */
public class JarScannerSF extends StoreFactoryBase {

    /**
     * Store the specified JarScanner properties and children
     * (JarScannerFilter)
     *
     * @param aWriter
     *            PrintWriter to which we are storing
     * @param indent
     *            Number of spaces to indent this element
     * @param aJarScanner
     *            JarScanner whose properties are being stored
     *
     * @exception Exception
     *                if an exception occurs while storing
     */
    @Override
    public void storeChildren(PrintWriter aWriter, int indent, Object aJarScanner,
            StoreDescription parentDesc) throws Exception {
        if (aJarScanner instanceof JarScanner) {
            JarScanner jarScanner = (JarScanner) aJarScanner;
            // Store nested <JarScanFilter> element
            JarScanFilter jarScanFilter = jarScanner.getJarScanFilter();
            if (jarScanFilter != null) {
                storeElement(aWriter, indent, jarScanFilter);
            }
        }
    }

}