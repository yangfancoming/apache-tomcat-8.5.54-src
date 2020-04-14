
package org.apache.catalina.storeconfig;

import java.io.PrintWriter;

import org.apache.catalina.deploy.NamingResourcesImpl;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

/**
 * store server.xml GlobalNamingResource.
 */
public class GlobalNamingResourcesSF extends StoreFactoryBase {
    private static Log log = LogFactory.getLog(GlobalNamingResourcesSF.class);

    /*
     * Store with NamingResource Factory
     *
     * @see org.apache.catalina.storeconfig.IStoreFactory#store(java.io.PrintWriter,
     *      int, java.lang.Object)
     */
    @Override
    public void store(PrintWriter aWriter, int indent, Object aElement)
            throws Exception {

        if (aElement instanceof NamingResourcesImpl) {

            StoreDescription elementDesc = getRegistry().findDescription(
                    NamingResourcesImpl.class.getName()
                            + ".[GlobalNamingResources]");

            if (elementDesc != null) {
                getStoreAppender().printIndent(aWriter, indent + 2);
                getStoreAppender().printOpenTag(aWriter, indent + 2, aElement,
                        elementDesc);
                NamingResourcesImpl resources = (NamingResourcesImpl) aElement;
                StoreDescription resourcesdesc = getRegistry().findDescription(
                        NamingResourcesImpl.class.getName());
                if (resourcesdesc != null) {
                    resourcesdesc.getStoreFactory().store(aWriter, indent + 2,
                            resources);
                } else {
                    if(log.isWarnEnabled())
                        log.warn("Can't find NamingResources Store Factory!");
                }

                getStoreAppender().printIndent(aWriter, indent + 2);
                getStoreAppender().printCloseTag(aWriter, elementDesc);
            } else {
                if (log.isWarnEnabled())
                    log.warn("Descriptor for element" + aElement.getClass()
                            + " not configured!");
            }
        } else {
            if (log.isWarnEnabled())
                log.warn("wrong element " + aElement.getClass());

        }
    }
}

