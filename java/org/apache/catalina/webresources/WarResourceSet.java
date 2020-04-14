
package org.apache.catalina.webresources;

import java.util.jar.JarEntry;
import java.util.jar.Manifest;

import org.apache.catalina.WebResource;
import org.apache.catalina.WebResourceRoot;

/**
 * Represents a {@link org.apache.catalina.WebResourceSet} based on a WAR file.
 */
public class WarResourceSet extends AbstractSingleArchiveResourceSet {

    /**
     * A no argument constructor is required for this to work with the digester.
     */
    public WarResourceSet() {
    }


    /**
     * Creates a new {@link org.apache.catalina.WebResourceSet} based on a WAR
     * file.
     *
     * @param root          The {@link WebResourceRoot} this new
     *                          {@link org.apache.catalina.WebResourceSet} will
     *                          be added to.
     * @param webAppMount   The path within the web application at which this
     *                          {@link org.apache.catalina.WebResourceSet} will
     *                          be mounted.
     * @param base          The absolute path to the WAR file on the file system
     *                          from which the resources will be served.
     *
     * @throws IllegalArgumentException if the webAppMount is not valid (valid
     *         paths must start with '/')
     */
    public WarResourceSet(WebResourceRoot root, String webAppMount, String base)
            throws IllegalArgumentException {
        super(root, webAppMount, base, "/");
    }


    @Override
    protected WebResource createArchiveResource(JarEntry jarEntry,
            String webAppPath, Manifest manifest) {
        return new WarResource(this, webAppPath, getBaseUrlString(), jarEntry);
    }
}
