
package org.apache.tomcat.dbcp.dbcp2;

import org.apache.juli.logging.Log;
import org.apache.tomcat.dbcp.pool2.SwallowedExceptionListener;

/**
 * Class for logging swallowed exceptions.
 *
 * @since 2.0
 */
public class SwallowedExceptionLogger implements SwallowedExceptionListener {

    private final Log log;
    private final boolean logExpiredConnections;

    /**
     * Create a SwallowedExceptionLogger with the given logger. By default, expired connection logging is turned on.
     *
     * @param log
     *            logger
     */
    public SwallowedExceptionLogger(final Log log) {
        this(log, true);
    }

    /**
     * Create a SwallowedExceptionLogger with the given logger and expired connection logging property.
     *
     * @param log
     *            logger
     * @param logExpiredConnections
     *            false suppresses logging of expired connection events
     */
    public SwallowedExceptionLogger(final Log log, final boolean logExpiredConnections) {
        this.log = log;
        this.logExpiredConnections = logExpiredConnections;
    }

    @Override
    public void onSwallowException(final Exception e) {
        if (logExpiredConnections || !(e instanceof LifetimeExceededException)) {
            log.warn(Utils.getMessage("swallowedExceptionLogger.onSwallowedException"), e);
        }
    }
}
