
package org.apache.catalina.ha.session;

import java.io.Serializable;

import org.apache.catalina.SessionListener;

/**
 * This is a marker interface used to indicate an implementation of
 * {@link SessionListener} that should be replicated with the session across the
 * cluster.
 */
public interface ReplicatedSessionListener extends SessionListener, Serializable {
}
