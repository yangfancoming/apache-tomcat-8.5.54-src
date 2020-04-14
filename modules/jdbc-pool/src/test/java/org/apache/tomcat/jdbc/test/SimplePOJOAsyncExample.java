
package org.apache.tomcat.jdbc.test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.Future;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolConfiguration;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class SimplePOJOAsyncExample {

    public static void main(String[] args) throws Exception {
        PoolConfiguration p = new PoolProperties();
        p.setFairQueue(true);
        p.setUrl("jdbc:mysql://localhost:3306/mysql?autoReconnect=true");
        p.setDriverClassName("com.mysql.jdbc.Driver");
        p.setUsername("root");
        p.setPassword("password");
        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(100);
        p.setInitialSize(10);
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(60);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setMinIdle(10);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        p.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
        DataSource datasource = new DataSource();
        datasource.setPoolProperties(p);

        Connection con = null;
        try {
            Future<Connection> future = datasource.getConnectionAsync();
            while (!future.isDone()) {
                System.out.println("Connection is not yet available. Do some background work");
            try {
                Thread.sleep(100); //simulate work
                } catch (InterruptedException x) {
                    Thread.interrupted();
                }
            }
            con = future.get(); //should return instantly
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from user");
            int cnt = 1;
            while (rs.next()) {
                System.out.println((cnt++)+". Host:" +rs.getString("Host")+" User:"+rs.getString("User")+" Password:"+rs.getString("Password"));
            }
            rs.close();
            st.close();
        } finally {
            if (con!=null) {
                try {
                    con.close();
                } catch (Exception ignore) {
                    // Ignore
                }
            }
        }
    }
}