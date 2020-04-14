

package org.apache.tomcat.jdbc.pool.interceptor;

import java.lang.reflect.Method;
import java.security.SecureRandom;

public class InduceSlowQuery extends AbstractQueryReport {
    public static final SecureRandom random = new SecureRandom();

    public InduceSlowQuery() {
        // TODO Auto-generated constructor stub
    }

    public void doWait() {
        try {
            int b = random.nextInt(10);
            if (b == 0) {
                Thread.sleep(random.nextInt(2000));
            }
        } catch (InterruptedException x) {

        }

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // TODO Auto-generated method stub
        Object result = super.invoke(proxy, method, args);
        return result;
    }

    @Override
    protected void prepareCall(String query, long time) {
    }

    @Override
    protected void prepareStatement(String sql, long time) {
    }

    @Override
    public void closeInvoked() {
    }

    @Override
    protected String reportQuery(String query, Object[] args, String name, long start, long delta) {
        doWait();
        return super.reportQuery(query, args, name, start, delta);
    }

    @Override
    protected String reportSlowQuery(String query, Object[] args, String name, long start, long delta) {
        doWait();
        return super.reportSlowQuery(query, args, name, start, delta);
    }
}
