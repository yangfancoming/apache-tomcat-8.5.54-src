
package org.apache.tomcat.jdbc.test;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

public class BorrowWaitTest extends DefaultTestCase {

    @Test
    public void testWaitTime() throws Exception {

        int wait = 10000;
        this.datasource.setMaxActive(1);
        this.datasource.setMaxWait(wait);
        Connection con = datasource.getConnection();
        long start = System.currentTimeMillis();
        try {
            Connection con2 = datasource.getConnection();
            Assert.assertFalse("This should not happen, connection should be unavailable.",true);
            con2.close();
        }catch (SQLException x) {
            long delta = System.currentTimeMillis() - start;
            boolean inrange = Math.abs(wait-delta) <= 1000;
            Assert.assertTrue("Connection should have been acquired within +/- 1 second, but was "+(wait-delta)+" ms.",inrange);
        }
        con.close();
    }

    public void testWaitTimeInfinite() throws Exception {
        if(true){
            System.err.println("testWaitTimeInfinite() test is disabled.");
            return;//this would lock up the test suite
        }
        /*
        int wait = -1;
        this.datasource.setMaxActive(1);
        this.datasource.setMaxWait(wait);
        Connection con = datasource.getConnection();
        long start = System.currentTimeMillis();
        try {
            Connection con2 = datasource.getConnection();
            Assert.assertFalse("This should not happen, connection should be unavailable.",true);
        }catch (SQLException x) {
            long delta = System.currentTimeMillis();
            boolean inrange = Math.abs(wait-delta) < 1000;
            Assert.assertTrue("Connection should have been acquired within +/- 1 second.",true);
        }
        con.close();
        */
    }


}
