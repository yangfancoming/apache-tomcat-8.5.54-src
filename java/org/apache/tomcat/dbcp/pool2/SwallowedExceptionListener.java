
package org.apache.tomcat.dbcp.pool2;

/**
 * Pools that unavoidably swallow exceptions may be configured with an instance
 * of this listener so the user may receive notification of when this happens.
 * The listener should not throw an exception when called but pools calling
 * listeners should protect themselves against exceptions anyway.
 *
 * @since 2.0
 */
public interface SwallowedExceptionListener {

    /**
     * This method is called every time the implementation unavoidably swallows
     * an exception.
     *
     * @param e The exception that was swallowed
     */
    void onSwallowException(Exception e);
}
