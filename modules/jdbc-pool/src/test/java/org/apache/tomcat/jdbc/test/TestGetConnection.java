
package org.apache.tomcat.jdbc.test;

import java.sql.Connection;

import javax.sql.PooledConnection;

import org.junit.Assert;
import org.junit.Test;

public class TestGetConnection extends DefaultTestCase {

    @Test
    public void testGetConnection() throws Exception {
        Connection con = this.datasource.getConnection();
        Assert.assertTrue("Connection should implement javax.sql.PooledConnection",con instanceof PooledConnection);
        Connection actual = ((PooledConnection)con).getConnection();
        Assert.assertNotNull("Connection delegate should not be null.",actual);
        System.out.println("Actual connection:"+actual.getClass().getName());
    }
}
