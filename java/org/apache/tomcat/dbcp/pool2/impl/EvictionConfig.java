
package org.apache.tomcat.dbcp.pool2.impl;

/**
 * This class is used by pool implementations to pass configuration information
 * to {@link EvictionPolicy} instances. The {@link EvictionPolicy} may also have
 * its own specific configuration attributes.
 * <p>
 * This class is immutable and thread-safe.
 * </p>
 *
 * @since 2.0
 */
public class EvictionConfig {

    private final long idleEvictTime;
    private final long idleSoftEvictTime;
    private final int minIdle;

    /**
     * Create a new eviction configuration with the specified parameters.
     * Instances are immutable.
     *
     * @param poolIdleEvictTime Expected to be provided by
     *        {@link BaseGenericObjectPool#getMinEvictableIdleTimeMillis()}
     * @param poolIdleSoftEvictTime Expected to be provided by
     *        {@link BaseGenericObjectPool#getSoftMinEvictableIdleTimeMillis()}
     * @param minIdle Expected to be provided by
     *        {@link GenericObjectPool#getMinIdle()} or
     *        {@link GenericKeyedObjectPool#getMinIdlePerKey()}
     */
    public EvictionConfig(final long poolIdleEvictTime, final long poolIdleSoftEvictTime,
            final int minIdle) {
        if (poolIdleEvictTime > 0) {
            idleEvictTime = poolIdleEvictTime;
        } else {
            idleEvictTime = Long.MAX_VALUE;
        }
        if (poolIdleSoftEvictTime > 0) {
            idleSoftEvictTime = poolIdleSoftEvictTime;
        } else {
            idleSoftEvictTime  = Long.MAX_VALUE;
        }
        this.minIdle = minIdle;
    }

    /**
     * Obtain the {@code idleEvictTime} for this eviction configuration
     * instance.
     * <p>
     * How the evictor behaves based on this value will be determined by the
     * configured {@link EvictionPolicy}.
     *
     * @return The {@code idleEvictTime} in milliseconds
     */
    public long getIdleEvictTime() {
        return idleEvictTime;
    }

    /**
     * Obtain the {@code idleSoftEvictTime} for this eviction configuration
     * instance.
     * <p>
     * How the evictor behaves based on this value will be determined by the
     * configured {@link EvictionPolicy}.
     *
     * @return The (@code idleSoftEvictTime} in milliseconds
     */
    public long getIdleSoftEvictTime() {
        return idleSoftEvictTime;
    }

    /**
     * Obtain the {@code minIdle} for this eviction configuration instance.
     * <p>
     * How the evictor behaves based on this value will be determined by the
     * configured {@link EvictionPolicy}.
     *
     * @return The {@code minIdle}
     */
    public int getMinIdle() {
        return minIdle;
    }

    /**
     * @since 2.4
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("EvictionConfig [idleEvictTime=");
        builder.append(idleEvictTime);
        builder.append(", idleSoftEvictTime=");
        builder.append(idleSoftEvictTime);
        builder.append(", minIdle=");
        builder.append(minIdle);
        builder.append("]");
        return builder.toString();
    }
}
