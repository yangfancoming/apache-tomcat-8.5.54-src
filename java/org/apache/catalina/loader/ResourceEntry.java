
package org.apache.catalina.loader;

/**
 * Resource entry.
 *
 * @author Remy Maucherat
 */
public class ResourceEntry {

    /**
     * The "last modified" time of the origin file at the time this resource
     * was loaded, in milliseconds since the epoch.
     */
    public long lastModified = -1;


    /**
     * Loaded class.
     */
    public volatile Class<?> loadedClass = null;
}

