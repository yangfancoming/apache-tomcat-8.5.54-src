


package org.apache.tomcat.jdbc.pool;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.apache.tomcat.jdbc.pool.interceptor.AbstractCreateStatementInterceptor;

public class StatementFacade extends AbstractCreateStatementInterceptor {

    private static final Log logger = LogFactory.getLog(StatementFacade.class);

    protected StatementFacade(JdbcInterceptor interceptor) {
        setUseEquals(interceptor.isUseEquals());
        setNext(interceptor);
    }

    @Override
    public void closeInvoked() {
        // nothing to do
    }

    /**
     * Creates a statement interceptor to monitor query response times
     */
    @Override
    public Object createStatement(Object proxy, Method method, Object[] args, Object statement, long time) {
        try {
            String name = method.getName();
            Constructor<?> constructor = null;
            String sql = null;
            if (compare(CREATE_STATEMENT, name)) {
                // createStatement
                constructor = getConstructor(CREATE_STATEMENT_IDX, Statement.class);
            } else if (compare(PREPARE_STATEMENT, name)) {
                // prepareStatement
                constructor = getConstructor(PREPARE_STATEMENT_IDX, PreparedStatement.class);
                sql = (String)args[0];
            } else if (compare(PREPARE_CALL, name)) {
                // prepareCall
                constructor = getConstructor(PREPARE_CALL_IDX, CallableStatement.class);
                sql = (String)args[0];
            } else {
                // do nothing
                return statement;
            }
            return constructor.newInstance(new Object[] { new StatementProxy(statement,sql) });
        } catch (Exception x) {
            logger.warn("Unable to create statement proxy.", x);
        }
        return statement;
    }

    /**
     * Class to measure query execute time.
     */
    protected class StatementProxy implements InvocationHandler {
        protected boolean closed = false;
        protected Object delegate;
        protected final String query;
        public StatementProxy(Object parent, String query) {
            this.delegate = parent;
            this.query = query;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (compare(TOSTRING_VAL,method)) {
                return toString();
            }
            if (compare(EQUALS_VAL, method)) {
                return Boolean.valueOf(
                        this.equals(Proxy.getInvocationHandler(args[0])));
            }
            if (compare(HASHCODE_VAL, method)) {
                return Integer.valueOf(this.hashCode());
            }
            if (compare(CLOSE_VAL, method)) {
                if (delegate == null) return null;
            }
            if (compare(ISCLOSED_VAL, method)) {
                if (delegate == null) return Boolean.TRUE;
            }
            if (delegate == null) throw new SQLException("Statement closed.");
            Object result =  null;
            try {
                //invoke next
                result =  method.invoke(delegate,args);
            } catch (Throwable t) {
                if (t instanceof InvocationTargetException && t.getCause() != null) {
                    throw t.getCause();
                } else {
                    throw t;
                }
            }
            //perform close cleanup
            if (compare(CLOSE_VAL, method)) {
                delegate = null;
            }
            return result;
        }

        @Override
        public int hashCode() {
            return System.identityHashCode(this);
        }

        @Override
        public boolean equals(Object obj) {
            return this==obj;
        }

        @Override
        public String toString() {
            StringBuffer buf = new StringBuffer(StatementProxy.class.getName());
            buf.append("[Proxy=");
            buf.append(hashCode());
            buf.append("; Query=");
            buf.append(query);
            buf.append("; Delegate=");
            buf.append(delegate);
            buf.append("]");
            return buf.toString();
        }
    }

}
