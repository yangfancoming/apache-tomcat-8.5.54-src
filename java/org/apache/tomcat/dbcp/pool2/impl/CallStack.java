
package org.apache.tomcat.dbcp.pool2.impl;

import java.io.PrintWriter;

/**
 * Strategy for obtaining and printing the current call stack. This is primarily useful for
 * {@link org.apache.tomcat.dbcp.pool2.UsageTracking usage tracking} so
 * that different JVMs and configurations can use more efficient strategies
 * for obtaining the current call stack depending on metadata needs.
 *
 * @see CallStackUtils
 * @since 2.4.3
 */
public interface CallStack {

    /**
     * Prints the current stack trace if available to a PrintWriter. The format is undefined and is primarily useful
     * for debugging issues with {@link org.apache.tomcat.dbcp.pool2.PooledObject} usage in user code.
     *
     * @param writer a PrintWriter to write the current stack trace to if available
     * @return true if a stack trace was available to print or false if nothing was printed
     */
    boolean printStackTrace(final PrintWriter writer);

    /**
     * Takes a snapshot of the current call stack. Subsequent calls to {@link #printStackTrace(PrintWriter)} will print
     * out that stack trace until it is {@linkplain #clear() cleared}.
     */
    void fillInStackTrace();

    /**
     * Clears the current stack trace snapshot. Subsequent calls to {@link #printStackTrace(PrintWriter)} will be
     * no-ops until another call to {@link #fillInStackTrace()}.
     */
    void clear();
}
