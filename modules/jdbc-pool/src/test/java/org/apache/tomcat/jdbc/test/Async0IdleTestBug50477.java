
package org.apache.tomcat.jdbc.test;

import java.sql.Connection;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import org.apache.tomcat.jdbc.pool.DataSourceProxy;

public class Async0IdleTestBug50477 extends DefaultTestCase {

    @Test
    public void testAsync0Idle0Size() throws Exception {
        System.out.println("[testPoolThreads20Connections10FairAsync] Starting fairness - Tomcat JDBC - Fair - Async");
        this.datasource.getPoolProperties().setMaxActive(10);
        this.datasource.getPoolProperties().setFairQueue(true);
        this.datasource.getPoolProperties().setInitialSize(0);
        try {
            Future<Connection> cf = ((DataSourceProxy)datasource).getConnectionAsync();
            cf.get(5, TimeUnit.SECONDS);
        }finally {
            tearDown();
        }
    }
}

