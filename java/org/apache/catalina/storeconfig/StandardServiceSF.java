

package org.apache.catalina.storeconfig;

import java.io.PrintWriter;

import org.apache.catalina.Engine;
import org.apache.catalina.Executor;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardService;

/**
 * Store server.xml Element Service and all children
 */
public class StandardServiceSF extends StoreFactoryBase {

    /**
     * Store the specified service element children.
     *
     * @param aWriter Current output writer
     * @param indent Indentation level
     * @param aService Service to store
     * @param parentDesc The element description
     * @throws Exception Configuration storing error
     */
    @Override
    public void storeChildren(PrintWriter aWriter, int indent, Object aService,
            StoreDescription parentDesc) throws Exception {
        if (aService instanceof StandardService) {
            StandardService service = (StandardService) aService;
            // Store nested <Listener> elements
            LifecycleListener listeners[] = ((Lifecycle) service)
                    .findLifecycleListeners();
            storeElementArray(aWriter, indent, listeners);

            // Store nested <Executor> elements
            Executor[] executors = service.findExecutors();
            storeElementArray(aWriter, indent, executors);

            Connector connectors[] = service.findConnectors();
            storeElementArray(aWriter, indent, connectors);

            // Store nested <Engine> element
            Engine container = service.getContainer();
            if (container != null) {
                StoreDescription elementDesc = getRegistry().findDescription(container.getClass());
                if (elementDesc != null) {
                    IStoreFactory factory = elementDesc.getStoreFactory();
                    factory.store(aWriter, indent, container);
                }
            }
        }
    }
}