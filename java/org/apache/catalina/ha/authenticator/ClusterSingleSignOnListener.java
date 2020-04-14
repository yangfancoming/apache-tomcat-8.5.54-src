
package org.apache.catalina.ha.authenticator;

import org.apache.catalina.authenticator.SingleSignOnListener;
import org.apache.catalina.ha.session.ReplicatedSessionListener;

/**
 * Cluster extension of {@link SingleSignOnListener} that simply adds the marker
 * interface {@link ReplicatedSessionListener} which allows the listener to be
 * replicated across the cluster along with the session.
 */
public class ClusterSingleSignOnListener extends SingleSignOnListener implements
        ReplicatedSessionListener {

    private static final long serialVersionUID = 1L;

    public ClusterSingleSignOnListener(String ssoId) {
        super(ssoId);
    }
}
