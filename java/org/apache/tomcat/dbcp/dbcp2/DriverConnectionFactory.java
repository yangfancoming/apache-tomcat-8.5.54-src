
package org.apache.tomcat.dbcp.dbcp2;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

/**
 * A {@link Driver}-based implementation of {@link ConnectionFactory}.
 *
 * @since 2.0
 */
public class DriverConnectionFactory implements ConnectionFactory {

    private final String connectionString;

    private final Driver driver;

    private final Properties properties;

    /**
     * Constructs a connection factory for a given Driver.
     *
     * @param driver
     *            The Driver.
     * @param connectString
     *            The connection string.
     * @param properties
     *            The connection properties.
     */
    public DriverConnectionFactory(final Driver driver, final String connectString, final Properties properties) {
        this.driver = driver;
        this.connectionString = connectString;
        this.properties = properties;
    }

    @Override
    public Connection createConnection() throws SQLException {
        return driver.connect(connectionString, properties);
    }

    /**
     * @return The connection String.
     * @since 2.6.0
     */
    public String getConnectionString() {
        return connectionString;
    }

    /**
     * @return The Driver.
     * @since 2.6.0
     */
    public Driver getDriver() {
        return driver;
    }

    /**
     * @return The Properties.
     * @since 2.6.0
     */
    public Properties getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " [" + String.valueOf(driver) + ";" + String.valueOf(connectionString) + ";"
                + String.valueOf(properties) + "]";
    }
}
