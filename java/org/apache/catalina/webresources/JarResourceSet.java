
package org.apache.catalina.webresources;

import java.util.jar.JarEntry;
import java.util.jar.Manifest;

import org.apache.catalina.WebResource;
import org.apache.catalina.WebResourceRoot;

/**
 * Represents a {@link org.apache.catalina.WebResourceSet} based on a JAR file.
 */
public class JarResourceSet extends AbstractSingleArchiveResourceSet {

    /**
     * A no argument constructor is required for this to work with the digester.
     */
    public JarResourceSet() {
    }


    /**
     * Creates a new {@link org.apache.catalina.WebResourceSet} based on a JAR
     * file.
     *
     * @param root          The {@link WebResourceRoot} this new
     *                          {@link org.apache.catalina.WebResourceSet} will
     *                          be added to.
     * @param webAppMount   The path within the web application at which this
     *                          {@link org.apache.catalina.WebResourceSet} will
     *                          be mounted.
     * @param base          The absolute path to the JAR file on the file system
     *                          from which the resources will be served.
     * @param internalPath  The path within this new {@link
     *                          org.apache.catalina.WebResourceSet} where
     *                          resources will be served from. E.g. for a
     *                          resource JAR, this would be "META-INF/resources"
     *
     * @throws IllegalArgumentException if the webAppMount or internalPath is
     *         not valid (valid paths must start with '/')
     */
    public JarResourceSet(WebResourceRoot root, String webAppMount, String base,
            String internalPath) throws IllegalArgumentException {
        super(root, webAppMount, base, internalPath);
    }


    @Override
    protected WebResource createArchiveResource(JarEntry jarEntry,
            String webAppPath, Manifest manifest) {
        return new JarResource(this, webAppPath, getBaseUrlString(), jarEntry);
    }
}
