
package org.apache.tomcat.jdbc.test;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.Assert;
import org.junit.Test;

import org.apache.tomcat.jdbc.pool.ConnectionPool;
import org.apache.tomcat.jdbc.pool.JdbcInterceptor;
import org.apache.tomcat.jdbc.pool.PooledConnection;

public class TestException extends DefaultTestCase {

    @Test
    public void testException() throws Exception {
        datasource.getPoolProperties().setJdbcInterceptors(TestInterceptor.class.getName());
        Connection con = datasource.getConnection();
        try (Statement s = con.createStatement()){
        } catch (Exception x) {
            Assert.fail();
        }
        con.close();
    }


    public static class TestInterceptor extends JdbcInterceptor {

        @Override
        public void reset(ConnectionPool parent, PooledConnection con) {
            // NO-OP
        }
    }
}
