

package org.apache.tomcat.dbcp.dbcp2.datasources;

import java.sql.SQLException;

import javax.sql.PooledConnection;

/**
 * Methods to manage PoolableConnections and the connection pools that source them.
 *
 * @since 2.0
 */
interface PooledConnectionManager {

    /**
     * Closes the PooledConnection and remove it from the connection pool to which it belongs, adjusting pool counters.
     *
     * @param pc
     *            PooledConnection to be invalidated
     * @throws SQLException
     *             if an SQL error occurs closing the connection
     */
    void invalidate(PooledConnection pc) throws SQLException;

    // /**
    // * Sets the database password used when creating connections.
    // *
    // * @param password password used when authenticating to the database
    // * @since 3.0.0
    // */
    // void setPassword(char[] password);

    /**
     * Sets the database password used when creating connections.
     *
     * @param password
     *            password used when authenticating to the database
     */
    void setPassword(String password);

    /**
     * Closes the connection pool associated with the given user.
     *
     * @param userName
     *            user name
     * @throws SQLException
     *             if an error occurs closing idle connections in the pool
     */
    void closePool(String userName) throws SQLException;

}
