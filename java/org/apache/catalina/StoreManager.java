
package org.apache.catalina;

/**
 * PersistentManager would have been a better name but that would have clashed
 * with the implementation name.
 */
public interface StoreManager extends DistributedManager {

    /**
     * @return the Store object which manages persistent Session
     * storage for this Manager.
     */
    Store getStore();

    /**
     * Remove this Session from the active Sessions for this Manager,
     * but not from the Store. (Used by the PersistentValve)
     *
     * @param session Session to be removed
     */
    void removeSuper(Session session);
}
