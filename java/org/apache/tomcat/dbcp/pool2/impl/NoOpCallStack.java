
package org.apache.tomcat.dbcp.pool2.impl;

import java.io.PrintWriter;

/**
 * CallStack strategy using no-op implementations of all functionality. Can be used by default when abandoned object
 * logging is disabled.
 *
 * @since 2.5
 */
public class NoOpCallStack implements CallStack {

    /**
     * Singleton instance.
     */
    public static final CallStack INSTANCE = new NoOpCallStack();

    /**
     * Constructs the singleton instance.
     */
    private NoOpCallStack() {
    }

    @Override
    public boolean printStackTrace(final PrintWriter writer) {
        return false;
    }

    @Override
    public void fillInStackTrace() {
        // no-op
    }

    @Override
    public void clear() {
        // no-op
    }
}
