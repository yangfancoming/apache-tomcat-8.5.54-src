

package org.apache.tomcat.jdbc.pool.interceptor;

import java.lang.reflect.Method;

import javax.management.ObjectName;

import org.apache.tomcat.jdbc.pool.ConnectionPool;
import org.apache.tomcat.jdbc.pool.PooledConnection;
import org.apache.tomcat.jdbc.pool.jmx.JmxUtil;

/**
 * Class that resets the abandoned timer on any activity on the
 * Connection or any successful query executions.
 * This interceptor is useful for when you have a {@link org.apache.tomcat.jdbc.pool.PoolConfiguration#setRemoveAbandonedTimeout(int)}
 * that is fairly low, and you want to reset the abandoned time each time any operation on the connection is performed
 * This is useful for batch processing programs that use connections for extensive amount of times.
 *
 */
public class ResetAbandonedTimer extends AbstractQueryReport implements ResetAbandonedTimerMBean {

    private PooledConnection pcon;

    private ObjectName oname = null;

    public ResetAbandonedTimer() {
    }

    @Override
    public void reset(ConnectionPool parent, PooledConnection con) {
        super.reset(parent, con);
        if (con == null) {
            this.pcon = null;
            if (oname != null) {
                JmxUtil.unregisterJmx(oname);
                oname = null;
            }
        } else {
            this.pcon = con;
            if (oname == null) {
                String keyprop = ",JdbcInterceptor=" + getClass().getSimpleName();
                oname = JmxUtil.registerJmx(pcon.getObjectName(), keyprop, this);
            }
        }
    }

    @Override
    public boolean resetTimer() {
        boolean result = false;
        if (pcon != null) {
            pcon.setTimestamp(System.currentTimeMillis());
            result = true;
        }
        return result;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = super.invoke(proxy, method, args);
        resetTimer();
        return result;
    }

    @Override
    protected void prepareCall(String query, long time) {
        resetTimer();
    }

    @Override
    protected void prepareStatement(String sql, long time) {
        resetTimer();

    }

    @Override
    public void closeInvoked() {
        resetTimer();
    }

    @Override
    protected String reportQuery(String query, Object[] args, String name,long start, long delta) {
        resetTimer();
        return super.reportQuery(query, args, name, start, delta);
    }

    @Override
    protected String reportSlowQuery(String query, Object[] args, String name,long start, long delta) {
        resetTimer();
        return super.reportSlowQuery(query, args, name, start, delta);
    }
}
