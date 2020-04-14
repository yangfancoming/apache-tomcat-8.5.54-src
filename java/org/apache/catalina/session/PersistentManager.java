
package org.apache.catalina.session;

/**
 * Implementation of the <b>Manager</b> interface that makes use of
 * a Store to swap active Sessions to disk. It can be configured to
 * achieve several different goals:
 *
 * <ul>
 * <li>Persist sessions across restarts of the Container</li>
 * <li>Fault tolerance, keep sessions backed up on disk to allow
 *     recovery in the event of unplanned restarts.</li>
 * <li>Limit the number of active sessions kept in memory by
 *     swapping less active sessions out to disk.</li>
 * </ul>
 *
 * @author Kief Morris (kief@kief.com)
 */
public final class PersistentManager extends PersistentManagerBase {

    // ----------------------------------------------------- Instance Variables

    /**
     * The descriptive name of this Manager implementation (for logging).
     */
    private static final String name = "PersistentManager";


    // ------------------------------------------------------------- Properties

    @Override
    public String getName() {
        return name;
    }
 }

