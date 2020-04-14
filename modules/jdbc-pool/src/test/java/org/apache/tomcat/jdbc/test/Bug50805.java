
package org.apache.tomcat.jdbc.test;

import java.sql.Connection;
import java.util.concurrent.Future;

import org.junit.Assert;
import org.junit.Test;

public class Bug50805 extends DefaultTestCase {

    @Test
    public void test50805() throws Exception {
        this.datasource.setInitialSize(0);
        this.datasource.setMaxActive(10);
        this.datasource.setMinIdle(1);

        Assert.assertEquals("Current size should be 0.", 0, this.datasource.getSize());

        this.datasource.getConnection().close();

        Assert.assertEquals("Current size should be 1.", 1, this.datasource.getSize());
        Assert.assertEquals("Idle size should be 1.", 1, this.datasource.getIdle());
        Assert.assertEquals("Busy size should be 0.", 0, this.datasource.getActive());

        Future<Connection> fc = this.datasource.getConnectionAsync();

        Connection con = fc.get();

        Assert.assertEquals("Current size should be 1.", 1, this.datasource.getSize());
        Assert.assertEquals("Idle size should be 0.", 0, this.datasource.getIdle());
        Assert.assertEquals("Busy size should be 1.", 1, this.datasource.getActive());

        con.close();
        Assert.assertEquals("Current size should be 1.", 1, this.datasource.getSize());
        Assert.assertEquals("Idle size should be 1.", 1, this.datasource.getIdle());
        Assert.assertEquals("Busy size should be 0.", 0, this.datasource.getActive());
    }
}
