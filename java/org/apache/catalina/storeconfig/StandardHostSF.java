

package org.apache.catalina.storeconfig;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.Cluster;
import org.apache.catalina.Container;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.Realm;
import org.apache.catalina.Valve;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.ha.ClusterValve;

/**
 * Store server.xml Element Host
 */
public class StandardHostSF extends StoreFactoryBase {

    /**
     * Store the specified Host properties and children
     * (Listener,Alias,Realm,Valve,Cluster, Context)
     *
     * @param aWriter
     *            PrintWriter to which we are storing
     * @param indent
     *            Number of spaces to indent this element
     * @param aHost
     *            Host whose properties are being stored
     *
     * @exception Exception
     *                if an exception occurs while storing
     */
    @Override
    public void storeChildren(PrintWriter aWriter, int indent, Object aHost,
            StoreDescription parentDesc) throws Exception {
        if (aHost instanceof StandardHost) {
            StandardHost host = (StandardHost) aHost;
            // Store nested <Listener> elements
            LifecycleListener listeners[] = ((Lifecycle) host)
                    .findLifecycleListeners();
            storeElementArray(aWriter, indent, listeners);

            // Store nested <Alias> elements
            String aliases[] = host.findAliases();
            getStoreAppender().printTagArray(aWriter, "Alias", indent + 2,
                    aliases);

            // Store nested <Realm> element
            Realm realm = host.getRealm();
            if (realm != null) {
                Realm parentRealm = null;
                if (host.getParent() != null) {
                    parentRealm = host.getParent().getRealm();
                }
                if (realm != parentRealm) {
                    storeElement(aWriter, indent, realm);
                }
            }

            // Store nested <Valve> elements
            Valve valves[] = host.getPipeline().getValves();
            if(valves != null && valves.length > 0 ) {
                List<Valve> hostValves = new ArrayList<>() ;
                for(int i = 0 ; i < valves.length ; i++ ) {
                    if(!( valves[i] instanceof ClusterValve))
                        hostValves.add(valves[i]);
                }
                storeElementArray(aWriter, indent, hostValves.toArray());
            }

            // store all <Cluster> elements
            Cluster cluster = host.getCluster();
            if (cluster != null) {
                Cluster parentCluster = null;
                if (host.getParent() != null) {
                    parentCluster = host.getParent().getCluster();
                }
                if (cluster != parentCluster) {
                    storeElement(aWriter, indent, cluster);
                }
            }

            // store all <Context> elements
            Container children[] = host.findChildren();
            storeElementArray(aWriter, indent, children);
        }
    }

}