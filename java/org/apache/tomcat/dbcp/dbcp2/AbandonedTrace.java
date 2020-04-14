
package org.apache.tomcat.dbcp.dbcp2;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.tomcat.dbcp.pool2.TrackedUse;

/**
 * Tracks db connection usage for recovering and reporting abandoned db connections.
 * <p>
 * The JDBC Connection, Statement, and ResultSet classes extend this class.
 * </p>
 *
 * @since 2.0
 */
public class AbandonedTrace implements TrackedUse {

    /** A list of objects created by children of this object. */
    private final List<WeakReference<AbandonedTrace>> traceList = new ArrayList<>();

    /** Last time this connection was used. */
    private volatile long lastUsedMillis = 0;

    /**
     * Creates a new AbandonedTrace without config and without doing abandoned tracing.
     */
    public AbandonedTrace() {
        init(null);
    }

    /**
     * Constructs a new AbandonedTrace with a parent object.
     *
     * @param parent
     *            AbandonedTrace parent object.
     */
    public AbandonedTrace(final AbandonedTrace parent) {
        init(parent);
    }

    /**
     * Adds an object to the list of objects being traced.
     *
     * @param trace
     *            AbandonedTrace object to add.
     */
    protected void addTrace(final AbandonedTrace trace) {
        synchronized (this.traceList) {
            this.traceList.add(new WeakReference<>(trace));
        }
        setLastUsed();
    }

    /**
     * Clears the list of objects being traced by this object.
     */
    protected void clearTrace() {
        synchronized (this.traceList) {
            this.traceList.clear();
        }
    }

    /**
     * Gets the last time this object was used in milliseconds.
     *
     * @return long time in milliseconds.
     */
    @Override
    public long getLastUsed() {
        return lastUsedMillis;
    }

    /**
     * Gets a list of objects being traced by this object.
     *
     * @return List of objects.
     */
    protected List<AbandonedTrace> getTrace() {
        final int size = traceList.size();
        if (size == 0) {
            return Collections.emptyList();
        }
        final ArrayList<AbandonedTrace> result = new ArrayList<>(size);
        synchronized (this.traceList) {
            final Iterator<WeakReference<AbandonedTrace>> iter = traceList.iterator();
            while (iter.hasNext()) {
                final AbandonedTrace trace = iter.next().get();
                if (trace == null) {
                    // Clean-up since we are here anyway
                    iter.remove();
                } else {
                    result.add(trace);
                }
            }
        }
        return result;
    }

    /**
     * Initializes abandoned tracing for this object.
     *
     * @param parent
     *            AbandonedTrace parent object.
     */
    private void init(final AbandonedTrace parent) {
        if (parent != null) {
            parent.addTrace(this);
        }
    }

    /**
     * Removes this object the source object is tracing.
     *
     * @param source The object tracing
     * @since 2.7.0
     */
    protected void removeThisTrace(final Object source) {
        if (source instanceof AbandonedTrace) {
            AbandonedTrace.class.cast(source).removeTrace(this);
        }
    }

    /**
     * Removes a child object this object is tracing.
     *
     * @param trace
     *            AbandonedTrace object to remove.
     */
    protected void removeTrace(final AbandonedTrace trace) {
        synchronized (this.traceList) {
            final Iterator<WeakReference<AbandonedTrace>> iter = traceList.iterator();
            while (iter.hasNext()) {
                final AbandonedTrace traceInList = iter.next().get();
                if (trace != null && trace.equals(traceInList)) {
                    iter.remove();
                    break;
                } else if (traceInList == null) {
                    // Clean-up since we are here anyway
                    iter.remove();
                }
            }
        }
    }

    /**
     * Sets the time this object was last used to the current time in milliseconds.
     */
    protected void setLastUsed() {
        lastUsedMillis = System.currentTimeMillis();
    }

    /**
     * Sets the time in milliseconds this object was last used.
     *
     * @param lastUsedMillis
     *            time in milliseconds.
     */
    protected void setLastUsed(final long lastUsedMillis) {
        this.lastUsedMillis = lastUsedMillis;
    }
}
