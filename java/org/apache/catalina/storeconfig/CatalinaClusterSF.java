

package org.apache.catalina.storeconfig;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.LifecycleListener;
import org.apache.catalina.Valve;
import org.apache.catalina.ha.CatalinaCluster;
import org.apache.catalina.ha.ClusterDeployer;
import org.apache.catalina.ha.ClusterListener;
import org.apache.catalina.ha.ClusterManager;
import org.apache.catalina.ha.tcp.SimpleTcpCluster;
import org.apache.catalina.tribes.Channel;

/**
 * Generate Cluster Element with Membership,Sender,Receiver,Deployer and
 * ReplicationValve
 */
public class CatalinaClusterSF extends StoreFactoryBase {

    /**
     * Store the specified Cluster children.
     *
     * @param aWriter
     *            PrintWriter to which we are storing
     * @param indent
     *            Number of spaces to indent this element
     * @param aCluster
     *            Cluster whose properties are being stored
     *
     * @exception Exception
     *                if an exception occurs while storing
     */
    @Override
    public void storeChildren(PrintWriter aWriter, int indent, Object aCluster,
            StoreDescription parentDesc) throws Exception {
        if (aCluster instanceof CatalinaCluster) {
            CatalinaCluster cluster = (CatalinaCluster) aCluster;
            if (cluster instanceof SimpleTcpCluster) {
                SimpleTcpCluster tcpCluster = (SimpleTcpCluster) cluster;
                // Store nested <Manager> element
                ClusterManager manager = tcpCluster.getManagerTemplate();
                if (manager != null) {
                    storeElement(aWriter, indent, manager);
                }
            }
            // Store nested <Channel> element
            Channel channel = cluster.getChannel();
            if (channel != null) {
                storeElement(aWriter, indent, channel);
            }
            // Store nested <Deployer> element
            ClusterDeployer deployer = cluster.getClusterDeployer();
            if (deployer != null) {
                storeElement(aWriter, indent, deployer);
            }
            // Store nested <Valve> element
            // ClusterValve are not store at Hosts element, see
            Valve valves[] = cluster.getValves();
            storeElementArray(aWriter, indent, valves);

            if (aCluster instanceof SimpleTcpCluster) {
                // Store nested <Listener> elements
                LifecycleListener listeners[] = ((SimpleTcpCluster)cluster).findLifecycleListeners();
                storeElementArray(aWriter, indent, listeners);
                // Store nested <ClusterListener> elements
                ClusterListener mlisteners[] = ((SimpleTcpCluster)cluster).findClusterListeners();
                List<ClusterListener> clusterListeners = new ArrayList<>();
                for (ClusterListener clusterListener : mlisteners) {
                    if (clusterListener != deployer) {
                        clusterListeners.add(clusterListener);
                    }
                }
                storeElementArray(aWriter, indent, clusterListeners.toArray());
            }
        }
    }
}