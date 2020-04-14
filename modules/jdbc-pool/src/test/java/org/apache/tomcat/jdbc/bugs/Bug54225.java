
package org.apache.tomcat.jdbc.bugs;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.apache.tomcat.jdbc.test.DefaultProperties;


@RunWith(Parameterized.class)
public class Bug54225 {

    private String initSQL;

    public Bug54225(String initSQL) {
        this.initSQL = initSQL;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][]{
            new Object[] {""},
            new Object[] {null},
        });
    }

    @Test
    public void testPool() throws SQLException {
        PoolProperties poolProperties = new DefaultProperties();
        poolProperties.setMinIdle(0);
        poolProperties.setInitialSize(0);
        poolProperties.setMaxWait(5000);
        poolProperties.setRemoveAbandoned(true);
        poolProperties.setRemoveAbandonedTimeout(300);
        poolProperties.setRollbackOnReturn(true);
        poolProperties.setInitSQL(initSQL);
        final DataSource ds = new DataSource(poolProperties);
        ds.getConnection().close();
        Assert.assertNull(poolProperties.getInitSQL());
    }
}