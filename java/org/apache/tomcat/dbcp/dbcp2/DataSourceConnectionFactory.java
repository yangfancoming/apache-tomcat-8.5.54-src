
package org.apache.tomcat.dbcp.dbcp2;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * A {@link DataSource}-based implementation of {@link ConnectionFactory}.
 *
 * @since 2.0
 */
public class DataSourceConnectionFactory implements ConnectionFactory {

    private final DataSource dataSource;

    private final String userName;

    private final char[] userPassword;

    /**
     * Constructs an instance for the given DataSource.
     *
     * @param dataSource
     *            The DataSource for this factory.
     */
    public DataSourceConnectionFactory(final DataSource dataSource) {
        this(dataSource, null, (char[]) null);
    }

    /**
     * Constructs an instance for the given DataSource.
     *
     * @param dataSource
     *            The DataSource for this factory.
     * @param userName
     *            The user name.
     * @param userPassword
     *            The user password.
     * @since 2.4.0
     */
    public DataSourceConnectionFactory(final DataSource dataSource, final String userName, final char[] userPassword) {
        this.dataSource = dataSource;
        this.userName = userName;
        this.userPassword = Utils.clone(userPassword);
    }

    /**
     * Constructs an instance for the given DataSource.
     *
     * @param dataSource
     *            The DataSource for this factory.
     * @param userName
     *            The user name.
     * @param password
     *            The user password.
     */
    public DataSourceConnectionFactory(final DataSource dataSource, final String userName, final String password) {
        this.dataSource = dataSource;
        this.userName = userName;
        this.userPassword = Utils.toCharArray(password);
    }

    @Override
    public Connection createConnection() throws SQLException {
        if (null == userName && null == userPassword) {
            return dataSource.getConnection();
        }
        return dataSource.getConnection(userName, Utils.toString(userPassword));
    }

    /**
     * @return The data source.
     * @since 2.6.0
     */
    public DataSource getDataSource() {
        return dataSource;
    }

    /**
     * @return The user name.
     * @since 2.6.0
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @return The user password.
     * @since 2.6.0
     */
    public char[] getUserPassword() {
        return userPassword;
    }
}
