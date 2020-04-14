
package org.apache.tomcat.dbcp.pool2;

/**
 * This interface may be implemented by an object pool to enable clients
 * (primarily those clients that wrap pools to provide pools with extended
 * features) to provide additional information to the pool relating to object
 * using allowing more informed decisions and reporting to be made regarding
 * abandoned objects.
 *
 * @param <T>   The type of object provided by the pool.
 *
 * @since 2.0
 */
public interface UsageTracking<T> {

    /**
     * This method is called every time a pooled object is used to enable the pool to
     * better track borrowed objects.
     *
     * @param pooledObject  The object that is being used
     */
    void use(T pooledObject);
}
