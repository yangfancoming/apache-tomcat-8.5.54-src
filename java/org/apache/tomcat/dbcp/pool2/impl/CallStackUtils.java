
package org.apache.tomcat.dbcp.pool2.impl;

import java.security.AccessControlException;

/**
 * Utility methods for {@link CallStack}.
 *
 * @since 2.4.3
 */
public final class CallStackUtils {

    /**
     * Returns whether the caller can create a security manager in the current environment.
     *
     * @return {@code true} if it is able to create a security manager in the current environment, {@code false}
     *         otherwise.
     */
    private static boolean canCreateSecurityManager() {
        final SecurityManager manager = System.getSecurityManager();
        if (manager == null) {
            return true;
        }
        try {
            manager.checkPermission(new RuntimePermission("createSecurityManager"));
            return true;
        } catch (final AccessControlException ignored) {
            return false;
        }
    }

    /**
     * Constructs a new {@link CallStack} using the fastest allowed strategy.
     *
     * @param messageFormat message (or format) to print first in stack traces
     * @param useTimestamp  if true, interpret message as a SimpleDateFormat and print the created timestamp; otherwise,
     *                      print message format literally
     * @return a new CallStack
     * @deprecated use {@link #newCallStack(String, boolean, boolean)}
     */
    @Deprecated
    public static CallStack newCallStack(final String messageFormat, final boolean useTimestamp) {
        return newCallStack(messageFormat, useTimestamp, false);
    }

    /**
     * Constructs a new {@link CallStack} using the fasted allowed strategy.
     *
     * @param messageFormat         message (or format) to print first in stack traces
     * @param useTimestamp          if true, interpret message as a SimpleDateFormat and print the created timestamp;
     *                              otherwise, print message format literally
     * @param requireFullStackTrace if true, forces the use of a stack walking mechanism that includes full stack trace
     *                              information; otherwise, uses a faster implementation if possible
     * @return a new CallStack
     * @since 2.5
     */
    public static CallStack newCallStack(final String messageFormat,
                                         final boolean useTimestamp,
                                         final boolean requireFullStackTrace) {
        return canCreateSecurityManager() && !requireFullStackTrace
            ? new SecurityManagerCallStack(messageFormat, useTimestamp)
            : new ThrowableCallStack(messageFormat, useTimestamp);
    }

    /**
     * Hidden constructor.
     */
    private CallStackUtils() {
    }
}
