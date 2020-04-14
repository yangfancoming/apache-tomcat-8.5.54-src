

package org.apache.catalina.storeconfig;

import java.io.PrintWriter;

import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.Service;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.deploy.NamingResourcesImpl;

/**
 * Store server.xml Server element and children (
 * Listener,GlobalNamingResource,Service)
 */
public class StandardServerSF extends StoreFactoryBase {

    /**
     * Store the specified Server properties.
     *
     * @param aWriter
     *            PrintWriter to which we are storing
     * @param indent
     *            Number of spaces to indent this element
     * @param aServer
     *            Object to be stored
     *
     * @exception Exception
     *                if an exception occurs while storing
     * @see org.apache.catalina.storeconfig.IStoreFactory#store(java.io.PrintWriter,
     *      int, java.lang.Object)
     */
    @Override
    public void store(PrintWriter aWriter, int indent, Object aServer)
            throws Exception {
        storeXMLHead(aWriter);
        super.store(aWriter, indent, aServer);
    }

    /**
     * Store the specified server element children.
     *
     * @param aWriter Current output writer
     * @param indent Indentation level
     * @param aObject Server to store
     * @param parentDesc The element description
     * @throws Exception Configuration storing error
     */
    @Override
    public void storeChildren(PrintWriter aWriter, int indent, Object aObject,
            StoreDescription parentDesc) throws Exception {
        if (aObject instanceof StandardServer) {
            StandardServer server = (StandardServer) aObject;
            // Store nested <Listener> elements
            LifecycleListener listeners[] = ((Lifecycle) server)
                    .findLifecycleListeners();
            storeElementArray(aWriter, indent, listeners);
            /*LifecycleListener listener = null;
            for (int i = 0; listener == null && i < listeners.length; i++)
                if (listeners[i] instanceof ServerLifecycleListener)
                    listener = listeners[i];
            if (listener != null) {
                StoreDescription elementDesc = getRegistry()
                        .findDescription(
                                StandardServer.class.getName()
                                        + ".[ServerLifecycleListener]");
                if (elementDesc != null) {
                    elementDesc.getStoreFactory().store(aWriter, indent,
                            listener);
                }
            }*/
            // Store nested <GlobalNamingResources> element
            NamingResourcesImpl globalNamingResources = server
                    .getGlobalNamingResources();
            StoreDescription elementDesc = getRegistry().findDescription(
                    NamingResourcesImpl.class.getName()
                            + ".[GlobalNamingResources]");
            if (elementDesc != null) {
                elementDesc.getStoreFactory().store(aWriter, indent,
                        globalNamingResources);
            }
            // Store nested <Service> elements
            Service services[] = server.findServices();
            storeElementArray(aWriter, indent, services);
        }
    }

}
