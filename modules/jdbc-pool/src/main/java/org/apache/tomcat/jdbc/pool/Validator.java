
package org.apache.tomcat.jdbc.pool;

import java.sql.Connection;

/**
 * Interface to be implemented by custom validator classes.
 *
 * @author mpassell
 */
public interface Validator {
    /**
     * Validate a connection and return a boolean to indicate if it's valid.
     *
     * @param connection the Connection object to test
     * @param validateAction the action used. One of {@link PooledConnection#VALIDATE_BORROW},
     *   {@link PooledConnection#VALIDATE_IDLE}, {@link PooledConnection#VALIDATE_INIT} or
     *   {@link PooledConnection#VALIDATE_RETURN}
     * @return true if the connection is valid
     */
    public boolean validate(Connection connection, int validateAction);
}
