
package org.apache.tomcat.dbcp.pool2.impl;

import java.lang.ref.SoftReference;

/**
 * Extension of {@link DefaultPooledObject} to wrap pooled soft references.
 *
 * <p>This class is intended to be thread-safe.</p>
 *
 * @param <T> the type of the underlying object that the wrapped SoftReference
 * refers to.
 *
 * @since 2.0
 */
public class PooledSoftReference<T> extends DefaultPooledObject<T> {

    /** SoftReference wrapped by this object */
    private volatile SoftReference<T> reference;

    /**
     * Creates a new PooledSoftReference wrapping the provided reference.
     *
     * @param reference SoftReference to be managed by the pool
     */
    public PooledSoftReference(final SoftReference<T> reference) {
        super(null);  // Null the hard reference in the parent
        this.reference = reference;
    }

    /**
     * Returns the object that the wrapped SoftReference refers to.
     * <p>
     * Note that if the reference has been cleared, this method will return
     * null.
     *
     * @return Object referred to by the SoftReference
     */
    @Override
    public T getObject() {
        return reference.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();
        result.append("Referenced Object: ");
        result.append(getObject().toString());
        result.append(", State: ");
        synchronized (this) {
            result.append(getState().toString());
        }
        return result.toString();
        // TODO add other attributes
        // TODO encapsulate state and other attribute display in parent
    }

    /**
     * Returns the SoftReference wrapped by this object.
     *
     * @return underlying SoftReference
     */
    public synchronized SoftReference<T> getReference() {
        return reference;
    }

    /**
     * Sets the wrapped reference.
     *
     * <p>This method exists to allow a new, non-registered reference to be
     * held by the pool to track objects that have been checked out of the pool.
     * The actual parameter <strong>should</strong> be a reference to the same
     * object that {@link #getObject()} returns before calling this method.</p>
     *
     * @param reference new reference
     */
    public synchronized void setReference(final SoftReference<T> reference) {
        this.reference = reference;
    }
}
