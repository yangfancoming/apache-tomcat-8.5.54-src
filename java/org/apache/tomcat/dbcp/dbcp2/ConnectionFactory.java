
package org.apache.tomcat.dbcp.dbcp2;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Abstract factory interface for creating {@link java.sql.Connection}s.
 *
 * @since 2.0
 */
public interface ConnectionFactory {
    /**
     * Create a new {@link java.sql.Connection} in an implementation specific fashion.
     *
     * @return a new {@link java.sql.Connection}
     * @throws SQLException
     *             if a database error occurs creating the connection
     */
    Connection createConnection() throws SQLException;
}
