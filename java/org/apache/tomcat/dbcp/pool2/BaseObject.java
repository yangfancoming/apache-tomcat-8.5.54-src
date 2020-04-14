
package org.apache.tomcat.dbcp.pool2;

/**
 * A base class for common functionality.
 *
 * @since 2.4.3
 */
public abstract class BaseObject {

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getClass().getSimpleName());
        builder.append(" [");
        toStringAppendFields(builder);
        builder.append("]");
        return builder.toString();
    }

    /**
     * Used by sub-classes to include the fields defined by the sub-class in the
     * {@link #toString()} output.
     *
     * @param builder Field names and values are appended to this object
     */
    protected void toStringAppendFields(final StringBuilder builder) {
        // do nothing by default, needed for b/w compatibility.
    }
}
