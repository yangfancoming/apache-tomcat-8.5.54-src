
package org.apache.tomcat.jdbc.bugs;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.apache.tomcat.jdbc.test.DefaultProperties;

public class Bug54978 {

    @Test
    public void testIllegalValidationQuery() {
        PoolProperties poolProperties = new DefaultProperties();
        poolProperties.setMinIdle(0);
        poolProperties.setInitialSize(1);
        poolProperties.setMaxActive(1);
        poolProperties.setMaxWait(5000);
        poolProperties.setMaxAge(100);
        poolProperties.setRemoveAbandoned(false);
        poolProperties.setTestOnBorrow(true);
        poolProperties.setTestOnConnect(false);
        poolProperties.setValidationQuery("sdadsada");
        final DataSource ds = new DataSource(poolProperties);
        try {
            ds.getConnection().close();
            Assert.fail("Validation should have failed.");
        }catch (SQLException x) {
        }
    }

    @Test
    public void testIllegalValidationQueryWithLegalInit() throws SQLException {
        PoolProperties poolProperties = new DefaultProperties();
        poolProperties.setMinIdle(0);
        poolProperties.setInitialSize(1);
        poolProperties.setMaxActive(1);
        poolProperties.setMaxWait(5000);
        poolProperties.setMaxAge(100);
        poolProperties.setRemoveAbandoned(false);
        poolProperties.setTestOnBorrow(true);
        poolProperties.setTestOnConnect(false);
        poolProperties.setValidationQuery("sdadsada");
        poolProperties.setInitSQL("SELECT 1");
        final DataSource ds = new DataSource(poolProperties);
        ds.getConnection().close();
    }
}