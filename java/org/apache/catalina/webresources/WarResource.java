
package org.apache.catalina.webresources;

import java.util.jar.JarEntry;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.apache.tomcat.util.buf.UriUtil;

/**
 * Represents a single resource (file or directory) that is located within a
 * WAR.
 */
public class WarResource extends AbstractSingleArchiveResource {

    private static final Log log = LogFactory.getLog(WarResource.class);


    public WarResource(AbstractArchiveResourceSet archiveResourceSet, String webAppPath,
            String baseUrl, JarEntry jarEntry) {
        super(archiveResourceSet, webAppPath, "war:" + baseUrl + UriUtil.getWarSeparator(),
                jarEntry, baseUrl);
    }


    @Override
    protected Log getLog() {
        return log;
    }
}
