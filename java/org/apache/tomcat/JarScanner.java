
package org.apache.tomcat;

import javax.servlet.ServletContext;

/**
 * Scans a web application and classloader hierarchy for JAR files. Uses
 * include TLD scanning and web-fragment.xml scanning. Uses a call-back
 * mechanism so the caller can process each JAR found.
 */
public interface JarScanner {

    /**
     * Scan the provided ServletContext and classloader for JAR files. Each JAR
     * file found will be passed to the callback handler to be processed.
     *
     * @param scanType      The type of JAR scan to perform. This is passed to
     *                          the filter which uses it to determine how to
     *                          filter the results
     * @param context       The ServletContext - used to locate and access
     *                      WEB-INF/lib
     * @param callback      The handler to process any JARs found
     */
    public void scan(JarScanType scanType, ServletContext context,
            JarScannerCallback callback);

    public JarScanFilter getJarScanFilter();

    public void setJarScanFilter(JarScanFilter jarScanFilter);
}
