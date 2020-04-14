
package org.apache.tomcat.jdbc.bugs;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.PooledConnection;

import org.junit.Assert;
import org.junit.Test;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.apache.tomcat.jdbc.test.DefaultProperties;

public class Bug54227 {


    public Bug54227() {
    }

    @Test
    public void testPool() throws SQLException, InterruptedException {
        PoolProperties poolProperties = new DefaultProperties();
        poolProperties.setMinIdle(0);
        poolProperties.setInitialSize(0);
        poolProperties.setMaxActive(1);
        poolProperties.setMaxWait(5000);
        poolProperties.setMaxAge(100);
        poolProperties.setRemoveAbandoned(false);

        final DataSource ds = new DataSource(poolProperties);
        Connection con;
        Connection actual1;
        Connection actual2;

        con = ds.getConnection();
        actual1 = ((PooledConnection)con).getConnection();
        con.close();
        con = ds.getConnection();
        actual2 = ((PooledConnection)con).getConnection();
        Assert.assertSame(actual1, actual2);
        con.close();
        Thread.sleep(150);
        con = ds.getConnection();
        actual2 = ((PooledConnection)con).getConnection();
        Assert.assertNotSame(actual1, actual2);
        con.close();
    }
}