
package org.apache.tomcat.dbcp.pool2.impl;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * CallStack strategy that uses the stack trace from a {@link Throwable}. This strategy, while slower than the
 * SecurityManager implementation, provides call stack method names and other metadata in addition to the call stack
 * of classes.
 *
 * @see Throwable#fillInStackTrace()
 * @since 2.4.3
 */
public class ThrowableCallStack implements CallStack {

    private final String messageFormat;
    //@GuardedBy("dateFormat")
    private final DateFormat dateFormat;

    private volatile Snapshot snapshot;

    /**
     * Create a new instance.
     *
     * @param messageFormat message format
     * @param useTimestamp whether to format the dates in the output message or not
     */
    public ThrowableCallStack(final String messageFormat, final boolean useTimestamp) {
        this.messageFormat = messageFormat;
        this.dateFormat = useTimestamp ? new SimpleDateFormat(messageFormat) : null;
    }

    @Override
    public synchronized boolean printStackTrace(final PrintWriter writer) {
        final Snapshot snapshotRef = this.snapshot;
        if (snapshotRef == null) {
            return false;
        }
        final String message;
        if (dateFormat == null) {
            message = messageFormat;
        } else {
            synchronized (dateFormat) {
                message = dateFormat.format(Long.valueOf(snapshotRef.timestamp));
            }
        }
        writer.println(message);
        snapshotRef.printStackTrace(writer);
        return true;
    }

    @Override
    public void fillInStackTrace() {
        snapshot = new Snapshot();
    }

    @Override
    public void clear() {
        snapshot = null;
    }

    /**
     * A snapshot of a throwable.
     */
    private static class Snapshot extends Throwable {
        private static final long serialVersionUID = 1L;
        private final long timestamp = System.currentTimeMillis();
    }
}
