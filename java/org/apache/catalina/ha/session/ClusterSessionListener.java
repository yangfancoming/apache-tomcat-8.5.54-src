

package org.apache.catalina.ha.session;

import java.util.Map;

import org.apache.catalina.ha.ClusterListener;
import org.apache.catalina.ha.ClusterManager;
import org.apache.catalina.ha.ClusterMessage;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.apache.tomcat.util.res.StringManager;

/**
 * Receive replicated SessionMessage form other cluster node.
 * @author Peter Rossbach
 */
public class ClusterSessionListener extends ClusterListener {

    private static final Log log =
        LogFactory.getLog(ClusterSessionListener.class);
    private static final StringManager sm = StringManager.getManager(ClusterSessionListener.class);

    //--Constructor---------------------------------------------

    public ClusterSessionListener() {
        // NO-OP
    }

    //--Logic---------------------------------------------------

    /**
     * Callback from the cluster, when a message is received, The cluster will
     * broadcast it invoking the messageReceived on the receiver.
     *
     * @param myobj
     *            ClusterMessage - the message received from the cluster
     */
    @Override
    public void messageReceived(ClusterMessage myobj) {
        if (myobj instanceof SessionMessage) {
            SessionMessage msg = (SessionMessage) myobj;
            String ctxname = msg.getContextName();
            //check if the message is a EVT_GET_ALL_SESSIONS,
            //if so, wait until we are fully started up
            Map<String,ClusterManager> managers = cluster.getManagers() ;
            if (ctxname == null) {
                for (Map.Entry<String, ClusterManager> entry :
                        managers.entrySet()) {
                    if (entry.getValue() != null)
                        entry.getValue().messageDataReceived(msg);
                    else {
                        //this happens a lot before the system has started
                        // up
                        if (log.isDebugEnabled())
                            log.debug(sm.getString("clusterSessionListener.noManager", entry.getKey()));
                    }
                }
            } else {
                ClusterManager mgr = managers.get(ctxname);
                if (mgr != null) {
                    mgr.messageDataReceived(msg);
                } else {
                    if (log.isWarnEnabled())
                        log.warn(sm.getString("clusterSessionListener.noManager", ctxname));

                    // A no context manager message is replied in order to avoid
                    // timeout of GET_ALL_SESSIONS sync phase.
                    if (msg.getEventType() == SessionMessage.EVT_GET_ALL_SESSIONS) {
                        SessionMessage replymsg = new SessionMessageImpl(ctxname,
                                SessionMessage.EVT_ALL_SESSION_NOCONTEXTMANAGER,
                                null, "NO-CONTEXT-MANAGER","NO-CONTEXT-MANAGER-" + ctxname);
                        cluster.send(replymsg, msg.getAddress());
                    }
                }
            }
        }
    }

    /**
     * Accept only SessionMessage
     *
     * @param msg
     *            ClusterMessage
     * @return boolean - returns true to indicate that messageReceived should be
     *         invoked. If false is returned, the messageReceived method will
     *         not be invoked.
     */
    @Override
    public boolean accept(ClusterMessage msg) {
        return msg instanceof SessionMessage;
    }
}

