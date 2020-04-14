

package org.apache.tomcat.jni;

/** PoolCallback Interface
 *
 * @author Mladen Turk
 */
public interface PoolCallback {

    /**
     * Called when the pool is destroyed or cleared
     * @return Function must return APR_SUCCESS
     */
    public int callback();
}
