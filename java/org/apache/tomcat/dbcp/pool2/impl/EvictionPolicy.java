
package org.apache.tomcat.dbcp.pool2.impl;

import org.apache.tomcat.dbcp.pool2.PooledObject;

/**
 * To provide a custom eviction policy (i.e. something other than {@link
 * DefaultEvictionPolicy} for a pool, users must provide an implementation of
 * this interface that provides the required eviction policy.
 *
 * @param <T> the type of objects in the pool
 *
 * @since 2.0
 */
public interface EvictionPolicy<T> {

    /**
     * This method is called to test if an idle object in the pool should be
     * evicted or not.
     *
     * @param config    The pool configuration settings related to eviction
     * @param underTest The pooled object being tested for eviction
     * @param idleCount The current number of idle objects in the pool including
     *                      the object under test
     * @return <code>true</code> if the object should be evicted, otherwise
     *             <code>false</code>
     */
    boolean evict(EvictionConfig config, PooledObject<T> underTest, int idleCount);
}
