

package org.apache.tomcat.dbcp.dbcp2;

import java.sql.SQLException;
import java.util.List;

/**
 * An SQLException based on a list of Throwable causes.
 * <p>
 * The first exception in the list is used as this exception's cause and is accessible with the usual
 * {@link #getCause()} while the complete list is accessible with {@link #getCauseList()}.
 * </p>
 *
 * @since 2.7.0
 */
public class SQLExceptionList extends SQLException {

    private static final long serialVersionUID = 1L;
    private final List<? extends Throwable> causeList;

    /**
     * Creates a new exception caused by a list of exceptions.
     *
     * @param causeList a list of cause exceptions.
     */
    public SQLExceptionList(List<? extends Throwable> causeList) {
        super(String.format("%,d exceptions: %s", Integer.valueOf(causeList == null ? 0 : causeList.size()), causeList),
                causeList == null ? null : causeList.get(0));
        this.causeList = causeList;
    }

    /**
     * Gets the cause list.
     *
     * @return The list of causes.
     */
    public List<? extends Throwable> getCauseList() {
        return causeList;
    }

}
